package com.journaldev.elasticsearch.controller;

import com.journaldev.elasticsearch.bean.Book;
import com.journaldev.elasticsearch.dao.BookDao;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BookController {

    private BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @PostMapping("/books")
    public Book insertBook(@RequestBody Book book) throws Exception{
        return bookDao.insertBook(book);
    }

    @GetMapping("/books/{id}")
    public Map<String, Object> getBookById(@PathVariable String id){
        return bookDao.getBookById(id);
    }

    @PutMapping("/books/{id}")
    public Map<String, Object> updateBookById(@RequestBody Book book, @PathVariable String id){
        return bookDao.updateBookById(id, book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookById(@PathVariable String id){
         bookDao.deleteBookById(id);
    }
    
    @GetMapping("/hello")
	public String greeting() {
		return "Hello World 2";
	}

	@GetMapping("/")
	public String greetingHello() {
		
		
		
		
		return "Hello World";
	}

}
