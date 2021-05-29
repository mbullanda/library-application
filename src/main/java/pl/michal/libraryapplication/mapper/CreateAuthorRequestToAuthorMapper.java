package pl.michal.libraryapplication.mapper;

import org.springframework.stereotype.Component;
import pl.michal.libraryapplication.model.Author;
import pl.michal.libraryapplication.web.rest.dto.CreateAuthorRequest;

@Component
public class CreateAuthorRequestToAuthorMapper {
    public Author toDomain(CreateAuthorRequest request) {
        return Author.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .build();
    }
}
