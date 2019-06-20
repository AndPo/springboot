package lits.com.springboot.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.typeMap(PersonDto.class, Person.class)

//                .addMapping(src -> src.getName(), Person::setName);
        return new ModelMapper();
    }
}
