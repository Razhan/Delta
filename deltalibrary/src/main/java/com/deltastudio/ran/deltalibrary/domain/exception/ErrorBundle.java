package com.deltastudio.ran.deltalibrary.domain.exception;

public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}
