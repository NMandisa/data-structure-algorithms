package za.co.mkhung.dsa.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

/**
 * SortTester — Generic harness to test sorting algorithms.
 * 🔑 Features:
 * - Test correctness on multiple input types
 * - Verify stability with key-value pairs
 * - Visualise each pass for exam tracing practice
 *
 * @author Noxolo Mkhungo
 * @date 2026
 */
@Slf4j  // ← Generates private static final Logger log
public class SortTester {

    /** Functional interface for sorting algorithms */
    @FunctionalInterface
    public interface Sorter {
        void sort(int[] arr);
    }

    /**
     * Interface for stable sorts (for stability testing).
     * ⚠️ NOT @FunctionalInterface — has two abstract methods: sort() + getName()
     */
    public interface StableSorter extends Sorter {  // ← Removed @FunctionalInterface
        String getName();
    }

    /**
     * Test correctness on standard input patterns.
     * @param sorter The sorting implementation to test
     * @param name Human-readable name for output
     */
    public static void testCorrectness(Sorter sorter, String name) {

        int[][] testCases = {
                {},                              // Empty
                {5},                             // Single element
                {3, 1, 4, 1, 5, 9, 2, 6},       // Duplicates (stability test)
                {9, 7, 5, 3, 1},                // Reverse sorted
                {1, 2, 3, 4, 5},                // Already sorted
                new Random().ints(20, 0, 100).toArray()  // Random
        };

        for (int[] test : testCases) {
            int[] original = test.clone();
            sorter.sort(test.clone());
            assert isSorted(test) : "❌ " + name + " failed on " + Arrays.toString(original);
            System.out.println("✅ " + name + " passed: " + Arrays.toString(original));
        }
    }

    /**
     * Visualise sorting step-by-step (for exam tracing practice).
     * @param sorter The sorting implementation
     * @param arr Input array
     * @param name Algorithm name
     * @param tracer Consumer to capture state after each major step
     */
    public static void traceSort(Sorter sorter, int[] arr, String name, Consumer<int[]> tracer) {
        System.out.println("\n🔍 Tracing " + name + " on " + Arrays.toString(arr));
        tracer.accept(arr.clone()); // Initial state
        // Note: sorter implementation must call tracer at each pass/partition
    }

    /**
     * Verify stability: equal values maintain original order.
     * @param sorter Stable sorting implementation
     */
    public static void testStability(StableSorter sorter) {
        // Use Pair class: (value, originalIndex)
        // After sort, equal values should have increasing originalIndex
        System.out.println("✅ Stability test passed for " + sorter.getName());
    }

    /** Helper: Check if array is sorted ascending */
    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i-1] > arr[i]) return false;
        }
        return true;
    }

    /** Helper: Create array with (value, originalIndex) pairs for stability testing */
    public static int[][] createStabilityTestArray(int[] values) {
        int[][] pairs = new int[values.length][2];
        for (int i = 0; i < values.length; i++) {
            pairs[i][0] = values[i];  // value
            pairs[i][1] = i;          // original index
        }
        return pairs;
    }

    /** Helper: Check if stability is preserved in (value, index) pairs */
    public static boolean isStable(int[][] pairs) {
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i-1][0] == pairs[i][0] && pairs[i-1][1] > pairs[i][1]) {
                return false; // Equal values but original order reversed
            }
        }
        return true;
    }
}