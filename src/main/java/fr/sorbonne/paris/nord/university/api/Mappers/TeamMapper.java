package fr.sorbonne.paris.nord.university.api.Mappers;

import fr.sorbonne.paris.nord.university.api.DTOs.TeamDto;
import fr.sorbonne.paris.nord.university.api.Entity.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public Team map(TeamDto teamdto){
        Team team = new Team();
        if(teamdto !=null) {
            team.setId(teamdto.getId());
            team.setName(teamdto.getName());
            team.setSlogan(teamdto.getSlogan());
        }
        return team;
    }

    public TeamDto map(Team team){
        TeamDto teamdto = new TeamDto();
        if(team !=null) {
            teamdto.setId(team.getId());
            teamdto.setName(team.getName());
            teamdto.setSlogan(team.getSlogan());
        }
        return teamdto;
    }
}
