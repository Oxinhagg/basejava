package com.oxinhagg.webapp.storage;/*
  Array based storage for Resumes
 */

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
        if (index == -1) {
            System.out.println(String.format("Резюме с uuid = %s - не существует!", uuid));
            return null;
        }
        return storage[index];
    }

    public void save(Resume resume){
        int index = getIndex(resume);
        if (arrSize == STORAGE_LIMIT) {
            System.out.println("Массив резюме переполнен!");
            return;
        }
        if (index >= 0){
            System.out.println(String.format("Резюме с uuid = %s - уже существует!", resume.getUuid()));
            return;
        }
        insertObject(resume, index);
        arrSize++;
    }

    public void update(Resume resume){
        int index = getIndex(resume);
        if (index == -1){
            System.out.println(String.format("Резюме с uuid = %s - не существует!", resume.getUuid()));
            return;
        }
        storage[index] = resume;
    }

    public void delete(String uuid){
        int index = getIndex(uuid);
        if (index < 0){
            System.out.println(String.format("Резюме с uuid = %s - не существует!", uuid));
            return;
        }
        arrSize--;
        deleteObject(index);
    }

    protected abstract void insertObject(Resume r, int index);

    protected abstract void deleteObject(int index);

    protected abstract int getIndex(String uuid);

    protected abstract int getIndex(Resume resume);
}
