package pl.michal.libraryapplication.mapper;

import org.springframework.stereotype.Component;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.repository.AuthorRepository;
import pl.michal.libraryapplication.web.rest.dto.CreateBookRequest;

import java.util.NoSuchElementException;

@Component
public class CreateBookRequestToBookMapper {

    private AuthorRepository repository;

    public Book toDomain(CreateBookRequest request){
        return Book.builder()
                .title(request.getTitle())
                .numberOfPages(request.getNumberOfPages())
                .isbn(request.getIsbn())
                .build();
    }
}
