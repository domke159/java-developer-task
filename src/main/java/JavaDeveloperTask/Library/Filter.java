package JavaDeveloperTask.Library;

import JavaDeveloperTask.Book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Filter {

    public static String modifyString(String value) {
        return value.toLowerCase().replace(" ","");
    }

    public static List<Book> filterByAuthor(List<Book> bookList, String authorName) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book b : bookList) {
            if (modifyString(b.getAuthor().getName()).equals(modifyString(authorName)))
                filteredBooks.add(b);
        }
        return filteredBooks;
    }

    public static List<Book> filterByCategory(List<Book> bookList, String category) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book b : bookList) {
            if (modifyString(b.getCategory()).equals(modifyString(category)))
                filteredBooks.add(b);
        }
        return filteredBooks;
    }

    public static List<Book> filterByLanguage(List<Book> bookList, String language) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book b : bookList) {
            if (modifyString(b.getLanguage()).equals(modifyString(language)))
                filteredBooks.add(b);
        }
        return filteredBooks;
    }

    public static List<Book> filterByISBN(List<Book> bookList, String isbn) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book b : bookList) {
            if (modifyString(b.getIsbn()).equals(modifyString(isbn)))
                filteredBooks.add(b);
        }
        return filteredBooks;
    }

    public static List<Book> filterByName(List<Book> bookList, String name) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book b : bookList) {
            if (modifyString(b.getName()).equals(modifyString(name)))
                filteredBooks.add(b);
        }
        return filteredBooks;
    }

    public static List<Book> filterByAvailability(List<Book> bookList, String availability) {
        return (modifyString(availability).equals("taken")) ? filterByTaken(bookList) :
                (modifyString(availability).equals("available")) ? filterByAvailable(bookList) :
                null;
    }

    public static List<Book> filterByTaken(List<Book> bookList) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book b : bookList) {
            if (b.isTaken())
                filteredBooks.add(b);
        }
        return filteredBooks;
    }

    public static List<Book> filterByAvailable(List<Book> bookList) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book b : bookList) {
            if (!b.isTaken())
                filteredBooks.add(b);
        }
        return filteredBooks;
    }

}
