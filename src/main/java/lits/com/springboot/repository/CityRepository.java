package lits.com.springboot.repository;

import lits.com.springboot.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);
}
