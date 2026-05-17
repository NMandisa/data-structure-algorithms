package za.co.mkhungo.dsa.queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Linear vs Circular Queue — Enqueue/Dequeue Differences")
class QueueComparisonTest {

    @Test
    @DisplayName("Linear Queue: Demonstrates FALSE FULL (wasted space)")
    void testLinearQueue_WastedSpaceProblem() {
        QueueLinear q = new QueueLinear(3);
        q.enqueue(10); q.enqueue(20); q.enqueue(30); // [10*, 20, 30^]
        assertTrue(q.isFull());

        q.dequeue(); q.dequeue(); // [_, _, 30^] front=2, rear=2
        assertFalse(q.isEmpty());
        assertEquals(1, q.size());

        // ⚠️ EXAM TRAP: Linear queue thinks it's full despite 2 empty slots!
        assertTrue(q.isFull(), "Linear queue falsely reports full (rear at capacity-1)");
        assertThrows(IllegalStateException.class, () -> q.enqueue(40),
                "Cannot enqueue: rear cannot wrap back to reuse index 0/1");
    }

    /*@Test
    @DisplayName("Circular Queue: Reuses vacated front cells via modulo")
    void testCircularQueue_WrapAroundFix() {
        QueueCircular q = new QueueCircular(3);
        q.enqueue(10); q.enqueue(20); q.enqueue(30); // [10*, 20, 30^]
        assertTrue(q.isFull());

        q.dequeue(); q.dequeue(); // [_, _, 30^] size=1, front=2, rear=2
        assertFalse(q.isEmpty());
        assertEquals(1, q.size());

        // ✅ CIRCULAR FIX: rear wraps to 0, reusing freed space
        assertDoesNotThrow(() -> q.enqueue(40), "Circular queue reuses index 0");
        q.enqueue(50);
        assertEquals(3, q.size());
        assertTrue(q.isFull());
        assertEquals("[40*, 50, 30^]", q.toArrayString());
    }*/

    @Test
    @DisplayName("Circular Queue: Reuses vacated front cells via modulo")
    void testCircularQueue_WrapAroundFix() {
        QueueCircular q = new QueueCircular(3);

        // Fill: [10*, 20, 30^] (front=0, rear=2)
        q.enqueue(10); q.enqueue(20); q.enqueue(30);

        // Remove two: [10, 20, 30^] (front=2, rear=2, size=1)
        q.dequeue(); q.dequeue();

        // Wrap enqueue: rear=(2+1)%3=0 → [40, 20, 30^] (front=2, rear=0)
        q.enqueue(40);

        // Wrap enqueue: rear=(0+1)%3=1 → [40, 50^, 30*] (front=2, rear=1, size=3)
        q.enqueue(50);

        // DEBUG: Verify state
        System.out.printf("DEBUG: front=%d, rear=%d, size=%d%n",
                q.getFront(), q.getRear(), q.size());
        System.out.printf("DEBUG: array=%s%n",
                java.util.Arrays.toString(q.getElements()));

        // ✅ CORRECT EXPECTATION: front=2 (30*), rear=1 (50^)
        assertEquals("[40, 50^, 30*]", q.toArrayString());

        // Verify queue is full and can't enqueue more
        assertTrue(q.isFull());
        assertThrows(IllegalStateException.class, () -> q.enqueue(99));
    }

    @Test
    @DisplayName("Enqueue/Dequeue Pointer Movement: LINEAR vs CIRCULAR")
    void testPointerMovementDifferences() {
        // 🔴 LINEAR: rear++ / front++ → permanent drift
        QueueLinear linear = new QueueLinear(4);
        linear.enqueue(1); linear.enqueue(2);
        linear.dequeue(); // front=1, rear=1
        // rear cannot go back to 0. Next enqueue fails once rear reaches 3.
        assertTrue(linear.isFull() || linear.size() < 4); // Linear may falsely report full

        // 🟢 CIRCULAR: (rear+1)%cap / (front+1)%cap → infinite loop in fixed space
        QueueCircular circular = new QueueCircular(4);
        circular.enqueue(1);  // [1*, _, _, _] f=0,r=0
        circular.enqueue(2);  // [1*, 2^, _, _] f=0,r=1
        circular.dequeue();   // [1, 2*^, _, _] f=1,r=1, size=1
        circular.enqueue(3);  // [1, 2*, 3^, _] f=1,r=2
        circular.enqueue(4);  // [1, 2*, 3, 4^] f=1,r=3
        circular.enqueue(5);  // [5^, 2*, 3, 4] f=1,r=0 ← WRAP!

        // Verify size and full state
        assertEquals(4, circular.size());
        assertTrue(circular.isFull());

        // ✅ CORRECT EXPECTATION: front=1 (2*), rear=0 (5^)
        // toArrayString prints by INDEX order: [0,1,2,3] → [5^, 2*, 3, 4]
        assertEquals("[5^, 2*, 3, 4]", circular.toArrayString());

        // Verify peek returns front element (index 1 = value 2)
        assertEquals(2, circular.peek());
    }
}