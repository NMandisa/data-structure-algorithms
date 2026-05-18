# 🗂️ Chapter 5: Sorting Algorithms
> 🎯 Subject Guide: SO4 — Sorting Algorithms (organizing data)

## 🔑 Core Concepts

| Property         | Meaning                                        | Exam Importance                              |
|------------------|------------------------------------------------|----------------------------------------------|
| Time Complexity  | Best/average/worst — scalability with `n`      | Always state all three cases                 |
| Space Complexity | In-place `O(1)` vs `O(n)` auxiliary            | QuickSort uses `O(log n)` stack — not `O(1)` |
| Stability        | Preserves relative order of equal-key elements | Merge is stable; Quick/Heap are **NOT**      |
| Adaptivity       | Performs better on nearly sorted input         | Only Insertion Sort is strongly adaptive     |

## 🧪 Practice Strategy
1. Implement each sort with trace output (`ArrayPrinter.printWithSortedMarker()`)
2. Verify stability with `StabilityChecker`
3. Compare trade-offs using the decision matrix in the theory doc

## ⚠️ Exam Traps
| Trap                                 | Correction                                                  |
|--------------------------------------|-------------------------------------------------------------|
| QuickSort uses `O(1)` space          | ❌ Uses `O(log n)` stack space for recursion                 |
| HeapSort is stable                   | ❌ HeapSort is **NOT** stable — use MergeSort for stability  |
| Forgetting to state pivot assumption | ✅ Always write: `"Assuming random pivot for QuickSort..."`  |
| Not showing array after each pass    | ✅ Trace questions want state after **EVERY** pass/partition |

📖 **[Full Theory Reference →](doc/sorting-theory.md)**

## 📁 Chapter Structure
```
05-Sorting-Algorithms/
├── README.md                 ← You are here
├── doc/
│   └── sorting-theory.md     ← Complete complexity, traces & exam kit
└── src/
    ├── main/java/            ← Algorithm implementations
    │   ├── MergeSort.java
    │   ├── QuickSortRandomPivot.java
    │   └── HeapSort.java
    └── test/java/            ← JUnit validation suite
```

## 📚 Resources
- **Blueprint §§5.1–5.8**: Complete sorting reference
- **Implementations**: `MergeSort.java`, `QuickSortRandomPivot.java`, `HeapSort.java` (The Big Three)
- **Utilities**: `SortTester.java` (correctness harness), `StabilityChecker.java`, `ArrayPrinter.java`

## 🚀 Quick Start *(When Complete)*
```bash
cd 05-Sorting-Algorithms
mvn clean test -Dtest=AllSortsTest
```
> 💡 Tip: Run individual sort tests with `-Dtest=MergeSortTest` or use `SortTester` for live terminal traces.

---
✅ **Status**: Theory complete • Implementations pending • Tests pending  
🔄 **Next**: Add `src/` structure, write `QuickSortTest.java`, run stability benchmarks
```
