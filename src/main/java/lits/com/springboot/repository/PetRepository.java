package lits.com.springboot.repository;

import lits.com.springboot.model.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends MongoRepository<Pet, String> {
    List<Pet> findByName(String name);

    List<Pet> findByOwner(Long id);
}
