package onlineTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class OnlineExam implements Exam {

	private static final long serialVersionUID = -1515160265377864579L;
	private String title;
	private int examId;
	HashMap<Integer, Question> questionList = new HashMap<Integer, Question>();

	//Constructor that initializes private variables.
	public OnlineExam(int examId, String title) {
		this.title = title;
		this.examId = examId;
	}

	//Gets the title.
	public String getTitle() {
		return title;
	}

	//Gets the exam id.
	public int getExamId() {
		return examId;
	}

	//Adds a question to the question list.
	public void addQuestion(Question question) {
		questionList.put(question.getQuestionNumber(), question);
	}

	//Gets the questions.
	public Collection<Question> getQuestions() {
		return questionList.values();
	}

	//The method that does the work for getKey in SystemManager. Returns the key of the
	//exam with the question, points, and correct answer.
	public String getKey() {
		String answer = "";
		for (Question question : questionList.values()) {
			if (question.getExamId() == examId) {
				answer += "Question Text: " + question.getText() +
						"\nPoints: " + question.getPoints();
				if (question instanceof TrueFalseQuestion) {
					boolean questionAnswer = ((TrueFalseQuestion) question).getAnswer();
					String answerStr = String.valueOf(questionAnswer);
					answerStr = answerStr.substring(0, 1).toUpperCase() 
							+ answerStr.substring(1);
					answer += "\nCorrect Answer: " + answerStr + "\n";
				} else if (question instanceof MultipleChoiceQuestion) {
					answer += getMultiAnswers(((MultipleChoiceQuestion) question).getAnswer());
				} else if (question instanceof FillInTheBlankQuestion) {
					answer += getMultiAnswers(((FillInTheBlankQuestion) question).getAnswer());
				}
			} else {
				return "Exam not found";
			}
		}
		return answer;
	}

	//Checks the answers with the questions answers.
	private String getMultiAnswers(String[] answer) {
		Arrays.sort(answer);
		String answerStr = "";
		for (int i = 0; i < answer.length; i++) {
			answerStr += answer[i];
			if (i < answer.length - 1) {
				answerStr = answerStr + ", ";
			}
		}
		return "\nCorrect Answer: [" + answerStr + "]";
	}
}
