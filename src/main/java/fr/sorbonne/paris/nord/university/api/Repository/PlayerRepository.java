package fr.sorbonne.paris.nord.university.api.Repository;

import fr.sorbonne.paris.nord.university.api.Entity.Player;
import fr.sorbonne.paris.nord.university.api.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
