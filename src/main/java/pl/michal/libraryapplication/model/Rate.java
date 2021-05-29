package pl.michal.libraryapplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Rate {

    @Id
    @GeneratedValue
    private Long id;
}
