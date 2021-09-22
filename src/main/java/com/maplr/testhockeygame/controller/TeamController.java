package com.maplr.testhockeygame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.maplr.testhockeygame.dao.PlayerDao;
import com.maplr.testhockeygame.dao.TeamDao;
import com.maplr.testhockeygame.exceptions.NotFoundException;
import com.maplr.testhockeygame.model.Player;
import com.maplr.testhockeygame.model.Team;

@RestController
public class TeamController {
    private final TeamDao teamDao;
    private final PlayerDao playerDao;

    @Autowired
    public TeamController(TeamDao teamDao, PlayerDao playerDao) {
        this.teamDao = teamDao;
        this.playerDao = playerDao;
    }

    @GetMapping(value = "/api/team/{Year}")
    public Team getTeamFromYear(@PathVariable int Year) throws NotFoundException {
        Team team = teamDao.findByYear(Year);

        if (team == null)
            throw new NotFoundException("The team from " + Year + " dosen't exist yet in database");
        return team;
    }

    @PostMapping(value = "/api/team/{Year}")
    public Player addAPlayerToAYearTeam(@RequestBody Player player, @PathVariable int Year) throws NotFoundException {
        // 1 - Find the team
        Team teamToUpdate = teamDao.findByYear(Year);
        if (teamToUpdate == null)
            throw new NotFoundException("The team from " + Year + " dosen't exist yet in database");

        // 2 - Set the referal team to the player
        player.setTeam(teamToUpdate);

        // Maybe need to control the data of the player ?

        // 3 - Save the player
        playerDao.save(player);

        return player;
    }
}
