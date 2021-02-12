package onlineTest;

public class FillInTheBlankAnswer implements Answer {

	private static final long serialVersionUID = -4954614088239178223L;
	private int examId;
	private int questionNumber;
	private String studentName;
	private String[] answer;

	//Constructor that initializes private variables.
	public FillInTheBlankAnswer(String studentName, int examId, int questionNumber, 
			String[] answer) {
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.studentName = studentName;
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

	//Gets the student name.
	@Override
	public String getStudentName() {
		return studentName;
	}

	//Gets the answer.
	public String[] getAnswer() {
		return answer;
	}
}
