package pl.michal.libraryapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.libraryapplication.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
