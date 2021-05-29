package pl.michal.libraryapplication.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.michal.libraryapplication.mapper.AuthorToAuthorResponseMapper;
import pl.michal.libraryapplication.model.Author;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.repository.AuthorRepository;
import pl.michal.libraryapplication.service.AuthorService;
import pl.michal.libraryapplication.mapper.CreateAuthorRequestToAuthorMapper;
import pl.michal.libraryapplication.service.BookService;
import pl.michal.libraryapplication.web.rest.dto.AuthorResponse;
import pl.michal.libraryapplication.web.rest.dto.CreateAuthorRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/author")
@AllArgsConstructor
public class AuthorRestController {

    private AuthorService authorService;
    private BookService bookService;
    private AuthorToAuthorResponseMapper authorMapper;
    private CreateAuthorRequestToAuthorMapper createAuthorRequestToAuthorMapper;

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody @Valid CreateAuthorRequest request, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

            return ResponseEntity.badRequest().body(errors);
        }
        Author authorToCreate = createAuthorRequestToAuthorMapper.toDomain(request);
        return new ResponseEntity<>(authorMapper.toResponse(authorService.createAuthor(authorToCreate)), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorResponse> getAllAuthors(){
        return authorService.getAllAuthors()
                .stream()
                .map(authorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorResponse findById(@PathVariable Long id){
        return authorMapper.toResponse(authorService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        Author author = authorService.findById(id);
        Set<Book> books = author.getBooks();
        books.stream()
                .map(book -> book.getId())
                .forEach(bookService::deleteById);
        authorService.deleteById(id);
    }





}
