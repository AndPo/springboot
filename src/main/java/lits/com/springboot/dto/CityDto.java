package lits.com.springboot.dto;

public class CityDto {

    private Integer id;

    private String name;

    public CityDto() {
    }

    public CityDto(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
