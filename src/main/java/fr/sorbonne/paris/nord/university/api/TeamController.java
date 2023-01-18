package fr.sorbonne.paris.nord.university.api.Service;

import fr.sorbonne.paris.nord.university.api.Entity.Team;
import fr.sorbonne.paris.nord.university.api.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RestController
@RequestMapping("/teams")
public class TeamController {


    private final TeamRepository teamRepository;
    @Autowired
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World !";
    }

    @GetMapping("/get/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    @GetMapping("/getAll")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/add")
    public List<Team> createTeam(@RequestParam("name") String name, @RequestParam("slogan") String slogan) {
        Team team = new Team(name, slogan);
        return insertTeam(team) ;
    }
    @PostMapping("/add")
    public List<Team> insertTeam(@RequestBody Team team) {
        teamRepository.save(team);
        return teamRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public Team updateTeamById(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team != null) {
            team.setName("Modified");
            teamRepository.save(team);
        }
        return team;
    }

    @DeleteMapping("/delete/{id}")
    public List<Team> deleteTeamById(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team != null)
            teamRepository.deleteById(id);
        return teamRepository.findAll();
    }
}
