package fr.sorbonne.paris.nord.university.api.DTOs;

import fr.sorbonne.paris.nord.university.api.Entity.Team;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;

import java.sql.Date;

@Data
public class PlayerDto {
    @NonNull
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private Date birthday;
    private String position;

    //private TeamDto teamDTO;


    public PlayerDto() {
    }
    public PlayerDto(Long id, String firstName, String lastName, Date birthday, String position,TeamDto teamDto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.position = position;
        //this.teamDTO = teamDto;
    }
}
