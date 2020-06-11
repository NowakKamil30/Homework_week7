package pl.akademiaspringa.homework_week7.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspringa.homework_week7.Dao.CarDao;
import pl.akademiaspringa.homework_week7.Dao.CarDaoImpl;
import pl.akademiaspringa.homework_week7.models.Car;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {

    private final CarDaoImpl carDao;

    @Autowired
    public CarController(CarDaoImpl carDao) {
        this.carDao = carDao;
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getCars() {
        Optional<List<Car>> carList = carDao.getAll();
        return carList
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable long id) {
        return carDao.getOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());

    }
    @GetMapping("/sort")
    public ResponseEntity<List<Car>> getCarsWhereDataIsBetween(@RequestParam String from, @RequestParam String to) {
        return carDao
                .getCarsWhereDataIsBetween(from, to)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    @PostMapping()
    public ResponseEntity<Car> postCar(@RequestBody Car car) {
        return carDao
                .save(car)
                .map(carItem -> ResponseEntity.status(201).body(car))
                .orElse(ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable long id) {
        carDao.deleteById(id);
        return ResponseEntity.accepted().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Car> putCar(@PathVariable long id, @RequestBody Car car) {
        return carDao.update(id, car)
                .map(carItem -> ResponseEntity.accepted().body(car))
                .orElse(ResponseEntity.notFound().build());
    }
}
