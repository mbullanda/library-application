package pl.michal.libraryapplication.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookResponse {

    private Long id;
    private String title;
    private String isbn;
    private int numberOfPages;
}
