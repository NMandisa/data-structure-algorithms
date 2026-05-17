package za.co.mkhung.dsa.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * ArrayPrinter — Visualise array state for exam tracing practice.
 *
 * 🔑 Features:
 * - Mark sorted portion with |
 * - Highlight pivot/front/rear indices
 * - Format for paper trace replication
 *
 * @author Noxolo Mkhungo
 * @date 2026
 */
@Slf4j  // ← Generates private static final Logger log
public class ArrayPrinter {

    /**
     * Print array with sorted portion marker.
     * Format: [1, 2, 3 | 4, 5] where | marks start of sorted suffix
     * @param arr The array
     * @param sortedFrom Index where sorted portion begins (elements from here to end are sorted)
     */
    public static void printWithSortedMarker(int[] arr, int sortedFrom) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == sortedFrom && i > 0) System.out.print(" | ");
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    /**
     * Print array with index markers (for queue/pointer tracing).
     * Format: [10*, 20, 30^] where * = front, ^ = rear
     * @param arr The array
     * @param front Index of front element (-1 if none)
     * @param rear Index of rear element (-1 if none)
     */
    public static void printWithPointers(int[] arr, int front, int rear) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == front) System.out.print("*");
            if (i == rear) System.out.print("^");
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    /**
     * Print array with pivot marker (for QuickSort tracing).
     * Format: [3, 1, |2|, 5, 4] where |2| is the pivot
     * @param arr The array
     * @param pivotIndex Index of pivot element
     */
    public static void printWithPivot(int[] arr, int pivotIndex) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == pivotIndex) System.out.print("|");
            System.out.print(arr[i]);
            if (i == pivotIndex) System.out.print("|");
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    /**
     * Print step header for trace output.
     * @param step Step number or description
     * @param action What happened in this step
     */
    public static void printStep(String step, String action) {
        System.out.printf("Step %-3s: %s%n", step, action);
    }

    /**
     * Print complexity summary for exam answers.
     * @param name Algorithm name
     * @param best Best-case complexity
     * @param avg Average-case complexity
     * @param worst Worst-case complexity
     * @param stable Is the sort stable?
     * @param inPlace Is the sort in-place?
     */
    public static void printComplexitySummary(String name, String best, String avg,
                                              String worst, boolean stable, boolean inPlace) {
        System.out.println("\n📊 " + name + " Complexity:");
        System.out.println("  Best:   " + best);
        System.out.println("  Average: " + avg);
        System.out.println("  Worst:  " + worst);
        System.out.println("  Stable: " + (stable ? "Yes" : "No"));
        System.out.println("  In-place: " + (inPlace ? "Yes" : "No"));
    }
}