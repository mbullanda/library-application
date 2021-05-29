package pl.michal.libraryapplication.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michal.libraryapplication.model.Author;
import pl.michal.libraryapplication.repository.AuthorRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author createAuthor(Author authorToCreate) {
        return authorRepository.save(authorToCreate);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findById(long authorId) {
        return authorRepository.findById(authorId).orElseThrow(NoSuchElementException::new);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
