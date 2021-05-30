package pl.michal.libraryapplication.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.michal.libraryapplication.mapper.BookToBookResponseMapper;
import pl.michal.libraryapplication.mapper.CreateBookRequestToBookMapper;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.service.AuthorService;
import pl.michal.libraryapplication.service.BookService;

import pl.michal.libraryapplication.web.rest.dto.BookResponse;
import pl.michal.libraryapplication.web.rest.dto.CreateBookRequest;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/book")
@AllArgsConstructor
public class BookRestController {

    private final BookService bookService;
    private final BookToBookResponseMapper bookMapper;
    private final CreateBookRequestToBookMapper createBookRequestToBookMapper;
    private final AuthorService authorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks()
                .stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/author/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getBooksByAuthorId(@PathVariable Long authorId){
        return bookService.findByAuthorId(authorId)
                .stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse findById(@PathVariable Long id){
        return bookMapper.toResponse(bookService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        bookService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody @Valid CreateBookRequest request, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

            return ResponseEntity.badRequest().body(errors);
        }
        Book bookToCreate = createBookRequestToBookMapper.toDomain(request);
        bookToCreate.setAuthor(authorService.findById(request.getAuthorId()));
        bookToCreate.getAuthor().getBooks().add(bookToCreate);
        return new ResponseEntity<>(bookMapper.toResponse(bookService.createBook(bookToCreate)), HttpStatus.CREATED);
    }

    @GetMapping("/isbn/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse findByIsbn(@PathVariable String isbn){
        return bookMapper.toResponse(bookService.findByIsbn(isbn));
    }

    @GetMapping("/isbn")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse findByIsbnByQueryParams(@RequestParam(value = "isbn", required = true) String isbn){
        return bookMapper.toResponse(bookService.findByIsbn(isbn));
    }




}
