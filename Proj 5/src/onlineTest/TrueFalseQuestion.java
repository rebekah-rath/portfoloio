package onlineTest;

public class TrueFalseQuestion implements Question {

	private static final long serialVersionUID = 7797387192941655401L;
	private int examId;
	private int questionNumber;
	private String text;
	private double points;
	private boolean answer;

	//Constructor that initializes private variables.
	public TrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.answer = answer;
	}

	//Gets the exam Id.
	public int getExamId() {
		return examId;
	}

	//Gets the question number.
	public int getQuestionNumber() {
		return questionNumber;
	}

	//Gets the text.
	public String getText() {
		return text;
	}

	//Gets the points.
	public double getPoints() {
		return points;
	}

	//Gets the answer.
	public boolean getAnswer() {
		return answer;
	}
}
