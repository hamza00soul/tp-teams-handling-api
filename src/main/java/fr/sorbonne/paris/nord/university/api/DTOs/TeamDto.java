package fr.sorbonne.paris.nord.university.api.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Data
public class TeamDto {
    private Long id;
    private String name;
    private String slogan;

    public TeamDto() {
    }

    public TeamDto(Long id, String name, String slogan) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
    }
}
