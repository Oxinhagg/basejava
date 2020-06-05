package com.oxinhagg.webapp.storage;

import com.oxinhagg.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = Arrays.binarySearch(storage, 0, arrSize, r);
        if (arrSize == STORAGE_LIMIT) {
            System.out.println("Массив резюме переполнен!");
        }
        if (index >= 0) {
            System.out.println(String.format("Резюме с uuid = %s - уже существует!", r.getUuid()));
            return;
        } else {
            index = -index - 1;
        }
        System.arraycopy(storage, index, storage, index + 1, arrSize - index);
        storage[index] = r;
        arrSize++;
    }

    @Override
    public void update(Resume r) {
        int pos = Arrays.binarySearch(storage, 0, arrSize, r);
        if (pos < 0) {
            System.out.println(String.format("Резюме с uuid = %s - не существует!", r.getUuid()));
            return;
        }
        storage[pos] = r;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println(String.format("Резюме с uuid = %s - не существует!", uuid));
            return;
        }
        System.arraycopy(storage, index+1, storage, index, arrSize - index);
        arrSize--;
    }


    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, arrSize, searchKey);
    }

}
