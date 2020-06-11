package pl.akademiaspringa.homework_week7.Dao;

import pl.akademiaspringa.homework_week7.models.Article;
import pl.akademiaspringa.homework_week7.models.Car;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {
    void deleteById(Long ID);
    Optional<T> save(T o);
    Optional<List<T>> getAll();
    Optional<T> getOne(Long ID);
    Optional<T> update(Long ID, T o);
}
