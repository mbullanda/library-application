package pl.michal.libraryapplication.mapper;

import org.springframework.stereotype.Component;
import pl.michal.libraryapplication.model.Author;
import pl.michal.libraryapplication.web.rest.dto.AuthorResponse;

import java.util.stream.Collectors;

@Component
public class AuthorToAuthorResponseMapper {

    public AuthorResponse toResponse(Author author){
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .lastName(author.getLastName())
                .booksId(author.getBooks().stream()
                    .map(book -> book.getId())
                    .collect(Collectors.toSet()))
                .build();
    }

}
