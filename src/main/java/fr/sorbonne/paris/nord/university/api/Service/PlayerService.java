package fr.sorbonne.paris.nord.university.api.Service;

import fr.sorbonne.paris.nord.university.api.DTOs.PlayerDto;

import fr.sorbonne.paris.nord.university.api.Entity.Player;

import fr.sorbonne.paris.nord.university.api.Mappers.PlayerMapper;
import fr.sorbonne.paris.nord.university.api.Repository.PlayerRepository;
import fr.sorbonne.paris.nord.university.api.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper mapper;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, PlayerMapper mapper) {
        this.playerRepository = playerRepository;
        this.mapper = mapper;
    }

    public PlayerDto getTeamById(@PathVariable Long id) {
        Player player = playerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        return mapper.map(player);
    }

    public List<PlayerDto> getAllPlayers() {
        return playerRepository.findAll().stream().map(team -> mapper.map(team)).toList();
    }

    public List<PlayerDto> insertTeam(PlayerDto playerdto) {
        Player player = mapper.map(playerdto);
        /*if(team.getName()== null || team.getSlogan() == null )
            throw new EntityInvalidException("Invalid Entity");*/
        playerRepository.save(player);
        return playerRepository.findAll().stream().map(t -> mapper.map(t)).toList();
    }

    public PlayerDto updateTeamById(Long id, Player UpdatedPlayer) {
        Player player = playerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        if (player != null) {
            player.setFirstName(UpdatedPlayer.getFirstName());
            player.setLastName(UpdatedPlayer.getLastName());
            player.setBirthday(UpdatedPlayer.getBirthday());
            playerRepository.save(player);
        }
        return mapper.map(player);
    }

    public List<PlayerDto> deleteTeamById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        if (player != null)
            playerRepository.deleteById(id);
        return playerRepository.findAll().stream().map(t -> mapper.map(t)).toList();
    }
}
