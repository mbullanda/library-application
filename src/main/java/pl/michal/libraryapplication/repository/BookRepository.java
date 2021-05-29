package pl.michal.libraryapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.libraryapplication.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
