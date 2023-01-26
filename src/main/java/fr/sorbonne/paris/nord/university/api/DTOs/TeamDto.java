package fr.sorbonne.paris.nord.university.api.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class TeamDto {

    private Long id;

    @NotEmpty
    private String name;

    @Size(max = 50)
    private String slogan;

    public TeamDto() {
    }

    public TeamDto(Long id, String name, String slogan) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
    }
}
