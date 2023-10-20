package com.rybindev.scoreboard.repository;

import com.rybindev.scoreboard.entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class PlayerRepository extends RepositoryBase<Integer, Player> {

    public PlayerRepository(EntityManager entityManager) {
        super(Player.class, entityManager);
    }

    @Transactional
    public Optional<Player> findByName(String name) {
        return getEntityManager()
                .createQuery("select p from Player p where p.name =:name", Player.class)
                .setParameter("name", name)
                .getResultList().stream().findFirst();

    }
}
