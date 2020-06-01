package com.oxinhagg.webapp.storage;/*
  Array based storage for Resumes
 */

import com.oxinhagg.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage implements Storage{
    private static final int STORAGE_LIMIT = 10000;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int arrSize = 0;

    public void clear() {
        Arrays.fill(storage, 0, arrSize, null);
        arrSize = 0;

    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);

        if (index != -1){
            System.out.println(String.format("Резюме с uuid = %s - уже существует!", uuid));
            return;
        } else if (arrSize == STORAGE_LIMIT) {
            System.out.println("Массив резюме переполнен!");
            return;
        }


        storage[arrSize] = r;
        arrSize++;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println(String.format("Резюме с uuid = %s - не существует!", uuid));
            return null;
        }

        return storage[index];
    }

    public void update(Resume resume){
        String uuid = resume.getUuid();
        int index = getIndex(uuid);

        if (index == -1){
            System.out.println(String.format("Резюме с uuid = %s - не существует!", uuid));
            return;
        }

        storage[index] = resume;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index == -1){
            System.out.println(String.format("Резюме с uuid = %s - не существует!", uuid));
            return;
        }

        arrSize--;
        storage[index] = storage[arrSize];
        storage[arrSize] = null;
    }

    private int getIndex(String uuid){
        for (int i = 0; i < arrSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        //return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
        return Arrays.copyOfRange(storage, 0, arrSize);
    }

    public int size() {
        return arrSize;
    }
}
