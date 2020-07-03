package com.oxinhagg.webapp.storage;/*
  Array based storage for Resumes
 */

import com.oxinhagg.webapp.exception.ExistsStorageException;
import com.oxinhagg.webapp.exception.NotExistStorageException;
import com.oxinhagg.webapp.exception.StorageException;
import com.oxinhagg.webapp.model.Resume;

import java.util.Arrays;


public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int arrSize = 0;

    public int size() {
        return arrSize;
    }

    public void clear() {
        Arrays.fill(storage, 0, arrSize, null);
        arrSize = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, arrSize);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (arrSize == STORAGE_LIMIT) {
            throw new StorageException(resume.getUuid(), "Массив переполнен!");
        }
        if (index > -1) {
            throw new ExistsStorageException(resume.getUuid());
        }
        insertElement(resume, index);
        arrSize++;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            arrSize--;
            deleteElement(index);
            storage[arrSize] = null;
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);
}
