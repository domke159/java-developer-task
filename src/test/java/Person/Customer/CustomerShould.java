package Person.Customer;

import JavaDeveloperTask.Person.Customer.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerShould {

    private Customer customer;

    @Before
    public void initialise() {
        customer = new Customer(123456789L, "David Davidson");
    }

    @Test
    public void changeCustomerID() {
        customer.setUserID(213456789L);
        assertEquals(213456789L, customer.getUserID());
    }
}
