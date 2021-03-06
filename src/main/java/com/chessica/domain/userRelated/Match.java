package com.chessica.domain.userRelated;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.enums.ResultType;
import com.chessica.util.GameSerializer;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`match`")
@Proxy(lazy=false)
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany
    private List<User> users = new ArrayList<>();

    private ResultType result;
    private byte[] game;

    public Match(User firstUser, User secondUser, byte[] game) {
        users.add(firstUser);
        users.add(secondUser);
        firstUser.getMatches().add(this);
        secondUser.getMatches().add(this);
        this.game = game;
        this.result = ResultType.PENDING;
    }

    public Match() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public byte[] getGame() {
        return game;
    }

    public Game getDeserializedGame() throws IOException {
        return GameSerializer.deSerialize(game);
    }

    public void setGame(byte[] game) {
        this.game = game;
    }
}
