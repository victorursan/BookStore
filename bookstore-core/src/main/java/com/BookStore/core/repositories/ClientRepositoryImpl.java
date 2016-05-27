package com.BookStore.core.repositories;

import com.BookStore.core.models.Client;
import com.BookStore.core.models.ClientBook;
import com.BookStore.core.models.ClientBook_;
import com.BookStore.core.models.Client_;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by victor on 5/20/16.
 */
public class ClientRepositoryImpl extends CustomRepositorySupport<Client, Integer> implements ClientRepositoryCustom {

    public ClientRepositoryImpl() {
        super(Client.class);
    }


    @Override
    @Transactional
    public List<Client> findAllWithBooksSqlQuery() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        Query query = session.createSQLQuery("select distinct {cl.*}, {cb.*}, {b.*}" +
                " from client cl" +
                " left join client_book cb on cb.client_id = cl.id" +
                " left join book b on b.id = cb.book_id")
                .addEntity("cl", Client.class)
                .addJoin("cb", "cl.clientBooks")
                .addJoin("b", "cb.book")
                .addEntity("cl", Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Client> clients = query.list();

        return clients;
    }

    @Override
    @Transactional
    public List<Client> findAllWithBooksJpql() {
        javax.persistence.Query query = getEntityManager().createQuery("select distinct c from Client c" +
                " left join fetch c.clientBooks cb" +
                " left join fetch cb.book b");

        List<Client> clients = query.getResultList();

        return clients;
    }

    @Override
    @Transactional
    public List<Client> findAllWithBooksJpaCriteria() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);

        query.distinct(Boolean.TRUE);

        Root<Client> from = query.from(Client.class);

        Fetch<Client, ClientBook> studentDisciplineFetch = from.fetch(Client_.clientBooks, JoinType.LEFT);
        studentDisciplineFetch.fetch(ClientBook_.book, JoinType.LEFT);

        List<Client> clients = getEntityManager().createQuery(query).getResultList();

        return clients;
    }
}
