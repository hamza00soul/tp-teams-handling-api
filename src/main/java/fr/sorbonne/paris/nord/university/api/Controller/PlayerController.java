package fr.sorbonne.paris.nord.university.api.Controller;

import fr.sorbonne.paris.nord.university.api.DTOs.PlayerDto;
import fr.sorbonne.paris.nord.university.api.Entity.Player;
import fr.sorbonne.paris.nord.university.api.Mappers.PlayerMapper;
import fr.sorbonne.paris.nord.university.api.Mappers.TeamMapper;
import fr.sorbonne.paris.nord.university.api.Repository.PlayerRepository;
import fr.sorbonne.paris.nord.university.api.Repository.TeamRepository;
import fr.sorbonne.paris.nord.university.api.Service.PlayerService;
import fr.sorbonne.paris.nord.university.api.Service.TeamService;
import fr.sorbonne.paris.nord.university.api.Service.TransferService;
import fr.sorbonne.paris.nord.university.api.exception.EntityInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/players")
public class PlayerController {

    private final TeamService teamService;
    private final PlayerService playerService;

    private final TransferService transferService;

    @Autowired
    public PlayerController(TeamRepository teamRepository, PlayerRepository playerRepository, TeamMapper teamMapper, PlayerMapper playerMapper, TransferService transferService) {
        this.transferService = transferService;
        this.playerService = new PlayerService(playerRepository,playerMapper, teamMapper);
        this.teamService = new TeamService(teamRepository,teamMapper);
    }

    @GetMapping("/get/{id}")
    public PlayerDto getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/getAll")
    public List<PlayerDto> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping(path = "/add/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PlayerDto> insertPlayer(@RequestBody @Validated() PlayerDto playerdto , Errors errors) {
        if(errors.hasErrors())
            throw new EntityInvalidException("Invalid Entity");
        return playerService.insertPlayer(playerdto);
    }

    @PutMapping(path = "/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlayerDto updatePlayerById(@PathVariable Long id, @RequestBody Player player) {
        return playerService.updatePlayerById(id,player);
    }

    @DeleteMapping("/delete/{id}")
    public List<PlayerDto> deletePlayerById(@PathVariable Long id) {
        return playerService.deletePlayerById(id);
    }

}
