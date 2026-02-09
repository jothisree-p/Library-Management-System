package in.kce.book.servlets;

import java.io.IOException;

import in.kce.book.bean.BookBean;
import in.kce.book.dao.AuthorDAO;
import in.kce.book.service.Adminstrator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if(operation.equals("AddBooks")) {
			String result=addBook(request);
			if(result.equals("SUCCESS")) {
				response.sendRedirect("Menu.html");
			}
			else if(result.equals("INVALID")) {
				response.sendRedirect("Invalid.html");
			}
			else if(result.equals("FAILURE")) {
				response.sendRedirect("Failure.html");
		}
			else if(operation.equals("Search")) {
				String isbn=request.getParameter("isbn");
				BookBean bookBean=viewBook(isbn);
				if(bookBean==null) {
					response.sendRedirect("Invalid.html");
				}
				else {
					HttpSession session=request.getSession();
					session.setAttribute("bookBean", bookBean);
					RequestDispatcher rd=request.getRequestDispatcher("ViewServlet");
					rd.forward(request, response);				
					}
			}
		}
	}
public String addBook(HttpServletRequest request) {
	        String isbn=request.getParameter("isbn");
			String bookName=request.getParameter("bookName");
			String bookType=request.getParameter("bookType");
			String author=request.getParameter("author");
			String cost=request.getParameter("cost");
BookBean bookBean=new BookBean();
			bookBean.setIsbn(isbn);
			bookBean.setBookName(bookName);
			bookBean.setBookType(bookType.charAt(0));
			bookBean.setCost(Float.parseFloat(cost));
			bookBean.setAuthor(new AuthorDAO().getAuthor(author));
	String result=new Adminstrator().addBook(bookBean);
	return result;
}
public BookBean viewBook(String isbn) {
	return new Adminstrator().viewBook(isbn);
}
}