package za.co.mkhungo.dsa.queue;

import lombok.extern.slf4j.Slf4j;

/**
 * Circular Array Queue — Fixes linear queue's wasted-space problem.
 * ✅ RECOMMENDED for exams and production.
 * 🔑 Key Fix: Modulo wrap (index+1)%capacity reuses vacated front cells.
 * Full/Empty detection uses size counter to avoid ambiguity (Blueprint §2.4 Option 1).
 *
 * @author Noxolo Mkhungo
 * @date 2026
 */
@Slf4j
public class QueueCircular {
    private final int capacity;
    private final int[] elements;
    private int front = -1;
    private int rear = -1;
    private int size = 0;

    public QueueCircular(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
        log.info("🔧 QueueCircular created: capacity={}", capacity);
    }

    // 🟢 CIRCULAR ENQUEUE: rear = (rear+1) % capacity
    public void enqueue(int value) {
        log.info("📥 enqueue({}): front={}, rear={}, size={}", value, front, rear, size);
        if (isFull()){
            log.warn("❌ enqueue({}) failed: queue is full", value);
            throw new IllegalStateException("Queue is full");
        }
        if (isEmpty()) {
            log.debug("   → First element: front=0, rear=0");
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % capacity; // ← CIRCULAR: modulo wrap
        }
        elements[rear] = value;
        size++;
        log.info("✅ enqueue({}) success: [{}] front={}, rear={}, size={}",
                value, toArrayString(), front, rear, size);
    }

    // 🟢 CIRCULAR DEQUEUE: front = (front+1) % capacity
    public int dequeue() {
        log.info("📤 dequeue(): front={}, rear={}, size={}", front, rear, size);

        if (isEmpty()) {
            log.warn("❌ dequeue() failed: queue is empty");
            throw new IllegalStateException("Queue is empty");
        }
        int item = elements[front];
        log.debug("   → Retrieved element at front[{}]: {}", front, item);

        if (size == 1) {
            log.debug("   → Last element removed: reset front=-1, rear=-1");
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity; // ← CIRCULAR: modulo wrap
            int oldFront = front;
            log.debug("   → front wrap: ({}+1)%{} = {}", oldFront, capacity, front);
        }
        size--;
        log.info("✅ dequeue() success: returned {}, [{}] front={}, rear={}, size={}",
                item, toArrayString(), front, rear, size);
        return item;
    }

    // ⚠️ EXAM TRAP: peek() must NOT modify state
    public int peek() {
        log.info("👀 peek(): front={}, rear={}, size={}", front, rear, size);
        if (isEmpty()) {
            log.warn("❌ peek() failed: queue is empty");
            throw new IllegalStateException("Queue is empty");
        }
        int item = elements[front];
        log.info("✅ peek() success: returned {} (state unchanged)", item);
        return elements[front];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }
    public int size() { return size; }
    public int getFront() { return front; }
    public int getRear() { return rear; }

    public String toArrayString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");

        // Print by ARRAY INDEX order: 0, 1, 2, ..., capacity-1
        for (int i = 0; i < capacity; i++) {
            // 1. Value first
            sb.append(elements[i]);

            // 2. Markers as SUFFIXES based on index
            if (i == front) sb.append("*");  // front marker
            if (i == rear) sb.append("^");   // rear marker

            // 3. Comma separator
            if (i < capacity - 1) sb.append(", ");
        }

        return sb.append("]").toString();
    }

    public int[] getElements() { return elements; } // For debugging only
}