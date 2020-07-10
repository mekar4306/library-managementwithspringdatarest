package com.karadayi.librarymanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.karadayi.librarymanagement.entities.Book;
import com.karadayi.librarymanagement.repos.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {

		return  bookRepository.findAll();
	}
	
	@Override
	public Page<Book> getAllBooksByPaging(Pageable pageable) {
		
		
		return bookRepository.findAll(pageable);
	}

	
	public List<Book> getBooksByName(String name) {
		
		return bookRepository.findByName(name);
	}

	

	@Override
	public Page<Book> getBooksByNamePaging(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return bookRepository.findByName(name, pageable);
	}

	
	

	@Override
	public Book getBookById(Long id) {

		return bookRepository.findById(id).get();
	}

	@Override
	public Book saveBook(Book book) {

		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book updateBookByPartial(Book book) {
		
		Book savedBook = bookRepository.findById(book.getId()).get();
		
		savedBook.setPageCount(book.getPageCount());
		savedBook.setPublishedAt(book.getPublishedAt());
		
		
		return bookRepository.save(savedBook);
	}

	@Override
	public void deleteBookById(Long id) {

		bookRepository.deleteById(id);

	}

	




	
	

}
