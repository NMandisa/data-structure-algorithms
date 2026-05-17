# DATA STRUCTURES & ALGORITHMS

### 📐 DOCUMENT STRUCTURE (Conceptual Build-Up Order)
1. **CHAPTER 1 — Big-O Notation & Loop Analysis** *(The Ruler: How we measure efficiency)*  
2. **CHAPTER 2 — Foundations: Data Structures & ADTs** *(The Objects: What we measure)*  
3. **CHAPTER 3 — Queue Theory (Linear → Circular → Linked-List)** *(The Case Study)*  
4. **CHAPTER 4 — Recursion vs Iteration** *(The Engine: Algorithmic control flow)*  
5. **CHAPTER 5 — Sorting Algorithms** *(The Synthesis: Combining structures, measurement, recursion)*  
6. **CHAPTER 6 — Exam Technique & Master Cheat Sheet** *(The Review: Strategy & rapid recall)*  
7. **CHAPTER 7 — Exam Predictions & Model Answers** *(The Strategy: High-yield practice)*

### 🎨 COLOUR KEY
✅ **ANSWER** — Correct exam answer | 🧠 **EXPLANATION** — Why correct | 🔍 **PATTERN** — What examiner tests  
⚠️ **TRAP** — Common mark-losing mistakes | 💡 **PRO TIP** — Strategy & memory aids

---

## CHAPTER 1 — BIG-O NOTATION & LOOP ANALYSIS
*(Prerequisite: Mathematical foundation for all subsequent complexity analysis)*

### 1.1 — What Big-O Means
Big-O = **worst-case upper bound** on how the number of operations grows with input size `n`. Constants and lower-order terms are always dropped.

| Notation | Meaning | Example |
|----------|---------|---------|
| `O(g(n))` | Upper bound — worst case | Binary search: `O(log n)` |
| `Ω(g(n))` | Lower bound — best case | Linear search best: `Ω(1)` |
| `Θ(g(n))` | Tight bound — exact growth (both O and Ω) | Binary search: `Θ(log n)` — all cases identical |

🔍 **Exam note:** Big O is only an upper bound. Use `Θ` when best and worst cases are the same rate — it is the more precise statement.  
⚠️ **Trap:** Never say "Big O = complexity". Say: "The algorithm has `Θ(n log n)` time complexity because best, average, and worst cases all grow logarithmically."

### 1.2 — The Five Rules (Memorise These)
| Rule | Pattern | Big-O | Reason |
|------|---------|-------|--------|
| 1 — Single Loop | `for i = 0; i < n; i++` | `O(n)` | `n` iterations |
| 2 — Sequential Loops | Loop A then Loop B (back-to-back) | `O(n)` | ADD: `O(n)+O(n)=O(2n)→O(n)` |
| 3 — Nested Loops | `for i { for j }` | `O(n²)` | MULTIPLY: `n × n` |
| 4 — Logarithmic Step | `j = j*2` or `j = j*3` per iteration | `O(log n)` | Multiplying: `2^k=n → k=log n` |
| 5 — Nested Log-Linear | `O(n)` outer × `O(log n)` inner | `O(n log n)` | MULTIPLY the two |

 **Golden rule:** Sequential = ADD. Nested = MULTIPLY. Constant factors (step `+3`, `×2`, `÷3`) are ALWAYS dropped.

### 1.3 — Past Exam Code Patterns (Solved)
| Pattern | Analysis | Answer |
|---------|----------|--------|
| Two separate loops, both `0→n` | Loop A: `O(n)` + Loop B: `O(n)` = `O(2n)` → drop constant | `O(n)` |
| Outer `O(n)`; inner `j = j*3` | Outer: `O(n)` × Inner: `O(log₃n)` = `O(n log n)` | `O(n log n)` |
| Outer `i+=3`; inner `j--` from `n` | Outer: `n/3 = O(n)` × Inner: `O(n)` — nested | `O(n²)` |
| Single while: `n = n/2` | Halving: `O(log n)` | `O(log n)` |

⚠️ **COMMON MISTAKES THAT COST MARKS**
| ❌ Wrong | ✅ Correct | Why |
|----------|------------|-----|
| `O(2n)` | `O(n)` | Constants are always dropped |
| `O(n²/3)` | `O(n²)` | Constant fractions are dropped |
| Two sequential loops = `O(n²)` | `O(n)` | They ADD — only nested loops MULTIPLY |
| `i += 3` changes complexity | Still `O(n)` | Step is a constant — irrelevant to Big-O class |
| Reverse loop `j--` is different | Same `O(n)` | Direction has no effect on iteration count |

### 1.4 — Complexity Growth Hierarchy
| Big-O | Name | For n = 1,000 (approx ops) |
|-------|------|---------------------------|
| `O(1)` | Constant | 1 |
| `O(log n)` | Logarithmic | ~10 |
| `O(n)` | Linear | 1,000 |
| `O(n log n)` | Linearithmic | 10,000 |
| `O(n²)` | Quadratic | 1,000,000 |
| `O(2ⁿ)` | Exponential | astronomical |

🧠 **Key insight:** A small change in complexity class massively affects scalability at large `n`. `O(n²)` and `O(n log n)` may look similar for `n=10` but diverge completely at `n=100,000`.

---

## CHAPTER 2 — FOUNDATIONS: DATA STRUCTURES & ADTs
*(Now that we have the analytical ruler, we define the structures we will measure)*

### 2.1 — What is a Data Structure & ADT?
**Data Structure:** A systematic way to organise data for efficient access and modification.  
Every data structure has three components:
1. **Logical model** — the abstract arrangement (sequence, tree, network)
2. **Operations** — the allowed actions (insert, delete, search, traverse)
3. **Physical representation** — how it is stored in memory (array, linked nodes)

**ADT (Abstract Data Type):** Specifies **WHAT** a structure does, not **HOW** it is built. It is a formal behaviour contract — the interface, not the implementation.

✨ **Algorithm Correctness Properties**  
For an algorithm to be correct, it must satisfy:
1. **Termination:** It must finish in finite steps (e.g., recursion must have a base case).
2. **Correctness:** It must produce the right result for all valid inputs (e.g., binary search requires sorted input).

⚠️ **Exam trap:** Never write `"Queue = LinkedList"`. Write: `"A Queue is a FIFO ADT; it can be implemented using an array or a linked list."`

### 2.2 — Linear vs Non-Linear: The Fundamental Split
| Aspect | Linear | Non-Linear |
|--------|--------|------------|
| Arrangement | Single, straight sequence — one path through all elements | Elements branch or connect in multiple directions |
| Predecessor / Successor | Exactly one of each (except endpoints) | May have multiple predecessors and/or successors |
| Traversal | Forward or backward along the sequence | Depth-first, breadth-first, or multi-path |
| Examples | Array, Linked List, Stack, Queue | BST, Heap, Graph |

💡 **Memory hook:** Linear = line. Non-linear = network.

### 2.3 — The Four Core Linear Structures
| Structure | ADT Rule | Key Operations | Array Implementation | Linked-List Implementation | Access Complexity |
|-----------|----------|---------------|---------------------|---------------------------|------------------|
| Array | Random access by index | read, write, index | Fixed size, `O(1)` access | — | Access `O(1)`, Insert/Delete `O(n)` |
| Linked List | Sequential access via pointers | insert, delete, search | — | Dynamic size, `O(1)` at ends | Access `O(n)`, Insert/Delete `O(1)` at known position |
| Stack | LIFO | push, pop, peek | Fixed capacity, `O(1)` | Unbounded, `O(1)` | All ops `O(1)`; call stack for recursion |
| Queue | FIFO | enqueue, dequeue, peek | Linear (waste) or Circular (modulo) | Head/tail pointers, `O(1)` | All ops `O(1)` |

✨ **Stack + Recursion Link**  
The call stack used in recursion IS a stack data structure. Each recursive call pushes a frame; each return pops it. This is why deep recursion can cause `StackOverflowError` — the stack ADT has finite capacity.

🧠 **Is a Stack linear?** YES. The ADT defines a LIFO sequence — that is linear. The implementation (array or linked list) does not change its classification.

### 2.4 — Non-Linear Structures (Why They Exist)
| Structure | Mechanism | Key Complexity | Exam Note |
|-----------|-----------|---------------|-----------|
| Binary Search Tree | Branching hierarchy; left < root < right | `O(log n)` avg search; `O(n)` unbalanced | Can degrade — needs balancing |
| Heap | Complete binary tree with heap-order property | `O(log n)` insert/extract; `O(1)` peek max | Partial order only — not fully sorted |
| Graph | Vertices connected by edges | `O(V+E)` BFS/DFS | Models networks, maps, dependencies |

---

## CHAPTER 3 — QUEUE THEORY (Linear → Circular → Linked-List)
*(Applied case study: Using Big-O and linear structure concepts to solve a real implementation problem)*

### 3.1 — Queue ADT: Definition & Operations
**Queue:** A linear ADT enforcing **FIFO** (First-In, First-Out) ordering. The element waiting longest is always removed first.

| Operation | Action | Notes |
|-----------|--------|-------|
| `enqueue(x)` | Add element x to the REAR | Fails if queue is full (bounded impl) |
| `dequeue()` | Remove and return the FRONT element | Fails if queue is empty |
| `peek()` | Return front element — NO removal | NEVER modify front — exam trap |
| `isEmpty()` | Return true iff size = 0 | `O(1)` |
| `isFull()` | Return true iff size = capacity | Relevant only for bounded arrays |
| `resize()` | Expand capacity (typically double) when full | Copy in circular order; reset `front=0`, `rear=size-1` |

**When to Use a Queue:** Any scenario where order of arrival must be preserved: CPU scheduling, print spoolers, BFS traversal, IO buffers, call-centre hold lines.

### 3.2 — Linear Array Queue: The Wasted-Space Problem
Visualise the array as a straight row of lockers numbered `0` to `capacity−1`. `front` and `rear` are index pointers; both start at `−1` (empty).

**How the Problem Emerges:**
- Each enqueue advances `rear` forward; each dequeue advances `front` forward
- After many operations: `front` may sit at index 5, `rear` at index 9 — lockers 0–4 are empty but inaccessible
- An enqueue now fails: `rear` cannot advance past the end, even though 5 vacant cells exist at the front
- Fix by shifting all elements down → `O(n)` — unacceptable for a queue

| Flaw | Effect | Cost of Workaround |
|------|--------|-------------------|
| Rear cannot wrap back | Vacated front cells permanently lost | `O(n)` shifting — defeats fast queue operations |
| False 'full' condition | Queue reports full when capacity is available | Incorrect program behaviour |

🧠 **Root cause:** Rear only moves forward. The only fix is to use modulo arithmetic to wrap it.

### 3.3 — Circular Queue: Modulo Magic
**Analogy:** Lockers arranged in a circle. After the last locker, the next is locker 0. No shifting — every vacated slot is reused automatically.

**The Mathematical Formula:**
| Movement | Formula | Example (capacity=10) |
|----------|---------|----------------------|
| Next rear position after enqueue | `(rear + 1) % capacity` | `rear=9 → next = (9+1)%10 = 0` |
| Next front position after dequeue | `(front + 1) % capacity` | `front=9 → next = (9+1)%10 = 0` |

**Step-by-Step Trace (capacity = 5):**
| Step | Operation | front | rear | Array [0..4] | Note |
|------|-----------|-------|------|--------------|------|
| 0 | (empty) | -1 | -1 | `[ , , , , ]` | Initial state |
| 1 | `enqueue(A)` | 0 | 0 | `[A, , , , ]` | First element |
| 2 | `enqueue(B)` | 0 | 1 | `[A,B, , , ]` | |
| 3 | `enqueue(C)` | 0 | 2 | `[A,B,C, , ]` | |
| 4 | `enqueue(D)` | 0 | 3 | `[A,B,C,D, ]` | |
| 5 | `dequeue()→A` | 1 | 3 | `[ ,B,C,D, ]` | Locker 0 freed |
| 6 | `enqueue(E)` | 1 | 4 | `[ ,B,C,D,E]` | rear reaches 4 |
| 7 | `dequeue()→B` | 2 | 4 | `[ , ,C,D,E]` | |
| 8 | `enqueue(F)` | 2 | 0 | `[F, ,C,D,E]` | rear WRAPS to 0 ← modulo |
| 9 | `enqueue(G)` | 2 | 1 | `[F,G,C,D,E]` | Queue FULL (5 elements) |
| 10 | `dequeue()→C` | 3 | 1 | `[F,G, ,D,E]` | |
| 12 | `dequeue()→F` | 0 | 1 | `[ ,G, , , ]` | front WRAPS to 0 |
| 14 | `dequeue()→G` | -1 | -1 | `[ , , , , ]` | Queue empty — reset |

🧠 **Key observations:** Whenever an index would reach capacity, modulo sends it to 0. Empty cells are reused immediately. The queue cycles indefinitely with no shifting.

### 3.4 — The Full-Detection Problem (Critical)
**The ambiguity:** Using only `front` and `rear`, an empty and a full circular queue can both satisfy `(rear+1) % capacity == front`. The two states are indistinguishable without extra information.

| Option | Mechanism | isEmpty Condition | isFull Condition | Trade-off |
|--------|-----------|------------------|-----------------|-----------|
| 1 — Size Counter | Maintain integer size | `size == 0` | `size == capacity` | ✅ Recommended — clean & clear |
| 2 — Sacrifice One Slot | Leave one cell unused always | `front == -1` | `(rear+1)%capacity == front` | Wastes one slot; max = `capacity−1` |
| 3 — Boolean Flag | Set `isFull` on every enqueue/dequeue | `!isFull && front==rear+1` | `isFull` flag | Extra state to maintain correctly |

✅ **Exam rule:** Always state which option you are using AND give its boolean condition explicitly. Option 1 (size counter) is the cleanest and most expected.

### 3.5 — Core Operation Mechanics (Circular, Size Counter)
**Enqueue:**
1. If full (`size == capacity`) → reject
2. If empty (`size == 0`) → set `front = rear = 0`, store element, size becomes 1
3. Otherwise → advance rear: `rear = (rear+1) % capacity`, store element, increment size
4. `front` never changes during enqueue

**Dequeue:**
1. If empty (`size == 0`) → reject
2. Retrieve element at front
3. Decrement size
4. If size becomes 0 → reset `front = rear = −1` (empty state)
5. Otherwise → advance front: `front = (front+1) % capacity`

**Peek:**
1. If empty → reject
2. Return element at front — NOTHING else. `front` is NEVER modified.  
⚠️ **Peek exam trap:** If your peek modifies front, it is actually a dequeue. This is the single most common queue exam error.

**Resize:**
1. Allocate new array of double capacity
2. Copy all elements in order starting from `front`, proceeding circularly for exactly `size` elements
3. Set `front = 0`, `rear = size−1` in the new array. Capacity updated.

### 3.6 — Linked-List Queue
Uses `head` (front) and `tail` (rear) pointers. Each node holds data + reference to next. Never full; dynamically sized.

| Operation | Behaviour |
|-----------|-----------|
| `enqueue(x)` | Create node. If empty: `head = tail = new node`. Otherwise: `tail.next = new node`; `tail = new node`. |
| `dequeue()` | Retrieve `head.data`. Advance `head = head.next`. If `head` becomes null, set `tail = null` too. |
| `peek()` | Return `head.data`. Nothing else. |
| `isFull()` | Always returns false — memory is the only limit. |

### 3.7 — Implementation Efficiency Comparison
| Implementation | Enqueue | Dequeue | Space Utilisation | Dynamic? |
|---------------|---------|---------|------------------|----------|
| Linear — with shifting | `O(1)` | `O(n)` | Full but time-costly | No |
| Linear — pointer only | `O(1)` | `O(1)` | Front cells permanently lost | No |
| Circular — modulo | `O(1)` | `O(1)` | ✅ Fully utilised | No (fixed) |
| Linked List | `O(1)` | `O(1)` | Dynamic — grows on demand | Yes |

🧠 **Key insight:** Circular queue wins on space utilisation. Linked list wins on dynamic sizing. Both are `O(1)` for all core operations.

---

## CHAPTER 4 — RECURSION vs ITERATION
*(Now that we understand linear structures and efficiency measurement, we examine the two fundamental algorithmic control flows)*

### 4.1 — The Full Comparison
| Aspect | Recursive | Iterative | Winner |
|--------|-----------|-----------|--------|
| Memory (Space) | `O(n)` — call stack grows | `O(1)` — fixed variables | ✅ Iterative |
| Stack overflow risk | Yes — for large n | None | ✅ Iterative |
| Function-call overhead | Yes — frame creation, param passing, return address | None — direct loop | ✅ Iterative |
| Readability | Elegant, mathematical — mirrors the definition | More verbose | Recursive |
| When to prefer | Trees, graphs, divide-and-conquer problems | Linear computations, production systems | Context-dependent |

### 4.2 — Factorial: Recursive
**Definition:** `factorial(n) = 1` if `n ≤ 1`; `factorial(n) = n × factorial(n−1)` if `n > 1`

**Two Phases of Execution:**
1. **Winding (building):** `factorial(n)` calls `factorial(n−1)`, which calls `factorial(n−2)`… until the base case. Each call PAUSES and waits. Call stack depth grows to `n`.
2. **Unwinding (returning):** Base case `factorial(1)` returns 1. Each paused call resumes, multiplies its `n` by the returned value, and passes the result up.

**Trace — factorial(4):**
*Phase 1 — Winding:*
- `factorial(4) → calls factorial(3)` (depth 1)
- `factorial(3) → calls factorial(2)` (depth 2)
- `factorial(2) → calls factorial(1)` (depth 3)
- `factorial(1) → returns 1 ← BASE CASE` (= 1, depth 4)

*Phase 2 — Unwinding:*
- `factorial(1) returns 1` (= 1, base)
- `factorial(2) resumes: 2 × 1` (= 2)
- `factorial(3) resumes: 3 × 2` (= 6)
- `factorial(4) resumes: 4 × 6` (= 24 ✓)

| Time | `O(n)` |
| Space | `O(n)` |

🧠 **The 'hidden' space:** `O(n)` space is not an explicit array. It is the call stack itself — automatically allocated by the runtime. Each frame stores: `n`, return address, partial multiplication result. This is the most common exam misconception.

### 4.3 — Factorial: Iterative
**Trace — n = 5:**
| Step | i | result = result × i | Value |
|------|---|---------------------|-------|
| Init | — | `result = 1` (start at 1, NOT 0) | 1 |
| 1 | 1 | `1 × 1` | 1 |
| 2 | 2 | `1 × 2` | 2 |
| 3 | 3 | `2 × 3` | 6 |
| 4 | 4 | `6 × 4` | 24 |
| 5 | 5 | `24 × 5` | 120 ✓ |
| Exit | 6 | `i > n → loop terminates` | return 120 |

| Time | `O(n)` |
| Space | `O(1)` |

⚠️ **Start result at 1 not 0.** Multiplying by 0 gives 0 for every case. Also show the loop exit condition for full marks.

### 4.4 — The 6-Point Answer (Guarantees Full Marks)
Any question comparing recursive vs iterative complexity must cover all six points:
| # | Point | One Sentence |
|---|-------|--------------|
| 1 | Recursive Time = `O(n)` | `n` recursive calls are made, each doing `O(1)` work |
| 2 | Recursive Space = `O(n)` | Call stack holds `n` frames simultaneously at peak depth |
| 3 | Iterative Time = `O(n)` | Loop runs `n` iterations, one multiplication each |
| 4 | Iterative Space = `O(1)` | Only a constant number of variables used regardless of `n` |
| 5 | Which is more space-efficient? | Iterative — by a factor of `n` |
| 6 | Why space matters? | Recursive version risks `StackOverflowError` for large `n`; iterative does not |

### 4.5 — Fibonacci: Why It Matters More Than Factorial
| Concept Tested | Recursive (Naive) | Iterative | DP (Memoised/Tabulation) |
|---------------|------------------|-----------|-------------------------|
| Time Complexity | `O(2ⁿ)` — exponential | `O(n)` | `O(n)` |
| Space Complexity | `O(n)` — call stack depth | `O(1)` — two variables | `O(n)` memo / `O(1)` tabulation |
| Why it's slow (naive) | Each call spawns two calls → complete binary tree of depth n; `fib(3)` computed multiple times | No overlapping subproblems — each value computed once | Cache prevents recomputation |
| Stack overflow? | Yes — for large n | No | No |

✨ **Recursion Tree Visual for fib(4)**
```
         fib(4)
       /        \
    fib(3)      fib(2)
   /      \     /      \
fib(2)  fib(1) fib(1)  fib(0)
 /    \
fib(1) fib(0)
```
🧠 **Why this matters:** The tree shows exponential growth — `fib(2)` is computed twice, `fib(1)` three times. This visual explains why naive recursion is `O(2ⁿ)`. Memoisation stores each result once, reducing to `O(n)`.

💡 **Exam tip:** If asked to "explain why Fibonacci is slow", draw this tree and point to the repeated subproblems.

---

## CHAPTER 5 — SORTING ALGORITHMS
*(The synthesis: Combining arrays, recursion, Big-O analysis, and structural trade-offs)*

### 5.1 — Core Concepts
| Property | Meaning | Exam Importance |
|----------|---------|----------------|
| Time Complexity | Best/average/worst — scalability with `n` | Always state all three cases |
| Space Complexity | In-place `O(1)` vs `O(n)` auxiliary | QuickSort uses `O(log n)` stack — not `O(1)` |
| Stability | Preserves relative order of equal-key elements | Merge is stable; Quick/Heap are NOT |
| Adaptivity | Performs better on nearly sorted input | Only Insertion Sort is strongly adaptive |
| In-place | Sorts using `O(1)` extra memory (excluding recursion stack) | All sorts except MergeSort and non-comparison sorts |
| Partitioning | Divide-and-conquer: split into sub-problems, solve, combine | QuickSort, MergeSort, Bucket Sort |

✨ **In-place vs Out-of-place Quick Reference**
| Algorithm | In-place? | Why? |
|-----------|-----------|--------|
| Bubble, Selection, Insertion, Quick, Heap | ✅ Yes | Only uses input array + O(1) variables |
| Merge Sort | ❌ No | Requires auxiliary array of size `n` for merging |
| Counting/Radix/Bucket | ❌ No | Requires extra arrays for counts/buckets |

### 5.2 — Quadratic Sorts (`O(n²)`)
| Algorithm | Mechanism | Best | Avg | Worst | Stable | Adaptive | When to use |
|-----------|-----------|------|-----|-------|--------|----------|-------------|
| Bubble Sort | Compare adjacent pairs; swap if out of order; largest bubbles to end each pass | `O(n)` | `O(n²)` | `O(n²)` | Yes | Weak (early exit) | Educational only |
| Shaker (Cocktail) | Bidirectional Bubble — one pass left-to-right, then right-to-left | `O(n)` | `O(n²)` | `O(n²)` | Yes | Weak | Slightly faster when small values at end |
| Selection Sort | Find minimum, swap to front; always n-1 swaps; never adaptive | `O(n²)` | `O(n²)` | `O(n²)` | No | No | When swap cost is very high |
| Insertion Sort | Build sorted prefix; shift elements right to insert each new key | `O(n)` | `O(n²)` | `O(n²)` | Yes | YES — strong | Small data or nearly sorted |

🔍 **Exam head-to-head:** All `O(n²)` worst, but **Insertion Sort** is preferred for nearly sorted data (adaptive + stable). Selection Sort wins only when minimising swaps matters. Bubble/Shaker are rarely used beyond education.

✨ **Insertion Sort Full Trace + Code**
```java
void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {      // Start from index 1
        int key = arr[i];                        // Element to insert
        int j = i - 1;
        // Shift elements > key to the right
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];                 // Shift right
            j--;
        }
        arr[j + 1] = key;                        // Insert key
    }
}
```
**Trace for [8, 3, 5, 2]:**
```
i=1, key=3:  [8,8,5,2] → insert 3 → [3,8,5,2]
i=2, key=5:  [3,8,8,2] → insert 5 → [3,5,8,2]
i=3, key=2:  [3,3,5,8] → [2,3,3,5] → insert 2 → [2,3,5,8] ✓
```
🧠 **Why it matters:** Insertion Sort is extremely efficient for small datasets (<50 elements). Many libraries (e.g., Java's `Arrays.sort()`) switch to Insertion Sort for small partitions within QuickSort.

### 5.3 — Shell Sort (Bridge Algorithm)
Generalised Insertion Sort with a gap sequence. Sorts elements far apart first, progressively reducing the gap to 1 (when it becomes standard insertion sort).
| Aspect | Detail |
|--------|--------|
| Mechanism | Insert with gap `g`; reduce `g` (e.g., `n/2→n/4→...→1`); final pass `gap=1` is nearly sorted |
| Best | `O(n log n)` with good gap sequence (e.g., Knuth: `(3^k−1)/2`) |
| Average | `O(n^(3/2))` or `O(n log² n)` depending on gap choice |
| Worst | `O(n²)` with poor gaps (e.g., Shell's original) |
| Space / Stable | `O(1)` in-place. Not stable. |
| Use case | Medium datasets; historically important bridge toward `O(n log n)` methods |

### 5.4 — The Big Three `O(n log n)` Sorts

**MergeSort**
- Mechanism: Divide array in half → recursively sort each half → merge two sorted halves in linear time
- Stable: Yes — takes left element first on ties
- Space: `O(n)` auxiliary array required
- Parallelism: Excellent — sub-arrays can be sorted independently
- Use: When stability matters, external sorting (sequential access), linked-list sorting

✨ **Merge Sort Visual Trace for [8, 3, 5, 2]**
```
Divide Phase:
[8, 3, 5, 2]
    ↓
[8, 3]        [5, 2]
   ↓            ↓
[8] [3]      [5] [2]

Merge Phase:
[3, 8]       [2, 5]
      ↓
    [2, 3, 5, 8] ✓
```
🧠 **Key insight:** The merge step compares the front of each sorted sub-array and takes the smaller element. This guarantees stability when equal elements are encountered.

| Best `Θ(n log n)` | Average `Θ(n log n)` | Worst `Θ(n log n)` | Space `O(n)` | Stable? YES |

**QuickSort**
- Mechanism: Choose pivot → partition (elements ≤ pivot left, ≥ pivot right) → recurse on both halves
- Pivot strategies: Last element (risky), random pivot (safe), median-of-three (balanced)
- Worst case trigger: Already sorted or reverse-sorted array with first/last pivot
- Stable: No
- Why fastest in practice: Cache-friendly sequential access + minimal data movement
- Expected comparisons ≈ `1.39·n·log₂n` — still `O(n log n)`

| Best `Θ(n log n)` | Average `Θ(n log n)` | Worst `O(n²)` | Space `O(log n)` | Stable? NO |

**HeapSort**
- Mechanism: Phase 1 — Build max-heap (start from last non-leaf: index `n/2−1`, work backwards). Phase 2 — Swap root (max) with last element, shrink heap, heapify from root. Repeat n times.
- ALWAYS `Θ(n log n)` — no bad input can cause degradation
- In-place: Yes — `O(1)` extra space. No auxiliary array.
- Stable: No
- Drawback: Poor cache behaviour — non-sequential heap traversal causes cache misses → ~2× slower than QuickSort in practice

| Best `Θ(n log n)` | Average `Θ(n log n)` | Worst `Θ(n log n)` | Space `O(1)` | Stable? NO |

**Head-to-Head: The Big Three (Exam Gold)**
| Comparison | Preferred Choice | Key Reason |
|------------|----------------|------------|
| Merge vs Heap | Merge (stability + cache); Heap (space) | Merge: stable, cache-friendly. Heap: in-place, guaranteed |
| Quick vs Heap | Quick (average speed); Heap (worst-case safety) | Quick ≈ `1.39n log n` avg; Heap never degrades to `O(n²)` |
| Merge vs Quick | Merge (stability + guarantee); Quick (speed + space) | Merge: stable, guaranteed `O(n log n)`. Quick: in-place, faster avg |
| All three vs Bubble | Any of the three | Bubble/Insertion: `O(n²)` worst — unacceptable for large n |

### 5.5 — Non-Comparison Sorts (Linear Under Conditions)
🧠 **Why they beat `Ω(n log n)`:** The comparison-sorting lower bound only applies when the only information extracted per step is one comparison result. Non-comparison sorts extract more information per operation (key indexing, digit extraction) — they bypass the limit entirely.

| Sort | Mechanism | Time | Space | Stable | Key Requirement |
|------|-----------|------|-------|--------|----------------|
| Counting Sort | Count occurrences; compute prefix sums; place elements | `O(n+k)` | `O(n+k)` | Yes | Integer keys in range `[0, k)` |
| Radix Sort | Sort digit by digit (LSD→MSD) using stable sub-sort | `O(d·(n+k))` | `O(n+k)` | Yes | Fixed-width digit keys; base `b` |
| Bucket Sort | Scatter into buckets; sort each bucket; concatenate | `O(n)` avg / `O(n²)` worst | `O(n)` | Depends | Uniform distribution assumed |

✨ **Why O(n log n) is the "Sorting Barrier"**  
**The Comparison-Sorting Lower Bound:** No comparison-based sorting algorithm can beat `Ω(n log n)` in the worst case.  
**Proof intuition:** To distinguish between `n!` possible permutations of `n` elements, a comparison tree must have at least `n!` leaves. The minimum height of such a tree is `log₂(n!) ≈ n log n` (by Stirling's approximation).  

✨ **How non-comparison sorts beat it:** They extract more than 1 bit of information per operation (e.g., key indexing in Counting Sort, digit extraction in Radix Sort). This bypasses the comparison limit but requires special constraints on the data.

🧠 **Exam application:** If asked "Can we sort faster than O(n log n)?", answer: "Only if we use non-comparison sorts like Counting/Radix/Bucket, which require integer keys, fixed-width digits, or uniform distribution respectively."

### 5.6 — Master Complexity Table (2025 Exam Reference)
| Algorithm | Best | Average | Worst | Space | Stable | In-place |
|-----------|------|---------|-------|-------|--------|----------|
| Bubble Sort | `O(n)` | `O(n²)` | `O(n²)` | `O(1)` | Yes | Yes |
| Shaker Sort | `O(n)` | `O(n²)` | `O(n²)` | `O(1)` | Yes | Yes |
| Selection Sort | `O(n²)` | `O(n²)` | `O(n²)` | `O(1)` | No | Yes |
| Insertion Sort | `O(n)` | `O(n²)` | `O(n²)` | `O(1)` | Yes | Yes |
| Shell Sort | `O(n log n)` | `O(n^(3/2))` | `O(n²)` | `O(1)` | No | Yes |
| Merge Sort | `O(n log n)` | `O(n log n)` | `O(n log n)` | `O(n)` | Yes | No |
| Quick Sort | `O(n log n)` | `O(n log n)` | `O(n²)` ⚠️ | `O(log n)` | No | Yes |
| Heap Sort | `O(n log n)` | `O(n log n)` | `O(n log n)` | `O(1)` | No | Yes |
| Counting Sort | `O(n+k)` | `O(n+k)` | `O(n+k)` | `O(n+k)` | Yes | No |
| Radix Sort | `O(d(n+k))` | `O(d(n+k))` | `O(d(n+k))` | `O(n+k)` | Yes | No |
| Bucket Sort | `O(n)` | `O(n)` | `O(n²)` | `O(n)` | Usually | No |

### 5.7 — Decision Matrix: When to Use Which Sort
| Requirement | Best Choice | Why |
|-------------|-------------|-------|
| Small / nearly sorted data | Insertion Sort | Adaptive `O(n)` on nearly sorted; stable |
| Medium data, simple improvement | Shell Sort | Better than quadratic, simpler than `O(n log n)` sorts |
| Must be stable | Merge Sort | Only stable `O(n log n)` sort |
| In-place + worst-case guarantee | Heap Sort | Always `O(n log n)`; only `O(1)` space with no worst-case risk |
| Fastest average + in-place | Quick Sort (random pivot) | Cache-friendly; ~`1.39n log n` expected |
| Integer keys, limited range | Counting / Radix Sort | Linear time when constraints met |
| Uniform distribution | Bucket Sort | Linear average time |

### 5.8 — Sorting Exam Traps
| Trap | Correct Statement |
|------|-----------------|
| QuickSort uses `O(1)` space | Wrong — it uses `O(log n)` average stack space for recursion |
| HeapSort is stable | Wrong — HeapSort is NOT stable. For stable `O(n log n)`, use MergeSort |
| Forgetting to state pivot assumption | Always write: `'Assuming random pivot for QuickSort...'` |
| Not showing array after each pass | Trace questions want array state after EVERY pass/partition/extraction |
| Confusing best/avg/worst | Always state all three for any sort; they differ for QuickSort and Shell |

---

## CHAPTER 6 — EXAM TECHNIQUE & CHEAT SHEET
*(Final review: Strategy, rapid recall, and cross-referencing the build-up)*

### 6.1 — Closed-Book Exam Strategies
| # | Strategy | Why It Wins Marks |
|---|----------|----------------|
| 1 | Always write the base case for any recursion | Missing base case = incomplete answer |
| 2 | Draw call stack for recursive traces — show BOTH winding AND unwinding | Unwinding phase is where marks live |
| 3 | Trace sorting with a table — show array state after every pass/partition | Examiners check each step, not just final result |
| 4 | Justify complexity with reasoning, not just the symbol | `'O(n²) because two nested loops each running n times' > 'O(n²)'` |
| 5 | State assumptions | `'Assuming random pivot...'` or `'Assuming nearly sorted input...'` |
| 6 | For sorting compare questions: use a table (Time, Space, Stable, In-place, Cache, Worst guarantee) | Structured comparison earns all comparison marks |
| 7 | Link to real-world use | Queue = print spooler; Heap = priority queue; Merge = external sort |

### 6.2 — Must-Know One-Liners (Closed Book Memory Anchors)
| Concept | One-Liner |
|---------|-----------|
| Queue | FIFO linear ADT — enqueue at rear, dequeue from front; circular array reuses space via `(index+1)%capacity` |
| Stack | LIFO linear ADT — push and pop at the same end (top) |
| ADT | Specifies WHAT operations a structure supports — not HOW it is built |
| Big-O loops | Sequential = ADD. Nested = MULTIPLY. Multiplicative step = `O(log n)`. |
| Recursion | Call stack grows with depth. Always need a base case. Space = max depth. |
| Factorial | Recursive: `O(n)` time, `O(n)` space (hidden call stack). Iterative: `O(n)` time, `O(1)` space. |
| Fibonacci | Naive: `O(2ⁿ)`. DP/Iterative: `O(n)`. Overlapping subproblems = exponential blow-up without caching. |
| QuickSort | Pivot + partition. Average `O(n log n)`. Worst `O(n²)` (bad pivot). In-place, unstable. |
| HeapSort | Build max-heap + extract. ALWAYS `O(n log n)`. In-place, unstable, cache-poor. |
| MergeSort | Divide + merge. ALWAYS `O(n log n)`. Stable. Requires `O(n)` extra space. |
| Stability | Stable: Bubble, Shaker, Insertion, Merge. Unstable: Selection, Shell, Quick, Heap. |
| In-place | All sorts except MergeSort and non-comparison sorts use `O(1)` extra (QuickSort stack is `O(log n)`). |
| Lower bound | No comparison sort can beat `Ω(n log n)` worst-case. Counting/Radix/Bucket bypass this via key structure. |

### 6.3 — Complete Coverage Index
| Question Type | Where to Find It |
|--------------|-----------------|
| What is a Queue / Stack / ADT? | Chapter 2 (§2.1, §2.3) + Chapter 3 (§3.1) |
| Why circular queue? | Chapter 3 (§3.2 — linear queue failure, §3.3 — modulo solution) |
| How does circular queue work? | Chapter 3 (§3.3 trace, §3.4 full detection, §3.5 operations) |
| Big-O of any code snippet? | Chapter 1 (§1.2 rules, §1.3 past exam patterns) |
| Recursive vs iterative comparison? | Chapter 4 (§4.1 table, §4.4 six-point answer) |
| Factorial trace? | Chapter 4 (§4.2 recursive trace, §4.3 iterative trace) |
| Fibonacci complexity + recursion tree? | Chapter 4 (§4.5) |
| Which sorting algorithm to choose? | Chapter 5 (§5.7 decision matrix) + Chapter 7 (§7.1 Q5) |
| Sort trace (Bubble/Quick/Heap)? | Chapter 5 (§5.2–§5.4 mechanism descriptions) |
| Sorting complexity comparison? | Chapter 5 (§5.6 master table, §5.4 head-to-heads) |
| Stable vs unstable? | Chapter 5 (§5.1, §5.6) + Chapter 6 (§6.2 one-liners) |

---

## ✨ CHAPTER 7 — EXAM PREDICTIONS & MODEL ANSWERS
*Based on past papers, lecturer patterns, and high-yield topic analysis*

### 7.1 — The 5 Most Likely Exam Questions (With Model Answers)

#### 🔹 QUESTION 1: Big-O Analysis of Code Snippets
**Typical prompt:**  
*"Determine the time complexity of the following code fragments. Show your reasoning."*

```java
// Fragment A
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j *= 2) {
        // constant time operation
    }
}

// Fragment B
int i = n;
while (i > 0) {
    // constant time
    i /= 2;
}
for (int k = 0; k < n; k++) {
    // constant time
}
```

✅ **Model Answer Structure:**

Fragment A:
- Outer loop: i runs 0 to n-1 → O(n)
- Inner loop: j starts at 0, multiplies by 2 each iteration → O(log n)
- Nested → MULTIPLY: O(n) × O(log n) = O(n log n)

Fragment B:
- First loop: i halves each iteration → O(log n)
- Second loop: k runs 0 to n-1 → O(n)
- Sequential → ADD: O(log n) + O(n) = O(n) [drop lower-order term]

Final: Fragment A = O(n log n), Fragment B = O(n)

🔍 **What examiner tests:** Ability to distinguish sequential (ADD) vs nested (MULTIPLY) loops; recognition of logarithmic patterns; simplification rules.

---

#### 🔹 QUESTION 2: Recursion Trace and Comparison
**Typical prompt:**  
*"Given the recursive function below, trace the computation for factorial(5). Then compare recursive and iterative implementations in terms of time and space complexity."*

```java
int fact(int n) {
    if (n <= 1) return 1;
    return n * fact(n - 1);
}
```

✅ **Model Answer Structure:**

TRACE for factorial(5):
WINDING PHASE (call stack builds):
fact(5) → calls fact(4) [depth 1]
fact(4) → calls fact(3) [depth 2]
fact(3) → calls fact(2) [depth 3]
fact(2) → calls fact(1) [depth 4]
fact(1) → returns 1 [BASE CASE, depth 5]

UNWINDING PHASE (returns propagate):
fact(1) = 1
fact(2) = 2 × 1 = 2
fact(3) = 3 × 2 = 6
fact(4) = 4 × 6 = 24
fact(5) = 5 × 24 = 120 ✓

COMPLEXITY COMPARISON:
- Time: Both O(n) — n operations total
- Space: Recursive O(n) [call stack depth], Iterative O(1) [fixed variables]
- Conclusion: Iterative is preferable for large n due to no stack overflow risk

🔍 **What examiner tests:** Understanding of call stack mechanics; ability to show BOTH winding and unwinding; space vs time distinction.

---

#### 🔹 QUESTION 3: Manual Sorting Trace (Bubble or Insertion)
**Typical prompt:**  
*"Show the step-by-step execution of Bubble Sort on the array [29, 10, 14, 37, 13]. Indicate which elements are sorted after each pass."*

✅ **Model Answer Structure:**
```
Initial: [29, 10, 14, 37, 13]

Pass 1 (bubble largest to end):
  29,10 → swap → [10, 29, 14, 37, 13]
  29,14 → swap → [10, 14, 29, 37, 13]
  29,37 → ok   → [10, 14, 29, 37, 13]
  37,13 → swap → [10, 14, 29, 13 | 37] ← sorted portion

Pass 2:
  10,14 → ok   → [10, 14, 29, 13 | 37]
  14,29 → ok   → [10, 14, 29, 13 | 37]
  29,13 → swap → [10, 14, 13 | 29, 37] ← sorted

Pass 3:
  10,14 → ok   → [10, 14, 13 | 29, 37]
  14,13 → swap → [10, 13 | 14, 29, 37] ← sorted

Pass 4:
  10,13 → ok   → [10 | 13, 14, 29, 37] ← fully sorted

Final: [10, 13, 14, 29, 37]
Complexity: Worst O(n²), Best O(n) with early-exit optimization
```
🔍 **What examiner tests:** Ability to trace algorithm step-by-step; use of visual markers (|) for sorted portion; complexity justification.

---

#### 🔹 QUESTION 4: Circular Queue Implementation
**Typical prompt:**  
*"Implement a circular queue using an array. Provide the enqueue, dequeue, and peek methods. Explain how you handle full and empty conditions."*

✅ **Model Answer Structure:**
```java
public class CircularQueue {
    private int[] elements;
    private int front = 0, rear = -1, size = 0;
    private int capacity;
    
    public CircularQueue(int cap) {
        capacity = cap;
        elements = new int[capacity];
    }
    
    // ENQUEUE: Add to rear
    public void enqueue(int value) {
        if (isFull()) throw new IllegalStateException("Full");
        rear = (rear + 1) % capacity;  // ← Modulo wrap
        elements[rear] = value;
        size++;
    }
    
    // DEQUEUE: Remove from front
    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Empty");
        int item = elements[front];
        front = (front + 1) % capacity;  // ← Modulo wrap
        size--;
        return item;
    }
    
    // PEEK: View front WITHOUT modifying ✨ CRITICAL
    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Empty");
        return elements[front];  // ← NO modification of front!
    }
    
    // Full/Empty using size counter (recommended approach)
    public boolean isFull() { return size == capacity; }
    public boolean isEmpty() { return size == 0; }
}
```

🧠 **Key points to mention:**
- Use `size` counter to avoid ambiguity between empty/full states
- Modulo operator `%` enables circular wrap-around
- `peek()` must NOT modify `front` — common exam trap
- Time complexity: All operations `O(1)`

🔍 **What examiner tests:** Understanding of modulo arithmetic; state management (size vs front/rear comparison); peek() correctness.

---

#### 🔹 QUESTION 5: Algorithm Selection & Justification
**Typical prompt:**  
*"You need to sort a large list of 1 million records. Which sorting algorithm would you choose and why? Discuss time and space complexity, stability, and worst-case behavior."*

✅ **Model Answer Structure:**

REQUIREMENTS ANALYSIS:
- Large dataset (n = 1,000,000) → need O(n log n) algorithm
- Stability? If sorting by multiple keys (e.g., name then grade), stability matters
- Memory constraints? If limited, prefer in-place algorithms
- Worst-case tolerance? If critical, avoid algorithms with O(n²) worst case

ALGORITHM COMPARISON:
| Algorithm | Time (Avg/Worst) | Space | Stable? | In-place? | Suitability |
|-----------|-----------------|-------|---------|-----------|-------------|
| QuickSort | O(n log n) / O(n²) | O(log n) | No | Yes | ⚠️ Risk of O(n²) on sorted data |
| MergeSort | O(n log n) / O(n log n) | O(n) | Yes | No | ✅ Stable, guaranteed, but needs memory |
| HeapSort  | O(n log n) / O(n log n) | O(1) | No | Yes | ✅ Guaranteed + in-place, but slower constant 

> 🤖 **GenAI note:** This project was built with the help of generative AI.  
> Human: design, testing, accountability. AI: brainstorming, code drafts, review.  
> See [LICENSE](./LICENSE) for full details.