package za.co.mkhungo.dsa.bigo;

import lombok.extern.slf4j.Slf4j;
import za.co.mkhungo.dsa.common.ArrayPrinter;

/**
 * Loop pattern examples for Big-O analysis practice.
 * 🔑 Exam Focus: Identify time complexity by inspection.
 *
 * @author Noxolo Mkhungo
 * @date 2026
 */
@Slf4j
public class LoopPatterns {

    // O(1) - Constant time
    public static int constantTime(int n) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {  // Fixed iterations
            sum += i;
        }
        return sum;
    }

    // O(n) - Linear time
    public static int linearTime(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {  // Runs n times
            sum += i;
        }
        return sum;
    }

    // O(n) - Sequential loops (NOT nested!)
    public static int sequentialLoops(int n) {
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < n; i++) sum1 += i;  // O(n)
        for (int j = 0; j < n; j++) sum2 += j;  // O(n)
        return sum1 + sum2;  // O(n) + O(n) = O(n)
    }

    //  O(n²) - Nested loops (full grid)
    public static int nestedLoops(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {          // Outer: n times
            for (int j = 0; j < n; j++) {      // Inner: n times per outer
                count++;                        // Total: n × n = n²
            }
        }
        return count;
    }

    // O(n²) - Triangular nested (j starts at i)
    public static int triangularNested(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {      // Runs (n-i) times
                count++;
            }
        }
        // Total: n + (n-1) + (n-2) + ... + 1 = n(n+1)/2 → O(n²)
        return count;
    }

    // O(log n) - Logarithmic (doubling)
    public static int logarithmicDoubling(int n) {
        int count = 0;
        for (int i = 1; i < n; i *= 2) {  // i: 1,2,4,8,...,2^k < n
            count++;                       // k ≈ log₂(n) iterations
        }
        return count;
    }

    // O(log n) - Logarithmic (halving)
    public static int logarithmicHalving(int n) {
        int count = 0;
        for (int i = n; i > 0; i /= 2) {  // i: n, n/2, n/4, ..., 1
            count++;                       // ≈ log₂(n) iterations
        }
        return count;
    }

    // O(n log n) - Linear × Logarithmic
    public static int linearLogarithmic(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {           // Outer: n times
            for (int j = 1; j < n; j *= 2) {    // Inner: log n times
                count++;
            }
        }
        return count;  // n × log n = O(n log n)
    }

    // O(√n) - Square root loop
    public static int squareRootLoop(int n) {
        int count = 0;
        for (int i = 0; i * i < n; i++) {  // i < √n
            count++;
        }
        return count;  // O(√n)
    }

    /**
     * Helper: Print complexity summary for exam revision.
     */
    public static void printComplexityGuide() {
        log.info("\n📊 Big-O Loop Patterns Reference:");
        log.info("  O(1)      : Fixed iterations (independent of n)");
        log.info("  O(log n)  : Loop variable doubles/halves each step");
        log.info("  O(n)      : Single loop over n elements");
        log.info("  O(n log n): Nested: linear × logarithmic");
        log.info("  O(n²)     : Nested: linear × linear");
        log.info("  O(2ⁿ)     : Recursion with 2 branches per call");
        log.info("  O(n!)     : Permutations, full enumeration");
    }
}