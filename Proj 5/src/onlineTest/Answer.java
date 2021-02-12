package onlineTest;

import java.io.Serializable;

public interface Answer extends Serializable {

	//Gets the exam Id.
	public int getExamId();

	//Gets the question number.
	public int getQuestionNumber();

	//Gets the student name.
	public String getStudentName();
}
