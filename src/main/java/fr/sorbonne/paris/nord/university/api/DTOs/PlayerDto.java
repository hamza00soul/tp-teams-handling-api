package fr.sorbonne.paris.nord.university.api.DTOs;

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

    public PlayerDto() {
    }
    public PlayerDto(Long id, String firstName, String lastName, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }
}
