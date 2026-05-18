# 🔄 Chapter 3: Queue Theory (Linear → Circular → Linked-List)
> 🎯 Subject Guide: SO5-SO6 — Data Structures (storing data)  
> 🔗 Builds on: Chapter 2 (Foundations)

## 🔑 Key Progression

### 1. Linear Array Queue (§2.2)
- **Problem**: Rear only moves forward → vacated front cells permanently lost
- **False full**: `rear == capacity-1` even if `front > 0`
- **Workaround cost**: O(n) shifting → defeats fast queue operations

### 2. Circular Queue (§2.3) — ✅ RECOMMENDED
- **Fix**: Modulo wrap: `(index + 1) % capacity`
- **Full detection**: Use size counter (Option 1) to avoid ambiguity
- **Benefit**: Reuses vacated cells in O(1), no shifting

### 3. Linked-List Queue (§2.6)
- **Benefit**: Dynamic sizing, never full
- **Trade-off**: Pointer overhead, poorer cache locality

## ⚠️ Critical Exam Traps
| Trap | Correction |
|------|-----------|
| `peek()` modifies front | ❌ peek() must return value ONLY — no state change |
| Using only front/rear for full detection | ✅ Use size counter: `isFull() = size == capacity` |
| Forgetting modulo in circular queue | ✅ Always: `rear = (rear + 1) % capacity` |

## 🧪 Practice Strategy
1. Trace step-by-step with `toArrayString()` output
2. Verify modulo calculations: `(2+1)%3 = 0`
3. Test edge cases: empty, full, wrap-around

## 📚 Resources
- Blueprint §2.2-2.7: Queue theory deep dive
- `QueueLinear.java`: Demonstrates wasted-space problem
- `QueueCircular.java`: Modulo wrap implementation with detailed logging
- `QueueComparisonTest.java`: Proves linear vs circular behavior

## 🚀 Quick Start
```bash
cd 03-Queue-Theory/adp470s-queue
mvn test -Dtest=QueueComparisonTest
# View detailed operation logs in console