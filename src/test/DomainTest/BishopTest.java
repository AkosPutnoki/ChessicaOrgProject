package DomainTest;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.AbstractFigurine;
import com.chessica.domain.figurines.Bishop;
import com.chessica.domain.figurines.Knight;
import com.chessica.domain.figurines.Pawn;
import com.chessica.domain.figurines.enums.Color;
import com.chessica.domain.figurines.enums.TargetType;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTest {

    private Game testGame = new Game();
    private AbstractFigurine pieceToTest = testGame.getGameState()[0][2];

    @BeforeEach
    public void resetGame(){
        testGame = new Game();
    }

    // positional tests

    @Test
    public void bishopStartingPositionsCorrect(){
        assertTrue(testGame.getGameState()[0][2] instanceof Bishop &&
                testGame.getGameState()[0][5] instanceof Bishop &&
                testGame.getGameState()[7][2] instanceof Bishop &&
                testGame.getGameState()[7][5] instanceof Bishop);

    }

    @Test
    public void bishopStartingPositionWithWrongCoordinates(){
        assertFalse(testGame.getGameState()[0][1] instanceof Bishop);
    }

    // validateMove

    @Test
    public void bishopValidateMoveForEmptyFieldValidDirection(){
        System.out.println(testGame.getGameState()[4][2]);
        assertEquals(TargetType.CLEAR, pieceToTest.validateMove(4, 2));
    }

    @Test
    public void bishopValidateMoveForEnemyFieldValidDirection(){
        testGame.getGameState()[2][0] = new Pawn(0, 2, Color.WHITE, testGame);
        assertEquals(TargetType.ENEMY, pieceToTest.validateMove(2, 0));
    }

    @Test
    public void bishopValidateMoveForInvalidFieldCausedByFriend(){
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(1, 1));
    }

    @Test
    public void bishopValidateMoveForInvalidFieldCausedByWrongDirection(){
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(1, 2));
    }

    @Test
    public void bishopValidateMoveWithDifferentRook(){
        pieceToTest = testGame.getGameState()[7][6];
        assertEquals(TargetType.CLEAR, pieceToTest.validateMove(5, 5));
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(6, 4));
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(4, 4));
    }

    // TODO validatePath

}
