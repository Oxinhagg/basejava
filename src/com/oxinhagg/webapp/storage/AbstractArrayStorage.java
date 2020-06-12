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
        if (index > -1) {
            return storage[index];
        }
        System.out.println(String.format("Резюме с uuid = %s - не существует!", uuid));
        return null;
    }

    public void save(Resume resume){
        int index = getIndex(resume.getUuid());
        if (arrSize == STORAGE_LIMIT) {
            System.out.println("Массив резюме переполнен!");
            return;
        }
        if (index > -1){
            System.out.println(String.format("Резюме с uuid = %s - уже существует!", resume.getUuid()));
            return;
        }
        insertElement(resume, index);
        arrSize++;
    }

    public void update(Resume resume){
        int index = getIndex(resume.getUuid());
        if (index > -1){
            storage[index] = resume;
            return;
        }
        System.out.println(String.format("Резюме с uuid = %s - не существует!", resume.getUuid()));
    }

    public void delete(String uuid){
        int index = getIndex(uuid);
        if (index > -1){
            arrSize--;
            deleteElement(index);
            storage[arrSize] = null;
            return;
        }
        System.out.println(String.format("Резюме с uuid = %s - не существует!", uuid));
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);
}
