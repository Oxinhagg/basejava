package com.oxinhagg.webapp.storage;/*
  Array based storage for Resumes
 */

import com.oxinhagg.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage{

    @Override
    protected void insertObject(Resume r, int index) {
        storage[arrSize] = r;
    }

    @Override
    protected void deleteObject(int index) {
        storage[index] = storage[arrSize];
        storage[arrSize] = null;
    }

    @Override
    protected int getIndex(String uuid){
        for (int i = 0; i < arrSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected int getIndex(Resume searchKey){
        return getIndex(searchKey.getUuid());
    }
}
