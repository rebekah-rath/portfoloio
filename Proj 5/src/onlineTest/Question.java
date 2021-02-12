package onlineTest;

import java.io.Serializable;

public interface Question extends Serializable {

	//Gets the exam Id.
	public int getExamId();

	//Gets the question number.
	public int getQuestionNumber();

	//Gets the text.
	public String getText();

	//Gets the points.
	public double getPoints();
}
