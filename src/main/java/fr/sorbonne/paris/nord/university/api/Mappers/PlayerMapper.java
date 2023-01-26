package fr.sorbonne.paris.nord.university.api.Mappers;

import fr.sorbonne.paris.nord.university.api.DTOs.PlayerDto;
import fr.sorbonne.paris.nord.university.api.Entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player map(PlayerDto playerdto){
        Player player = new Player();
        if(playerdto !=null) {
            player.setId(playerdto.getId());
            player.setFirstName(playerdto.getFirstName());
            player.setLastName(playerdto.getLastName());
            player.setBirthday(playerdto.getBirthday());
        }
        return player;
    }

    public PlayerDto map(Player player){
        PlayerDto playerdto = new PlayerDto();
        if(player !=null) {
            playerdto.setId(player.getId());
            playerdto.setFirstName(player.getFirstName());
            playerdto.setLastName(player.getLastName());
            playerdto.setBirthday(player.getBirthday());
        }
        return playerdto;
    }
}
