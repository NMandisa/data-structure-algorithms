# Theoretical Recap — Advanced & Rarely‑Examined Sorting Algorithms

> **Focus:** Counting Sort, Radix Sort, Bucket Sort (Advanced) + Shell, Comb, Cocktail, Gnome, Tree, Tournament (Rarely Examined)  
> **Context:** Chapter 5 – Sorting Algorithms  
> **Exam weight:** Tier 3 (Possible Theory Question) for advanced sorts; the rest are low‑probability but can appear as concept checks.

---

## PART A – Advanced / Theoretical Academic Sorts

These algorithms **bypass the Ω(n log n) lower bound** because they are **not comparison‑based**. They exploit structural properties of the keys (ranges, digits, distribution) to achieve linear time under specific conditions.

---

### A1. Counting Sort

**Mechanism**
1. **Count occurrences** of each distinct key in the input (keys must be integers in range `[0, k]`).
2. Compute **prefix sums** over the count array to determine the starting index of each key in the sorted output.
3. Traverse the input **in reverse** (for stability), placing each element directly into its correct output position using the prefix‑sum mapping, decrementing the mapping after each placement.

**Complexity**

| Case    | Time     | Reason                                                 |
|---------|----------|--------------------------------------------------------|
| Best    | O(n + k) | Counting takes O(n), prefix sums O(k), placement O(n). |
| Worst   | O(n + k) | Same steps; independent of input order.                |
| Average | O(n + k) | Always O(n + k).                                       |

**Stability & Memory**
- **Stable:** ✅ Yes – reverse traversal + decrementing mapping ensures equal elements keep original relative order.
- **In‑place:** ❌ No – requires an output array of size `n` and a count array of size `k+1` → **O(n + k) extra space**.
- **Main weakness:** Only works when the key range is **small and discrete** (k ≈ n). Large `k` (e.g., 10⁹) makes it impractical, and it cannot handle non‑integer keys directly.

---

### A2. Radix Sort

**Mechanism**
1. Sort numbers **digit by digit**, starting from the **least significant digit (LSD)** or most significant (MSD).
2. Use a **stable sub‑sort** (usually Counting Sort) on each digit.
3. The stability of the sub‑sort guarantees that higher‑order digits do not disrupt the ordering established by lower‑order digits.

*Example:* LSD Radix Sort on `[170, 45, 75, 90, 802, 24, 2, 66]` with base 10:
- Pass 1 (units): `[170, 90, 802, 2, 24, 45, 75, 66]`
- Pass 2 (tens): `[802, 2, 24, 45, 66, 170, 75, 90]`
- Pass 3 (hundreds): `[2, 24, 45, 66, 75, 90, 170, 802]`

**Complexity**

| Case    | Time           | Reason                                                          |
|---------|----------------|-----------------------------------------------------------------|
| Best    | O(d · (n + k)) | `d` digits, each digit pass does O(n + k) work (Counting Sort). |
| Worst   | O(d · (n + k)) | Same steps; no data‑dependent degradation.                      |
| Average | O(d · (n + k)) | Always O(d·(n+k)).                                              |

- For fixed‑width integers (e.g., 32‑bit), `d` is constant → **O(n)** when k = O(n).

**Stability & Memory**
- **Stable:** ✅ Yes – provided the underlying digit sort is stable (Counting Sort is).
- **In‑place:** ❌ No – requires auxiliary arrays for counting and output per digit pass → **O(n + k) extra space**.
- **Main weakness:** Works only for data that can be decomposed into **digits or fixed‑length keys**; inefficient if keys are very long or the radix (alphabet) is huge.

---

### A3. Bucket Sort

**Mechanism**
1. Scatter input elements into a number of **buckets** (often by range partitioning or a hash function).
2. **Sort each bucket** individually (often with Insertion Sort).
3. **Concatenate** buckets in order.

Assumes input is **uniformly distributed** across the bucket range; otherwise performance degrades.

**Complexity**

| Case    | Time  | Reason                                                                                                  |
|---------|-------|---------------------------------------------------------------------------------------------------------|
| Best    | O(n)  | Uniform distribution → each bucket has O(1) elements → sorting each bucket is O(1).                     |
| Worst   | O(n²) | All elements fall into a single bucket → reduces to the inner sort’s worst case (e.g., Insertion Sort). |
| Average | O(n)  | Expected under uniform distribution.                                                                    |

**Stability & Memory**
- **Stable:** Depends on inner sort. If a stable inner sort is used and concatenation preserves order → **usually stable**.
- **In‑place:** ❌ No – requires extra memory for the buckets (total O(n) for elements, plus overhead).
- **Main weakness:** Highly sensitive to the **distribution of data**; skewed data causes O(n²). Also, choosing the optimal number of buckets and a good distribution function is non‑trivial.

---

### A4. Advanced Sorts Complexity Table

| Algorithm     | Best       | Average    | Worst      | Space  | Stable  | Key Requirement         |
|---------------|------------|------------|------------|--------|---------|-------------------------|
| Counting Sort | O(n+k)     | O(n+k)     | O(n+k)     | O(n+k) | ✅ Yes   | Integer keys in `[0,k]` |
| Radix Sort    | O(d·(n+k)) | O(d·(n+k)) | O(d·(n+k)) | O(n+k) | ✅ Yes   | Fixed‑width digit keys  |
| Bucket Sort   | O(n)       | O(n)       | O(n²)      | O(n)   | Usually | Uniform distribution    |

> 🔍 **Why they beat Ω(n log n):** Non‑comparison sorts extract more than 1 bit of information per operation (key indexing, digit extraction) – they do not rely on pairwise comparisons. The Ω(n log n) barrier only applies to comparison‑based sorting.

---

## PART B – Rarely Examined but Sometimes Mentioned

These algorithms are less common in exams but can appear as **concept‑check questions** or for **completeness**.

### B1. Shell Sort
- **Mechanism:** Generalised Insertion Sort. First sorts elements far apart (gap sequence), then reduces gap to 1. Final pass is standard Insertion on a nearly sorted array.
- **Best:** O(n log n) with good gap sequence (e.g., Knuth’s).
- **Average:** Depends on gap; often O(n^(4/3)) or O(n log² n).
- **Worst:** O(n²) for poor gaps.
- **Stable:** ❌ No.
- **In‑place:** ✅ Yes (O(1) extra).
- **Main weakness:** Gap selection is complex; not stable.

### B2. Comb Sort
- **Mechanism:** Bubble Sort variant with a shrinking gap (initial gap = n/1.3). The gap reduces by factor 1.3 each pass until it becomes 1 (standard Bubble).
- **Best:** O(n log n).
- **Average:** O(n² / 2^p) ≈ still O(n²) in worst.
- **Worst:** O(n²).
- **Stable:** ❌ No.
- **In‑place:** ✅ Yes.
- **Main weakness:** Quadratic worst‑case; gap factor must be tuned carefully.

### B3. Cocktail (Shaker) Sort
- **Mechanism:** Bidirectional Bubble Sort – one pass left‑to‑right, then right‑to‑left. Moves both large and small elements faster.
- **Best:** O(n) (already sorted).
- **Average/Worst:** O(n²).
- **Stable:** ✅ Yes.
- **In‑place:** ✅ Yes.
- **Main weakness:** Still quadratic; only marginally better than Bubble.

### B4. Gnome Sort
- **Mechanism:** Like Insertion Sort but moves backward to correct position rather than shifting. If `arr[i] < arr[i-1]`, swap and go one step back; otherwise move forward.
- **Best:** O(n) (sorted).
- **Average/Worst:** O(n²).
- **Stable:** ✅ Yes.
- **In‑place:** ✅ Yes.
- **Main weakness:** Quadratic time; inefficient for large data.

### B5. Tree Sort
- **Mechanism:** Build a **binary search tree (BST)** from the elements, then perform an **in‑order traversal** to retrieve sorted data.
- **Best:** O(n log n) if tree is balanced.
- **Average:** O(n log n) with balanced tree.
- **Worst:** O(n²) if the tree degenerates into a linked list (e.g., already sorted input without balancing).
- **Stable:** Usually yes if duplicates are consistently placed (depends on BST insertion policy).
- **In‑place:** ❌ No – tree nodes require O(n) extra memory.
- **Main weakness:** Worst‑case O(n²); requires self‑balancing BST (AVL, Red‑Black) for guaranteed O(n log n).

### B6. Tournament Sort
- **Mechanism:** Uses a **tournament tree** (winner tree) – conceptually similar to Heap Sort but builds a full binary tree of winners. Repeatedly extracts the overall winner, replaces it with a new element, and replays matches.
- **Best:** O(n log n).
- **Average:** O(n log n).
- **Worst:** O(n log n).
- **Stable:** ❌ No.
- **In‑place:** ❌ No – requires extra space for the tournament tree (up to 2n).
- **Main weakness:** More complex than Heap Sort with higher constant overhead; rarely used in practice.

### B7. Rarely Examined Summary Table

| Algorithm       | Best       | Average     | Worst      | Stable  | In‑place | Main Weakness                          |
|-----------------|------------|-------------|------------|---------|----------|----------------------------------------|
| Shell Sort      | O(n log n) | ~O(n^(4/3)) | O(n²)      | No      | Yes      | Gap selection complex; unstable        |
| Comb Sort       | O(n log n) | O(n²)       | O(n²)      | No      | Yes      | Still quadratic; gap tuning needed     |
| Cocktail Sort   | O(n)       | O(n²)       | O(n²)      | Yes     | Yes      | Quadratic, only marginal improvement   |
| Gnome Sort      | O(n)       | O(n²)       | O(n²)      | Yes     | Yes      | Quadratic, inefficient                 |
| Tree Sort       | O(n log n) | O(n log n)  | O(n²)      | Usually | No       | Degenerates to O(n²) without balancing |
| Tournament Sort | O(n log n) | O(n log n)  | O(n log n) | No      | No       | High overhead, more complex than Heap  |

---

## C. Exam Traps & Quick Revision

| Trap                                | Correct Statement                                                                                                            |
|-------------------------------------|------------------------------------------------------------------------------------------------------------------------------|
| “Counting Sort is in‑place”         | ❌ — Requires O(n+k) extra memory.                                                                                            |
| “Radix Sort works on any data type” | ❌ — Only on keys that can be decomposed into digits/fixed‑length fields.                                                     |
| “Bucket Sort is always O(n)”        | ❌ — Only with **uniform distribution**; worst case O(n²).                                                                    |
| “Shell Sort is stable”              | ❌ — It is **not** stable because elements move long distances during gap insertion.                                          |
| “Tree Sort is always O(n log n)”    | ❌ — Without a self‑balancing BST, worst case is O(n²).                                                                       |
| Forgetting the lower bound context  | ✅ Always state: *“Ω(n log n) applies only to comparison‑based sorts; Counting/Radix bypass it by exploiting key structure.”* |

> 💡 **Memory hook:** Advanced sorts = break the Ω(n log n) barrier by using keys, not comparisons. Rarely‑examined sorts = mostly variations on O(n²) themes or historical footnotes.

---

**Coverage:** This recap delivers the full **what, why, how, when, advantages/disadvantages, and trade‑offs** for both the advanced and rarely‑examined sorting algorithms.`