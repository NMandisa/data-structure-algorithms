# Theoretical Recap — Recursion vs. Iteration: Factorial & Fibonacci Complexity

> **Focus:** Factorial and Fibonacci as vehicles for comparing recursive and iterative control flow  
> **Context:** Chapter 4 – Recursion vs Iteration  
> **Exam weight:** High (traces, complexity analysis, 6‑point answer, stack‑overflow risk)

---

## 1. Recursion vs. Iteration — The Core Distinction

Both recursion and iteration can solve the same problem, but they differ fundamentally in **how** they repeat logic, **memory usage**, and **risk profile**.

| Aspect               | Recursive                                                                        | Iterative                                      |
|----------------------|----------------------------------------------------------------------------------|------------------------------------------------|
| **Repetition**       | A function calls itself with a smaller argument until a base case is reached     | A loop repeats a block until a condition fails |
| **State management** | Each call creates a new stack frame storing local variables and a return address | All variables live in a single function frame  |
| **Memory**           | Grows with the depth of the call stack – can be large and hidden                 | Fixed – only a few local variables             |
| **Risk**             | Stack overflow if depth is too great (e.g., n > 10⁴)                             | No inherent stack limit                        |
| **Readability**      | Closely matches mathematical definitions                                         | More explicit, easier to trace step‑by‑step    |

> 🧠 **Key insight:** Recursion trades simplicity for hidden memory cost. The call stack is the invisible space complexity – examiners love testing whether you remember it.

---

## 2. Factorial — Recursive and Iterative Complexity

### 2.1 Recursive Factorial: Execution Model

**Definition:**  
factorial(n) = 1 if n ≤ 1, else n × factorial(n‑1).

**Two phases:**
1. **Winding (call‑stack build):**  
   `factorial(n)` calls `factorial(n‑1)`, which calls `factorial(n‑2)`… until the base case `factorial(1)` is reached. Every call **suspends** and waits. The stack grows to depth `n`.
2. **Unwinding (return propagation):**  
   Base case returns 1. Each suspended call resumes, multiplies its `n` by the returned value, and passes the result upward.

**Trace – factorial(4):**
```
Winding:
  factorial(4) → waits for factorial(3)
    factorial(3) → waits for factorial(2)
      factorial(2) → waits for factorial(1)
        factorial(1) → returns 1  (base case, depth 4)

Unwinding:
  factorial(1) = 1
  factorial(2) = 2 × 1 = 2
  factorial(3) = 3 × 2 = 6
  factorial(4) = 4 × 6 = 24
```

**Complexity:**

| Metric    | Value | Reason                                    |
|-----------|-------|-------------------------------------------|
| **Time**  | O(n)  | `n` recursive calls, each does O(1) work  |
| **Space** | O(n)  | Call stack holds `n` frames at peak depth |

> ⚠️ **Trap:** The O(n) space is **not an explicit array** – it’s the call stack. Each frame stores the parameter `n`, return address, and partial multiplication result. Many students forget this hidden cost.

### 2.2 Iterative Factorial: Execution Model

**Mechanism:**

A loop multiplies an accumulator (`result`) by each integer from 1 to `n`. No function calls, no stack growth.

**Trace – factorial(5):**

| Step | i | result = result × i | Value |
|------|---|---------------------|-------|
| Init | – | `result = 1`        | 1     |
| 1    | 1 | 1 × 1               | 1     |
| 2    | 2 | 1 × 2               | 2     |
| 3    | 3 | 2 × 3               | 6     |
| 4    | 4 | 6 × 4               | 24    |
| 5    | 5 | 24 × 5              | 120   |

> ⚠️ **Trap:** Start `result` at 1, **not 0**. Multiplying by 0 yields 0 for every input.

**Complexity:**
| Metric | Value | Reason |
|--------|-------|--------|
| **Time** | O(n) | Loop runs `n` iterations, one multiplication each |
| **Space** | O(1) | Only a fixed number of variables (accumulator, loop counter) regardless of `n` |

### 2.3 Factorial Comparison

| Metric              | Recursive         | Iterative |
|---------------------|-------------------|-----------|
| Time                | O(n)              | O(n)      |
| Space               | O(n)              | O(1)      |
| Stack‑overflow risk | Yes (for large n) | None      |

> 🧠 **Key insight:** Time is equal for both – the real difference is **space safety**. For n = 100 000, recursion will crash with a `StackOverflowError`; iteration will run fine.

---

## 3. Fibonacci — The Ultimate DSA Teaching Tool

Fibonacci is tested more often than factorial because it demonstrates **overlapping subproblems** – the key concept that motivates dynamic programming.

### 3.1 Naïve Recursive Fibonacci

**Definition:**  
fib(0) = 0, fib(1) = 1, fib(n) = fib(n‑1) + fib(n‑2).

**The problem:** Each call spawns two sub‑calls, creating a **complete binary recursion tree** of depth n. The same sub‑problems are recomputed many times.

**Example – fib(4) recursion tree:**
```
         fib(4)
       /        \
    fib(3)      fib(2)
   /      \     /      \
fib(2)  fib(1) fib(1) fib(0)
 /    \
fib(1) fib(0)
```
- fib(2) is computed **twice**, fib(1) **three times**.
- The number of calls grows exponentially → **O(2ⁿ)**.

**Complexity:**

| Metric    | Value | Reason                                                                    |
|-----------|-------|---------------------------------------------------------------------------|
| **Time**  | O(2ⁿ) | Exponential – each call spawns two more; the recursion tree has ~2ⁿ nodes |
| **Space** | O(n)  | Call stack depth is at most n (the height of the tree)                    |

> ⚠️ **Trap:** Students sometimes write O(n log n) or O(n²) for naïve Fibonacci. The correct bound is **O(2ⁿ)** – exponential. The recursive tree explains why.

### 3.2 Iterative Fibonacci

**Mechanism:** 

Bottom‑up loop using two variables to track `fib(n‑1)` and `fib(n‑2)`. No recursion, no stack.

**Trace – fib(5):**

| Step  | a (fib(n‑2)) | b (fib(n‑1)) | fib(n) |
|-------|--------------|--------------|--------|
| Start | 0 (fib(0))   | 1 (fib(1))   | –      |
| n=2   | 0            | 1            | 0+1=1  |
| n=3   | 1            | 1            | 1+1=2  |
| n=4   | 1            | 2            | 1+2=3  |
| n=5   | 2            | 3            | 2+3=5  |

**Complexity:**

| Metric    | Value | Reason                                         |
|-----------|-------|------------------------------------------------|
| **Time**  | O(n)  | Loop runs `n‑1` iterations, O(1) per iteration |
| **Space** | O(1)  | Only two integer variables used                |

### 3.3 Dynamic Programming — Eliminating Redundancy

**Memoization (Top‑Down DP):**

Recursive approach with a cache (array or map). Before computing `fib(k)`, check if it’s already stored. If yes, return cached value; otherwise compute and store it. This reduces time to **O(n)** because each `fib(k)` is computed exactly once. Space remains O(n) for the cache + O(n) call stack = O(n).

**Tabulation (Bottom‑Up DP):**  

Iterative approach that builds a table from `fib(0)` up to `fib(n)`. Time **O(n)**, space **O(n)** for the table, or **O(1)** if only the last two values are kept (which is exactly the iterative method above).

### 3.4 Fibonacci Complexity Summary

| Approach                         | Time  | Space        | Notes                                |
|----------------------------------|-------|--------------|--------------------------------------|
| Naïve Recursive                  | O(2ⁿ) | O(n)         | Exponential – impractical for n > 40 |
| Iterative                        | O(n)  | O(1)         | Fast and memory‑efficient            |
| Memoized Recursive (Top‑Down DP) | O(n)  | O(n)         | Cache + call stack                   |
| Tabulation (Bottom‑Up DP)        | O(n)  | O(n) or O(1) | Can be optimised to O(1) space       |

> 🧠 **Why Fibonacci, not factorial?**  
> Factorial has **no overlapping subproblems** – each call is unique, so DP doesn’t help. Fibonacci’s repeated sub‑calls make it the perfect example for **why memoisation/tabulation transform exponential to linear**.

---

## 4. The 6‑Point Answer (Guarantees Full Marks)

Any exam question that asks *“Compare recursive and iterative implementations of factorial/Fibonacci”* expects these six points. Use them as a checklist.

| # | Point                          | Answer                                                                                 |
|---|--------------------------------|----------------------------------------------------------------------------------------|
| 1 | Recursive Time                 | O(n) for factorial / O(2ⁿ) for naïve Fibonacci – explain the number of recursive calls |
| 2 | Recursive Space                | O(n) – the call stack grows to depth n; each frame consumes memory                     |
| 3 | Iterative Time                 | O(n) – loop runs n times with constant‑time operations                                 |
| 4 | Iterative Space                | O(1) – only a fixed number of variables, no stack growth                               |
| 5 | Which is more space‑efficient? | Iterative – by a factor of n (or exponential for Fibonacci)                            |
| 6 | Why does space matter?         | Recursive version risks **StackOverflowError** for large n; iterative is safe          |

> 💡 **Pro tip:** Always state both time and space separately. Examiners look for the distinction between the explicit array space and the hidden call‑stack space.

---

## 5. Memory Hooks & Exam Traps

| Concept                     | Hook / Trap                                                                                                 |
|-----------------------------|-------------------------------------------------------------------------------------------------------------|
| **Hidden space**            | Recursive `O(n)` space is the call stack, not an array. Students often forget this.                         |
| **Factorial base case**     | Must return 1 for n ≤ 1. Missing base case → infinite recursion.                                            |
| **Accumulator start**       | Iterative factorial: start at 1, not 0.                                                                     |
| **Fibonacci exponential**   | Naïve recursion is `O(2ⁿ)`, not `O(n²)` – the recursion tree is exponential.                                |
| **Overlapping subproblems** | Factorial has none; Fibonacci has many. This is why DP helps Fibonacci but not factorial.                   |
| **Stack overflow**          | Recursion for large n (e.g., 100 000) crashes; iteration does not. This is a practical, real‑world concern. |

---

## 6. Quick Decision Matrix

| Scenario                         | Best Choice      | Reason                                                      |
|----------------------------------|------------------|-------------------------------------------------------------|
| Mathematical elegance, small n   | Recursive        | Mirrors definition; stack depth is manageable               |
| Large n, production code         | Iterative        | O(1) space, no stack‑overflow risk                          |
| Fibonacci with repeated queries  | DP (memoization) | Caches results → O(n) time after initial computation        |
| Teaching overlapping subproblems | Naïve Fibonacci  | Demonstrates why naïve recursion fails and why DP is needed |
