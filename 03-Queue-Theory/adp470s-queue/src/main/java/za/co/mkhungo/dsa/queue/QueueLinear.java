package za.co.mkhungo.dsa.queue;

import lombok.extern.slf4j.Slf4j;

/**
 * Linear Array Queue — Demonstrates the "wasted space" problem.
 * ⚠️ NOT for production. Shows why circular queues are needed.
 * 🔑 Key Flaw: rear only moves forward. Vacated front cells are permanently lost.
 * When rear == capacity-1, isFull() returns true even if front > 0 (false full).
 *
 * @author Noxolo Mkhungo
 * @date 2026
 */
@Slf4j
public class QueueLinear {
    private final int capacity;
    private final int[] elements;
    private int front = -1;
    private int rear = -1;

    public QueueLinear(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
        log.info("🔧 QueueLinear created: capacity={}", capacity);
    }

    // 🔴 LINEAR ENQUEUE: rear++ (no wrap)
    public void enqueue(int value) {
        log.info("📥 enqueue({}): front={}, rear={}", value, front, rear);

        if (isFull()) {
            log.warn("❌ enqueue({}) failed: rear at capacity-{} (linear waste!)", value, capacity-1);
            throw new IllegalStateException("Queue is full (linear waste)");
        }
        if (isEmpty()) {
            front = 0;
            log.debug("   → First element: front=0");
        }
        rear++; // ← LINEAR: just increments, no wrap
        log.debug("   → rear++: {} → {}", rear-1, rear);
        elements[rear] = value;
        log.info("✅ enqueue({}) success: [{}] front={}, rear={}",
                value, toArrayString(), front, rear);
    }

    // 🔴 LINEAR DEQUEUE: front++ (no wrap)
    public int dequeue() {
        log.info("📤 dequeue(): front={}, rear={}", front, rear);
        if (isEmpty()) {
            log.warn("❌ dequeue() failed: queue is empty");
            throw new IllegalStateException("Queue is empty");
        }

        int item = elements[front];
        log.debug("   → Retrieved element at front[{}]: {}", front, item);
        if (front == rear) { // Last element removed
            log.debug("   → Last element removed: reset front=-1, rear=-1");
            front = -1;
            rear = -1;
        } else {
            front++; // ← LINEAR: just increments, no wrap
            log.debug("   → front++: {} → {}", front-1, front);
        }
        log.info("✅ dequeue() success: returned {}, [{}] front={}, rear={}",
                item, toArrayString(), front, rear);
        return item;
    }

    public int peek() {
        log.info("👀 peek(): front={}, rear={}", front, rear);
        if (isEmpty()) {
            log.warn("❌ peek() failed: queue is empty");
            throw new IllegalStateException("Queue is empty");
        }
        int item = elements[front];
        log.info("✅ peek() success: returned {} (state unchanged)", item);
        return item;
    }

    public boolean isEmpty() {
        return front == -1 || front > rear;
    }

    // ⚠️ EXAM TRAP: False full condition!
    public boolean isFull() {
        boolean full = rear == capacity - 1;
        if (full && front > 0) {
            log.warn("⚠️ isFull()=true but front={} > 0 → {} wasted slots!", front, front);
        }
        return full;
    }

    public int size() {
        return isEmpty() ? 0 : rear - front + 1;
    }

    public String toArrayString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");

        // Print by ARRAY INDEX order (0, 1, 2, ..., capacity-1)
        for (int i = 0; i < capacity; i++) {
            // Append the value first
            sb.append(elements[i]);

            // Then append markers as SUFFIXES based on which INDEX is front/rear
            if (i == front) {
                sb.append("*");  // front marker
            }
            if (i == rear) {
                sb.append("^");  // rear marker
            }

            // Add comma separator (except after last element)
            if (i < capacity - 1) {
                sb.append(", ");
            }
        }

        return sb.append("]").toString();
    }
}