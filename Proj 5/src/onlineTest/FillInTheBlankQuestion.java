package onlineTest;

public class FillInTheBlankQuestion implements Question {

	private static final long serialVersionUID = 5582461209893085878L;
	private int examId;
	private int questionNumber;
	private String text;
	private double points;
	private String[] answer;

	//Constructor that initializes private variables.
	public FillInTheBlankQuestion(int examId, int questionNumber, String text,
			double points, String[] answer) {
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.answer = answer;
	}

	//Gets the exam Id.
	@Override
	public int getExamId() {
		return examId;
	}

	//Gets the question number.
	@Override
	public int getQuestionNumber() {
		return questionNumber;
	}

	//Gets the text.
	@Override
	public String getText() {
		return text;
	}

	//Gets the points.
	@Override
	public double getPoints() {
		return points;
	}

	//Gets the answer.
	public String[] getAnswer() {
		return answer;
	}
}
