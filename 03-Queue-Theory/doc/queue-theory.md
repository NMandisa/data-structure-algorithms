# 📘 Theoretical Recap — Queue Theory
> 🎯 Subject Guide: SO5–SO6 — Data Structures (Linear ADTs & Queue Implementations)

<details>
<summary><strong>📖 Quick Navigation</strong></summary>

1. [Queue Definition & Properties](#1-queue-definition-and-fundamental-properties)
2. [The Simple (Linear) Queue](#2-the-simple-linear-queue--the-wasted-space-problem)
3. [Circular Queue & Modulo Wrapping](#3-circular-queue--the-circle-of-lockers-and-modulo-wrapping)
4. [Full-Detection Problem](#4-the-critical-full-detection-problem--why-we-need-an-explicit-rule)
5. [Core Queue Operations](#5-core-queue-operations-circular-array-size-counter)
6. [Linked-List Implementation](#6-linked-list-implementation--theoretical)
7. [Efficiency Comparison](#7-efficiency-comparison--why-circular-queue-wins)
8. [Common Mistakes](#8-common-mistakes-and-misinterpretations)
9. [Memory Hooks](#9-memory-hooks)
10. [Coverage Index](#10-coverage-index-what-why-how-when-advantagesdisadvantages-trade-offs)
</details>

## 1. Queue Definition and Fundamental Properties

A **queue** is a linear abstract data type (ADT) that enforces **FIFO** (First‑In, First‑Out) ordering: the element that has been in the queue the longest is always the next one removed.

### 1.1 Linear Nature & Analogy
- **Linear structure**: Elements form a single unbranched sequence; each (except front/rear) has exactly one predecessor and one successor. This classifies queues alongside stacks and lists, in contrast to non-linear structures (trees, graphs).
- **Real-world analogy**: A line of people — new arrivals join at the back, service occurs at the front.

### 1.2 ADT vs Implementation
| Operation    | Behaviour                                                                               |
|--------------|-----------------------------------------------------------------------------------------|
| `enqueue(x)` | Add element `x` to the rear.                                                            |
| `dequeue()`  | Remove and return the element at the front.                                             |
| `peek()`     | Return the front element without removal.                                               |
| `isEmpty()`  | Return `true` iff the queue contains zero elements.                                     |
| `isFull()`   | Return `true` iff the queue cannot accept more elements (bounded implementations only). |

The **ADT** is a logical contract; it says nothing about memory layout, pointers, or indices.  
Two standard implementation approaches exist:
1. **Array‑based** – Contiguous block with `front`/`rear` indices. May be linear (wastes space) or circular (wraps via modulo). Bounded unless resized.
2. **Linked‑list‑based** – Chain of nodes with `head` (front) and `tail` (rear) pointers. Unbounded; `isFull()` always `false`.

> ⚠️ **Exam Note**: `Queue` is an interface (the ADT). Classes like `LinkedList` or `ArrayDeque` are implementations. Conflating them (e.g., *“Queue = LinkedList”*) loses marks.

### 1.3 When to Use a Queue — Typical Applications
- CPU scheduling (processes waiting for the processor)
- Request buffering (web servers, print spoolers)
- Breadth‑first search (BFS) in graphs/trees
- Asynchronous data transfer (I/O buffers, message queues)
- Call centre holding systems

Any **“first‑come, first‑served”** scenario is a natural fit for a queue.

### 1.4 Advantages and Disadvantages of the Queue ADT
| Advantages                                                                         | Disadvantages                                                                               |
|------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| Simple, intuitive FIFO semantics — easy to reason about.                           | Strict ordering — cannot prioritise/reorder without extra machinery (e.g., priority queue). |
| Fast operations — `enqueue`/`dequeue` are `O(1)` in well‑designed implementations. | No random access — elements only accessible at the front.                                   |
| Natural fit for buffering, scheduling, BFS traversal.                              | Bounded arrays require resizing or careful capacity management.                             |
| Clean ADT/implementation separation allows optimal structure choice.               | Linked lists carry per‑node pointer overhead and suffer cache misses.                       |

## 2. The Simple (Linear) Queue — The Wasted Space Problem

### 2.1 The Array as a Row of Lockers
An array is a straight row of lockers `0` to `capacity-1`. Two indices track the queue:
- `front` — locker of the first element.
- `rear` — locker of the last element.

### 2.2 Initial State & First Operations
- Empty queue: `front = -1`, `rear = -1`.
- First `enqueue(A)`: `front = 0`, `rear = 0`, `array[0] = A`.
- Second `enqueue(B)`: `rear = 1`, `array[1] = B`.
- `dequeue()`: removes `array[front]`, `front = 1`. Locker `0` is now empty.

### 2.3 How the Problem Emerges
Assume `capacity = 10`. After several operations, `front = 5`, `rear = 9`. Lockers `5–9` are occupied; `0–4` are empty.  
A new `enqueue` fails because `rear + 1 = 10` (out of bounds), **even though 5 slots are free**.

> 🅿️ **Analogy**: Linear Queue = One‑Way Parking Garage. Once cars leave from the front, those spots become permanently unreachable. The garage reports `FULL` despite half being empty — a **false full condition**.

**Root Cause**: `rear` only moves forward. Reusing front cells requires shifting all elements down → `O(n)` operation, defeating the purpose of fast queues.

### 2.4 Summary of the Flaw
- ❌ Wasted space: vacated front lockers are permanently lost.
- ❌ False full condition.
- ❌ Shifting workaround is `O(n)` → unacceptable for performance.
✅ **Solution**: The circular queue.

## 3. Circular Queue — The Circle of Lockers and Modulo Wrapping

> 🍣 **Analogy**: Circular Queue = Sushi Belt + Pac‑Man Tunnel. Empty plates loop back automatically. The modulo operator `%` acts as the wrap tunnel: `(rear + 1) % capacity` sends you from the last locker back to `0`. No shifting. No wasted space. Just a wrap.

### 3.1 The Circle Analogy
If lockers are arranged in a circle, after the last locker (`capacity-1`) the next is `0`. When `rear` reaches the end, the next `enqueue` wraps to `0` if empty. Front slots are reused instantly.

### 3.2 The Mathematical Tool: Modulo Operator
For capacity `n`, the next position after index `i` is `(i + 1) % n`.
- New rear after `enqueue`: `(rear + 1) % capacity`
- New front after `dequeue`: `(front + 1) % capacity`

### 3.3 Detailed Step‑by‑Step Trace (`capacity = 5`)
| Step | Operation       | `front` | `rear` | Array `[0..4]`      | Notes                          |
|------|-----------------|---------|--------|---------------------|--------------------------------|
| 0    | (empty)         | -1      | -1     | `[ , , , , ]`       | Initial state                  |
| 1    | `enqueue(A)`    | 0       | 0      | `[A, , , , ]`       | First element                  |
| 2    | `enqueue(B)`    | 0       | 1      | `[A, B, , , ]`      |                                |
| 3    | `enqueue(C)`    | 0       | 2      | `[A, B, C, , ]`     |                                |
| 4    | `enqueue(D)`    | 0       | 3      | `[A, B, C, D, ]`    |                                |
| 5    | `dequeue() → A` | 1       | 3      | `[ , B, C, D, ]`    | `front` moves to 1             |
| 6    | `enqueue(E)`    | 1       | 4      | `[ , B, C, D, E]`   | `rear` reaches end             |
| 7    | `dequeue() → B` | 2       | 4      | `[ , , C, D, E]`    |                                |
| 8    | `enqueue(F)`    | 2       | 0      | `[F, , C, D, E]`    | `rear` wraps to `0`            |
| 9    | `enqueue(G)`    | 2       | 1      | `[F, G, C, D, E]`   | Queue full (5 elements)        |
| 10   | `dequeue() → C` | 3       | 1      | `[F, G, , D, E]`    |                                |
| 11   | `dequeue() → D` | 4       | 1      | `[F, G, , , E]`     |                                |
| 12   | `dequeue() → E` | 0       | 1      | `[F, G, , , ]`      | `front` wraps to `0`           |
| 13   | `dequeue() → F` | 1       | 1      | `[ , G, , , ]`      | Only `G` remains               |
| 14   | `dequeue() → G` | -1      | -1     | `[ , , , , ]`       | Empty → reset to `-1`          |

**Key Observations**:
- Modulo `%` automatically handles the wrap.
- Empty cells are reused instantly; no permanent waste.
- The array cycles indefinitely as long as `size ≤ capacity`.

## 4. The Critical Full-Detection Problem — Why We Need an Explicit Rule

> 🔍 **Ambiguity**: With only `front` and `rear`, an empty queue and a full queue can both satisfy `(rear + 1) % capacity == front`. Without extra state, they are indistinguishable.

### 4.1 Three Standard Solutions
| Option                              | Description                          | Empty Condition | Full Condition              | Pros / Cons                       |
|-------------------------------------|--------------------------------------|-----------------|-----------------------------|-----------------------------------|
| **1. Size Counter** (✅ Recommended) | Maintain `int size`.                 | `size == 0`     | `size == capacity`          | No wasted slots, zero ambiguity.  |
| **2. Sacrifice One Slot**           | Leave one cell permanently unused.   | `front == -1`   | `(rear + 1) % cap == front` | Simple, but wastes 1 slot.        |
| **3. Boolean Flag**                 | `boolean isFull` toggled on enqueue. | `front == -1`   | `isFull == true`            | Works, but adds state complexity. |

> 📝 **Exam Rule**: Always state your chosen full-detection strategy explicitly with its Boolean condition.

## 5. Core Queue Operations (Circular Array, Size Counter)

### 5.1 `enqueue(x)`
- **If full** (`size == capacity`): reject.
- **If empty** (`size == 0`): `front = rear = 0`, place `x`, `size = 1`.
- **Otherwise**: `rear = (rear + 1) % capacity`, place `x`, `size++`.
- `front` unchanged unless queue was empty.

### 5.2 `dequeue()`
- **If empty** (`size == 0`): reject.
- Retrieve `array[front]`.
- `size--`.
- **If empty now** (`size == 0`): `front = rear = -1`.
- **Otherwise**: `front = (front + 1) % capacity`.

### 5.3 `peek()`
- Return `array[front]` if `!isEmpty()`; else fail.

### 5.4 `isEmpty()` / `isFull()`
- `isEmpty()` → `size == 0`
- `isFull()` → `size == capacity`

### 5.5 `resize()` (Dynamic Expansion)
- Allocate new array `2 × capacity`.
- Copy `size` elements contiguously from old `front`, wrapping as needed.
- Set `front = 0`, `rear = size - 1`, update `capacity`.
- Queue becomes linear in new array until it fills again.

## 6. Linked-List Implementation — Theoretical

A queue can be built with a singly linked list using `head` (front) and `tail` (rear) pointers.

### 6.1 Structural Overview
- Each node: `data` + `next` reference.
- `head` → first element; `tail` → last element.
- `size` optional; empty when `head == null`.

### 6.2 Operation Theory
- **`enqueue`**: Create new node. If empty, `head = tail = newNode`. Else `tail.next = newNode`, `tail = newNode`. → `O(1)`
- **`dequeue`**: If empty, reject. Save `head.data`. `head = head.next`. If `head == null`, `tail = null`. Free old node. → `O(1)`
- **`peek`**: Return `head.data` if `!isEmpty()`. → `O(1)`
- **`isFull`**: Always `false` (unbounded, barring system memory limits).

### 6.3 Array vs Linked-List Trade-offs
| Aspect              | Array (Circular)          | Linked List               |
|---------------------|---------------------------|---------------------------|
| `enqueue`/`dequeue` | `O(1)`                    | `O(1)`                    |
| Memory overhead     | Minimal, contiguous       | Per-node pointer overhead |
| Cache performance   | Excellent                 | Poorer (scattered nodes)  |
| Capacity            | Fixed / requires resize   | Dynamic, no `isFull`      |
| Complexity          | Modulo + full detection   | Simple pointer updates    |

Both are `O(1)`; choose based on capacity bounds, memory tolerance, and cache needs.

## 7. Efficiency Comparison — Why Circular Queue Wins

| Implementation                  | Enqueue | Dequeue | Space Utilisation          |
|---------------------------------|---------|---------|----------------------------|
| Linear queue (with shifting)    | `O(1)`  | `O(n)`  | Full, but time penalty     |
| Linear queue (pointers only)    | `O(1)`  | `O(1)`  | Vacated front cells lost   |
| **Circular queue (modulo)**     | `O(1)`  | `O(1)`  | **All cells reused** ✅     |

> 💡 **Key Insight**: Circular queues win on **space utilisation**, not speed. All core operations are `Θ(1)` in best/average/worst cases.

## 8. Common Mistakes and Misinterpretations

| Mistake                         | Why It’s Wrong                         | Correct View                                                                   |
|---------------------------------|----------------------------------------|--------------------------------------------------------------------------------|
| `"Queue = LinkedList"`          | Confuses ADT with a concrete class.    | `Queue` is a FIFO contract; implemented via array **or** linked list.          |
| Clockwise vs anti‑clockwise     | Visual direction is irrelevant.        | Wrap is strictly defined by `(index + 1) % capacity`.                          |
| Ignoring full‑detection         | Leaves testable condition unspecified. | Must state explicit Boolean (`size == cap` or sacrificed slot).                |
| BST/Heap/Graph listed as linear | These branch or network.               | **Linear**: Array, LinkedList, Stack, Queue. **Non‑linear**: BST, Heap, Graph. |

## 9. Memory Hooks
- **Queue** — Linear ADT, FIFO: `enqueue` at rear, `dequeue` from front.
- **Circular Queue** — Array queue where indices wrap via `(i + 1) % capacity`, reusing vacated cells.
- **Linked-List Queue** — Dynamic queue using `head`/`tail`; all ops `O(1)`, unbounded.
- **Stack** — Linear ADT, LIFO: `push`/`pop` at top.
- **ADT** — Specification of *what* a structure does, independent of *how* it's built.

## 10. Coverage Index: What, Why, How, When, Advantages/Disadvantages, Trade-offs

| Aspect              | Where Covered                                                                                     |
|---------------------|---------------------------------------------------------------------------------------------------|
| **What**            | §1 (ADT definition, FIFO, linear nature, analogy), §2.1–2.2 (linear array representation)         |
| **Why**             | §2.3–2.4 (why linear queue fails), §3 (why circular solves it), §4 (why full-detection is needed) |
| **How**             | §3.2–3.3 + trace table (modulo wrapping), §5 (enqueue/dequeue/peek/resize), §6 (linked-list ops)  |
| **When**            | §1.3 (applications), §6.3 (array vs linked-list trade-offs), §7 (circular vs linear)              |
| **Adv/Disadv**      | §1.4 (ADT pros/cons), §4.2 (full-detection strategies), §6.3 (implementation trade-offs)          |
| **Trade-offs**      | §4.2 (size vs sacrificed slot), §6.3 (array vs linked list), §7 (linear shifting vs circular)     |
| **Common Mistakes** | §8 (misconceptions & corrections)                                                                 |


