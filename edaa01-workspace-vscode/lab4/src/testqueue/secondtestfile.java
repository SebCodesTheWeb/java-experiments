package testqueue;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;


class secondtestfile {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@BeforeEach
	void setUp() {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown(){
		q1 = null;
		q2 = null;
	}

    // Test 1 med 2 tomma köer.
    @Test 
    void testAppendTwoEmptyQueues() {
        q1.append(q2);

        assertTrue(q1.isEmpty());
        assertTrue(q2.isEmpty());
    }

    //Test 2 tom kö --> icke-tom kö
    @Test 
    public void testEmptyToNonEmpty() {

        q1.offer(1);
        q1.offer(2);

        q1.append(q2);
        
        assertEquals(2, q1.size());
        assertTrue(q2.isEmpty());

        assertEquals(Integer.valueOf(1),q1.poll());
        assertEquals(Integer.valueOf(2),q1.poll());
    }

    //Test 3 icke tom --> tom
    @Test 
    public void testNonEmptyToEmpty() {
        q2.offer(1);
        q2.offer(2);

        q1.append(q2);

        assertEquals(2,q1.size());
        assertTrue(q2.isEmpty());

        assertEquals(Integer.valueOf(1), q1.poll());
        assertEquals(Integer.valueOf(2), q1.poll());

        assertTrue(q1.isEmpty());
    }

    // Test 4 två icke-tomma köer
    @Test 
    public void testTwoNonEmpty(){
        q1.offer(1);
        q1.offer(2);

        q2.offer(3);
        q2.offer(4);

        q1.append(q2);
        assertTrue(q2.isEmpty());

        assertEquals(4,q1.size());
        assertEquals(Integer.valueOf(1),q1.poll());
        assertEquals(Integer.valueOf(2),q1.poll());
        assertEquals(Integer.valueOf(3),q1.poll());
        assertEquals(Integer.valueOf(4),q1.poll());

        assertTrue(q1.isEmpty());
    }

    //Test 5 Slå ihop med sig själv
    @Test
    public void testWithItself(){
        q1.offer(1);
        assertThrows(IllegalArgumentException.class, () -> {
            q1.append(q1);
        });
    }
}
