package DomainTest;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.AbstractFigurine;
import com.chessica.domain.figurines.Knight;
import com.chessica.domain.figurines.Pawn;
import com.chessica.domain.figurines.Queen;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTest {

    private Game testGame = new Game();
    private AbstractFigurine pieceToTest = testGame.getGameState()[0][3];

    @BeforeEach
    public void resetGame(){
        testGame = new Game();
    }

    // positional tests

    @Test
    public void queenStartingPositionsCorrect(){
        assertTrue(testGame.getGameState()[0][3] instanceof Queen &&
                testGame.getGameState()[7][3] instanceof Queen);

    }

    @Test
    public void queenStartingPositionWithWrongCoordinates(){
        assertFalse(testGame.getGameState()[0][0] instanceof Queen);
    }

    // validateMove

    @Test
    public void queenValidateMoveForEmptyFieldValidDirection(){
        System.out.println(testGame.getGameState()[3][2]);
        System.out.println(testGame.getGameState()[2][1]);
        assertEquals(TargetType.CLEAR, pieceToTest.validateMove(3, 2));
        assertEquals(TargetType.CLEAR, pieceToTest.validateMove(2, 1));
    }

    @Test
    public void queenValidateMoveForEnemyFieldValidDirection(){
        testGame.getGameState()[2][1] = new Pawn(1, 2, Color.WHITE, testGame);
        assertEquals(TargetType.ENEMY, pieceToTest.validateMove(2, 1));
        assertEquals(TargetType.ENEMY, pieceToTest.validateMove(3, 6));
    }

    @Test
    public void queenValidateMoveForInvalidFieldCausedByFriend(){
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(3, 1));
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(1, 2));
    }

    @Test
    public void queenValidateMoveForInvalidFieldCausedByWrongDirection(){
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(2, 2));
    }

    //TODO validatePath
}
