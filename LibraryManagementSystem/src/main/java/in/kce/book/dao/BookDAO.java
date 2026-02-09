package in.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.kce.book.bean.BookBean;
import in.kce.book.util.DButil;

public class BookDAO {
public int createBook(BookBean bookBean) {
	Connection connection= DButil.getDBConnection();
	   String query="insert into Book_tbl values (?, ?, ?, ?, ?)";
	   try {
		   PreparedStatement ps=connection.prepareStatement(query);
           ps.setString(1, bookBean.getIsbn());
           ps.setString(2, bookBean.getBookName());
           ps.setString(3, String.valueOf(bookBean.getBookType()));
           ps.setInt(4, bookBean.getAuthor().getAuthorCode());
           ps.setDouble(5, bookBean.getCost());
int result = ps.executeUpdate();
if (result > 0) {
    return 1;
}

} catch (SQLException e) {
return 0; 
}

return 0;
}
public BookBean fetchBook(String isbn) {

    Connection connection = DButil.getDBConnection();
    BookBean bookBean = null;

    String query = "SELECT * FROM book_tbl WHERE isbn = ?";

    try {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, isbn);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
        	  bookBean = new BookBean();
              bookBean.setIsbn(rs.getString(1));
              bookBean.setBookName(rs.getString(2));
              bookBean.setBookType(rs.getString(3).charAt(0));
              bookBean.setAuthor(new AuthorDAO().getAuthor(rs.getInt(4)));
              bookBean.setCost(rs.getFloat(5));
              return bookBean;
        }
    } catch (SQLException e) {
        return null;
    }
    return bookBean;
}}
