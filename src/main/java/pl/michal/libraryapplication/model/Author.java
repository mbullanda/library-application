package pl.michal.libraryapplication.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "books")
@ToString(exclude = "books")
public class Author {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    @OneToMany
    @Builder.Default
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book){
        books.add(book);
    }
}
