package com.rybindev.scoreboard.repository;

import com.rybindev.scoreboard.entity.Match;
import com.rybindev.scoreboard.model.MatchFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

public class MatchRepository extends RepositoryBase<Integer, Match> {
    public MatchRepository(EntityManager entityManager) {
        super(Match.class, entityManager);
    }

    @Transactional
    public List<Match> findAll(MatchFilter filter) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Match> criteria = cb.createQuery(Match.class);

        Root<Match> match = criteria.from(Match.class);

        ArrayList<Predicate> predicates = new ArrayList<>();

        if (filter.getPlayerName() != null) {
            predicates.add(cb.or(
                    cb.equal(match.get("first").get("name"), filter.getPlayerName()),
                    cb.equal(match.get("second").get("name"), filter.getPlayerName()))
            );
        }

        criteria.select(match)
                .where(predicates.toArray(Predicate[]::new))
                .orderBy(cb.desc(match.get("id")));

        return getEntityManager().createQuery(criteria)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getLimit() * filter.getPage())
                .getResultList();

    }
}
