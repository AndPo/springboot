package lits.com.springboot.service;

import lits.com.springboot.dto.CityDto;
import lits.com.springboot.model.City;
import lits.com.springboot.repository.CityRepository;
import lits.com.springboot.service.impl.CityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    CityService cityService = new CityServiceImpl();


    @Test
    public void shouldGetOneCity(){
        when(cityRepository.findOne(1l)).thenReturn(new City());

        cityService.findOne(1l);
        verify(cityRepository).findOne(1l);
    }

    @Test
    public void shouldSaveCity() {
        CityDto cityDto = new CityDto();
        City city = new City();

        when(modelMapper.map(cityDto, City.class)).thenReturn(city);
        when(modelMapper.map(city, CityDto.class)).thenReturn(cityDto);
        when(cityRepository.save(city)).thenReturn(city);

        assertEquals(cityDto, cityService.save(cityDto));
        verify(cityRepository).save(city);
    }

    @Test
    public void shouldUpdateCity() {

    }

    @Test
    public void shouldDeleteCity() {

    }

    @Test
    public void shouldFindByNameCity() {

    }
}
