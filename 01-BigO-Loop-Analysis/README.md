# 📊 Chapter 1: Big-O Loop Analysis

## 🔑 Key Rules for Exam Questions

| Pattern | Code Example | Time Complexity | Why? |
|---------|-------------|-----------------|------|
| **Single loop** | `for(i=0; i<n; i++)` | **O(n)** | Runs n times |
| **Sequential loops** | `for(n) { } for(n) { }` | **O(n)** | O(n) + O(n) = O(n) |
| **Nested loops** | `for(n) { for(n) { } }` | **O(n²)** | n × n iterations |
| **Triangular nested** | `for(n) { for(i) { } }` | **O(n²)** | n+(n-1)+...+1 = n(n+1)/2 |
| **Logarithmic** | `for(i=1; i<n; i*=2)` | **O(log n)** | i doubles each time |
| **Halving** | `for(i=n; i>0; i/=2)` | **O(log n)** | i halves each time |
| **Nested log** | `for(n) { for(log n) { } }` | **O(n log n)** | Common in efficient sorts |
| **Constant** | `for(i=0; i<10; i++)` | **O(1)** | Fixed iterations, independent of n |

## ⚠️ Common Exam Traps

1. **Don't multiply constants**: `3n + 5` → **O(n)**, not O(3n)
2. **Dominant term only**: `n² + n log n + 100` → **O(n²)**
3. **Log base doesn't matter**: O(log₂n) = O(log₁₀n) = O(log n)
4. **Sequential ≠ Nested**: Two separate O(n) loops = O(n), NOT O(n²)

## 🧪 Practice Strategy

1. Identify loop bounds and step size
2. Count iterations as function of n
3. Multiply for nesting, add for sequencing
4. Drop constants and lower-order terms

## 📚 Resources
- Course Blueprint §1.3: Loop Analysis Rules
- ArrayPrinter.java: Use for tracing iteration counts

# Build the Big-O module
mvn clean compile -pl 01-BigO-Loop-Analysis/adp470s-bigo

# Run tests
mvn test -pl 01-BigO-Loop-Analysis/adp470s-bigo

# Run complexity comparison helper
mvn exec:java -pl 01-BigO-Loop-Analysis/adp470s-bigo \
-Dexec.mainClass="za.co.mkhungo.dsa.bigo.ComplexityCalculator" \
-Dexec.args="100"