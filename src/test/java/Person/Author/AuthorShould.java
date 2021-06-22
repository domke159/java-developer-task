package Person.Author;

import JavaDeveloperTask.Book.Book;
import JavaDeveloperTask.Person.Author.Author;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class AuthorShould {

    private Author author;

    @Before
    public void initialise() {
        author = new Author("John Baker");
    }

    @Test
    public void addBook() {
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        assertEquals(book, author.getBookList().get(0));
    }

    @Test
    public void findBook() {
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        Book foundBook = author.findBook("The Dark Side");
        assertSame(author.getBookList().get(0), foundBook);
    }

    @Test
    public void notFindBookWithWrongName() {
        assertSame(null, author.findBook("The Bright Side"));
    }

    @Test
    public void deleteBook() {
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        assertEquals(1, author.getBookList().size());
        author.getBookList().remove(book);
        assertEquals(0, author.getBookList().size());
    }
}
