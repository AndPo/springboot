package lits.com.springboot.service.impl;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.dto.PetDto;
import lits.com.springboot.exception.PersonNotFoundException;
import lits.com.springboot.exception.PetNotFoundException;
import lits.com.springboot.model.Person;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.repository.PetRepository;
import lits.com.springboot.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "petService")
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<PetDto> findByName(String petName) {
        return petRepository.findByName(petName).stream()
                .filter(Objects::nonNull)
                .map(e -> modelMapper.map(e, PetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDto> findPetsByOwnerPersonId(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person with id: " + id + " not found"));

        return petRepository.findByOwner(id).stream()
                .peek(e -> {
                    if (e == null)
                    throw new PetNotFoundException("Pet with owner id:" + id + "null or not found");
                })
                .map(e -> modelMapper.map(e, PetDto.class))
                .collect(Collectors.toList());
    }
}
