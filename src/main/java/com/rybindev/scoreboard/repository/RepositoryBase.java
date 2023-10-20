package com.rybindev.scoreboard.repository;

import com.rybindev.scoreboard.entity.BaseEntity;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RepositoryBase<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {
    private final Class<E> clazz;
    @Getter
    private final EntityManager entityManager;

    @Transactional
    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Transactional
    @Override
    public List<E> findAll() {
        CriteriaQuery<E> criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return entityManager.createQuery(criteria)
                .getResultList();
    }
}
