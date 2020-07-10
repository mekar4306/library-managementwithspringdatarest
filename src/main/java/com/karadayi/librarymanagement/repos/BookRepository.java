package com.karadayi.librarymanagement.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.karadayi.librarymanagement.entities.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> ,JpaRepository<Book, Long>{

	
			List<Book> findByName(@Param("name") String name);
			
			Page<Book> findByName(@Param("name") String name,Pageable pageable);
}
