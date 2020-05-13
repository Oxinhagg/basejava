/*
  Array based storage for Resumes
 */

import java.util.Arrays;
import java.util.Objects;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage)
            if (resume != null && uuid.equals(resume.uuid)) {
                return resume;
            }
        return null;
    }

    void delete(String uuid) {
        for (Resume resume : storage) {
            if (resume != null && uuid.equals(resume.uuid)) {
                resume.uuid = null;
                break;
            }
        }
    }

    private Resume[] getArrNonNull() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return getArrNonNull();
    }

    int size() {
        return getArrNonNull().length;
    }
}
