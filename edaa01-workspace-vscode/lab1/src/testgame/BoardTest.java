package testgame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import game.Board;

class BoardTest {
	private Board board;
	
	// Den här metoden exekveras före varje enskilt test.
	@BeforeEach
	void setUp() {
		board = new Board();
	}


	// Den här metoden exekveras efter varje enskilt test.
	@AfterEach
	void tearDown() {
		board  = null;
	}


    @Test
    void testSetUp() {
        board.setUp(5);
        assertEquals(5, board.getNumPins());
    }

    @Test
    void testTakeOnePin() {
        board.setUp(5);
        board.takePins(1);
        assertEquals(4, board.getNumPins());
    }

    @Test
    void testTakeTwoPins() {
        board.setUp(5);
        board.takePins(2);
        assertEquals(3, board.getNumPins());
    }
    
    @Test
    void testTakeMorePins() {
        board.setUp(5);
        board.takePins(2);
        assertEquals(3, board.getNumPins());
        board.takePins(1);
        assertEquals(2, board.getNumPins());
        board.takePins(2);
        assertEquals(0, board.getNumPins());
    }

    @Test
    void testCannotTakeZeroPins() {
        board.setUp(5);
        assertThrows(IllegalArgumentException.class, () -> board.takePins(0));
    }

    @Test
    void testCannotTakeMoreThanTwoPins() {
        board.setUp(5);
        assertThrows(IllegalArgumentException.class, () -> board.takePins(3));
    }

    @Test
    void testCannotTakeTwoPinsWhenOnlyOneLeft() {
        board.setUp(1);
        assertThrows(IllegalArgumentException.class, () -> board.takePins(2));
    }
    
    @Test
    void testCannotTakeOnePinWhenNoLeft() {
        board.setUp(1);
        board.takePins(1);
        assertThrows(IllegalArgumentException.class, () -> board.takePins(1));
  }
}
