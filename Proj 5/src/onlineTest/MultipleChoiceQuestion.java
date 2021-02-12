package onlineTest;

public class MultipleChoiceQuestion implements Question {

	private static final long serialVersionUID = 4750557108348696679L;
	private int examId;
	private int questionNumber;
	private String text;
	private double points;
	private String[] answer;

	//Constructor that initializes private variables.
	public MultipleChoiceQuestion(int examId, int questionNumber, String text, 
			double points, String[] answer) {
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.answer = answer;
	}

	//Gets the exam id.
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
