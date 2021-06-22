package JavaDeveloperTask.Library;

import JavaDeveloperTask.Book.Book;
import JavaDeveloperTask.Person.Customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private Library library = new Library("The National Library", "The Great Street, 1");

    public LibraryService() {
    }

    public LibraryService (Library library) {
        this.library = library;
    }

    public void addBook(Book book) {
        library.getBookList().add(book);
    }

    public void addCustomer(Customer customer) {
        library.getCustomers().add(customer);
    }

    public List<Book> getBooks(String filter, String value) {
        switch (filter) {
            case "author":
                return Filter.filterByAuthor(library.getBookList(), value);
            case "category":
                return Filter.filterByCategory(library.getBookList(), value);
            case "language":
                return Filter.filterByLanguage(library.getBookList(), value);
            case "isbn":
                return Filter.filterByISBN(library.getBookList(), value);
            case "name":
                return Filter.filterByName(library.getBookList(), value);
            case "availability":
                return Filter.filterByAvailability(library.getBookList(), value);
            default:
                return null;
        }
    }

    public Book getBookByGUID(String guid) {
        for (Book b : library.getBookList()) {
            if (Filter.modifyString(b.getGuid()).equals(Filter.modifyString(guid)))
                return b;
        }
        return null;
    }

    public void deleteBook(String guid) {
        for (Book b : library.getBookList()) {
            if (Filter.modifyString(b.getGuid()).equals(Filter.modifyString(guid))) {
                library.getBookList().remove(b);
                return;
            }
        }
        System.out.println("Book does not exist");
    }

    public void deleteCustomer(String customerID) {
        for (Customer c : library.getCustomers()) {
            if (c.getUserID().equals(Long.valueOf(Filter.modifyString(customerID)))) {
                library.getCustomers().remove(c);
                return;
            }
        }
        System.out.println("Customer does not exist");
    }

    public void takeBook(String customerID, String guid, int period) {
        Customer customer = null;
        for (Customer c : library.getCustomers()) {
            if (c.getUserID().equals(Long.valueOf(Filter.modifyString(customerID)))) {
                customer = c;
            }
        }
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }
        Book book = getBookByGUID(guid);
        if (canTakeBook(customer, period, book)) {
            customer.getTakenBooks().add(getBookByGUID(guid));
            book.setAvailability(true);
        }
        else
            System.out.println("Book cannot be taken");
    }

    private boolean canTakeBook(Customer customer, int period, Book book) {
        return (customer.getTakenBooks().size() >= library.getBookLimit() ||
                period > library.getPeriodLimit() || book.isTaken()) ?
                false : true;
    }

}
