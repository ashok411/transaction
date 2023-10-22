package com.ashok.transaction.repository;



import com.ashok.transaction.exception.MyCustomException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created on 26/04/21 GenericDao.java
 *
 * <p>Generic DAO (Data Access Object) with common methods to CRUD POJOs. Extend this interface if
 * * you want typesafe (no casting necessary) DAO's for your domain objects.
 */
public interface IGenericRepository<T, PK extends Serializable> {

  /**
   * Generic method used to get all objects of a particular communicationType. This is the same as
   * lookup up all rows in a table.
   *
   * @return List of populated objects
   */
  List<T> getAll();

  /**
   * Generic method to get an object based on class and identifier. An
   * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
   *
   * @param id the identifier (primary key) of the object to get
   * @return a populated object
   * @see org.springframework.orm.ObjectRetrievalFailureException
   */
  T get(PK id);

  /**
   * Checks for existence of an object of T using the id arg.
   *
   * @param id the id of the entity
   * @return - true if it exists, false if it doesn't
   */
  boolean exists(PK id);

  /**
   * Generic method to save an object - handles both update and insert.
   *
   * @param object the object to saveMedication
   * @return the persisted object
   */
  T save(T object) throws MyCustomException;

  /**
   * Generic method to save an list of object - handles both update and insert.
   *
   * @param object the object to saveMedication
   * @return the persisted object
   */
  List<T> saveAll(List<T> object) throws MyCustomException;

  /**
   * Generic method to update an object - caller must ensure that the passed object is persistent.
   *
   * @param object the object to saveMedication
   */
  void update(T object);

  /**
   * Generic method to delete an object
   *
   * @param object the object to remove
   */
  void remove(T object);

  /**
   * Generic method to delete an object
   *
   * @param id the identifier (primary key) of the object to remove
   */
  void remove(PK id);

  /**
   * execute a raw sql query and return the results along with the meta data
   */
  Map getRawSqlResults(String sql);

  /**
   * execute a create query
   */
  String executeCreate(final String sql) throws SQLException;
}
