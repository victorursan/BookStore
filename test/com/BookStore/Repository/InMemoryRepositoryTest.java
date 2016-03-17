package com.BookStore.Repository;

import com.BookStore.Model.BaseEntity;
import com.BookStore.Model.Validators.ValidatorException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by victor on 3/17/16.
 */
public class InMemoryRepositoryTest {
    private InMemoryRepository<BaseEntity<Integer>> repo;
    @Before
    public void setUp() throws Exception {
        repo = new InMemoryRepository<>();
        repo.add(new BaseEntity<>(0));
        repo.add(new BaseEntity<>(1));
        repo.add(new BaseEntity<>(2));
        repo.add(new BaseEntity<>(3));
        repo.add(new BaseEntity<>(4));
        repo.add(new BaseEntity<>(5));
    }

    @Test
    public void add() throws Exception {
        repo.add(new BaseEntity<>(6));
        assertSame(repo.getAll().size(), 7);
    }

    @Test
    public void get() throws Exception {
        assertSame(repo.get(1).isPresent(), true);
        repo.get(1).ifPresent(v -> assertSame(v.getId(), 1));
    }

    @Test
    public void getAll() throws Exception {
        assertSame(repo.getAll().size(), 6);
    }

    @Test
    public void update() throws Exception {
        repo.get(0).ifPresent(oldValue -> {
            try {
                this.repo.update(new BaseEntity<>(0));
                assertNotSame(oldValue, repo.get(0));
            } catch (ValidatorException e) {
                assertTrue(false);
            }

        });

    }

    @Test
    public void delete() throws Exception {
        repo.delete(0);
        assertSame(repo.getAll().size(), 5);
    }
}