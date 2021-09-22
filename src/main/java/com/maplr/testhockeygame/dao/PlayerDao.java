package com.maplr.testhockeygame.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.maplr.testhockeygame.model.Player;

@Repository
public interface PlayerDao extends JpaRepository<Player, Long> {
    Player findByNumber(long number);
}
