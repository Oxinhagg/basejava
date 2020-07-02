package com.oxinhagg.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super(String.format("Резюме %s не существует!", uuid), uuid);
    }
}
