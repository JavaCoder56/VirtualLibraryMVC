package VirtualLibrary.dao;

import VirtualLibrary.models.Книга;
import VirtualLibrary.models.Человек;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Component
public class ЧеловекDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ЧеловекDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Человек> showPeople() {
        return jdbcTemplate.query("select * from person;", new BeanPropertyRowMapper<>(Человек.class));
    }

    public Человек showPerson(int id) {
        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Человек.class)).stream().findAny().orElse(null);
    }

    public void create(Человек человек) {
        jdbcTemplate.update("insert into person(fullname, yearWhenBorn) values(?,?);",
                человек.getFullName(), человек.getYearWhenBorn());
    }

    public void edit(int id, Человек updatedЧеловек) {
        jdbcTemplate.update("update person set fullname=?, yearWhenBorn=? where id=?;",
                updatedЧеловек.getFullName(), updatedЧеловек.getYearWhenBorn(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }

    public List<Книга> ownedBooks(int id) {
        return jdbcTemplate.query("select * from book where person_id=?;", new Object[]{id},
                new BeanPropertyRowMapper<>(Книга.class));
    }

    public Optional<Человек> getPersonByFullName (String name) {
        return jdbcTemplate.query("select * from person where person fullname = ?", new Object[]{name},
                new BeanPropertyRowMapper<>(Человек.class)).stream().findAny();
    }
}
