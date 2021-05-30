package pl.michal.libraryapplication.exception;

public class MissingBookByIsbnException extends RuntimeException {
    public MissingBookByIsbnException(String isbn) {
    }
}
