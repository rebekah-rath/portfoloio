package onlineTest;

public class TrueFalseAnswer implements Answer {

	private static final long serialVersionUID = 1233376269708815507L;
	private int examId;
	private int questionNumber;
	private String studentName;
	private boolean answer;

	//Constructor that initializes private variables.
	public TrueFalseAnswer(String studentName, int examId, int questionNumber, boolean answer) {
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.studentName = studentName;
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

	//Gets the student name.
	public String getStudentName() {
		return studentName;
	}

	//Gets the answer.
	public boolean getAnswer() {
		return answer;
	}
}
