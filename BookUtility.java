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
		for (Book l : books) {
			PreparedStatement pst;
			try {
				pst = con.prepareStatement(query);
				pst.setInt(1, l.getBookId());
				pst.setString(2, l.getBookName());
				pst.setString(3, l.getAuthor());
				pst.setString(4, l.getPublisher());
				pst.setDouble(5, l.getPrice());
				pst.executeUpdate();
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
				books.add(b);
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

	public static void deleteBookById(int id, Connection con) {
		String str = "delete from books where bookId = '" + id + "'";
		Statement st = null;
		try {
			st = con.createStatement();
			int count = st.executeUpdate(str);
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteBookByName(String name, Connection con) throws SQLException {
		String str = "delete from books where bookId = '" + name + "'";
		List<Book> books = new ArrayList();
		PreparedStatement st = null;
		st = con.prepareStatement(str);
		ResultSet rs = st.executeQuery(str);
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt(1));
			b.setBookName(rs.getString(2));
			b.setAuthor(rs.getString(3));
			b.setPublisher(rs.getString(4));
			b.setPrice(rs.getDouble(5));
			books.add(b);
		}
		for (Book b : books) {
			books.remove(b);
		}
	}

//	public static void deleteBookBy
}
