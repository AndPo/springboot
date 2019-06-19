package lits.com.springboot.service.impl;

import lits.com.springboot.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "cityService")
public class CityServiceImpl {

    @Autowired
    private CityRepository cityRepository;

}
