package pl.michal.libraryapplication.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
}
