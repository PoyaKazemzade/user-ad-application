package se.yrgo.listingservice.rest;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Resource not found: " + super.getMessage();
    }
}
