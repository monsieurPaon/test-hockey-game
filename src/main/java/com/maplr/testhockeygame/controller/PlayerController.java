package com.maplr.testhockeygame.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.maplr.testhockeygame.dao.PlayerDao;
import com.maplr.testhockeygame.exceptions.NotFoundException;
import com.maplr.testhockeygame.model.Player;
import com.maplr.testhockeygame.model.Team;

@RestController
public class PlayerController {
    private final PlayerDao playerDao;

    @Autowired
    public PlayerController(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @PutMapping(value = "api/player/captain/{ID}")
    public Player setPlayersCaptain(@PathVariable long ID) throws NotFoundException {

        // 1 - Find the player to update as captain
        Player playerToUpdate = playerDao.findByNumber(ID);

        if (playerToUpdate == null)
            throw new NotFoundException("Player with id " + ID + " not found.");

        // 2 - Find the related Team to list all the player
        Team relatedTeam = playerToUpdate.getTeam();

        // 3 - Find all the player from the team
        List<Player> playersFromThisTeam = relatedTeam.getPlayers();

        // 4 - Loop over the team to find the current captain
        for (Player player : playersFromThisTeam) {
            if (player.getCaptain()) {
                player.setCaptain(false);
            }
        }

        // 5 - Set the player as the new captain (hip hip oura)
        playerToUpdate.setCaptain(true);

        playerDao.save(playerToUpdate);

        return playerToUpdate;
    }
}
