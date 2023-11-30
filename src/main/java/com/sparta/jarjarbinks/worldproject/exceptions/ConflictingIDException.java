package com.sparta.jarjarbinks.worldproject.exceptions;

public class ConflictingIDException extends Exception {

    public ConflictingIDException(String idname) {
        super("Conflicting ID for: " + idname);
    }

}
