package lits.com.springboot.repository;

import lits.com.springboot.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    City findById(Integer id);
    City findByName(String name);

}
