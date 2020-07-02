package com.oxinhagg.webapp.storage;

import com.oxinhagg.webapp.exception.ExistsStorageException;
import com.oxinhagg.webapp.exception.NotExistStorageException;
import com.oxinhagg.webapp.exception.StorageException;
import com.oxinhagg.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final int STORAGE_LIMIT = 100000;



    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] list = storage.getAll();
        assertEquals(3, list.length);
    }

    @Test
    public void get() {
        storage.get(UUID_1);
        storage.get(UUID_2);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(new Resume("dummy"));
        storage.get("dummy");
    }

    @Test(expected = ExistsStorageException.class)
    public void saveExist(){ storage.save(new Resume(UUID_1)); }

    @Test
    public void saveOverflow() {
        for (int i = 0; i < STORAGE_LIMIT-3; i++){
            storage.save(new Resume(String.valueOf(i)));
        }
        try { storage.save(new Resume()); } catch (StorageException ignore){ }
    }

    @Test
    public void update() {
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist(){ storage.delete("dummy"); }
}