package dev.johnson.exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound (int eId){
        super("The resource with id "+eId+" was not found.");
    }
}
