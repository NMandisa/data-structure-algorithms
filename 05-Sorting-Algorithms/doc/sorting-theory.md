# 📘 Theoretical Recap — Sorting Algorithms

<details>
<summary><strong>📖 Quick Navigation</strong></summary>

1. [Core Concepts](#1-core-concepts-applicable-to-all-sorting-algorithms)
2. [Core Academic Sorting Algorithms](#2-core-academic-sorting-algorithms)
3. [Core Sorts Complexity Table](#3-sorting-algorithm-complexities--core-sorts)
4. [Intermediate Academic Sorting Algorithms](#4-intermediate-academic-sorting-algorithms)
5. [Intermediate Sorts Complexity Table](#5-intermediate-sorts-complexity-table)
6. [Advanced / Theoretical Academic Sorts](#6-advanced--theoretical-academic-sorts)
7. [Advanced Sorts Complexity Table](#7-advanced-sorts-complexity-table)
8. [Rarely Examined but Sometimes Mentioned](#8-rarely-examined-but-sometimes-mentioned)
9. [Master Complexity Table](#9-master-complexity-table-all-sorts)
10. [Exam Priority](#10-exam-priority--based-on-past-papers)
11. [Quick-Reference & Exam Attack Kit](#11-quickreference--exam-attack-kit)
</details>

## 1. Core Concepts (Applicable to All Sorting Algorithms)

| Concept                          | Why It Matters                                                                                                                                            |
|----------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Time complexity**              | Determines scalability — how runtime grows with input size `n`. Best/average/worst cases reveal an algorithm’s behaviour on different data distributions. |
| **Space complexity**             | Measures extra memory beyond the input. In‑place algorithms use `O(1)` extra space; others may require `O(n)` auxiliary arrays.                           |
| **Stability**                    | A stable sort preserves the relative order of equal elements. Crucial when sorting by multiple keys (e.g., sort by grade, then by name).                  |
| **In‑place vs auxiliary memory** | In‑place algorithms modify the input directly and use little extra memory. Memory trade‑offs often decide suitability for constrained environments.       |
| **Recursion**                    | Divide‑and‑conquer algorithms (Merge Sort, Quick Sort) use recursion; understanding call stack depth is essential for space complexity analysis.          |
| **Data movement**                | Swapping (e.g., Bubble, Quick) vs shifting (Insertion) vs merging to a new array (Merge Sort). Affects practical speed and stability.                     |

## 2. Core Academic Sorting Algorithms

### 2.1 Bubble Sort
**Mechanism**: Repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. Larger elements “bubble” to the end. After each full pass, the largest unsorted element is in its correct final position. The process repeats for the remaining unsorted portion, ignoring the sorted tail.

**Complexity**
- **Best case**: `O(n)` — the input is already sorted; a single pass with no swaps confirms this, then stops.
- **Worst case**: `O(n²)` — reverse‑sorted input; every pair must be swapped, requiring `n‑1` passes, each with up to `n‑1` comparisons.
- **Average case**: `O(n²)` — random order; expected number of swaps is proportional to `n²`.

**Stable?** ✅ Yes! Swaps only occur when elements are strictly out of order; equal elements are never swapped.
**In‑place?** ✅ Yes! Only a constant amount of extra space is used for the temporary swap variable.
**Main weakness**: Extremely slow for large datasets due to quadratic time complexity. Almost never used in practice.

**Trace example (4 elements)**
`Data: [5, 3, 7, 1]`
- Pass 1: `(5,3)` swap → `[3,5,7,1]`; `(5,7)` no swap; `(7,1)` swap → `[3,5,1,7]`. Largest `7` at end.
- Pass 2: `[3,5,1,7]` → `(3,5)` no swap; `(5,1)` swap → `[3,1,5,7]`. `5` now in place.
- Pass 3: `[3,1,5,7]` → `(3,1)` swap → `[1,3,5,7]`. Sorted.
- Pass 4 (optional) would detect no swaps → sorted, stops.

### 2.2 Selection Sort
**Mechanism**: Divides the input into a sorted prefix (initially empty) and an unsorted suffix. In each iteration, finds the smallest (or largest) element in the unsorted part and swaps it with the first element of the unsorted part, extending the sorted prefix by one. Repeats until only one unsorted element remains.

**Complexity**
- **Best/Average/Worst**: `O(n²)` — always scans the entire unsorted tail to find the minimum. Number of comparisons is always `n(n‑1)/2`.

**Stable?** ❌ No! The swap may jump over equal elements, disrupting their relative order.
**In‑place?** ✅ Yes! Only `O(1)` extra space for index variables.
**Main weakness**: Quadratic time in all cases; never adapts to already sorted data. Worse than Insertion Sort for nearly sorted inputs.

### 2.3 Insertion Sort
**Mechanism**: Builds the sorted list one element at a time by taking the next unsorted element and inserting it into its correct position within the already‑sorted prefix. Elements are shifted rightwards to make room. Similar to sorting playing cards in hand.

**Complexity**
- **Best case**: `O(n)` — input already sorted; each new element is compared once, no shifting needed.
- **Worst case**: `O(n²)` — reverse‑sorted; each insertion must shift all already‑sorted elements right.
- **Average case**: `O(n²)` — random input; expected shifts per insertion are ~half the sorted prefix length.

**Stable?** ✅ Yes! Insertion places equal elements after existing ones, preserving original order.
**In‑place?** ✅ Yes! Uses `O(1)` extra space; shifting is done within the array.
**Main weakness**: Quadratic time on average; inefficient for large lists, though it excels on small or nearly sorted data.

## 3. Sorting Algorithm Complexities — Core Sorts

| Algorithm      | Best    | Average | Worst   | Stable? | In-place? |
|----------------|---------|---------|---------|---------|-----------|
| Bubble Sort    | `O(n)`  | `O(n²)` | `O(n²)` | ✅ Yes   | ✅ Yes     |
| Selection Sort | `O(n²)` | `O(n²)` | `O(n²)` | ❌ No    | ✅ Yes     |
| Insertion Sort | `O(n)`  | `O(n²)` | `O(n²)` | ✅ Yes   | ✅ Yes     |

## 4. Intermediate Academic Sorting Algorithms

### 4.1 Merge Sort
**Mechanism**: Divide‑and‑conquer. Recursively divides the array into halves until sub‑arrays have length 1, then merges two sorted sub‑arrays by repeatedly taking the smaller front element. Recursion bottoms out at single elements, then merges back up.

**Complexity**
- **Best/Average/Worst**: `O(n log n)` — division still occurs, and each merge pass takes linear time across `log n` levels.

**Stable?** ✅ Yes. Takes left element first on ties.
**In‑place?** ❌ No. Requires `O(n)` auxiliary array for merging.
**Main weakness**: Requires `O(n)` extra memory, which can be prohibitive for large or memory‑constrained datasets.

### 4.2 Quick Sort
**Mechanism**: Selects a pivot, partitions so elements `< pivot` come before and `> pivot` come after. Pivot lands in final position. Recursively applies to left/right sub‑arrays. Pivot strategies: last element, median‑of‑three, or random.

**Complexity**
- **Best/Average**: `O(n log n)` — balanced splits yield `log n` depth with `O(n)` work per level.
- **Worst case**: `O(n²)` — pivot is always min/max (e.g., already sorted + last‑element pivot), causing depth `n`.

**Stable?** ❌ No. Partitioning can reorder equal elements.
**In‑place?** ✅ Yes (typically). `O(1)` extra for indices, `O(log n)` recursion stack on average.
**Main weakness**: Susceptible to `O(n²)` worst‑case. Mitigated by randomisation or median‑of‑three.

### 4.3 Heap Sort
**Mechanism**: Builds a max‑heap. Repeatedly swaps root (largest) with last unsorted element, reduces heap size by 1, and restores heap property via “sift down”. Places maximums at the end in order.

**Complexity**
- **Best/Average/Worst**: `O(n log n)` — heap construction `O(n)`, each extraction `O(log n)`. No input can worsen sift‑down.

**Stable?** ❌ No! Swapping root with last element and sifting breaks relative order.
**In‑place?** ✅ Yes! Array represents the heap; `O(1)` extra space.
**Main weakness**: Not stable; cache‑unfriendly due to non‑sequential memory access during sift‑down → often slower than Quick Sort in practice.

**HeapSort Trace — array `{7, 5, 15, 20, 16, 17, 8, 3}`**
**Phase 1: Build Max‑Heap** (indices `0..7`)
- Index 3 (20): children (3) → no change.
- Index 2 (15): children 17,8 → swap 15↔17 → `[7,5,17,20,16,15,8,3]`
- Index 1 (5): children 20,16 → swap 5↔20 → `[7,20,17,5,16,15,8,3]`
- Index 0 (7): children 20,17 → swap 7↔20, then 7↔16 → `[20,16,17,5,7,15,8,3]`

**Phase 2: Extract Max**
- Swap 20↔3 → sift 3 → `[17,16,15,5,7,3,8, |20]`
- Swap 17↔8 → sift 8 → `[16,8,15,5,7,3, |17,20]`
- Swap 16↔3 → sift 3 → `[15,8,3,5,7, |16,17,20]`
- Swap 15↔7 → sift 7 → `[8,7,3,5, |15,16,17,20]`
- Swap 8↔5 → sift 5 → `[7,5,3, |8,15,16,17,20]`
- Swap 7↔3 → sift 3 → `[5,3, |7,8,15,16,17,20]`
- Swap 5↔3 → sorted: `[3,5,7,8,15,16,17,20]`

### 4.4 Quick Sort vs Heap Sort — Key Comparison
| Metric               | Quick Sort                               | Heap Sort                            |
|----------------------|------------------------------------------|--------------------------------------|
| Time                 | Avg `O(n log n)`, Worst `O(n²)`          | Always `O(n log n)`                  |
| Space                | `O(log n)` stack (avg)                   | `O(1)` extra                         |
| Stability            | ❌ No                                     | ❌ No                                 |
| Practical speed      | ⚡ Faster (cache‑friendly, low constants) | 🐢 Slower (non‑sequential sift‑down) |
| Worst‑case guarantee | ❌ Risky without good pivot               | ✅ Guaranteed `O(n log n)`            |

**Main take‑away**: Heap Sort is the algorithm of choice when worst‑case `O(n log n)` is non‑negotiable and in‑place operation is required, but Quick Sort often wins in average‑case real‑world performance with good pivot selection.

## 5. Intermediate Sorts Complexity Table

| Algorithm  | Best         | Average      | Worst        | Stable? | In-place?         |
|------------|--------------|--------------|--------------|---------|-------------------|
| Merge Sort | `O(n log n)` | `O(n log n)` | `O(n log n)` | ✅ Yes   | ❌ No (`O(n)` aux) |
| Quick Sort | `O(n log n)` | `O(n log n)` | `O(n²)`      | ❌ No    | ✅ Yes (w/ stack)  |
| Heap Sort  | `O(n log n)` | `O(n log n)` | `O(n log n)` | ❌ No    | ✅ Yes             |

## 6. Advanced / Theoretical Academic Sorts

### 6.1 Counting Sort
**Mechanism**: Assumes integers in known small range `[0, k]`. Counts occurrences, computes prefix sums for starting indices, then places each element directly using the mapping.
**Complexity**: `O(n + k)` best/avg/worst. Not comparison‑based.
**Stable?** ✅ Yes! Placement iterates input in order and decrements prefix index.
**In‑place?** ❌ No. Requires output array `O(n)` + count array `O(k)`.
**Main weakness**: Only works for small, discrete integer ranges. Large `k` makes it impractical.

### 6.2 Radix Sort
**Mechanism**: Sorts digit‑by‑digit (LSD or MSD) using a stable sub‑sort (usually Counting Sort). LSD starts rightmost, moves left; stability ensures higher digits don’t disrupt lower‑digit ordering.
**Complexity**: `O(d·(n + k))`. Linear for fixed‑width integers.
**Stable?** ✅ Yes (if sub‑sort is stable).
**In‑place?** ❌ No. Requires aux arrays per digit pass.
**Main weakness**: Requires decomposable keys; inefficient for long keys or large alphabets.

### 6.3 Bucket Sort
**Mechanism**: Scatters elements into buckets (range/hash). Sorts each bucket (e.g., Insertion Sort), then concatenates. Assumes uniform distribution.
**Complexity**: Best/Avg `O(n)`, Worst `O(n²)` (all in one bucket).
**Stable?** Varies (depends on inner sort).
**In‑place?** ❌ No. `O(n)` bucket storage.
**Main weakness**: Highly sensitive to data distribution; poor if skewed.

## 7. Advanced Sorts Complexity Table

| Algorithm     | Best         | Average      | Worst        | Stable? | In-place? |
|---------------|--------------|--------------|--------------|---------|-----------|
| Counting Sort | `O(n+k)`     | `O(n+k)`     | `O(n+k)`     | ✅ Yes   | ❌ No      |
| Radix Sort    | `O(d·(n+k))` | `O(d·(n+k))` | `O(d·(n+k))` | ✅ Yes   | ❌ No      |
| Bucket Sort   | `O(n)`       | `O(n)`       | `O(n²)`      | Varies  | ❌ No      |

## 8. Rarely Examined but Sometimes Mentioned

| Algorithm       | Mechanism (brief)                        | Time (Best/Avg/Worst)                       | Stable? | In-place?           | Main weakness              |
|-----------------|------------------------------------------|---------------------------------------------|---------|---------------------|----------------------------|
| Shell Sort      | Insertion with decreasing gaps           | `O(n log n)` / gap‑dependent / `O(n^(3/2))` | ❌ No    | ✅ Yes               | Gap selection complex      |
| Comb Sort       | Bubble with gap `n/1.3`                  | `O(n log n)` / `O(n²)` / `O(n²)`            | ❌ No    | ✅ Yes               | Quadratic worst‑case       |
| Cocktail Sort   | Bidirectional Bubble                     | `O(n)` / `O(n²)` / `O(n²)`                  | ✅ Yes   | ✅ Yes               | Still quadratic            |
| Gnome Sort      | Insertion‑like, step back on wrong order | `O(n)` / `O(n²)` / `O(n²)`                  | ✅ Yes   | ✅ Yes               | Inefficient for large data |
| Tree Sort       | Build BST → in‑order traversal           | `O(n log n)` / `O(n log n)` / `O(n²)`       | Varies  | ❌ No (`O(n)` nodes) | Degrades on sorted input   |
| Tournament Sort | Winner tree to repeatedly extract min    | `O(n log n)` / `O(n log n)` / `O(n log n)`  | ❌ No    | ❌ No                | High constant overhead     |

## 9. Master Complexity Table (All Sorts)

| Algorithm      | Best         | Average      | Worst        | Stable    | In-place |
|----------------|--------------|--------------|--------------|-----------|----------|
| Bubble Sort    | `O(n)`       | `O(n²)`      | `O(n²)`      | ✅ Yes     | ✅ Yes    |
| Selection Sort | `O(n²)`      | `O(n²)`      | `O(n²)`      | ❌ No      | ✅ Yes    |
| Insertion Sort | `O(n)`       | `O(n²)`      | `O(n²)`      | ✅ Yes     | ✅ Yes    |
| Merge Sort     | `O(n log n)` | `O(n log n)` | `O(n log n)` | ✅ Yes     | ❌ No     |
| Quick Sort     | `O(n log n)` | `O(n log n)` | `O(n²)`      | ❌ No      | ✅ Yes    |
| Heap Sort      | `O(n log n)` | `O(n log n)` | `O(n log n)` | ❌ No      | ✅ Yes    |
| Counting Sort  | `O(n+k)`     | `O(n+k)`     | `O(n+k)`     | ✅ Yes     | ❌ No     |
| Radix Sort     | `O(d·(n+k))` | `O(d·(n+k))` | `O(d·(n+k))` | ✅ Yes     | ❌ No     |
| Bucket Sort    | `O(n)`       | `O(n)`       | `O(n²)`      | Usually ✅ | ❌ No     |

## 10. Exam Priority — Based on Past Papers
- **Tier 1 (Almost Guaranteed)**: Bubble Sort, Insertion Sort, Quick Sort, Heap Sort
- **Tier 2 (Very Likely)**: Merge Sort, Binary Search, Linear Search
- **Tier 3 (Possible Theory)**: Counting Sort, Radix Sort

## 11. Quick‑Reference & Exam Attack Kit

### 11.1 Condensed Complexity Table (with Space)
| Algorithm | Best         | Average      | Worst        | Space            | Stable? | In-place? |
|-----------|--------------|--------------|--------------|------------------|---------|-----------|
| Bubble    | `O(n)`       | `O(n²)`      | `O(n²)`      | `O(1)`           | ✅       | ✅         |
| Selection | `O(n²)`      | `O(n²)`      | `O(n²)`      | `O(1)`           | ❌       | ✅         |
| Insertion | `O(n)`       | `O(n²)`      | `O(n²)`      | `O(1)`           | ✅       | ✅         |
| Merge     | `O(n log n)` | `O(n log n)` | `O(n log n)` | `O(n)`           | ✅       | ❌         |
| Quick     | `O(n log n)` | `O(n log n)` | `O(n²)`      | `O(log n)` stack | ❌       | ✅         |
| Heap      | `O(n log n)` | `O(n log n)` | `O(n log n)` | `O(1)`           | ❌       | ✅         |
| Counting  | `O(n+k)`     | `O(n+k)`     | `O(n+k)`     | `O(n+k)`         | ✅       | ❌         |
| Radix     | `O(d·(n+k))` | `O(d·(n+k))` | `O(d·(n+k))` | `O(n+k)`         | ✅       | ❌         |
| Bucket    | `O(n)`       | `O(n)`       | `O(n²)`      | `O(n)`           | Varies  | ❌         |

### 11.2 Sorting Complexities — Θ Bounds (Key Sorts)
| Sort      | Best         | Average      | Worst        | Space      | Stable? |
|-----------|--------------|--------------|--------------|------------|---------|
| Bubble    | `Θ(n)`       | `Θ(n²)`      | `Θ(n²)`      | `Θ(1)`     | Yes     |
| QuickSort | `Θ(n log n)` | `Θ(n log n)` | `Θ(n²)` ⚠️   | `O(log n)` | No      |
| HeapSort  | `Θ(n log n)` | `Θ(n log n)` | `Θ(n log n)` | `Θ(1)`     | No      |
| MergeSort | `Θ(n log n)` | `Θ(n log n)` | `Θ(n log n)` | `Θ(n)`     | Yes     |

### 11.3 Distinction Quick‑Fire
| Question                                    | Answer                                                        |
|---------------------------------------------|---------------------------------------------------------------|
| Lower bound for comparison sorting?         | `Ω(n log n)` — decision tree: `2^h ≥ n! → h = Ω(n log n)`     |
| Which sorts achieve it?                     | MergeSort & HeapSort (worst). QuickSort (expected only)       |
| MergeSort exact comparisons?                | `C(n) = n·log₂(n) − n + 1` for `n = 2^k`                      |
| QuickSort expected comparisons?             | `≈ 2n·ln(n) = 1.386·n·log₂(n)`                                |
| Why QuickSort faster than MergeSort?        | Cache locality + minimal data movement                        |
| How Introsort guarantees `O(n log n)`?      | QuickSort + switches to HeapSort when depth `> 2·log n`       |
| Why non‑comparison sorts beat `Ω(n log n)`? | Extract >1 bit per operation (key indexing, digit extraction) |

### 11.4 Full Academic Complexity Table (with Θ bounds & exact counts)
| Algorithm | Best         | Average      | Worst        | Space      | Stable | Adaptive | Avg Comparisons |
|-----------|--------------|--------------|--------------|------------|--------|----------|-----------------|
| Bubble    | `Θ(n)`       | `Θ(n²)`      | `Θ(n²)`      | `Θ(1)`     | Yes    | Weak     | `n(n−1)/2`      |
| Insertion | `Θ(n)`       | `Θ(n²)`      | `Θ(n²)`      | `Θ(1)`     | Yes    | Yes      | `n²/4`          |
| Selection | `Θ(n²)`      | `Θ(n²)`      | `Θ(n²)`      | `Θ(1)`     | No     | No       | `n(n−1)/2`      |
| Merge     | `Θ(n log n)` | `Θ(n log n)` | `Θ(n log n)` | `Θ(n)`     | Yes    | No       | `≈n log n − n`  |
| Quick     | `Θ(n log n)` | `Θ(n log n)` | `Θ(n²)`      | `O(log n)` | No     | No       | `≈1.386n log n` |
| Heap      | `Θ(n log n)` | `Θ(n log n)` | `Θ(n log n)` | `Θ(1)`     | No     | No       | `≈2n log n`     |

| Metric               | QuickSort (random)                     | MergeSort           | HeapSort                     |
|----------------------|----------------------------------------|---------------------|------------------------------|
| Expected comparisons | `≈ 1.386·n·log₂n`                      | `≈ n·log₂n − n`     | `≈ 2·n·log₂n`                |
| Worst case           | `Θ(n²)`                                | `Θ(n log n)`        | `Θ(n log n)`                 |
| Why Quick wins       | Minimal data movement + cache‑friendly | Extra `O(n)` memory | Cache‑unfriendly heap access |

*Note*: With random pivot, `O(n²)` probability decays exponentially. For `n=1000`, chance `< 1/n!`.

### 11.5 High‑Yield Visual Traces (ASCII)
**Quick Sort Partition (Lomuto, pivot = last)**
```
Array: [4, 7, 2, 9, 5]  pivot = 5
i = -1
j=0: 4 < 5 → i=0, swap(0,0) → [4, 7, 2, 9, 5]
j=1: 7 ≥ 5 → skip
j=2: 2 < 5 → i=1, swap(1,2) → [4, 2, 7, 9, 5]
j=3: 9 ≥ 5 → skip
End: swap(i+1, pivot) → swap(2,4) → [4, 2, 5, 9, 7]
Pivot 5 now at index 2. Left: [4, 2] | Right: [9, 7]
```

**Heap Sort (Max‑Heap Build & Extract)**
```
Initial: [7, 5, 15, 20, 16, 17, 8, 3]
Tree:        7
/   \
5     15
/ \   /  \
20  16 17   8
/
3

Build Max‑Heap → [20, 16, 17, 5, 7, 15, 8, 3]
Extract 20 → swap with 3, sift → [17, 16, 15, 5, 7, 3, 8, |20]
Repeat until sorted: [3, 5, 7, 8, 15, 16, 17, 20]
```

**Merge Sort (Divide & Merge)**
```
[5, 3, 8, 1]
→ [5, 3] [8, 1]
→ [5] [3] → merge → [3, 5]
→ [8] [1] → merge → [1, 8]
→ merge [3,5] & [1,8] → [1, 3, 5, 8]
```

### 11.6 Memory Hooks & Exam Traps
| Concept              | Hook / Trap                                                                               |
|----------------------|-------------------------------------------------------------------------------------------|
| Bubble vs Insertion  | Bubble swaps adjacent; Insertion shifts & inserts. Insertion wins on nearly sorted data.  |
| Selection Sort       | Always `O(n²)` comparisons. Never adapts. Unstable due to long‑distance swaps.            |
| QuickSort Worst Case | Triggered by sorted/reverse‑sorted + bad pivot. Fix: random pivot or median‑of‑three.     |
| HeapSort Guarantee   | Always `O(n log n)`. No bad inputs. But cache‑unfriendly → slower in practice.            |
| MergeSort Stability  | Takes left element first on ties → stable. Requires `O(n)` aux memory.                    |
| Non‑Comparison Sorts | Beat `Ω(n log n)` by using key structure. Not general‑purpose.                            |
| In‑place ≠ Stable    | Heap & Quick are in‑place but unstable. Merge & Insertion are stable but differ in space. |

### 11.7 Algorithm Selection Flowchart (Mental)
- Nearly sorted / small input → **Insertion Sort**
- Need guaranteed `O(n log n)` + in‑place → **Heap Sort**
- Need stability + don't mind `O(n)` space → **Merge Sort**
- Fastest average + can randomise pivot → **Quick Sort**
- Keys are small integers / fixed digits → **Counting / Radix**
- Data uniformly distributed → **Bucket Sort**
- Memory extremely constrained → **Heap or Insertion**

### 11.8 Exam Strategy Tips
1. Always state assumptions (e.g., *“assuming random pivot for QuickSort”*).
2. Specify space clearly: QuickSort uses `O(log n)` stack, not `O(1)`.
3. Stability matters in multi‑pass sorts → highlight when relevant.
4. Trace questions: Show array state after each pass/partition/merge. Label pointers.
5. Compare questions: Use table format (Time, Space, Stable, In‑place, Cache, Worst‑case guarantee).

### 11.9 Head‑to‑Head Comparisons (Lecturer’s Trick)
**Core Sorts**
| Comparison | Preferred | Why |
|---|---|---|
| Bubble vs Selection | Depends | Bubble can hit `O(n)` best case; Selection does exactly `n‑1` swaps. If swap cost dominates, Selection wins. |
| Selection vs Insertion | **Insertion** | Adaptive, stable, shifts instead of swaps. Better for nearly sorted data. |
| Bubble vs Insertion | **Insertion** | Same `O(n)` best case, but fewer comparisons, shifts > swaps, stable. Bubble is obsolete. |
> 💬 *“Although all three are `O(n²)` worst‑case, Insertion is preferred for nearly sorted or small data due to adaptiveness and stability. Selection only wins when swaps are extremely expensive. Bubble is largely obsolete.”*

**Intermediate Sorts**
| Comparison | Preferred | Why |
|---|---|---|
| Merge vs Heap | Merge (speed/stability) or Heap (memory) | Merge is faster & stable but needs `O(n)` aux. Heap is strictly `O(1)` in‑place, but unstable & cache‑unfriendly. |
| Quick vs Heap | Quick (avg) or Heap (worst‑case guarantee) | Quick averages `≈1.39 n log n` with great locality. Heap is always `Θ(n log n)`, safe when worst‑case is non‑negotiable. |
| Merge vs Quick | Merge (guarantees/stability) or Quick (space/speed) | Merge guarantees `Θ(n log n)` + stable, but needs `O(n)` space. Quick is in‑place & faster on average, but risks `O(n²)` & unstable. |
> 💬 *“Merge Sort is the only stable option with guaranteed `Θ(n log n)`, but requires `O(n)` extra memory. Quick Sort is the fastest on average and in‑place, but risks `O(n²)` without good pivot selection. Heap Sort is the safest in‑place algorithm with no worst‑case surprises, but is slower in practice and not stable.”*

**Quick Decision Matrix (Memorise for 2‑Mark Questions)**
| Requirement | Best Choice |
|---|---|
| Nearly sorted / small input | Insertion Sort |
| Must be stable | Merge Sort |
| In‑place + worst‑case guarantee | Heap Sort |
| Fastest average + in‑place + can randomise pivot | Quick Sort |
| Keys are small integers / fixed digits | Counting / Radix Sort |
| Memory severely constrained | Heap or Insertion |

### 📊 Sorting Recap — Coverage Index
| Aspect         | Where Covered                                                           |
|----------------|-------------------------------------------------------------------------|
| **What**       | §1, §2–§4 & §6 (mechanism descriptions)                                 |
| **Why**        | §2–§4 & §6, §4.4 (why Heap for worst‑case), §11.9 (why choose X over Y) |
| **How**        | §2.1 trace, §4.3 Heap trace, §11.5 ASCII traces                         |
| **When**       | §11.7 flowchart, §11.9 decision matrix, §6.1–6.3, §4.4                  |
| **Adv/Disadv** | Each algorithm’s “Main weakness”, §11.6 hooks, §11.9 comparisons        |
| **Trade‑offs** | §5 & §9 tables, §11.4 Θ bounds, §11.9 direct trade‑offs, §4.4, §6.3     |
