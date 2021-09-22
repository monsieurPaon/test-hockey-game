package com.maplr.testhockeygame.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private int id;

    private String coach;
    private Long year;

    @JsonManagedReference
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<Player> players;

    public Team() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" +
            "id=" + id +
            ", coach='" + coach + '\'' +
            ", year=" + year +
            '}';
    }
}
