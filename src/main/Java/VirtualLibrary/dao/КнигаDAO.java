package VirtualLibrary.dao;

import VirtualLibrary.models.Книга;
import VirtualLibrary.models.Человек;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class КнигаDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public КнигаDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Книга> showBooks() {
        return jdbcTemplate.query("select * from book;", new BeanPropertyRowMapper<>(Книга.class));
    }

    public Книга showBook(int id) {
        return jdbcTemplate.query("select * from book where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Книга.class)).stream().findAny().orElse(null);
    }

    public void create(Книга книга) {
        jdbcTemplate.update("insert into book(title, authorName, publicationYear) values(?,?,?);",
                книга.getTitle(), книга.getAuthorName(),книга.getPublicationYear());
    }

    public void edit(int id, Книга updatedКнига) {
        jdbcTemplate.update("update book set title=?,authorName=?, publicationYear=? where id=?;",
                updatedКнига.getTitle(), updatedКнига.getAuthorName(), updatedКнига.getPublicationYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id=?;", id);
    }

    public void giveBook(int bookId, int personId) {
        jdbcTemplate.update("update book set person_id=? where id=?;", personId, bookId);
    }

    public void receiveBook(int id) {
        jdbcTemplate.update("update book set person_id=null where id=?;", id);
    }

    public Человек currentOwner (int id) {
        return jdbcTemplate.query("select * from person where id=(select person_id from book where id = ?);",
                new Object[]{id}, new BeanPropertyRowMapper<>(Человек.class)).stream().findAny().orElse(null);
    }
}
