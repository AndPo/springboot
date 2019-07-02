package lits.com.springboot.service;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.dto.PetDto;

import java.util.List;

public interface PetService {
    List<PetDto> findByName(String petName);

    List<PetDto> findPetsByOwnerPersonId(Long id);
}
