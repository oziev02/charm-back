package ru.oziev.charm.back.dao;

import ru.oziev.charm.back.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileDao {
    private final ConcurrentHashMap<Long, Profile> storage;
    private final AtomicLong idStorage;

    public ProfileDao() {
        this(new ConcurrentHashMap<>(), new AtomicLong());
    }

    public ProfileDao(ConcurrentHashMap<Long, Profile> storage, AtomicLong idStorage) {
        this.storage = storage;
        this.idStorage = idStorage;
    }

    public Profile save(Profile profile) {
        profile.setId(idStorage.incrementAndGet());
        storage.put(profile.getId(), profile);
        System.out.println(storage.values());
        return profile;
    }

    public Optional<Profile> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Profile> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(Profile profile) {
        Long id = profile.getId();
        if(id == null) return;
        storage.put(id, profile);
    }

    public boolean delete(long id) {
        return storage.remove(id) != null;
    }

}
