package pl.michal.libraryapplication.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.michal.libraryapplication.model.Book;
import pl.michal.libraryapplication.repository.BookRepository;

import java.util.EventListener;

@Component
@RequiredArgsConstructor
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final BookRepository bookRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Book book1 = Book.builder()
                .title("Kolor magii")
                .isbn("123-123-123")
                .numberOfPages(327)
                .build();

        Book book2 = Book.builder()
                .title("Blask fantastyczny")
                .isbn("123-456-789")
                .numberOfPages(259)
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
