package JavaDeveloperTask.Person.Author;

import JavaDeveloperTask.Book.Book;
import JavaDeveloperTask.Person.Person;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {

    // books written by the author
    private List<Book> bookList = new ArrayList<>();

    public Author() {}

    public Author(String name) {
        super(name);
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Book findBook(String bookName) {
        for (Book b : bookList) {
            if (b.getName().equals(bookName))
                return b;
        }
        return null;
    }

    public void printBooks() {
        for (Book b : bookList)
            System.out.println(b);
    }

    @Override
    public String toString() {
        return "Author{" +
                ", name='" + getName() + '\'' +
                '}';
    }
}
