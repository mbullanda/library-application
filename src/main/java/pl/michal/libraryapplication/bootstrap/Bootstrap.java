package pl.michal.libraryapplication.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.michal.libraryapplication.model.Author;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.repository.AuthorRepository;
import pl.michal.libraryapplication.repository.BookRepository;

import java.util.EventListener;

@Component
@RequiredArgsConstructor
@Profile("!prod")
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Author author1 = Author.builder()
                    .name("Madam")
                    .lastName("Ickiewicz")
                    .build();

        Book book1 = Book.builder()
                .title("Kolor magii")
                .isbn("123-123-123")
                .numberOfPages(327)
                .author(author1)
                .build();

        author1.addBook(book1);


        Book book2 = Book.builder()
                .title("Blask fantastyczny")
                .isbn("123-456-789")
                .numberOfPages(259)
                .author(author1)
                .build();

        author1.addBook(book2);

        authorRepository.save(author1);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
