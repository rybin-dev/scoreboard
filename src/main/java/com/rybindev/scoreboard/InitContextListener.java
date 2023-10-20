package com.rybindev.scoreboard;

import com.rybindev.scoreboard.interceptor.TransactionInterceptor;
import com.rybindev.scoreboard.mapper.MatchDtoMapper;
import com.rybindev.scoreboard.mapper.MatchMapper;
import com.rybindev.scoreboard.mapper.ScoreboardMapper;
import com.rybindev.scoreboard.repository.MatchRepository;
import com.rybindev.scoreboard.repository.OngoingMatchesRepository;
import com.rybindev.scoreboard.repository.PlayerRepository;
import com.rybindev.scoreboard.service.MatchService;
import com.rybindev.scoreboard.service.OngoingMatchesService;
import com.rybindev.scoreboard.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;

@WebListener
public class InitContextListener implements ServletContextListener {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final TransactionInterceptor transactionInterceptor = new TransactionInterceptor(sessionFactory);
    private final Session session = getCurrentSessionProxy();
    private final PlayerRepository playerRepository = getPlayerRepositoryProxy();
    private final MatchRepository matchRepository = getMatchRepositoryProxy();
    private final MatchMapper matchMapper = new MatchMapper(playerRepository);
    private final OngoingMatchesRepository ongoingMatchesRepository = new OngoingMatchesRepository();
    private final OngoingMatchesService ongoingMatchesService = getOngoingMatchServiceProxy();
    private final ScoreboardMapper scoreboardMapper = new ScoreboardMapper();
    private final MatchDtoMapper matchDtoMapper = new MatchDtoMapper();

    private final MatchService matchService = getMatchServiceProxy();


    @Override
    public void contextInitialized(ServletContextEvent sce) {


        ServletContext context = sce.getServletContext();
        context.setAttribute(PlayerRepository.class.getName(), playerRepository);
        context.setAttribute(MatchRepository.class.getName(), matchRepository);
        context.setAttribute(OngoingMatchesRepository.class.getName(), ongoingMatchesRepository);
        context.setAttribute(OngoingMatchesService.class.getName(), ongoingMatchesService);
        context.setAttribute(ScoreboardMapper.class.getName(), scoreboardMapper);
        context.setAttribute(MatchDtoMapper.class.getName(), matchDtoMapper);
        context.setAttribute(MatchMapper.class.getName(), matchMapper);
        context.setAttribute(MatchService.class.getName(), matchService);


    }

    private MatchRepository getMatchRepositoryProxy() {
        try {
            return new ByteBuddy()
                    .subclass(MatchRepository.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(Thread.currentThread().getContextClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(EntityManager.class)
                    .newInstance(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PlayerRepository getPlayerRepositoryProxy() {
        try {
            return new ByteBuddy()
                    .subclass(PlayerRepository.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(Thread.currentThread().getContextClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(EntityManager.class)
                    .newInstance(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private MatchService getMatchServiceProxy() {
        try {
            return new ByteBuddy()
                    .subclass(MatchService.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(Thread.currentThread().getContextClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(MatchRepository.class, MatchDtoMapper.class)
                    .newInstance(matchRepository, matchDtoMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private OngoingMatchesService getOngoingMatchServiceProxy() {

        try {
            return new ByteBuddy()
                    .subclass(OngoingMatchesService.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(Thread.currentThread().getContextClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(OngoingMatchesRepository.class, PlayerRepository.class, MatchRepository.class, MatchMapper.class)
                    .newInstance(ongoingMatchesRepository, playerRepository, matchRepository, matchMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Session getCurrentSessionProxy() {
        return (Session) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
    }
}
