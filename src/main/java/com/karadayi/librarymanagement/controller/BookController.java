package com.karadayi.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karadayi.librarymanagement.entities.Book;
import com.karadayi.librarymanagement.exceptions.BookNotFoundException;
import com.karadayi.librarymanagement.services.BookService;

@RepositoryRestController   //This Controller annotation is used with Spring Data Rest instead of @RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;

	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/books") public
	 * ResponseEntity<List<Book>> findAllBooks() {
	 * 
	 * List<Book> books = bookService.getAllBooks();
	 * 
	 * return ResponseEntity.ok(books); }
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/books")
	public ResponseEntity<Page<Book>> findAllBooksByPaging(Pageable pageable) {

		Page<Book> allBooksByPaging = bookService.getAllBooksByPaging(pageable);

		return ResponseEntity.ok(allBooksByPaging);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/books/{id}")
	public ResponseEntity<?> findBookById(@PathVariable("id") Long id) {

		try {

			Book book = bookService.getBookById(id);
			return ResponseEntity.ok(book);

		} catch (BookNotFoundException e) {

			return ResponseEntity.notFound().build();
		}

	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/booksByName")
	public ResponseEntity<Page<Book>> findBookByName(@RequestParam("name") String name,Pageable pageable) {

		try {

			//List<Book> books = bookService.getBooksByName(name);
			Page<Book> books = bookService.getBooksByNamePaging(name, pageable);
			
			return ResponseEntity.ok(books);

		} catch (BookNotFoundException e) {

			return ResponseEntity.notFound().build();
		}

	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/librarybook")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {

		Book saveBook = bookService.saveBook(book);

		return ResponseEntity.ok(saveBook);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/librarybook/{id}")
	public ResponseEntity<Book> updateOwner(@PathVariable("id") Long id, @RequestBody Book BookRequest) {
		try {
			Book savedBook = bookService.getBookById(id);
			savedBook.setAuthor(BookRequest.getAuthor());
			savedBook.setCreatedAt(BookRequest.getCreatedAt());
			savedBook.setName(BookRequest.getName());
			savedBook.setPageCount(BookRequest.getPageCount());
			savedBook.setPublished(BookRequest.isPublished());
			savedBook.setPublishedAt(BookRequest.getPublishedAt());

			savedBook.setUserUuid(BookRequest.getUserUuid());

			Book saveBook = bookService.saveBook(savedBook);

			return ResponseEntity.ok(saveBook);

		} catch (BookNotFoundException ex) {

			return ResponseEntity.notFound().build();
		}

	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/librarybook/{id}")
	public ResponseEntity<Book> updateOwnerPartial(@PathVariable("id") Long id, @RequestBody Book BookRequest) {
		try {
			Book savedBook = bookService.getBookById(id);
			savedBook.setPageCount(BookRequest.getPageCount());
			savedBook.setPublishedAt(BookRequest.getPublishedAt());
			Book saveBook = bookService.saveBook(savedBook);

			return ResponseEntity.ok(saveBook);

		} catch (BookNotFoundException ex) {

			return ResponseEntity.notFound().build();
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/librarybook/{id}")
	public ResponseEntity deleteBookById(@PathVariable("id") Long id) {

		try {

			Book book = bookService.getBookById(id);

			bookService.deleteBookById(id);

			return ResponseEntity.ok(book.getName() + " deleted succesfully ");

		} catch (BookNotFoundException ex) {

			throw ex;
		}

	}

}
