package pl.akademiaspringa.homework_week7.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.akademiaspringa.homework_week7.models.Car;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CarDaoImpl implements CarDao, Dao<Car,Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM cars WHERE cars.id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Car> save(Car car) {
        String sql = "INSERT INTO cars (mark, model, color, year) Values (?,?,?,?)";
        jdbcTemplate.update(sql, car.getMark(), car.getModel(), car.getColor(), car.getYear());
        return Optional.of(car);
    }

    @Override
    public Optional<List<Car>> getAll() {
        String sql = "SELECT * FROM cars";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        List<Car> carList = new ArrayList<>();
        mapList.forEach(car ->
                carList.add(getCarFromMap(car)));
        return Optional.of(carList);
    }

    @Override
    public Optional<Car> getOne(Long id) {
        String sql = "SELECT * FROM cars WHERE cars.id = ?";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, id);
        List<Car> carList = new ArrayList<>();
        mapList.forEach(car ->
                carList.add(getCarFromMap(car)));
        return Optional.of(carList.get(0));
    }

    @Override
    public Optional<Car> update(Long id, Car car) {
        String sql = "UPDATE cars SET cars.mark=?, cars.model=?, cars.color=?, cars.year=? WHERE cars.id=?";
        jdbcTemplate.update(sql, car.getMark(), car.getModel(), car.getColor(), car.getYear(), id);
        return getOne(id);
    }

    @Override
    public Optional<List<Car>> getCarsWhereDataIsBetween(String from, String to) {
        String sql = "SELECT * FROM cars WHERE cars.year BETWEEN ? AND ?";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, from, to);
        List<Car> carList = new ArrayList<>();
        mapList.forEach(car ->
                carList.add(getCarFromMap(car)));
        return Optional.of(carList);
    }

    private Car getCarFromMap(Map<String, Object> car) {
        return new Car(Long.parseLong(String.valueOf(car.get("id"))),
                (String) car.get("mark"),
                (String) car.get("model"),
                (String) car.get("color"),
                (String) car.get("year"));
    }
}
