
package onlineTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class SystemManager implements Manager, Serializable {
	
	private static final long serialVersionUID = 8223041221399892623L;
	HashMap<Integer, Exam> examList = new HashMap<Integer, Exam>();
	TreeMap<String, Student> studentList = new TreeMap<String, Student>();
	ArrayList<Exam> scoreList = new ArrayList<Exam>();
	TreeMap<Double, String> cutOffGrades = new TreeMap<Double, String>();

	//Adds a true and false question to the specified exam.  If the question
	//already exists it is overwritten.
	@Override
	public boolean addExam(int examId, String title) {
		OnlineExam exam = new OnlineExam(examId, title);
		if (examList.containsKey(examId)) {
			return false;
		} else {
			examList.put(examId, exam);
			return true;
		}
	}

	//Adds a True or False question to the specified exam.   If the question
		//already exists it is overwritten.
	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, 
			double points, boolean answer) {
		Exam exam = examList.get(examId);
		Question newQuestion = new TrueFalseQuestion(examId, questionNumber, text, 
				points, answer);
		exam.addQuestion(newQuestion);
	}

	//Adds a multiple choice question to the specified exam.   If the question
	//already exists it is overwritten.
	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, 
			double points, String[] answer) {
		Question newQuestion = new MultipleChoiceQuestion(examId, questionNumber, text, 
				points, answer);
		Exam exam = examList.get(examId);
		exam.addQuestion(newQuestion);
	}

	//Adds a fill in the blank question to the specified exam.   If the question
		//already exists it is overwritten.
	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text,
			double points, String[] answer) {
		Question newQuestion = new FillInTheBlankQuestion(examId, questionNumber, text, 
				points, answer);
		Exam exam = examList.get(examId);
		exam.addQuestion(newQuestion);
	}

	//Returns the key of the exam with the question, points, and correct answer.
	@Override
	public String getKey(int examId) {
		Exam exam = examList.get(examId);
		return exam.getKey();
	}

	//Enters the question's answer into the database.
	@Override
	public boolean addStudent(String name) {
		OnlineStudent student = new OnlineStudent(name);
		if (studentList.containsKey(name)) {
			return false;
		} else {
			studentList.put(name, student);
			return true;
		}
	}

	//Enters the question's answer into the database.
	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber,
			boolean answer) {
		Student student = studentList.get(studentName);
		Answer newAnswer = new TrueFalseAnswer(studentName, examId, questionNumber, answer);
		student.addAnswer(examId, newAnswer);
	}

	//Enters the question's answer into the database.
	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, 
			int questionNumber, String[] answer) {
		Student student = studentList.get(studentName);
		Answer newAnswer = new MultipleChoiceAnswer(studentName, examId, questionNumber,
				answer);
		student.addAnswer(examId, newAnswer);

	}
	
	//Enters the question's answer into the database.
	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, 
			int questionNumber, String[] answer) {
		Student student = studentList.get(studentName);
		Answer newAnswer = new FillInTheBlankAnswer(studentName, examId, questionNumber,
				answer);
		student.addAnswer(examId, newAnswer);
	}

	//Generates a grading report for the specified exam. 
	@Override
	public double getExamScore(String studentName, int examId) {
		Exam exam = examList.get(examId);
		Student student = studentList.get(studentName);
		Collection<Question> allQuestions = exam.getQuestions();
		return student.getScore(examId, allQuestions);
	}

	//Sets the cutoffs for letter grades. 
	@Override
	public String getGradingReport(String studentName, int examId) {
		Exam exam = examList.get(examId);
		Student student = studentList.get(studentName);
		Collection<Question> allQuestions = exam.getQuestions();
		return student.getGradingReport(examId, allQuestions);
	}

	//Computes a numeric grade (value between 0 and a 100) for the student taking
	//into consideration all the exams.  All exams have the same weight.
	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		if (letterGrades.length != cutoffs.length) {
			throw new IllegalArgumentException("Different cutoff");
		}
		for (int i = 0; i < letterGrades.length; i++) {
			cutOffGrades.put(cutoffs[i], letterGrades[i]);
		}

	}

	//Computes a letter grade based on cutoffs provided.
	@Override
	public double getCourseNumericGrade(String studentName) {
		Student student = studentList.get(studentName);
		Set<Integer> examIds = examList.keySet();
		double totalExamScores = 0.0;
		for (Integer examId : examIds) {
			Exam exam = examList.get(examId);
			Collection<Question> allQuestions = exam.getQuestions();
			totalExamScores += student.getScore(examId, allQuestions);
		}
		return totalExamScores / examList.size();
	}

	//Returns a listing with the grades for each student. 
	@Override
	public String getCourseLetterGrade(String studentName) {
		Student student = studentList.get(studentName);
		double grade = student.getLetterGrade();
		return computeGrade(grade);
	}

	//Returns the maximum score (among all the students) for the specified exam.
	@Override
	public String getCourseGrades() {
		Collection<Student> students = studentList.values();

		Collection<Exam> exams = examList.values();
		String answerStr = "";
		for (Student student : students) {
			answerStr += getStudentGrade(student, exams);
		}
		return answerStr;
	}

	//Gets the total student grade.
	private String getStudentGrade(Student student, Collection<Exam> exams) {
		double totalExamScores = 0.0;
		String answerStr = "";
		for (Exam exam : exams) {
			Collection<Question> allQuestions = exam.getQuestions();
			totalExamScores += student.getScore(exam.getExamId(), allQuestions);
		}
		String letterGrade = computeGrade(totalExamScores);

		return answerStr += student.getStudentName() + " " + String.valueOf(totalExamScores) + " " + letterGrade + "\n";
	}

	//Computes the grade to generate the final grade. Returns the letter grade.
	private String computeGrade(double totalExamScores) {
		Collection<Double> cutoffs = cutOffGrades.keySet();
		String letter = "";
		for (Double cutoff : cutoffs) {
			if (totalExamScores < cutoff) {
				double prevKey;
				if (cutOffGrades.lowerKey(cutoff) == null) {
					letter = cutOffGrades.get(cutoff);
				} else {
					prevKey = cutOffGrades.lowerKey(cutoff);
					letter = cutOffGrades.get(prevKey);
				}
				break;
			}
		}
		if (letter.length() == 0) {
			letter = cutOffGrades.get(cutOffGrades.lastKey());
		}
		return letter;
	}

	//Returns the maximum score (among all the students) for the specified exam.
	@Override
	public double getMaxScore(int examId) {
		Collection<Student> students = studentList.values();
		Exam exam = examList.get(examId);
		Collection<Question> allQuestions = exam.getQuestions();
		double maxScore = 0.0;
		for (Student student : students) {
			double studentScore = student.getScore(examId, allQuestions);
			if (studentScore > maxScore) {
				maxScore = studentScore;
			}
		}
		return maxScore;
	}

	//Returns the minimum score (among all the students) for the specified exam.
	@Override
	public double getMinScore(int examId) {
		Collection<Student> students = studentList.values();
		Exam exam = examList.get(examId);
		Collection<Question> allQuestions = exam.getQuestions();
		double minScore = 100.0;
		for (Student student : students) {
			double studentScore = student.getScore(examId, allQuestions);
			if (studentScore < minScore) {
				minScore = studentScore;
			}
		}
		return minScore;
	}

	//Returns the average score for the specified exam.
	@Override
	public double getAverageScore(int examId) {
		Collection<Student> students = studentList.values();
		Exam exam = examList.get(examId);
		Collection<Question> allQuestions = exam.getQuestions();
		double score = 0.0;
		for (Student student : students) {
			double studentScore = student.getScore(examId, allQuestions);
			score += studentScore;
		}
		return score / students.size();
	}

	//Serializes the Manager object and store it in the
	//specified file.
	@Override
	public void saveManager(Manager manager, String fileName) {
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(manager);

			out.close();
			file.close();

		}

		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//Returns a Manager object based on the serialized data
	//found in the specified file.
	@Override
	public Manager restoreManager(String fileName) {
		Manager manager = null;
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);

			manager = (SystemManager) in.readObject();

			in.close();
			file.close();

		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return manager;

	}

}
