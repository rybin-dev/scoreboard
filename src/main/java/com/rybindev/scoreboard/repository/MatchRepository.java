package com.rybindev.scoreboard.repository;

import com.rybindev.scoreboard.entity.Match;
import com.rybindev.scoreboard.model.MatchFilter;
import com.rybindev.scoreboard.model.PageMatch;
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
    public PageMatch findAll(MatchFilter filter) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Match> criteria = cb.createQuery(Match.class);

        Root<Match> match = criteria.from(Match.class);

        criteria.select(match)
                .where(getPredicates(cb, match, filter))
                .orderBy(cb.desc(match.get("id")));

        List<Match> resultList = getEntityManager().createQuery(criteria)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getLimit() * (filter.getPage() - 1))
                .getResultList();

        Long count = getCount(cb, filter);

        return new PageMatch(count, filter.getPage(), filter.getLimit(), resultList);
    }

    private Long getCount(CriteriaBuilder cb, MatchFilter filter) {
        CriteriaQuery<Long> criteriaCount = cb.createQuery(Long.class);

        Root<Match> matchCount = criteriaCount.from(Match.class);

        criteriaCount.select(cb.count(matchCount))
                .where(getPredicates(cb, matchCount, filter));

        Long count = getEntityManager().createQuery(criteriaCount).getSingleResult();
        return count;
    }

    private Predicate[] getPredicates(CriteriaBuilder cb, Root<?> root, MatchFilter filter) {
        ArrayList<Predicate> predicates = new ArrayList<>();

        if (filter.getPlayerName() != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("first").get("name"),
                            filter.getPlayerName()),
                    cb.equal(root.get("second").get("name"),
                            filter.getPlayerName()))
            );
        }

        return predicates.toArray(Predicate[]::new);
    }


}
