package VirtualLibrary.models;

import javax.validation.constraints.*;

public class Книга {

    public Книга() {
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty
    @Size(min = 2, max = 50, message = "Title length should be between 2 and 50 characters")
    private String title;
    @NotEmpty
    @Size(min = 2, max = 20, message = "Author`s name should be between 2 and 20 characters")
    private String authorName;

    @Min(value = 1600, message = "Publication year can`t be lower then 1600")
    @Max(value = 2022, message = "Publication year can`t be higher then 2022")
    private int publicationYear;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Книга(String title, String authorName, int publicationYear) {
        this.title = title;
        this.authorName = authorName;
        this.publicationYear = publicationYear;
    }
}
