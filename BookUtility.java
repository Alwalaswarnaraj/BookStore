package mybookstore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class BookUtility {
	public static Connection getConnection(String url,String dbname, String dbpass)  {
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
		String query = "insert into Book values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, b.getBookId());
			pst.setString(1, b.getBookName());
			pst.setString(3, b.getAuthor());
			pst.setString(4, b.getPublisher());
			pst.setDouble(5, b.getPrice());
			pst.execute();
			System.out.println("added");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
