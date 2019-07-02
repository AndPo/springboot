package lits.com.springboot.controller;

import lits.com.springboot.dto.PetDto;
import lits.com.springboot.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pets")
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping(value = "")
    List<PetDto> findByName(@RequestParam(value = "name") String name) {
        return petService.findByName(name);
    }

    @GetMapping(value = "/person")
    List<PetDto> findByOwnerPersonId(@RequestParam(value = "id") Long id) {
        return petService.findPetsByOwnerPersonId(id);
    }


}
