package Book;

import JavaDeveloperTask.Book.Book;
import JavaDeveloperTask.Person.Author.Author;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class BookShould {

    private Book book;
    private Author author;

    @Before
    public void initialise() {
        author = new Author("John Baker");
        book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
    }

    @Test
    public void changeAuthor() {
        Author newAuthor = new Author("James Jameson");
        book.setAuthor(newAuthor);
        assertSame(newAuthor, book.getAuthor());
    }

    @Test
    public void changeName() {
        book.setName("The Bright Side");
        assertEquals("The Bright Side", book.getName());
    }

    @Test
    public void changeCategory() {
        book.setCategory("Horror");
        assertEquals("Horror", book.getCategory());
    }

    @Test
    public void changeLanguage() {
        book.setLanguage("Lithuanian");
        assertEquals("Lithuanian", book.getLanguage());
    }

    @Test
    public void changePublicationDate() {
        book.setPublicationDate(LocalDate.parse("2021-07-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals(LocalDate.parse("2021-07-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")), book.getPublicationDate());
    }

    @Test
    public void changeISBN() {
        book.setIsbn("918-2-13-218310-8");
        assertEquals("918-2-13-218310-8", book.getIsbn());
    }

    @Test
    public void changeGUID() {
        book.setGuid("1224e7f3-1d74-453e-9451-f6vbf8549b3a");
        assertEquals("1224e7f3-1d74-453e-9451-f6vbf8549b3a", book.getGuid());
    }

    @Test
    public void changeAvailability() {
        book.setAvailability(true);
        assertEquals(true, book.isTaken());
    }
}
