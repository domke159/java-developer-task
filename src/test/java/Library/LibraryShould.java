package Library;

import JavaDeveloperTask.Book.Book;
import JavaDeveloperTask.Library.Library;
import JavaDeveloperTask.Person.Author.Author;
import JavaDeveloperTask.Person.Customer.Customer;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class LibraryShould {

    private Library library;

    @Before
    public void initialise() {
        library = new Library("The National Library", "The famous street, 1");
    }

    @Test
    public void changeName() {
        library.setName("The Local Library");
        assertEquals("The Local Library", library.getName());
    }

    @Test
    public void changeAddress() {
        library.setAddress("The infamous street, 2");
        assertEquals("The infamous street, 2", library.getAddress());
    }

    @Test
    public void changePeriodLimit() {
        library.setPeriodLimit(5);
        assertEquals(5, library.getPeriodLimit());
    }

    @Test
    public void addCustomer() {
        Customer customer = new Customer(12345678911L, "James Jameson");
        library.getCustomers().add(customer);
        assertSame(customer, library.getCustomers().get(0));
    }

    @Test
    public void removeCustomer() {
        Customer customer = new Customer(12345678911L, "James Jameson");
        library.getCustomers().add(customer);
        assertSame(customer, library.getCustomers().get(0));
        library.getCustomers().remove(customer);
        assertEquals(0, library.getCustomers().size());
    }

    @Test
    public void findCustomer() {
        Customer customer = new Customer(12345678911L, "James Jameson");
        library.getCustomers().add(customer);
        assertSame(customer, library.findCustomer("James Jameson"));
    }

    @Test
    public void notFindCustomerWithWrongName() {
        Customer customer = new Customer(12345678911L, "James Jameson");
        library.getCustomers().add(customer);
        assertSame(null, library.findCustomer("John Jameson"));
    }

    @Test
    public void addBook() {
        Author author = new Author("John Baker");
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        library.getBookList().add(book);
        assertSame(book, library.getBookList().get(0));
    }

    @Test
    public void deleteBook() {
        Author author = new Author("John Baker");
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        library.getBookList().add(book);
        assertSame(book, library.getBookList().get(0));
        library.getBookList().remove(book);
        assertEquals(0, library.getBookList().size());
    }

    @Test
    public void changeBookLimit() {
        library.setBookLimit(5);
        assertEquals(5, library.getBookLimit());
    }
}
