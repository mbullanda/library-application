package pl.michal.libraryapplication.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.michal.libraryapplication.model.Author;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{3}")
    private String isbn;
    @Min(value = 20, message = "At least 20 pages")
    private int numberOfPages;
    private long authorId;
}
