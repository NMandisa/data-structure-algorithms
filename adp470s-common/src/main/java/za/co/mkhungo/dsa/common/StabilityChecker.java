package za.co.mkhung.dsa.common;

import lombok.extern.slf4j.Slf4j;

/**
 * StabilityChecker — Verify if a sort preserves relative order of equal keys.
 *
 * 🔑 Key Concept:
 * A stable sort maintains the original order of elements with equal keys.
 * Critical when sorting by multiple criteria (e.g., sort by grade, then by name).
 *
 * @author Noxolo Mkhungo
 * @date 2026
 */
@Slf4j  // ← Generates private static final Logger log
public class StabilityChecker {

    /**
     * Element wrapper for stability testing: holds value + original index.
     */
    public static class Element {
        public final int value;
        public final int originalIndex;

        public Element(int value, int originalIndex) {
            this.value = value;
            this.originalIndex = originalIndex;
        }

        @Override
        public String toString() {
            return value + "@" + originalIndex;
        }
    }

    /**
     * Create test array with original indices for stability verification.
     * @param values Input values (may contain duplicates)
     * @return Array of Element objects with original indices
     */
    public static Element[] createTestArray(int[] values) {
        Element[] elements = new Element[values.length];
        for (int i = 0; i < values.length; i++) {
            elements[i] = new Element(values[i], i);
        }
        return elements;
    }

    /**
     * Check if sorting preserved stability for equal values.
     * @param elements Array after sorting (must be sorted by value)
     * @return true if stable, false if unstable
     */
    public static boolean isStable(Element[] elements) {
        for (int i = 1; i < elements.length; i++) {
            // If values are equal but original order is reversed → unstable
            if (elements[i-1].value == elements[i].value &&
                    elements[i-1].originalIndex > elements[i].originalIndex) {
                return false;
            }
        }
        return true;
    }

    /**
     * Print stability test result in exam-friendly format.
     * @param sorterName Name of the sorting algorithm
     * @param isStable Result of stability check
     */
    public static void printResult(String sorterName, boolean isStable) {
        System.out.println("🔍 Stability Test: " + sorterName);
        System.out.println("  Result: " + (isStable ? "✅ STABLE" : "❌ UNSTABLE"));
        System.out.println("  Exam Note: " +
                (isStable ? "Preserves order of equal keys — safe for multi-key sorts"
                        : "May reorder equal keys — avoid when stability matters"));
    }

    /**
     * Quick reference: which common sorts are stable?
     * @param sorterName Algorithm name
     * @return true if known to be stable
     */
    public static boolean isKnownStable(String sorterName) {
        return switch (sorterName.toLowerCase()) {
            case "bubble", "shaker", "cocktail", "insertion", "merge",
                 "counting", "radix", "bucket" -> true;
            case "selection", "quick", "heap", "shell" -> false;
            default -> false; // Unknown — assume unstable for exam safety
        };
    }
}