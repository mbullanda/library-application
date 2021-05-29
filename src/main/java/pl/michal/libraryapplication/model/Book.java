package pl.michal.libraryapplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Set<Tag> tags;
    private User user;
}
