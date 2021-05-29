package pl.michal.libraryapplication.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.michal.libraryapplication.exception.MissingBookException;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.repository.BookRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new MissingBookException(id));
    }
}
