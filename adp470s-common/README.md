# 🧰 adp470s-common: Shared Utilities
> Cross-cutting utilities for exam-focused practice

## 🔑 Key Utilities

| Class | Purpose | Exam Benefit |
|-------|---------|-------------|
| `SortTester.java` | Generic harness to test sorting algorithms | Automates correctness/stability verification so you focus on reasoning |
| `ArrayPrinter.java` | Visualise array state with sorted portion markers | Matches blueprint trace table format for exam answers |
| `StabilityChecker.java` | Verify if sort preserves relative order of equal keys | Critical for stability comparison questions |

## 🧪 Usage Example
```java
// Test any sorting implementation
SortTester.testCorrectness(myMergeSort, "MergeSort");

// Visualise sorting steps for exam tracing
ArrayPrinter.printWithSortedMarker(arr, sortedFromIndex);

// Verify stability
boolean isStable = StabilityChecker.isStable(pairs);