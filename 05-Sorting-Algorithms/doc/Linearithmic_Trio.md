# Theoretical Recap — Linearithmic Trio [O(n log n)]

> **Focus:** Merge Sort, Quick Sort, Heap Sort  
> **Context:** Intermediate academic sorting algorithms – Chapter 5  
> **Exam weight:** Very High (traces, comparisons, pivot selection, stability, worst‑case guarantees)

---

## 1. Shared Properties & Key Differences

| Property            | Merge Sort           | Quick Sort              | Heap Sort              |
|---------------------|----------------------|-------------------------|------------------------|
| **Best case**       | O(n log n)           | O(n log n)              | O(n log n)             |
| **Average case**    | O(n log n)           | O(n log n)              | O(n log n)             |
| **Worst case**      | O(n log n)           | ⚠️ O(n²)                | O(n log n)             |
| **Space**           | O(n) auxiliary       | O(log n) stack (avg)    | O(1) (in‑place)        |
| **Stable?**         | ✅ Yes                | ❌ No                    | ❌ No                   |
| **In‑place?**       | ❌ No                 | ✅ Yes                   | ✅ Yes                  |
| **Recursive?**      | Yes (divide & merge) | Yes (pivot & partition) | No (iterative heapify) |
| **Data movement**   | Merge (copy to aux)  | Swap (partition)        | Swap (heap extraction) |
| **Cache behaviour** | Good (sequential)    | Excellent (sequential)  | Poor (random access)   |

All three are **comparison‑based** and achieve **O(n log n)** average time, but they differ fundamentally in **worst‑case safety**, **memory usage**, **stability**, and **practical constants**.

---

## 2. Merge Sort

### 2.1 Mechanism
A **divide‑and‑conquer** algorithm.
1. **Divide:** Recursively split the array into two halves until each sub‑array has 1 element (trivially sorted).
2. **Conquer:** Merge two sorted halves by repeatedly taking the smaller front element from each, building a larger sorted array.
3. The recursion bottoms out at single elements, then merges back up the call stack.

### 2.2 Complexity & Behaviour

| Case        | Time       | Reason                                                                                         |
|-------------|------------|------------------------------------------------------------------------------------------------|
| **Best**    | Θ(n log n) | Even if sorted, division happens, and each merge level does O(n) work; there are log n levels. |
| **Worst**   | Θ(n log n) | Same structure — merging always linear.                                                        |
| **Average** | Θ(n log n) | Dominated by merge steps, independent of input order.                                          |

### 2.3 Stability & Memory
- **Stable:** ✅ Yes – during merging, when two elements are equal, the algorithm consistently takes the element from the **left** sub‑array first, preserving original relative order.
- **In‑place:** ❌ No – standard implementation requires an auxiliary array of size O(n) for merging.
- **Main weakness:** O(n) extra memory can be prohibitive for large datasets or memory‑constrained environments.

### 2.4 Trace Example (n=4)
Data: `[5, 3, 8, 1]`

```
Divide:
[5, 3, 8, 1]
→ [5, 3]      [8, 1]
→ [5] [3]    → [8] [1]

Merge:
[5] & [3] → [3, 5]
[8] & [1] → [1, 8]
[3, 5] & [1, 8] → [1, 3, 5, 8]  ← sorted
```

> 🔍 **Exam tip:** Always show **both** the divide and the merge phases. Label the recursion tree clearly.

---

## 3. Quick Sort

### 3.1 Mechanism
A **divide‑and‑conquer** algorithm that uses a **pivot**.
1. Choose a **pivot** element (common strategies: last element, random, median‑of‑three).
2. **Partition** the array into two parts: elements ≤ pivot on the left, elements ≥ pivot on the right.
3. The pivot is now in its correct sorted position.
4. **Recursively** apply the same process to the left and right sub‑arrays.

### 3.2 Complexity & Behaviour

| Case        | Time       | Reason                                                                                                                                              |
|-------------|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| **Best**    | Θ(n log n) | Pivot always splits the array into two roughly equal halves → recursion depth log n, O(n) work per level.                                           |
| **Worst**   | O(n²)      | Pivot is always the smallest or largest element (e.g., already sorted array with last‑element pivot) → recursion depth n, O(n) work per level → n². |
| **Average** | Θ(n log n) | With random pivot, expected depth is log n. Expected comparisons ≈ 1.39 n log n.                                                                    |

### 3.3 Stability & Memory
- **Stable:** ❌ No – partitioning can reorder equal elements (e.g., swapping around the pivot breaks original order).
- **In‑place:** ✅ Yes – partitioning uses O(1) extra space for indices; recursion stack uses O(log n) space on average, O(n) worst.
- **Main weakness:** Susceptible to O(n²) worst‑case if pivot selection is poor. Mitigated by randomisation or median‑of‑three.

### 3.4 Trace Example (Lomuto partition, pivot = last)
Data: `[4, 7, 2, 9, 5]`, pivot = `5`

```
i = -1 (boundary for smaller elements)

j=0: 4 < 5 → i=0, swap(0,0) → [4, 7, 2, 9, 5]
j=1: 7 ≥ 5 → skip
j=2: 2 < 5 → i=1, swap(1,2) → [4, 2, 7, 9, 5]
j=3: 9 ≥ 5 → skip
End: swap(i+1, pivot) → swap(2,4) → [4, 2, 5, 9, 7]

Pivot 5 now at index 2 (final position).
Left: [4, 2]   Right: [9, 7]
→ Recursively sort left and right.
```

> 💡 **Key insight:** Quick Sort’s speed in practice comes from **excellent cache locality** (sequential memory access during partitioning) and minimal data movement. It makes about 1.39 n log n comparisons on average, which is slightly more than Merge Sort’s ~n log n, but the cache advantage usually makes it faster.

---

## 4. Heap Sort

### 4.1 Mechanism
Uses a **binary heap** data structure.
1. **Build a max‑heap** from the input array (so largest element is at the root, index 0).
2. Repeatedly **swap** the root (maximum) with the last unsorted element, reduce heap size by one, and **restore** the heap property by “sifting down” the new root.
3. After n‑1 extractions, the array is sorted in ascending order.

### 4.2 Complexity & Behaviour

| Case        | Time       | Reason                                                                                            |
|-------------|------------|---------------------------------------------------------------------------------------------------|
| **Best**    | Θ(n log n) | Build‑heap is O(n); each of the n‑1 extractions does a sift‑down of O(log n). Total = O(n log n). |
| **Worst**   | Θ(n log n) | Sift‑down never exceeds O(log n). No input can force worse behaviour.                             |
| **Average** | Θ(n log n) | Always Θ(n log n) — consistent and predictable.                                                   |

### 4.3 Stability & Memory
- **Stable:** ❌ No – heap operations (swapping root with last element, sifting) do not preserve the relative order of equal elements.
- **In‑place:** ✅ Yes – the array itself is used to represent the heap; only O(1) extra space for loop variables.
- **Main weakness:** Not stable. In practice, about **2× slower** than Quick Sort due to **poor cache behaviour** (non‑sequential memory access during sift‑down).

### 4.4 Trace Example
Data: `[7, 5, 15, 20, 16, 17, 8, 3]`

**Phase 1: Build Max‑Heap**
```
Initial tree:      7
/   \
5     15
/ \   /  \
20  16 17   8
/
3

After build-heap: [20, 16, 17, 5, 7, 15, 8, 3]
```
**Phase 2: Extract Max (repeatedly)**
```
Swap 20 with 3, sift down → [17, 16, 15, 5, 7, 3, 8 | 20]
Swap 17 with 8, sift down → [16, 8, 15, 5, 7, 3 | 17, 20]
... continue ...
Final sorted array: [3, 5, 7, 8, 15, 16, 17, 20]
```

> 🔍 **Exam tip:** For full marks in a Heap Sort trace, show both the **build‑heap** phase (starting from the last non‑leaf node, index `n/2 - 1`) and the **extraction** phase with the sorted boundary marker `|`.

---

## 5. Head‑to‑Head Comparisons (Exam Gold)

| Comparison         | Preferred                                                    | Reasoning                                                                                                           |
|--------------------|--------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| **Merge vs Heap**  | **Merge** (stability, cache) or **Heap** (memory)            | Merge: stable, typically faster (sequential access). Heap: in‑place O(1) space, guaranteed O(n log n).              |
| **Quick vs Heap**  | **Quick** (average speed) or **Heap** (worst‑case safety)    | Quick: faster avg (~1.39 n log n comparisons), excellent cache. Heap: never degrades to O(n²), strictly O(1) space. |
| **Merge vs Quick** | **Merge** (stability, guarantee) or **Quick** (speed, space) | Merge: stable, guaranteed Θ(n log n). Quick: in‑place, faster on average, but unstable and O(n²) worst.             |

### The definitive statement to memorise:
> *“Merge Sort is the only stable one with guaranteed O(n log n), but requires O(n) extra memory. Quick Sort is the fastest on average and in‑place, but can degrade to O(n²) without a good pivot. Heap Sort is the safest in‑place option with no worst‑case surprises, but is slower in practice and not stable.”*

---

## 6. Quick Decision Matrix

| Requirement                                      | Best Choice                                            |
|--------------------------------------------------|--------------------------------------------------------|
| Stability essential                              | **Merge Sort**                                         |
| In‑place + worst‑case O(n log n) guarantee       | **Heap Sort**                                          |
| Fastest average + in‑place + can randomise pivot | **Quick Sort** (random pivot)                          |
| External sorting (disk, sequential access)       | **Merge Sort**                                         |
| Memory extremely constrained                     | **Heap Sort** (or Quick with iterative implementation) |

---

## 7. Common Exam Traps

| Trap                                         | Correction                                                                     |
|----------------------------------------------|--------------------------------------------------------------------------------|
| “Quick Sort uses O(1) space”                 | ❌ – It uses **O(log n)** average stack space for recursion; worst‑case O(n).   |
| “Heap Sort is stable”                        | ❌ – Heap Sort is **not** stable. For stable O(n log n), use Merge Sort.        |
| Forgetting to state pivot assumption         | ✅ Always write: *“Assuming random pivot (or median‑of‑three) for Quick Sort.”* |
| Saying Quick Sort always runs in O(n log n)  | ❌ – Only if the pivot splits the array evenly; worst case is O(n²).            |
| Not marking the sorted boundary in Heap Sort | ✅ In extraction phase, use `                                                   |` to separate sorted (right) from heap (left). |
| Confusing space of Merge vs Quick            | Merge = O(n) auxiliary array; Quick = O(log n) call stack.                     |
