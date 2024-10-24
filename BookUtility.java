package mybookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class BookUtility {
	public static Connection getConnection(String url, String dbname, String dbpass) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, dbname, dbpass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	

	public static void add(Book b, Connection con) {
		String query = "insert into books values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, b.getBookId());
			pst.setString(2, b.getBookName());
			pst.setString(3, b.getAuthor());
			pst.setString(4, b.getPublisher());
			pst.setDouble(5, b.getPrice());
			pst.executeUpdate();
			System.out.println("added");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String addListOfBooks(List<Book> books, Connection con) {
		String query = "insert into books values (?, ?, ?, ?, ?)";
		if (books.size() == 0) {
			return null;
		}
		PreparedStatement pst;
		for (Book l : books) {
			
			try {
				pst = con.prepareStatement(query);
				pst.setInt(1, l.getBookId());
				pst.setString(2, l.getBookName());
				pst.setString(3, l.getAuthor());
				pst.setString(4, l.getPublisher());
				pst.setDouble(5, l.getPrice());
				pst.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return "Added Succesfully";
	}

	public static void FetchBooks(Connection con) {
		List<Book> books = new ArrayList<Book>();
		String query = "select * from books";

		try {
			Statement st = con.createStatement();
			ResultSet re = st.executeQuery(query);
			while (re.next()) {
				Book b = new Book();
				b.setBookId(re.getInt(1));
				b.setBookName(re.getString(2));
				b.setAuthor(re.getString(3));
				b.setPublisher(re.getString(4));
				b.setPrice(re.getDouble(5));
				st.addBatch(query);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Iterator<Book> itr = books.iterator();
		while (itr.hasNext()) {
			Book book = itr.next();
			System.out.println(book);
		}
	}

	public static void deleteBookById(int id, Connection con) throws SQLException, BookNotFoundException {
		String str = "delete from books where bookId = ?";
		PreparedStatement st = null;
			st = con.prepareStatement(str);
			st.setInt(1,id);
			int count = st.executeUpdate();
			if(count==0) {
				throw new BookNotFoundException();
			}else {
				System.out.println("Book Deleted by: "+id);
			}
	}

	public static void deleteBookByName(String name, Connection con) throws SQLException, BookNotFoundException {
		String str = "delete from books where bookName = ?";
		PreparedStatement st = null;
		st = con.prepareStatement(str);
		st.setString(1, name);
		int count = st.executeUpdate(str);
		if(count == 0) {
			throw new BookNotFoundException();
		}else {
			System.out.println("Deleted SuccessFully book: "+name);
		}
	}
	
	public static void updateBookId(int id, Double newPrice, Connection con) throws SQLException {
		String query = "update books set price ? where bookId = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(2, id);
		pst.setDouble(1, newPrice);
		int count = pst.executeUpdate();
		if(count != 0) {
			System.out.println( "update Successful: "+count);
		}
		else {
			System.out.println("book not found");
		}
	}
}
