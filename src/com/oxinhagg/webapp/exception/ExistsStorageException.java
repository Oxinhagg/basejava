package com.oxinhagg.webapp.exception;

public class ExistsStorageException extends StorageException {
    public ExistsStorageException(String uuid) {
        super(String.format("Резюме %s уже существует!", uuid), uuid);
    }
}
