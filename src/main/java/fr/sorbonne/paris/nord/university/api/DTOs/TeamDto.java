package fr.sorbonne.paris.nord.university.api.DTOs;

import fr.sorbonne.paris.nord.university.api.Entity.Player;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TeamDto {

    private Long id;

    @NotEmpty
    private String name;

    @Size(max = 50)
    private String slogan;

    private List<PlayerDto> playersDto = new ArrayList<>();

    public TeamDto() {
    }

    public TeamDto(Long id, String name, String slogan) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
    }
}
