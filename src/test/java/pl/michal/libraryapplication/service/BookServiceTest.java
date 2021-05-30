package pl.michal.libraryapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.michal.libraryapplication.exception.MissingBookException;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void init(){
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testGetAll(){
        //given
        Book book = Book.builder()
                .id(12L)
                .build();
        when(bookRepository.findAll()).thenReturn(List.of(book));

        //when
        List<Book> books = bookService.getAllBooks();

        //then
        assertThat(books).isEqualTo(List.of(book));
        verify(bookRepository).findAll();
    }

    @Test
    void finaById_bookPresent(){
        //given
        Book book = Book.builder()
                .id(12L)
                .build();
        when(bookRepository.findById(12L)).thenReturn(Optional.of(book));

        //when
        Book foundBook = bookService.findById(12L);

        //then
        assertThat(foundBook).isEqualTo(book);
        verify(bookRepository).findById(12L);
    }

    @Test
    void findById_bookMissing(){
        //given
        Book book = Book.builder()
                .id(12L)
                .build();
        when(bookRepository.findById(12L)).thenReturn(Optional.empty());

        //when & then
        assertThatThrownBy(() -> bookService.findById(12L)).isExactlyInstanceOf(MissingBookException.class).hasMessage("Book with id 12 missing!");
    }

    @Test
    void test_createBook(){
        //given
        Book book = Book.builder()
                .id(12L)
                .build();
        when(bookRepository.save(book)).thenReturn(book);
        //when
        Book createdBook = bookService.createBook(book);

        //then
        assertThat(createdBook).isEqualTo(book);
        verify(bookRepository).save(book);
    }

    @Test
    void test_deleteBook(){
        //given
        Book book = Book.builder()
                .id(12L)
                .build();

        //when
        bookService.deleteById(12L);

        //then
        verify(bookRepository).deleteById(12L);
    }

}