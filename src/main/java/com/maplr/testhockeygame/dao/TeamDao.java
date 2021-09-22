package com.maplr.testhockeygame.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.maplr.testhockeygame.model.Team;

@Repository
public interface TeamDao extends JpaRepository<Team, Long> {
    Team findByYear(long year);
}
