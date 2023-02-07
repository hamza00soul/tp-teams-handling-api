package fr.sorbonne.paris.nord.university.api.Repository;

import fr.sorbonne.paris.nord.university.api.DTOs.PlayerDto;
import fr.sorbonne.paris.nord.university.api.DTOs.TeamDto;
import fr.sorbonne.paris.nord.university.api.Entity.Player;
import fr.sorbonne.paris.nord.university.api.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeam(Team team);
    List<Player> findByTeamNullOrTeamIdNot(Long id);
}
