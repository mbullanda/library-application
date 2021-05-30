package pl.michal.libraryapplication.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pl.michal.libraryapplication.model.Author;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.repository.AuthorRepository;
import pl.michal.libraryapplication.repository.BookRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void getBookByIdReturnsBook() throws Exception {
        //given
        Book testBook = Book.builder()
                .title("TestBook")
                .isbn("000-000-000")
                .numberOfPages(200)
                .author(authorRepository.save(new Author()))
                .build();
        bookRepository.save(testBook);

        //when
        System.out.println("testBook = " + testBook);

        //then
        mockMvc.perform(get("/api/v1/book/" + testBook.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"id\":3,\"title\":\"TestBook\"," +
                        "\"isbn\":\"000-000-000\",\"numberOfPages\":200,\"authorId\":2}"))
                .andExpect(header().string("Content-Type", "application/json"));
    }

}