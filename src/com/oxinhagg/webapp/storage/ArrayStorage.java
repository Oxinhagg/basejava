package com.oxinhagg.webapp.storage;/*
  Array based storage for Resumes
 */

import com.oxinhagg.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage{

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

    protected int getIndex(String uuid){
        for (int i = 0; i < arrSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
