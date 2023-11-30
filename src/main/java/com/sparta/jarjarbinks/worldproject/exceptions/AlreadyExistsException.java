package com.sparta.jarjarbinks.worldproject.exceptions;

public class AlreadyExistsException extends Exception {

    public AlreadyExistsException(String idname) {
        super("Conflicting ID for: " + idname);
    }

}
