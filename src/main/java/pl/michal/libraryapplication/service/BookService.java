package pl.michal.libraryapplication.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
}
