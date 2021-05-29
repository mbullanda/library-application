package pl.michal.libraryapplication.exception;

public class MissingBookException extends RuntimeException{

    public MissingBookException(Long id){
        super("Book with id " + id + " missing!");
    }
}
