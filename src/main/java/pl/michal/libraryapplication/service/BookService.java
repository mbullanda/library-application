package pl.michal.libraryapplication.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.michal.libraryapplication.exception.MissingBookByIsbnException;
import pl.michal.libraryapplication.exception.MissingBookException;
import pl.michal.libraryapplication.mapper.CreateBookRequestToBookMapper;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.repository.BookRepository;
import pl.michal.libraryapplication.web.rest.dto.CreateBookRequest;

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

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(()-> new MissingBookByIsbnException(isbn));
    }

    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthor_Id(authorId);
    }
}
