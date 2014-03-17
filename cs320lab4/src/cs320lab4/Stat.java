package cs320lab4;

public class Stat {
	private String _player;
	private int _points;
	private int _rebounds;
	private int _assists;
	
	public Stat(){}
	
	public Stat(String player, int points, int rebounds, int assists)
	{
		_player = player;
		_points = points;
		_rebounds = rebounds;
		_assists = assists;
	}
	
	public String getPlayer() {
		return _player;
	}
	public void setPlayer(String _player) {
		this._player = _player;
	}
	public int getPoints() {
		return _points;
	}
	public void setPoints(int _points) {
		this._points = _points;
	}
	public int getRebounds() {
		return _rebounds;
	}
	public void setRebounds(int _rebounds) {
		this._rebounds = _rebounds;
	}
	public int getAssists() {
		return _assists;
	}
	public void setAssists(int _assists) {
		this._assists = _assists;
	}

}
