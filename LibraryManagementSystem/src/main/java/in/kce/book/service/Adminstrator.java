package in.kce.book.service;

import in.kce.book.bean.BookBean;
import in.kce.book.dao.BookDAO;

public class Adminstrator {
public String addBook(BookBean bookBean) {

    if (bookBean == null) {
        return "INVALID";
    }

    if (bookBean.getIsbn() == null || bookBean.getIsbn().trim().isEmpty()) {
        return "INVALID";
    }
    if (bookBean.getBookName() == null || bookBean.getBookName().trim().isEmpty()) {
        return "INVALID";
    }
    char bookType = bookBean.getBookType();
    if (bookType == ' ' || (bookType != 'G' && bookType != 'T')) {
        return "INVALID";
    }
    if (bookBean.getCost() == 0) {
        return "INVALID";
    }
    if (bookBean.getAuthor() == null ||
        bookBean.getAuthor().getAuthorName() == null ||
        bookBean.getAuthor().getAuthorName().isEmpty()) {
        return "INVALID";
    }
    int result = new BookDAO().createBook(bookBean);
    if (result == 1) {
        return "SUCCESS";
    } else {
        return "FAILURE";
    }
}
public BookBean viewBook(String isbn) {
	if(isbn==null) {
		return null;
		}
	BookBean result=new BookDAO().fetchBook(isbn);
		return result;
}
}