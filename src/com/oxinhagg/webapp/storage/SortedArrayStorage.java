package com.oxinhagg.webapp.storage;

import com.oxinhagg.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertObject(Resume r, int index) {
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, arrSize - index);
        storage[index] = r;
    }

    @Override
    protected void deleteObject(int index) {
        System.arraycopy(storage, index+1, storage, index, arrSize - index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return getIndex(searchKey);
    }

    @Override
    protected int getIndex(Resume searchKey) {
        return Arrays.binarySearch(storage, 0, arrSize, searchKey);
    }
}
