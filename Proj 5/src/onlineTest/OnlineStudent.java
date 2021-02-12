package onlineTest;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;

public class OnlineStudent implements Student {

	private static final long serialVersionUID = 7745152925875829570L;
	private String studentName;
	HashMap<String, Answer> answerList = new HashMap<String, Answer>();
	private double finalAnswerScore = 0.0;
	private double finalQuestionScore = 0.0;
	private TreeMap<Integer, Double> examScores = new TreeMap<Integer, Double>();

	//Constructor that initializes private variables.
	public OnlineStudent(String studentName) {
		this.studentName = studentName;
	}

	//Gets the student name.
	@Override
	public String getStudentName() {
		return studentName;
	}

	//Adds an answer to the answer list.
	public void addAnswer(int examId, Answer answer) {
		String key = String.valueOf(examId) + String.valueOf(answer.getQuestionNumber());
		answerList.put(key, answer);
	}

	//Gets the score of the answer provided based on the type of question
	@Override
	public double getScore(int examId, Collection<Question> allQuestions) {
		finalAnswerScore = 0.0;
		finalQuestionScore = 0.0;
		for (Question question : allQuestions) {
			String key = String.valueOf(examId) + String.valueOf(question.getQuestionNumber());
			Answer answerQuestion = answerList.get(key);
			if (question instanceof TrueFalseQuestion) {
				updateFinalQuestionScore(((TrueFalseQuestion) question).getPoints());
				boolean a1 = ((TrueFalseQuestion) question).getAnswer();
				if (answerQuestion instanceof TrueFalseAnswer) {
					boolean a2 = ((TrueFalseAnswer) answerQuestion).getAnswer();
					if (a1 == a2) {
						updateFinalAnswerScore(((TrueFalseQuestion) question).getPoints());

					}
				}
			}
			if (question instanceof MultipleChoiceQuestion) {
				if (answerQuestion instanceof MultipleChoiceAnswer) {
					MultipleChoiceQuestion mQuest = (MultipleChoiceQuestion) question;
					MultipleChoiceAnswer mAnswer = (MultipleChoiceAnswer) answerQuestion;
					checkMultipleChoiceAnswers(mQuest, mAnswer);
				}
			}
			if (question instanceof FillInTheBlankQuestion) {
				if (answerQuestion instanceof FillInTheBlankAnswer) {
					FillInTheBlankQuestion mQuest = (FillInTheBlankQuestion) question;
					FillInTheBlankAnswer mAnswer = (FillInTheBlankAnswer) answerQuestion;
					checkFillInTheBlankAnswers(mQuest, mAnswer);
				}
			}
		}
		examScores.put(examId, getFinalAnswerScore()/getFinalQuestionScore() * 100);
		return getFinalAnswerScore();
	}

	//returns a report of the final score out of the highest possible score.
	@Override
	public String getGradingReport(int examId, Collection<Question> allQuestions) {
		String answer = "";
		finalAnswerScore = 0.0;
		finalQuestionScore = 0.0;
		for (Question question : allQuestions) {
			String key = String.valueOf(examId) + String.valueOf(question.getQuestionNumber());
			Answer answerQuestion = answerList.get(key);
			if (question instanceof TrueFalseQuestion) {
				if (answerQuestion instanceof TrueFalseAnswer) {
					TrueFalseQuestion mQuest = (TrueFalseQuestion) question;
					TrueFalseAnswer mAnswer = (TrueFalseAnswer) answerQuestion;
					answer += checkTrueFalseAnswers(mQuest, mAnswer);
				}
			}
			if (question instanceof MultipleChoiceQuestion) {
				if (answerQuestion instanceof MultipleChoiceAnswer) {
					MultipleChoiceQuestion mQuest = (MultipleChoiceQuestion) question;
					MultipleChoiceAnswer mAnswer = (MultipleChoiceAnswer) answerQuestion;
					answer += checkMultipleChoiceAnswers(mQuest, mAnswer);
				}
			}
			if (question instanceof FillInTheBlankQuestion) {
				if (answerQuestion instanceof FillInTheBlankAnswer) {
					FillInTheBlankQuestion mQuest = (FillInTheBlankQuestion) question;
					FillInTheBlankAnswer mAnswer = (FillInTheBlankAnswer) answerQuestion;
					answer += checkFillInTheBlankAnswers(mQuest, mAnswer);
				}
			}
		}
		return answer + "Final Score: " + getFinalAnswerScore() + 
				" out of " + getFinalQuestionScore();
	}
	
	//checks the true false answer against the correct answer.
	private String checkTrueFalseAnswers(TrueFalseQuestion mQuest, TrueFalseAnswer mAnswer) {
		boolean questAnswer = mQuest.getAnswer();
		boolean answerAnswer = mAnswer.getAnswer();
		String answer = "Question ";
		updateFinalQuestionScore(mQuest.getPoints());
		if (questAnswer == answerAnswer) {
			answer += "#" + mQuest.getQuestionNumber() + " " + mQuest.getPoints() 
			+ " points out of " + mQuest.getPoints() + "\n";
			updateFinalAnswerScore(mQuest.getPoints());
		} else {
			answer += "#" + mQuest.getQuestionNumber() + " 0.0 points out of " 
		+ mQuest.getPoints() + "\n";
		}
		return answer;
	}

	//checks the multiple choice answer against the correct answer.
	private String checkMultipleChoiceAnswers(MultipleChoiceQuestion mQuest, MultipleChoiceAnswer mAnswer) {
		String[] questArray = mQuest.getAnswer();
		String[] answerArray = mAnswer.getAnswer();
		if (questArray.length != answerArray.length) {
			updateFinalAnswerScore(0);
			updateFinalQuestionScore(mQuest.getPoints());
			return "Question #" + mQuest.getQuestionNumber() + " 0.0 points out of " 
			+ mQuest.getPoints() + "\n";
		}
		boolean badAnswer = false;
		for (int i = 0; i < questArray.length; i++) {
			if (answerArray[i].compareTo(questArray[i]) != 0) {
				badAnswer = true;
				break;
			}
		}
		if (badAnswer) {
			updateFinalAnswerScore(0);
			updateFinalQuestionScore(mQuest.getPoints());
			return "Question #" + mQuest.getQuestionNumber() + " 0.0 points out of " 
			+ mQuest.getPoints() + "\n";
		}

		updateFinalQuestionScore(mQuest.getPoints());
		updateFinalAnswerScore(mQuest.getPoints());
		return "Question #" + mQuest.getQuestionNumber() + " " + mQuest.getPoints() 
		+ " points out of " + mQuest.getPoints() + "\n";
	}

	//checks the fill in the blank answer against the correct answer.
	private String checkFillInTheBlankAnswers(FillInTheBlankQuestion mQuest, 
			FillInTheBlankAnswer mAnswer) {
		String[] questArray = mQuest.getAnswer();
		String[] answerArray = mAnswer.getAnswer();
		int goodAnswers = 0;
		for (int i = 0; i < answerArray.length; i++) {
			String anAnswer = answerArray[i];
			for (int j = 0; j < questArray.length; j++) {
				if (anAnswer.compareTo(questArray[j]) == 0) {
					goodAnswers++;
				}
			}
		}
		double pointsAvg = mQuest.getPoints() / questArray.length;
		updateFinalAnswerScore(pointsAvg * goodAnswers);
		updateFinalQuestionScore(mQuest.getPoints());
		return "Question #" + mQuest.getQuestionNumber() + " " + pointsAvg * 
				goodAnswers + " points out of " + mQuest.getPoints() + "\n";
	}

	//Gets the final answer score after it is updated.
	private double getFinalAnswerScore() {
		return finalAnswerScore;
	}
		
	//Updates the final answer score after checking the answers.
	private void updateFinalAnswerScore(double points) {
		finalAnswerScore += points;
	}

	//Gets the final question score after it is updated.
	private double getFinalQuestionScore() {
		return finalQuestionScore;
	}

	//Updates the final answer score after checking the answers.
	private void updateFinalQuestionScore(double points) {
		finalQuestionScore += points;
	}

	//Gets the letter grade based on the score.
	@Override
	public double getLetterGrade() {
		Collection<Double> values = examScores.values();
		double total = 0.0;
		for (Double score : values) {
			total += score;
		}
		return total/examScores.size();
	
	}
}
