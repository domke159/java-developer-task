package Library;

import JavaDeveloperTask.Book.Book;
import JavaDeveloperTask.Library.Library;
import JavaDeveloperTask.Library.LibraryController;
import JavaDeveloperTask.Library.LibraryService;
import JavaDeveloperTask.Person.Author.Author;
import JavaDeveloperTask.Person.Customer.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


public class LibraryControllerShould {

    @Autowired
    private LibraryController libraryController;
    private LibraryService libraryService;
    private Library library;

    @Before
    public void initialise() {
       library = new Library("The national library", "Street 1");
       libraryService = new LibraryService(library);
       libraryController = new LibraryController(libraryService);
    }

    @Test
    public void addBook() {
        Author author = new Author("John Baker");
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        libraryController.addBook(book);
        assertSame(book, library.getBookList().get(0));
    }

    @Test
    public void getBookByGUID() {
        Author author = new Author("John Baker");
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        libraryController.addBook(book);
        assertSame(book, libraryController.getBookByGUID("1414e7d3-1d74-452e-9451-f6ebf8549b3a"));
    }

    @Test
    public void notGetBookWithWrongGUID() {
        Author author = new Author("John Baker");
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        libraryController.addBook(book);
        assertEquals(null, libraryController.getBookByGUID("1414e7d3-1d74-452e-9451-f6ebf8549b3b"));
    }

    @Test
    public void addCustomer() {
        Customer customer = new Customer(12345678911L, "James Jameson");
        libraryController.addCustomer(customer);
        assertSame(customer, library.getCustomers().get(0));
    }

    @Test
    public void deleteCustomer() {
        Customer customer = new Customer(12345678911L, "James Jameson");
        libraryController.addCustomer(customer);
        assertEquals(1, library.getCustomers().size());
        libraryController.deleteCustomer("12345678911");
        assertEquals(0, library.getCustomers().size());
    }

    @Test
    public void notDeleteCustomerWithWrongID(){
        Customer customer = new Customer(12345678911L, "James Jameson");
        libraryController.addCustomer(customer);
        assertEquals(1, library.getCustomers().size());
        libraryController.deleteCustomer("12345678912");
        assertEquals(1, library.getCustomers().size());
    }

    @Test
    public void deleteBook() {
        Author author = new Author("John Baker");
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        libraryController.addBook(book);
        assertEquals(1, library.getBookList().size());
        libraryController.deleteBook("1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        assertEquals(0, library.getBookList().size());
    }

    @Test
    public void notDeleteBookWithNonExistingGUID() {
        Author author = new Author("John Baker");
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        libraryController.addBook(book);
        assertEquals(1, library.getBookList().size());
        libraryController.deleteBook("1414e7d3-1d74-452e-9451-f6ebf8549b3b");
        assertEquals(1, library.getBookList().size());
    }

    @Test
    public void takeBook() {
        Customer customer = new Customer(123456789L, "David Davidson");
        Author author = new Author("John Baker");
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        libraryController.addCustomer(customer);
        libraryController.addBook(book);
        libraryController.takeBook("123456789", "1414e7d3-1d74-452e-9451-f6ebf8549b3a", 2);
        assertSame(book, customer.getTakenBooks().get(0));
    }

    @Test
    public void notTakeBookWhenPeriodIsLongerThanTwoMonths() {
        Customer customer = new Customer(123456789L, "David Davidson");
        Author author = new Author("John Baker");
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        libraryController.addCustomer(customer);
        libraryController.addBook(book);
        libraryController.takeBook("123456789", "1414e7d3-1d74-452e-9451-f6ebf8549b3a", 3);
        assertEquals(0, customer.getTakenBooks().size());
    }

    @Test
    public void notTakeBookWhenBookLimitIsExceeded() {
        Author author = new Author("John Baker");
        Book book1 = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        Book book2 = new Book(author, "The Bright Side", "Horror", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-1", "1414e7d3-1d74-452e-9451-f6ebf8549b3b");
        Book book3 = new Book(author, "The Right Side", "Horror", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3c");
        Book book4 = new Book(author, "The Wrong Side", "Horror", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-6", "1414e7d3-1d74-452e-9451-f6ebf8549b3v");
        libraryController.addBook(book1);
        libraryController.addBook(book2);
        libraryController.addBook(book3);
        libraryController.addBook(book4);

        Customer customer = new Customer(123456789L, "David Davidson");
        libraryController.addCustomer(customer);

        libraryController.takeBook("123456789", "1414e7d3-1d74-452e-9451-f6ebf8549b3a", 1);
        libraryController.takeBook("123456789", "1414e7d3-1d74-452e-9451-f6ebf8549b3b", 1);
        libraryController.takeBook("123456789", "1414e7d3-1d74-452e-9451-f6ebf8549b3c", 1);
        libraryController.takeBook("123456789", "1414e7d3-1d74-452e-9451-f6ebf8549b3v", 1);

        assertEquals(3, customer.getTakenBooks().size());
    }

    @Test
    public void notTakeUnavailableBook() {
        Author author = new Author("John Baker");
        Book book1 = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        libraryController.addBook(book1);

        Customer customer = new Customer(123456789L, "David Davidson");
        libraryController.addCustomer(customer);

        libraryController.takeBook("123456789", "1414e7d3-1d74-452e-9451-f6ebf8549b3a", 1);
        libraryController.takeBook("123456789", "1414e7d3-1d74-452e-9451-f6ebf8549b3a", 1);

        assertEquals(1, customer.getTakenBooks().size());
    }

    @Test
    public void notFilterWithWrongFilter() {
        Author author = new Author("John Baker");
        Book book = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        List<Book> books = new ArrayList<>();
        books.add(book);
        libraryController.addBook(book);
        assertEquals(null, libraryController.getBooks("address", "John Baker"));
    }

    @Test
    public void filterByAuthor() {
        Author author = new Author("John Baker");
        Book book1 = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        Book book2 = new Book(author, "The Bright Side", "Horror", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-1", "1414e7d3-1d74-452e-9451-f6ebf8549b3b");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        libraryController.addBook(book1);
        libraryController.addBook(book2);
        assertEquals(books, libraryController.getBooks("author", "John Baker"));
    }

    @Test
    public void filterByCategory() {
        Author author = new Author("John Baker");
        Book book1 = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        Book book2 = new Book(author, "The Bright Side", "Horror", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-1", "1414e7d3-1d74-452e-9451-f6ebf8549b3b");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        libraryController.addBook(book1);
        libraryController.addBook(book2);
        assertEquals(books, libraryController.getBooks("category", "Fantasy"));
    }

    @Test
    public void filterByLanguage() {
        Author author = new Author("John Baker");
        Book book1 = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        Book book2 = new Book(author, "The Bright Side", "Horror", "French",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-1", "1414e7d3-1d74-452e-9451-f6ebf8549b3b");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        libraryController.addBook(book1);
        libraryController.addBook(book2);
        assertEquals(books, libraryController.getBooks("language", "English"));
    }

    @Test
    public void filterByISBN() {
        Author author = new Author("John Baker");
        Book book1 = new Book(author, "The Dark Side", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        Book book2 = new Book(author, "The Bright Side", "Horror", "French",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-1", "1414e7d3-1d74-452e-9451-f6ebf8549b3b");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        libraryController.addBook(book1);
        libraryController.addBook(book2);
        assertEquals(books, libraryController.getBooks("isbn", "918-2-13-148410-5"));
    }

    @Test
    public void filterByName() {
        Author author1 = new Author("John Baker");
        Book book1 = new Book(author1, "Night", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        Author author2 = new Author("John Maker");
        Book book2 = new Book(author2, "Night", "Horror", "French",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-1", "1414e7d3-1d74-452e-9451-f6ebf8549b3b");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        libraryController.addBook(book1);
        libraryController.addBook(book2);
        assertEquals(books, libraryController.getBooks("name", "Night"));
    }

    @Test
    public void filterByAvailable() {
        Author author1 = new Author("John Baker");
        Book book1 = new Book(author1, "Night", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        Author author2 = new Author("John Maker");
        Book book2 = new Book(author2, "Night", "Horror", "French",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-1", "1414e7d3-1d74-452e-9451-f6ebf8549b3b");
        List<Book> books = new ArrayList<>();
        books.add(book2);
        libraryController.addBook(book1);
        libraryController.addBook(book2);

        Customer customer = new Customer(123456789L, "David Davidson");
        libraryController.addCustomer(customer);

        libraryController.takeBook("123456789", "1414e7d3-1d74-452e-9451-f6ebf8549b3a", 1);
        assertEquals(books, libraryController.getBooks("availability", "available"));
    }

    @Test
    public void filterByTaken() {
        Author author1 = new Author("John Baker");
        Book book1 = new Book(author1, "Night", "Fantasy", "English",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-5", "1414e7d3-1d74-452e-9451-f6ebf8549b3a");
        Author author2 = new Author("John Maker");
        Book book2 = new Book(author2, "Night", "Horror", "French",
                LocalDate.parse("2021-05-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "918-2-13-148410-1", "1414e7d3-1d74-452e-9451-f6ebf8549b3b");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        libraryController.addBook(book1);
        libraryController.addBook(book2);

        Customer customer = new Customer(123456789L, "David Davidson");
        libraryController.addCustomer(customer);

        libraryController.takeBook("123456789", "1414e7d3-1d74-452e-9451-f6ebf8549b3a", 1);
        assertEquals(books, libraryController.getBooks("availability", "taken"));
    }

}
