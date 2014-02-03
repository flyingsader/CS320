package cs320lab2;

public class Question {
	private String _description;
	private String _answerA;
	private String _answerB;
	private String _answerC;
	private int _correctAnswer;
	private int _answer;
	
	public Question()
	{
		_description = "";
		_answerA = "";
		_answerB = "";
		_answerC = "";
		_correctAnswer = -1;
		_answer = -1;
	}
	
	public String getDescription() {
		return _description;
	}

	public void setDescription(String _description) {
		this._description = _description;
	}

	public String getAnswerA() {
		return _answerA;
	}

	public void setAnswerA(String _answerA) {
		this._answerA = _answerA;
	}

	public String getAnswerB() {
		return _answerB;
	}

	public void setAnswerB(String _answerB) {
		this._answerB = _answerB;
	}

	public String getAnswerC() {
		return _answerC;
	}

	public void setAnswerC(String _answerC) {
		this._answerC = _answerC;
	}

	public int getCorrectAnswer() {
		return _correctAnswer;
	}

	public void setCorrectAnswer(int _correctAnswer) {
		this._correctAnswer = _correctAnswer;
	}

	public int getAnswer() {
		return _answer;
	}

	public void setAnswer(int _answer) {
		this._answer = _answer;
	}

	public boolean isAnswerCorrect() {
		return _correctAnswer > 0 && Integer.compare(_correctAnswer, _answer) == 0;
	}
}
