package com.ashok.transaction.repository;





import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created on 26/04/21 GenericDaoHibernate.java
 *
 * @author vikrant
 */
public class GenericRepository<T, PK extends Serializable> implements IGenericRepository<T, PK> {

  protected static final int DEFAULT_OFFSET = 0;
  protected static final int DEFAULT_LIMIT = 100;

  protected Class<T> persistentClass;

  @Autowired
   EntityManager entityManager;

  public GenericRepository() {
  }

  /**
   * Constructor that takes in a class and sessionFactory for easy creation of DAO.
   *
   * @param persistentClass the class source you'd like to persist
   */
  public GenericRepository(final Class<T> persistentClass) {
    this.persistentClass = persistentClass;
  }

  public Session getSession() throws HibernateException {
    Session sess;
    sess = entityManager.unwrap(Session.class);
    return sess;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public List<T> getAll() {
    Session sess = getSession();
    CriteriaBuilder criteriaBuilder = sess.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
    Root<T> root = criteriaQuery.from(persistentClass);
    criteriaQuery.select(root);
    Query<T> query = sess.createQuery(criteriaQuery);
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public T get(PK id) {
    if (ObjectUtils.isEmpty(id)) {
      return null;
    }
    Session sess = getSession();
    IdentifierLoadAccess<T> byId = sess.byId(persistentClass);
    return byId.load(id);
  }

  public List<T> getMultiple(List<PK> ids) {
    if (CollectionUtils.isEmpty(ids)) {
      return Collections.emptyList();
    }
    Session sess = getSession();
    MultiIdentifierLoadAccess<T> byIds = sess.byMultipleIds(persistentClass);
    return byIds.multiLoad(ids);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public boolean exists(PK id) {
    Session sess = getSession();
    IdentifierLoadAccess<T> byId = sess.byId(persistentClass);

    // for some reason hibernate is throwing an exception org.hibernate.ObjectNotFoundException
    // its not supposed to but it is
    // lets handle it here
    T entity = null;
    try {
      entity = byId.load(id);
    } catch (ObjectNotFoundException ex) {
      // ignore it
      // it gets already logged in the hibernate code so don't log it here
    }
    return entity != null;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public T save(T codTransaction) {
    Session sess = getSession();
    return (T) sess.merge(codTransaction);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public List<T> saveAll(List<T> object) {
    Session sess = getSession();
    List<T> resultSet = new ArrayList<>();
    object.forEach(obj -> {
      resultSet.add((T) sess.merge(obj));
    });
    return resultSet;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public void update(T object) {
    Session sess = getSession();
    sess.update(object);
  }

  @Override
  public void remove(T object) {

  }

  @Override
  public void remove(PK id) {

  }


  @Override
  public Map getRawSqlResults(String sql) {
    final Map<String, Object> resultMap = new HashMap<String, Object>();
    final String finalSql = sql.trim().toLowerCase();
    if (finalSql.startsWith("select")) {
      Session session = getSession();
      session.doWork(
              connection -> {
                connection.createStatement().executeQuery(finalSql);
                ResultSet rs = connection.createStatement().executeQuery(finalSql);
                ResultSetMetaData rsmd = rs.getMetaData();

                // add the metadata to the map as well
                resultMap.put("metadata", rsmd);

                List<List<Object>> data = new ArrayList<List<Object>>();
                resultMap.put("data", data);

                int cols = rsmd.getColumnCount();
                while (rs.next()) {
                  // Get the data from the row using the column index
                  List<Object> row = new ArrayList<Object>();
                  for (int i = 1; i <= cols; i++) {
                    row.add(rs.getObject(i));
                  }
                  data.add(row);
                }
              });
    }
    return resultMap;
  }

  @Override
  public String executeCreate(String sql) throws SQLException {
    final List<String> l = new ArrayList<>();
    Session session = getSession();
    session.doWork(
            connection -> {
              int result;
              try {
                result = connection.createStatement().executeUpdate(sql);
                l.add("" + result);
              } catch (Exception e) {
                l.add(e.toString());
              }
            });
    return l.get(0);
  }

  void hardDeleteAll(List<T> entities) {
    Session session = getSession();
    for(T entity : entities) {
      session.delete(entity);
    }
  }

}
