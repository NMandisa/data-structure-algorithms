# Theoretical Recap — Quadratic Trio [O(n²)]

> **Focus:** Bubble Sort, Selection Sort, Insertion Sort  
> **Context:** Core academic sorting algorithms – Chapter 5  
> **Exam weight:** High (traces, comparisons, adaptiveness, stability)

---

## 1. Shared Properties of the Trio

| Property         | Bubble            | Selection | Insertion |
|------------------|-------------------|-----------|-----------|
| **Best case**    | O(n)              | O(n²)     | O(n)      |
| **Average case** | O(n²)             | O(n²)     | O(n²)     |
| **Worst case**   | O(n²)             | O(n²)     | O(n²)     |
| **Space**        | O(1)              | O(1)      | O(1)      |
| **Stable?**      | ✅ Yes             | ❌ No      | ✅ Yes     |
| **In‑place?**    | ✅ Yes             | ✅ Yes     | ✅ Yes     |
| **Adaptive?**    | Weak (early exit) | No        | ✅ Strong  |

All three are **quadratic in the average and worst cases**, but they differ fundamentally in **how** they move elements, **adaptivity**, **stability**, and **practical speed on small/nearly sorted data**.

---

## 2. Bubble Sort

### 2.1 Mechanism
Repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. Larger elements “bubble” to the end. After each full pass, the largest unsorted element reaches its correct final position. The process repeats for the remaining unsorted prefix, ignoring the already sorted tail.

### 2.2 Complexity & Behaviour

| Case        | Time  | Reason                                                     |
|-------------|-------|------------------------------------------------------------|
| **Best**    | O(n)  | Input already sorted; one pass with no swaps → stops.      |
| **Worst**   | O(n²) | Reverse‑sorted input; every adjacent pair must be swapped. |
| **Average** | O(n²) | Random order; expected number of swaps ~ n²/4.             |

### 2.3 Stability & Memory
- **Stable:** Yes – elements are only swapped when strictly out of order; equal keys stay in original order.
- **In‑place:** Yes – only a single temporary variable is used for swapping.
- **Main weakness:** Extremely slow for large data; O(n²) comparisons and swaps. Almost never used in practice.

### 2.4 Trace Example (n=4)
Data: `[5, 3, 7, 1]`

| Pass | Array after pass     | Sorted tail  |
|------|----------------------|--------------|
| 1    | `[3, 5, 1, 7]`       | `7`          |
| 2    | `[3, 1, 5, 7]`       | `5, 7`       |
| 3    | `[1, 3, 5, 7]`       | `3, 5, 7`    |
| 4    | *(no swaps → stops)* | fully sorted |

> 🔍 **Exam tip:** Always show the sorted portion after each pass and mention that an optimised version stops when no swaps occur.

---

## 3. Selection Sort

### 3.1 Mechanism
Divides the input into a sorted prefix (initially empty) and an unsorted suffix. In each iteration, it **finds the smallest element** in the unsorted part and **swaps it with the first unsorted element**, extending the sorted prefix by one. This continues until only one unsorted element remains.

### 3.2 Complexity & Behaviour

| Case        | Time  | Reason                                                     |
|-------------|-------|------------------------------------------------------------|
| **Best**    | O(n²) | Always scans the entire unsorted tail to find the minimum. |
| **Worst**   | O(n²) | Same number of comparisons regardless of input order.      |
| **Average** | O(n²) | Comparisons always n(n‑1)/2.                               |

### 3.3 Stability & Memory
- **Stable:** ❌ No – the swap may jump over equal elements, disrupting their relative order.
- **In‑place:** ✅ Yes – uses O(1) extra space for loop counters and the minimum index.
- **Main weakness:** Quadratic time in **all** cases; never adapts to pre‑sorted data. Worse than Insertion Sort for nearly sorted inputs.

### 3.4 Trace Example (n=4)
Data: `[5, 3, 7, 1]`

| Pass | Sorted prefix | Unsorted suffix | Min found    | Action             | Array          |
|------|---------------|-----------------|--------------|--------------------|----------------|
| 1    | `[]`          | `[5, 3, 7, 1]`  | `1` at idx 3 | swap idx 0 ↔ 3     | `[1, 3, 7, 5]` |
| 2    | `[1]`         | `[3, 7, 5]`     | `3` at idx 1 | (already in place) | `[1, 3, 7, 5]` |
| 3    | `[1, 3]`      | `[7, 5]`        | `5` at idx 3 | swap idx 2 ↔ 3     | `[1, 3, 5, 7]` |
| 4    | `[1, 3, 5]`   | `[7]`           | –            | done               | `[1, 3, 5, 7]` |

> ⚠️ **Exam trap:** Selection Sort does **exactly n‑1 swaps** total — far fewer than Bubble Sort’s potential O(n²) swaps. If the question mentions “minimise write operations”, Selection might be the answer.

---

## 4. Insertion Sort

### 4.1 Mechanism
Builds the sorted list one element at a time. Takes the next unsorted element and **inserts** it into its correct position within the already‑sorted prefix, **shifting** larger elements to the right to make room. Works like sorting playing cards in hand.

### 4.2 Complexity & Behaviour

| Case        | Time  | Reason                                                                                                       |
|-------------|-------|--------------------------------------------------------------------------------------------------------------|
| **Best**    | O(n)  | Input already sorted; each new element is compared once with the last sorted element, no shifting.           |
| **Worst**   | O(n²) | Reverse‑sorted; each new element must be compared with **all** already sorted elements and shift them right. |
| **Average** | O(n²) | Random input; expected shifts per insertion ≈ half the sorted prefix length.                                 |

### 4.3 Stability & Memory
- **Stable:** ✅ Yes – elements are only shifted if strictly greater; equal elements stay in original order.
- **In‑place:** ✅ Yes – uses O(1) extra space; shifting happens within the array.
- **Main weakness:** Still quadratic on average; inefficient for large random lists, but **excellent for small or nearly sorted data**.

### 4.4 Trace Example (n=4)
Data: `[5, 3, 7, 1]`

| i | key | Sorted prefix before | Action                              | Array after insertion |
|---|-----|----------------------|-------------------------------------|-----------------------|
| 1 | `3` | `[5]`                | 5 > 3 → shift 5 right; insert 3     | `[3, 5, 7, 1]`        |
| 2 | `7` | `[3, 5]`             | 5 < 7 → no shift                    | `[3, 5, 7, 1]`        |
| 3 | `1` | `[3, 5, 7]`          | 7>1, 5>1, 3>1 → shift all; insert 1 | `[1, 3, 5, 7]`        |

> 💡 **Key strength:** On nearly sorted data, Insertion Sort runs in near‑linear time — this is why it’s often used as the final stage of hybrid sorts (like Timsort).

---

## 5. Head‑to‑Head Comparisons (Lecturer’s Trick)

| Comparison                 | Preferred                          | Reasoning                                                                                                                                                      |
|----------------------------|------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Bubble vs Selection**    | Depends on context                 | Bubble can exit early (O(n) best) but performs many swaps. Selection does **exactly n‑1 swaps** — ideal when write operations are expensive.                   |
| **Selection vs Insertion** | **Insertion** (nearly sorted data) | Insertion is adaptive (O(n) best) and stable; Selection is always O(n²) and unstable. Insertion uses shifts, which are often cheaper than swaps.               |
| **Bubble vs Insertion**    | **Insertion**                      | Same O(n) best case, but Insertion typically makes **half the comparisons**, shifts instead of swapping, and is stable. Bubble is never preferred in practice. |

### Exam‑ready summary line:
> *“Although all three are O(n²) worst‑case, Insertion Sort is preferred for small or nearly sorted data because of its adaptiveness and stability. Selection Sort is only better when minimising the number of swaps is critical. Bubble Sort is largely obsolete.”*

---

## 6. Quick Decision Matrix

| Scenario                                         | Best Choice                    |
|--------------------------------------------------|--------------------------------|
| Nearly sorted / tiny input (≤ 50 elements)       | **Insertion Sort**             |
| Minimising write operations (e.g., flash memory) | **Selection Sort**             |
| Educational purposes / understanding swaps       | **Bubble Sort**                |
| Preserving original order of equal keys          | **Insertion Sort** (or Bubble) |

---

## 7. Common Exam Traps

| Trap                                               | Correction                                                                                              |
|----------------------------------------------------|---------------------------------------------------------------------------------------------------------|
| “Selection Sort is stable”                         | ❌ — It is **not** stable due to long‑distance swaps.                                                    |
| “Bubble Sort is adaptive like Insertion”           | ❌ — Bubble’s early‑exit only helps on already sorted data; Insertion adapts to *nearly* sorted as well. |
| “Insertion Sort uses swapping”                     | ❌ — It uses **shifting**, not swapping. Swapping would lose stability.                                  |
| Forgetting to show sorted portion in Bubble trace  | ✅ Always mark the sorted tail (e.g., with `                                                             |`). |
| Not stating the number of swaps for Selection Sort | ✅ Mention: exactly **n‑1 swaps**, regardless of input.                                                  |

---

**Coverage:** This recap delivers the full **what, why, how, when, advantages/disadvantages, and trade‑offs** for the Quadratic Trio. Use it alongside the master sorting recap for exam revision.