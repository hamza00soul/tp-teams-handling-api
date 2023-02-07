package fr.sorbonne.paris.nord.university.api.Controller;
import fr.sorbonne.paris.nord.university.api.DTOs.PlayerDto;
import fr.sorbonne.paris.nord.university.api.DTOs.TeamDto;
import fr.sorbonne.paris.nord.university.api.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }
    @GetMapping("/getPlayersByTeamId/{id}")
    public List<PlayerDto> getPlayersByTeam(@PathVariable Long id) {
        return transferService.getPlayersByTeam(id);
    }

    @PostMapping(path = "/Buy/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlayerDto BuyPlayer(@PathVariable Long id , @RequestBody @Validated() TeamDto teamDto, Errors errors) {
        return transferService.BuyPlayer(teamDto,id);
    }
}
