package camping.dto;

public class Board {
	int id;
	String author;
	String title;
	String text;
	public Board() {}
	public Board(String author, String title, String text) {
		this.author = author;
		this.title = title;
		this.text = text;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", text=" + text + "]";
	}
}
