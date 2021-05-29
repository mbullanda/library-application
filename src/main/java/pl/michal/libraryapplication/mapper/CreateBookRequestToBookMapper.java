package pl.michal.libraryapplication.mapper;

import org.springframework.stereotype.Component;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.web.rest.dto.CreateBookRequest;

@Component
public class CreateBookRequestToBookMapper {
    public Book toDomain(CreateBookRequest request){
        return Book.builder()
                .title(request.getTitle())
                .numberOfPages(request.getNumberOfPages())
                .isbn(request.getIsbn())
                .build();
    }
}
