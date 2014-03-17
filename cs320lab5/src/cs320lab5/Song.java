package cs320lab5;

public class Song {
	private int _id;
	private int _ranking;
	private String _name;
	private String _artist;
	
	private static int _idCounter = 1;
	
	public Song()
	{
		_id = _idCounter++;
	}
	
	public Song(String name, String artist)
	{
		this();
		_ranking = _id;
		_name = name;
		_artist = artist;
	}
	
	public Song(int ranking, String name, String artist)
	{
		this();
		_ranking = ranking;
		_name = name;
		_artist = artist;
	}

	public int getId()
	{
		return _id;
	}
	
	public int getRanking() {
		return _ranking;
	}

	public void setRanking(int _ranking) {
		this._ranking = _ranking;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public String getArtist() {
		return _artist;
	}

	public void setArtist(String _artist) {
		this._artist = _artist;
	}
	
	
}
