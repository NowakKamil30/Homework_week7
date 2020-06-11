package pl.akademiaspringa.homework_week7.Dao;

import pl.akademiaspringa.homework_week7.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {
    Optional<List<Car>> getCarsWhereDataIsBetween(String from, String to);

}
