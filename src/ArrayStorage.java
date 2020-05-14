/*
  Array based storage for Resumes
 */

import java.util.Arrays;
import java.util.Objects;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int arrSize = 0;

    void clear() {
        for (int i = 0; i < arrSize; i++) {
            storage[i] = null;
        }
        arrSize = 0;
    }

    void save(Resume r) {
        storage[arrSize] = r;
        arrSize++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < arrSize; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int lastResume;
        lastResume = arrSize - 1;
        for (int i = 0; i < arrSize; i++) {
            if (uuid.equals(storage[i].uuid)) {
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    int size() {
        return arrSize;
    }
}
