# 🔁 Chapter 4: Recursion vs Iteration
> 🎯 Subject Guide: SO2 — Recursion vs Iteration (two ways to repeat)

## 🔑 Core Comparison

| Aspect                 | Recursive                                           | Iterative                               | Winner            |
|------------------------|-----------------------------------------------------|-----------------------------------------|-------------------|
| Memory (Space)         | O(n) — call stack grows                             | O(1) — fixed variables                  | ✅ Iterative       |
| Stack overflow risk    | Yes — for large n                                   | None                                    | ✅ Iterative       |
| Function-call overhead | Yes — frame creation, param passing, return address | None — direct loop                      | ✅ Iterative       |
| Readability            | Elegant, mathematical — mirrors the definition      | More verbose                            | Recursive         |
| When to prefer         | Trees, graphs, divide-and-conquer problems          | Linear computations, production systems | Context-dependent |

## 🧪 Practice Focus
- Implement factorial recursive + iterative with trace output
- Visualise call stack winding/unwinding
- Compare Fibonacci naive vs memoized vs tabulation

## ⚠️ Exam Traps
- Forgetting base case → infinite recursion
- Misidentifying space complexity: recursive factorial is O(n) space (call stack), not O(1)
- Not showing both winding AND unwinding phases in traces

## 📚 Resources
- Blueprint §4.1-4.5: Recursion theory, factorial/Fibonacci traces
- `FactorialRecursive.java`, `FactorialIterative.java`: Side-by-side implementations
- `FibonacciMemoized.java`: DP optimization example

## 🚀 Quick Start *(When Complete)*
```bash
cd 04-Recursion-Iteration/adp470s-recursion
mvn test -Dtest=FactorialTest