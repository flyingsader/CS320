package cs320lab2web;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrivingTest {

	private List<Question> _questions;
	private int _currentQuestionIndex;
	private int _score;

	public DrivingTest() throws FileNotFoundException {
		// Load questions from file into List
		_questions = loadQuestions();
		_currentQuestionIndex = 0;
		_score = 0;
	}

	public int getCurrentQuestionIndex() {
		return _currentQuestionIndex;
	}

	public void setCurrentQuestionIndex(int _currentQuestionIndex) {
		this._currentQuestionIndex = _currentQuestionIndex;
	}

	public Question getCurrentQuestion() {
		return _questions.get(_currentQuestionIndex);
	}

	public boolean isLastQuestion() {
		return _currentQuestionIndex == (_questions.size() - 1);
	}

	public int getScore() {
		return _score;
	}

	private List<Question> loadQuestions() throws FileNotFoundException {
		List<Question> questions = new ArrayList<Question>();
		File input = new File("DrivingTest.txt");
		Scanner sc;
		sc = new Scanner(input);
		int qLine = 0;
		Question q = new Question();

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.trim().equals("")) {
				// save question object
				questions.add(q);

				// reset question object
				q = new Question();
				qLine = 0;
			} else {
				qLine++;
				switch (qLine) {
				case 1:
					// description
					q.setDescription(line);
					break;

				case 2:
					// answer A
					q.setAnswerA(line);
					break;

				case 3:
					// answer B
					q.setAnswerB(line);
					break;

				case 4:
					// answer C
					q.setAnswerC(line);
					break;

				case 5:
					// correct answer
					q.setCorrectAnswer(Integer.parseInt(line));
					break;
				}
			}
		}
		sc.close();
		return questions;
	}
}
