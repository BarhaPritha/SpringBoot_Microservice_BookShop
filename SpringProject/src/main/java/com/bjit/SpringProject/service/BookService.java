package com.bjit.SpringProject.service;

import com.bjit.SpringProject.entity.BookEntity;
import com.bjit.SpringProject.model.BookRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    ResponseEntity<Object> getBook(Long id);
    BookEntity getBookById(Long id);
    ResponseEntity<Iterable<BookEntity>> getBookByAuthor(String author);
    ResponseEntity<Iterable<BookEntity>> getBookByTitle(String title);
    ResponseEntity<Iterable<BookEntity>> getAllBooks();
    ResponseEntity<Object> createBook(BookRequestModel requestModel);
    ResponseEntity<Object> updateBook(Long id, BookRequestModel requestModel);
    ResponseEntity<Object> deleteBook(Long id);

}
