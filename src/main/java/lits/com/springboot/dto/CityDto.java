package lits.com.springboot.dto;

public class CityDto {

    private Integer id;

    private String name;

    private String description;

    public CityDto(String name) {
        this.name = name;
    }

    public CityDto() {
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

    public String getDescription() {
        return description;
    }

    public CityDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
