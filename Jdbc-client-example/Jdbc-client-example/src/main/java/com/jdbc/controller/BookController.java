package com.jdbc.controller;

import com.jdbc.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private JdbcClient jdbcClient;

    @PostMapping
    public String addBook(@RequestBody Book book) {
        jdbcClient.sql("INSERT INTO Book(id,name,title) values(?,?,?)")
                .param(List.of(book.getId(), book.getName(), book.getId()))
                .update();
        return "Book Added to the System ?";
    }
    @GetMapping
    public List<Book> getAllBook() {
        return jdbcClient.sql("SELECT id,name,title from Book")
                .query(Book.class)
                .list();
    }
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable int id) {
        return jdbcClient.sql("SELECT id,name,title from Book where id=:id")
                .param("id", id)
                .query(Book.class).optional();
    }
    @PutMapping("/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book book) {
        jdbcClient.sql("update Book set title=?,name=? where id=?")
                .param(List.of(book.getName(), book.getTitle(), id))
                .update();
        return "book modified in system";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        jdbcClient.sql("delete from Book where id=:id")
                .param("id", id)
                .update();
        return "book record removed !";
    }
}

