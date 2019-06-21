package lits.com.springboot.repository;

import lits.com.springboot.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

    City findById(Long id);

    City findByName(String name);

}
