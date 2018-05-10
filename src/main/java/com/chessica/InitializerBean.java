package com.chessica;

import com.chessica.domain.Game;
import com.chessica.domain.userRelated.Match;
import com.chessica.domain.userRelated.User;
import com.chessica.repository.MatchRepository;
import com.chessica.repository.UserRepository;
import com.chessica.util.GameSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class InitializerBean {

    public InitializerBean(UserRepository userRepository, MatchRepository matchRepository) throws IOException {

        Game game1 = new Game();

        User user1 = new User("Sanyi", "sanyi@sanyi.sanyi", "sanyi");
        User user2 = new User("Ákos", "akos@akos.sanyi", "akos");

        Match match = null;
        try {
            match = new Match(user1, user2, GameSerializer.serialize(game1));
        } catch (IOException e) {
            System.out.println("match creation failed");
            e.printStackTrace();
        }

        matchRepository.save(match);
        System.out.println("match saved...");
        Match newMatch = matchRepository.getOne(1L);
        System.out.println(Arrays.deepToString(newMatch.getDeserializedGame().getGameState()));

    }

}
