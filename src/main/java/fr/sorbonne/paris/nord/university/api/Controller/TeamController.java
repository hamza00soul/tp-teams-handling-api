package fr.sorbonne.paris.nord.university.api.Controller;

import fr.sorbonne.paris.nord.university.api.DTOs.TeamDto;
import fr.sorbonne.paris.nord.university.api.Entity.Team;
import fr.sorbonne.paris.nord.university.api.Mappers.TeamMapper;
import fr.sorbonne.paris.nord.university.api.Repository.TeamRepository;
import fr.sorbonne.paris.nord.university.api.Service.TeamService;
import fr.sorbonne.paris.nord.university.api.exception.EntityInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/teams")
public class TeamController {


    private final TeamService teamService;

    @Autowired
    public TeamController(TeamRepository teamRepository, TeamMapper mapper) {
        this.teamService = new TeamService(teamRepository,mapper);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World !";
    }

    @GetMapping("/get/{id}")
    public TeamDto getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/getAll")
    public List<TeamDto> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping("/add")
    public List<TeamDto> insertTeam(@RequestBody @Validated() TeamDto teamdto , Errors errors) {
        if(errors.hasErrors())
            throw new EntityInvalidException("Invalid Entity");
        return teamService.insertTeam(teamdto);
    }

    @PutMapping("/update/{id}")
    public TeamDto updateTeamById(@PathVariable Long id, @RequestBody Team team) {
        return teamService.updateTeamById(id,team);
    }

    @DeleteMapping("/delete/{id}")
    public List<TeamDto> deleteTeamById(@PathVariable Long id) {
        return teamService.deleteTeamById(id);
    }
}
