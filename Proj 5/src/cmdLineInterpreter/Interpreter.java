package cmdLineInterpreter;

import java.util.Scanner;

import onlineTest.SystemManager;

/**
 * 
 * By running the main method of this class we will be able to execute commands
 * associated with the SystemManager. This command interpreter is text based.
 *
 */
public class Interpreter {

	public static void main(String[] args) {

		String selection;
		@SuppressWarnings("resource")
		Scanner selectionInput = new Scanner(System.in);
		SystemManager sysMan = new SystemManager();

		while (true) {
			System.out.println("\n\nChoose from the following");
			System.out.println("-----------\n");
			System.out.println("1 - Add a student");
			System.out.println("2 - Add an exam");
			System.out.println("3 - Add a true or false question");
			System.out.println("4 - Answer a true or false question");
			System.out.println("5 - Get the student's exam score");
			System.out.print("\n Enter selection here: ");


			selection = selectionInput.next();
			switch (selection) {
			case "1":
				System.out.print("Enter a name (last name,first name): ");
				selection = selectionInput.next();
				sysMan.addStudent(selection);
				break;
			case "2":
				System.out.print("Enter an exam id: ");
				int examId = selectionInput.nextInt();
				System.out.println("Enter an exam title: ");
				String title = selectionInput.next();
				sysMan.addExam(examId, title);
				break;

			case "3":
				System.out.println("Enter an exam id: ");
				int examId1 = selectionInput.nextInt();
				System.out.println("Enter a question number: ");
				int questionNumber = selectionInput.nextInt();
				System.out.println("Enter the question: ");
				String text = selectionInput.next();
				System.out.println("Enter the point value: ");
				double points = selectionInput.nextDouble();
				System.out.println("Enter the answer (True/False): ");
				boolean answer = selectionInput.nextBoolean();
				sysMan.addTrueFalseQuestion(examId1, questionNumber, text, points, answer);
				break;
			case "4":
				System.out.print("Enter a name (last name,first name): ");
				String name = selectionInput.next();
				System.out.println("Enter an exam id: ");
				int examId2 = selectionInput.nextInt();
				System.out.println("Enter a question number: ");
				int questionNumber1 = selectionInput.nextInt();
				System.out.println("Enter the answer (True/False): ");
				boolean answer1 = selectionInput.nextBoolean();
				sysMan.answerTrueFalseQuestion( name, examId2, questionNumber1, answer1);
				break;
			case "5":
				System.out.print("Enter a name (last name,first name): ");
				String name1 = selectionInput.next();
				System.out.println("Enter an exam id: ");
				int examId3 = selectionInput.nextInt();
				double score = sysMan.getExamScore(name1, examId3);
				System.out.println("Student: " + name1 + " received a score of: " + score + " on Exam: " + examId3);
				break;
			}
		}

	}
}



