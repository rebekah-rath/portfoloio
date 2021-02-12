package onlineTest;

import java.io.Serializable;
import java.util.Collection;

public interface Student extends Serializable {
	
	//Gets the student name.
	public String getStudentName();

	//Gets the score.
	public double getScore(int examId, Collection<Question> allQuestions);

	//Adds the answer.
	public void addAnswer(int examId, Answer answer);

	//Gets the grading report.
	public String getGradingReport(int examId, Collection<Question> allQuestions);

	//Gets the final letter grade.
	public double getLetterGrade();
}
