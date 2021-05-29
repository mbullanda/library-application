package pl.michal.libraryapplication.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {
    private String title;
    private String isbn;
    private int numberOfPages;
}
