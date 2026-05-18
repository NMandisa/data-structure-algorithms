# 🏗️ Chapter 2: Foundations & ADTs
> 🎯 Subject Guide: SO5-SO6 — Data Structures (storing data)

## 🔑 Core Concepts

### Data Structure vs ADT
| Concept            | Definition                                                                             | Exam Trap                                                                            |
|--------------------|----------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| **Data Structure** | Systematic way to organise data (logical model + operations + physical representation) | Don't confuse with ADT                                                               |
| **ADT**            | Specifies WHAT operations are allowed, not HOW they're implemented                     | ❌ "Queue = LinkedList" → ✅ "Queue is FIFO ADT; implemented via array or linked list" |

### Linear vs Non-Linear
| Aspect                | Linear                           | Non-Linear           |
|-----------------------|----------------------------------|----------------------|
| Arrangement           | Single sequence — one path       | Branching/networked  |
| Predecessor/Successor | Exactly one each (except ends)   | Multiple allowed     |
| Traversal             | Forward/backward along sequence  | DFS, BFS, multi-path |
| Examples              | Array, Linked List, Stack, Queue | BST, Heap, Graph     |

💡 **Memory hook**: Linear = line. Non-linear = network.

## 🧪 Practice Focus
- Implement `QueueCircular.java` with modulo wrap
- Trace pointer movements for enqueue/dequeue
- Verify ADT contract vs implementation distinction

## 📚 Resources
- Blueprint §1.1-1.4: ADT definitions, linear/non-linear split
- `QueueCircular.java`: Size-counter implementation with logging
- `QueueComparisonTest.java`: Linear vs circular behavior tests

## 🚀 Quick Start
```bash
cd 02-Foundations-ADTs/adp470s-foundations
mvn test -Dtest=QueueComparisonTest