package com.chessica.domain.userRelated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private User firstUser;
    private User secondUser;
    private byte[] game;

    public Match(User firstUser, User secondUser, byte[] game) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.game = game;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public byte[] getGame() {
        return game;
    }

    public void setGame(byte[] game) {
        this.game = game;
    }
}
