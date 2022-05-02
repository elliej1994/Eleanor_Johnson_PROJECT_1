package dev.johnson.exceptions;

public class CannotEditException extends RuntimeException {
    public CannotEditException (String status){
        super("That record cannot be edited");


    }

}
