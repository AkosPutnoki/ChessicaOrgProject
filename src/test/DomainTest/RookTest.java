package DomainTest;

import com.chessica.domain.Game;
import com.chessica.domain.figurines.AbstractFigurine;
import com.chessica.domain.figurines.Rook;
import com.chessica.domain.figurines.enums.TargetType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RookTest {

    private Game testGame = new Game();
    private AbstractFigurine pieceToTest = testGame.getGameState()[0][0];

    // positional tests

    @Test
    public void rookStartingPositionsCorrect(){
        assertTrue(testGame.getGameState()[0][0] instanceof Rook &&
                testGame.getGameState()[0][7] instanceof Rook &&
                testGame.getGameState()[7][0] instanceof Rook &&
                testGame.getGameState()[7][7] instanceof Rook);

    }

    @Test
    public void rookStartingPositionWithWrongCoordinates(){
        assertFalse(testGame.getGameState()[0][1] instanceof Rook);
    }

    // validateMove

    @Test
    public void rookValidateMoveForEmptyFieldValidDirection(){
        assertEquals(TargetType.CLEAR, pieceToTest.validateMove(2, 0));
    }

    @Test
    public void rookValidateMoveForEnemyFieldValidDirection(){
        assertEquals(TargetType.ENEMY, pieceToTest.validateMove(6, 0));
    }

    @Test
    public void rookValidateMoveForInvalidFieldCausedByFriend(){
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(1, 0));
    }

    @Test
    public void rookValidateMoveForInvalidFieldCausedByWrongDirection(){
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(2, 2));
    }

    @Test
    public void rookValidateMoveWithDifferentRook(){
        pieceToTest = testGame.getGameState()[7][7];
        assertEquals(TargetType.CLEAR, pieceToTest.validateMove(5, 7));
        assertEquals(TargetType.ENEMY, pieceToTest.validateMove(1, 7));
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(6, 7));
        assertEquals(TargetType.INVALID, pieceToTest.validateMove(5, 5));
    }

    //TODO validatePath


}
