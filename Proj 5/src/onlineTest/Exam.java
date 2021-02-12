package onlineTest;

import java.io.Serializable;
import java.util.Collection;

public interface Exam extends Serializable {

	//Gets the title.
	public String getTitle();

	//Gets the exam Id.
	public int getExamId();

	//Adds the question to the question list.
	public void addQuestion(Question question);

	//Gets the key.
	public String getKey();

	//Collection function for getting the question
	public Collection<Question> getQuestions();

}
