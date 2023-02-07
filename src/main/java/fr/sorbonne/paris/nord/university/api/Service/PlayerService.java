package fr.sorbonne.paris.nord.university.api.Service;

import fr.sorbonne.paris.nord.university.api.DTOs.PlayerDto;

import fr.sorbonne.paris.nord.university.api.Entity.Player;

import fr.sorbonne.paris.nord.university.api.Mappers.PlayerMapper;
import fr.sorbonne.paris.nord.university.api.Mappers.TeamMapper;
import fr.sorbonne.paris.nord.university.api.Repository.PlayerRepository;
import fr.sorbonne.paris.nord.university.api.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper pm;
    private final TeamMapper tm;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, PlayerMapper pm, TeamMapper tm) {
        this.playerRepository = playerRepository;
        this.pm = pm;
        this.tm = tm;
    }
    public PlayerDto getPlayerById(@PathVariable Long id) {
        Player player = playerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        return pm.map(player);
    }

    public List<PlayerDto> getAllPlayers() {
        return playerRepository.findAll().stream().map(player -> pm.map(player)).toList();
    }

    public List<PlayerDto> insertPlayer(PlayerDto playerdto) {
        Player player = pm.map(playerdto);
        //player.setTeam(tm.map(playerdto.getTeamDTO()));
        /*if(team.getName()== null || team.getSlogan() == null )
            throw new EntityInvalidException("Invalid Entity");*/
        playerRepository.save(player);
        return playerRepository.findAll().stream().map(t -> pm.map(t)).toList();
    }

    public PlayerDto updatePlayerById(Long id, Player UpdatedPlayer) {
        Player player = playerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        if (player != null) {
            player.setFirstName(UpdatedPlayer.getFirstName());
            player.setLastName(UpdatedPlayer.getLastName());
            player.setBirthday(UpdatedPlayer.getBirthday());
            playerRepository.save(player);
        }
        return pm.map(player);
    }

    public List<PlayerDto> deletePlayerById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        if (player != null)
            playerRepository.deleteById(id);
        return playerRepository.findAll().stream().map(t -> pm.map(t)).toList();
    }
}
