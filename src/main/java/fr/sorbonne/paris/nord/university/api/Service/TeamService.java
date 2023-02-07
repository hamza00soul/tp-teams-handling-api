package fr.sorbonne.paris.nord.university.api.Service;

import fr.sorbonne.paris.nord.university.api.DTOs.PlayerDto;
import fr.sorbonne.paris.nord.university.api.DTOs.TeamDto;
import fr.sorbonne.paris.nord.university.api.Entity.Team;
import fr.sorbonne.paris.nord.university.api.Mappers.TeamMapper;
import fr.sorbonne.paris.nord.university.api.Repository.TeamRepository;
import fr.sorbonne.paris.nord.university.api.exception.EntityInvalidException;
import fr.sorbonne.paris.nord.university.api.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper mapper;

    @Autowired
    public TeamService(TeamRepository teamRepository, TeamMapper mapper) {
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    public TeamDto getTeamById(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        return mapper.map(team);
    }

    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream().map(team -> mapper.map(team)).toList();
    }

    public List<TeamDto> insertTeam(TeamDto teamdto) {
        Team team = mapper.map(teamdto);
        /*if(team.getName()== null || team.getSlogan() == null )
            throw new EntityInvalidException("Invalid Entity");*/
        teamRepository.save(team);
        return teamRepository.findAll().stream().map(t -> mapper.map(t)).toList();
    }

    public TeamDto updateTeamById(Long id, Team UpdatedTeam) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        if (team != null) {
            team.setName(UpdatedTeam.getName());
            team.setSlogan(UpdatedTeam.getSlogan());
            teamRepository.save(team);
        }
        return mapper.map(team);
    }

    public List<TeamDto> deleteTeamById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        if (team != null)
            teamRepository.deleteById(id);
        return teamRepository.findAll().stream().map(t -> mapper.map(t)).toList();
    }
}
