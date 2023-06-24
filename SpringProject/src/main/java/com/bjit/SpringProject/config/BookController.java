package com.bjit.SpringProject.config;

import com.bjit.SpringProject.entity.BookEntity;
import com.bjit.SpringProject.model.BookRequestModel;
import com.bjit.SpringProject.repository.BookRepository;
import com.bjit.SpringProject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    Logger logger = Logger.getLogger("BookController");

    @GetMapping("price/{bookId}")
    public Long price(@PathVariable String bookId) {
        BookEntity book = bookService.getBookById(Long.parseLong(bookId));
        Long price = book.getPrice();
        logger.info("Book title: " + book.getTitle() + ", Price: " + price);
        return price;
    }

    @GetMapping("name/{bookId}")
    public String name(@PathVariable String bookId) {
        BookEntity book = bookService.getBookById(Long.parseLong(bookId));
        if(book==null){
            logger.info("No book found with this ID!");
            return "No book found with this ID!";
        }
        else {
            String name = book.getTitle();
            logger.info("Book title: " + book.getTitle() + ", Price: " + book.getPrice());
            return name;
        }
    }

    @GetMapping("inventory/{bookId}-{quantity}")
    public Long inventory(@PathVariable String bookId, @PathVariable String quantity) {
        BookEntity book = bookService.getBookById(Long.parseLong(bookId));
        Long stock = book.getInventory();
        Long quant = Long.parseLong(quantity);
        if(stock>=quant) {
            stock = stock - quant;
            book.setInventory(stock);
            BookEntity savedBook = bookRepository.save(book);
        }
        logger.info("Book title: " + book.getTitle() + ", Inventory: " + stock);
        return stock;
    }

    @GetMapping("/id/{bookId}")
    public ResponseEntity<Object> getBook(@PathVariable String bookId) {
        return bookService.getBook(Long.parseLong(bookId));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> newBook(@RequestBody BookRequestModel book) {
        return bookService.createBook(book);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable String id, @RequestBody BookRequestModel book) {
        return bookService.updateBook(Long.parseLong(id), book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable String id) {
        return bookService.deleteBook(Long.parseLong(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<BookEntity>> allBook() {
        return bookService.getAllBooks();
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<Iterable<BookEntity>> getBookByAuthor(@PathVariable String author) {
        return bookService.getBookByAuthor(author);
    }

    @GetMapping("/get/{title}")
    public ResponseEntity<Iterable<BookEntity>> getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

}
