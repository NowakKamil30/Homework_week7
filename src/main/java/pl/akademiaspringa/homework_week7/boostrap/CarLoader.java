package pl.akademiaspringa.homework_week7.boostrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.akademiaspringa.homework_week7.Dao.CarDao;
import pl.akademiaspringa.homework_week7.Dao.CarDaoImpl;
import pl.akademiaspringa.homework_week7.models.Car;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CarLoader implements CommandLineRunner {

    private final CarDaoImpl carDao;

    @Autowired
    public CarLoader(CarDaoImpl carDao) {
        this.carDao = carDao;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<List<Car>> optionalCarList = carDao.getAll();

        optionalCarList.ifPresent(list -> {
            if(list.size() == 0) {
                loadCars();
            }
        });
    }

    private void loadCars() {
        carDao.save(
                new Car(
                        "mark",
                        "model",
                        "color",
                        "2005"));
        carDao.save(
                new Car(
                        "mark1",
                        "model1",
                        "color1",
                        "2010"));
        carDao.save(
                new Car(
                        "mark2",
                        "model2",
                        "color2",
                        "2012"));
    }
}
