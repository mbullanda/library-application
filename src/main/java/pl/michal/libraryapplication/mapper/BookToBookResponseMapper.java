package pl.michal.libraryapplication.mapper;

import org.springframework.stereotype.Component;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.web.rest.dto.BookResponse;

@Component
public class BookToBookResponseMapper {

    public BookResponse toResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .numberOfPages(book.getNumberOfPages())
                .authorId(book.getAuthor().getId())
                .build();
    }
}
