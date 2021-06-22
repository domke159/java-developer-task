package JavaDeveloperTask.Book;

import JavaDeveloperTask.Person.Author.Author;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import javax.validation.constraints.*;


public class Book {

    @NotNull
    private Author author;

    @NotEmpty
    private String name;

    @NotEmpty
    private String category;

    @NotEmpty @Size(max = 30)
    private String language;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    @NotEmpty @Size(max = 20)
    private String isbn;

    @NotEmpty @Size(max = 40)
    private String guid;

    private boolean availability;

    public Book() {
    }

    public Book(Author author, String name, String category, String language,
                LocalDate publicationDate, String isbn, String guid) {
        this.author = author;
        author.getBookList().add(this);
        this.name = name;
        this.category = category;
        this.language = language;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.guid = guid;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public boolean isTaken() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                ", publicationDate=" + publicationDate +
                ", ISBN=" + isbn +
                ", GUID=" + guid +
                '}';
    }
}
