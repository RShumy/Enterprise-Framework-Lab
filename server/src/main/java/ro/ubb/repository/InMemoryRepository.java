package ro.ubb.repository;

import ro.ubb.domain.BaseEntity;
import ro.ubb.validators.ValidatorException;

import java.util.*;

public class InMemoryRepository<ID ,Type extends BaseEntity<ID>> implements Repository<ID ,Type> {

    SortedMap<ID, Type> storage = new TreeMap<>();

    @Override
    public Optional<Type> save(Type entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        this.storage.putIfAbsent(entity.getIdEntity(), entity);
        return Optional.ofNullable(storage.putIfAbsent(entity.getIdEntity(), entity));
    }

    @Override
    public Optional<Type> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Iterable<Type> findAll() {
        return new ArrayList<Type>((Collection<? extends Type>) this.storage.values());
    }


    @Override
    public Optional<Type> update(Type entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }
        return Optional.ofNullable(storage.put(entity.getIdEntity(), entity));
    }

    @Override
    public Optional<Type> delete(ID id) throws ValidatorException {
        if (id == null)
            throw new IllegalArgumentException("Entity must not be null");
        return Optional.ofNullable(storage.remove(id));
    }
}
