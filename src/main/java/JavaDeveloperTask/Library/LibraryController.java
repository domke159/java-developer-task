package JavaDeveloperTask.Library;

import JavaDeveloperTask.Book.Book;
import JavaDeveloperTask.Person.Customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    public LibraryController() {}

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/addBook")
    public void addBook(@Valid @RequestBody Book book) {
        libraryService.addBook(book);
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@Valid @RequestBody Customer customer) {
        libraryService.addCustomer(customer);
    }

    @DeleteMapping("/deleteCustomer/{customerID}")
    public void deleteCustomer(@Valid @PathVariable String customerID) {
        libraryService.deleteCustomer(customerID);
    }

    @GetMapping("/books/{filter}/{value}")
    public List<Book> getBooks(@Valid @PathVariable String filter, @Valid @PathVariable String value) {
        return libraryService.getBooks(filter, value);
    }

    @GetMapping("/book/{guid}")
    public Book getBookByGUID(@Valid @PathVariable String guid) {
        return libraryService.getBookByGUID(guid);
    }

    @DeleteMapping("/deleteBook/{guid}")
    public void deleteBook(@Valid @PathVariable String guid) {
        libraryService.deleteBook(guid);
    }

    @PutMapping("takeBook/{customerID}/{guid}/{period}")
    public void takeBook(@Valid @PathVariable String customerID,
                          @Valid @PathVariable String guid,
                          @Valid @PathVariable int period) {
        libraryService.takeBook(customerID, guid, period);
    }
}
