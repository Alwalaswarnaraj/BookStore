package mybookstore;

public class BookNotFoundException extends Exception{
	private String message = "Book is not Found";
	public BookNotFoundException() {
		
	}
	public String getMessage() {
		return message;
	}
}
