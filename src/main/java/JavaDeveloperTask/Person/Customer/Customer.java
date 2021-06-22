package JavaDeveloperTask.Person.Customer;

import JavaDeveloperTask.Book.Book;
import JavaDeveloperTask.Person.Person;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {

    private Long userID;
    private List<Book> takenBooks = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long userID, String name) {
        super(name);
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public List<Book> getTakenBooks() {
        return takenBooks;
    }

    public void printTakenBooks() {
        for (Book b : takenBooks)
            System.out.println(b);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "User ID=" + userID +
                ", name=" + getName() +
                '}';
    }
}
