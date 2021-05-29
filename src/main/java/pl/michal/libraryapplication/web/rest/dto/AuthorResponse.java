package pl.michal.libraryapplication.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthorResponse {
    private Long id;
    private String name;
    private String lastName;
    private Set<Long> booksId;
}
