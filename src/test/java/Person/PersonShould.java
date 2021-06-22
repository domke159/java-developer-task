package Person;

import JavaDeveloperTask.Person.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonShould {

    private Person person;

    @Before
    public void initialise() {
        person = new Person("Dominykas Mickevicius");
    }

    @Test
    public void changeName() {
        person.setName("John Johnson");
        assertEquals("John Johnson", person.getName());
    }
}
