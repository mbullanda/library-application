package pl.michal.libraryapplication.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.michal.libraryapplication.mapper.BookToBookResponseMapper;
import pl.michal.libraryapplication.mapper.CreateBookRequestToBookMapper;
import pl.michal.libraryapplication.service.BookService;

import pl.michal.libraryapplication.web.rest.dto.BookResponse;
import pl.michal.libraryapplication.web.rest.dto.CreateBookRequest;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/book")
@AllArgsConstructor
public class BookRestController {

    private final BookService bookService;
    private final BookToBookResponseMapper bookMapper;
    private final CreateBookRequestToBookMapper createBookRequestToBookMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks()
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
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody CreateBookRequest request){
        return bookMapper.toResponse(bookService.createBook(createBookRequestToBookMapper.toDomain(request)));
    }


}
