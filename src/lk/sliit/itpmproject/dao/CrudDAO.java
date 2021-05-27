package lk.sliit.itpmproject.dao;


import lk.sliit.itpmproject.entity.SuperEntity;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T extends SuperEntity, E> extends SuperDAO {

    List<T> findAll() throws SQLException;

    T find(E id) throws SQLException;

    boolean save(T entity) throws SQLException;

    boolean update(T entity) throws SQLException;

    boolean delete(E id) throws SQLException;

}
