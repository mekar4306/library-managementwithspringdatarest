package com.karadayi.librarymanagement.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.karadayi.librarymanagement.entities.Book;

public interface BookService {
	
	Page<Book> getAllBooksByPaging(Pageable pageable);
	
	List<Book> getAllBooks();
	
	List<Book> getBooksByName(String name);
	
	Page<Book> getBooksByNamePaging(String name,Pageable pageable);
	
	Book getBookById(Long id);
	
	Book saveBook(Book book);
	
	Book updateBook(Book book);
	
	Book  updateBookByPartial(Book books);
	
	void deleteBookById(Long id);
	

}
