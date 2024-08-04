package application.data.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T,K> {

    T get(K id);

    List<T> getAll();

    void create(T t);

    void update(T t) throws SQLException;

    void deleteById(K id);
}
