package DomainTest;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.AbstractFigurine;
import com.chessica.domain.figurines.Knight;
import com.chessica.domain.figurines.Pawn;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {

    private Game testGame = new Game();
    private AbstractFigurine pieceToTest = testGame.getGameState()[0][1];

    @BeforeEach
    public void resetGame(){
        testGame = new Game();
    }

    // positional tests

    @Test
    public void knightStartingPositionsCorrect(){
        assertTrue(testGame.getGameState()[0][1] instanceof Knight &&
                testGame.getGameState()[0][6] instanceof Knight &&
                testGame.getGameState()[7][1] instanceof Knight &&
                testGame.getGameState()[7][6] instanceof Knight);

    }

    @Test
    public void knightStartingPositionWithWrongCoordinates(){
        assertFalse(testGame.getGameState()[0][0] instanceof Knight);
    }

    // validateMove

    @Test
    public void knightValidateMoveForEmptyFieldValidDirection(){
        assertEquals(TargetType.CLEAR, pieceToTest.validateMove(2, 2));
    }

    @Test
    public void knightValidateMoveForEnemyFieldValidDirection(){
        testGame.getGameState()[2][2] = new Pawn(2, 2, Color.WHITE, testGame);
        assertEquals(TargetType.ENEMY, pieceToTest.validateMove(2, 2));
    }

    @Test
    public void knightValidateMoveForInvalidFieldCausedByFriend(){
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(1, 3));
    }

    @Test
    public void knightValidateMoveForInvalidFieldCausedByWrongDirection(){
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(3, 3));
    }

    @Test
    public void knightValidateMoveWithDifferentRook(){
        pieceToTest = testGame.getGameState()[7][6];
        assertEquals(TargetType.CLEAR, pieceToTest.validateMove(5, 5));
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(6, 4));
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(4, 4));
    }

}
