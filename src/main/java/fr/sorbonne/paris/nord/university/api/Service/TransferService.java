package fr.sorbonne.paris.nord.university.api.Service;

import fr.sorbonne.paris.nord.university.api.DTOs.PlayerDto;
import fr.sorbonne.paris.nord.university.api.DTOs.TeamDto;
import fr.sorbonne.paris.nord.university.api.Entity.Player;
import fr.sorbonne.paris.nord.university.api.Entity.Team;
import fr.sorbonne.paris.nord.university.api.Mappers.PlayerMapper;
import fr.sorbonne.paris.nord.university.api.Mappers.TeamMapper;
import fr.sorbonne.paris.nord.university.api.Repository.PlayerRepository;
import fr.sorbonne.paris.nord.university.api.Repository.TeamRepository;
import fr.sorbonne.paris.nord.university.api.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;
    private final TeamMapper teamMapper;

    @Autowired
    public TransferService(PlayerRepository playerRepository, PlayerMapper mapper, TeamRepository teamRepository, PlayerMapper playerMapper, TeamMapper teamMapper) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.playerMapper = playerMapper;
        this.teamMapper = teamMapper;
    }
    public List<PlayerDto> getPlayersByTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        return playerRepository.findByTeam(team).stream().map(player -> playerMapper.map(player)).toList();
    }

    public PlayerDto BuyPlayer(TeamDto teamdto, Long id) {
        PlayerDto playerDto = playerMapper.map(playerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found")));
        //playerDto.setTeamDTO(teamdto);
        Player player = playerMapper.map(playerDto);
        player.setTeam(teamMapper.map(teamdto));
        return playerMapper.map(playerRepository.save(player));
    }

}
