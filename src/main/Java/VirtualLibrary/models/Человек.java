package VirtualLibrary.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
public class Человек {

    public Человек() {
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty(message = "FullName form should not be empty")
    @Size(min = 8, max = 60, message = "FullName length should be between 8 and 60 characters")
    @Pattern(regexp = "[ЁёА-Яа-я]+ [А-Яа-я]+ [А-Яа-я]+", message = "Your full name should be in this format: " +
            "Фамилия Имя Отчество")
    private String fullName;

    @Min(value = 1900, message = "Year of born can`t be lower then 1901")
    @Max(value = 2022, message = "Year of born can`t be higher then 2022")
    private int yearWhenBorn;

    public Человек(String fullName, int yearWhenBorn) {
        this.fullName = fullName;
        this.yearWhenBorn = yearWhenBorn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearWhenBorn() {
        return yearWhenBorn;
    }

    public void setYearWhenBorn(int yearWhenBorn) {
        this.yearWhenBorn = yearWhenBorn;
    }
}
