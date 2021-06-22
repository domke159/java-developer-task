package JavaDeveloperTask.Library;

import JavaDeveloperTask.Book.Book;
import JavaDeveloperTask.Person.Customer.Customer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Library {

    @NotEmpty @Size(min = 2)
    private String name;
    @NotEmpty
    private String address;
    private List<Customer> customers = new ArrayList<>();
    // period limit (in months)
    private int periodLimit = 2;
    private int bookLimit = 3;
    private List<Book> bookList = new ArrayList<>();

    public Library() {}

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void printBookList() {
        for (Book b : bookList)
            System.out.println(b);
    }

    public Customer findCustomer(String customerName) {
        for (Customer c : customers) {
            if (c.getName().equals(customerName))
                return c;
        }
        return null;
    }

    public void printCustomers() {
        for (Customer c : customers)
            System.out.println(c);
    }

    public int getPeriodLimit() {
        return periodLimit;
    }

    public void setPeriodLimit(int periodLimit) {
        this.periodLimit = periodLimit;
    }

    public int getBookLimit() {
        return bookLimit;
    }

    public void setBookLimit(int bookLimit) {
        this.bookLimit = bookLimit;
    }

    @Override
    public String toString() {
        return "Library{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
