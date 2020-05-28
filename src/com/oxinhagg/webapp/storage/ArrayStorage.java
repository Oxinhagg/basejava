package com.oxinhagg.webapp.storage;/*
  Array based storage for Resumes
 */

import com.oxinhagg.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int arrSize = 0;

    public void clear() {
        for (int i = 0; i < arrSize; i++) {
            storage[i] = null;
        }
        arrSize = 0;

    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        if (uuid_check_exist(uuid)){
            return;
        }
        storage[arrSize] = r;
        arrSize++;
    }

    public Resume get(String uuid) {
        Resume r = get_object(uuid);
        if (r == null) {
            System.out.println(String.format("Резюме с uuid = %s - не существует!", uuid));
            return null;
        }
        return r;
    }

    public void update(Resume resume, String newUuid){
        if (uuid_check_exist(newUuid) || resume == null){
            return;
        }
        resume.setUuid(newUuid);
    }

    public void delete(String uuid) {
        int lastResume = arrSize - 1;

        if (uuid_check_not_exist(uuid)){
            return;
        }
        for (int i = 0; i < arrSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                if (i < lastResume) {
                    storage[i] = storage[lastResume];
                    storage[lastResume] = null;
                } else {
                    storage[i] = null;
                }
                arrSize--;
                break;
            }
        }
    }

    private Resume get_object(String uuid){
        for (int i = 0; i < arrSize; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }

    private boolean uuid_check_exist(String uuid){
        if (get_object(uuid) != null){
            System.out.println(String.format("Резюме с uuid = %s - уже существует!", uuid));
            return true;
        }
        return false;
    }
    private boolean uuid_check_not_exist(String uuid){
        if (get_object(uuid) == null){
            System.out.println(String.format("Резюме с uuid = %s - не существует!", uuid));
            return true;
        }
        return false;
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    public int size() {
        return arrSize;
    }
}
