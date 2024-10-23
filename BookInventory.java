package mybookstore;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInventory {
	
	public static void main(String[] args) throws SQLException{
		BookUtility bu = null;
		String url= "jdbc:mysql://localhost:3306/project";
		String dbname = "root";
		String dbpass = "swarna@08";
		Connection con =bu.getConnection(url, dbname, dbpass);
		
		 Book book1 = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Scribner", 10.99);
		 Book book2 = new Book(2, "1984", "George Orwell", "Secker & Warlistrg", 8.99);
		 Book book3 = new Book(3, "To Kill a Mockingbird", "Harper Lee", "J.B. Lippincott & Co.", 12.50);
		 
		 Book book4 = new Book(4, "Pride and Prejudice", "Jane Austen", "T. Egerton", 9.99);
		 Book book5 = new Book(5, "Moby-Dick", "Herman Melville", "Harper & Brothers", 11.50);
		 Book book6 = new Book(6, "War and Peace", "Leo Tolstoy", "The Russian Messenger", 14.75);
		 Book book7 = new Book(7, "The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company", 10.00);
		 Book book8 = new Book(8, "The Lord of the Rings", "J.R.R. Tolkien", "George Allen & Unwin", 25.99);
		 Book book9 = new Book(9, "The Hobbit", "J.R.R. Tolkien", "George Allen & Unwin", 15.75);
		 Book book10 = new Book(10, "Ulysses", "James Joyce", "Sylvia Beach", 13.99);
		 Book book11 = new Book(11, "Brave New World", "Aldous Huxley", "Chatto & Windus", 9.75);
		 Book book12 = new Book(12, "The Odyssey", "Homer", "Penguin Classics", 12.99);
		 Book book13 = new Book(13, "Crime and Punishment", "Fyodor Dostoevsky", "The Russian Messenger", 10.50);
		 Book book14 = new Book(14, "Jane Eyre", "Charlotte Brontë", "Smith, Elder & Co.", 8.99);
		 Book book15 = new Book(17, "The Divine Comedy", "Dante Alighieri", "John Murray", 18.99);
		 List<Book> list1= new ArrayList();
		 list1.add(book15);
		 list1.add(book14);
		 list1.add(book13);
		 list1.add(book12);
		 list1.add(book11);
		 list1.add(book10);
		 list1.add(book9);
		 list1.add(book8);
		 list1.add(book7);
		 list1.add(book6);
		 list1.add(book5);
		 list1.add(book4);
		 list1.add(book3);
		 list1.add(book2);
		 list1.add(book1);
		 
//		 bu.add(book15, con);
//		 bu.addListOfBooks(list1, con);
//		 bu.FetchBooks(con);
		 
		 try {
			bu.deleteBookById(178, con);
		 }catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
		 
		 try {
			bu.deleteBookByName("swar",con);
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		} 
		
	}
}
