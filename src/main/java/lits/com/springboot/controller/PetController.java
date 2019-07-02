package lits.com.springboot.controller;

import lits.com.springboot.dto.PetDto;
import lits.com.springboot.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller(value = "/api")
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping(value = "/pets")
    List<PetDto> findByName(@RequestParam String name) {
        return petService.findByName(name);
    }

    @GetMapping(value = "/pets/person")
    List<PetDto> findByOwnerPersonId(@RequestParam Long id) {
        return petService.findPetsByOwnerPersonId(id);
    }


}
