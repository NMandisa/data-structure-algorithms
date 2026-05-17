package za.co.mkhungo.dsa.bigo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Loop Patterns — Big-O Verification")
class LoopPatternsTest {

    @Test
    @DisplayName("O(1): Constant time loop")
    void testConstantTime() {
        assertEquals(45, LoopPatterns.constantTime(10));
        assertEquals(45, LoopPatterns.constantTime(1000)); // Same result!
    }

    @Test
    @DisplayName("O(n): Linear time loop")
    void testLinearTime() {
        assertEquals(0, LoopPatterns.linearTime(0));
        assertEquals(1, LoopPatterns.linearTime(2)); // 0+1
        assertEquals(45, LoopPatterns.linearTime(10)); // 0+1+...+9
    }

    @Test
    @DisplayName("O(n): Sequential loops (not nested!)")
    void testSequentialLoops() {
        assertEquals(90, LoopPatterns.sequentialLoops(10)); // 45 + 45
    }

    @Test
    @DisplayName("O(n²): Full nested loops")
    void testNestedLoops() {
        assertEquals(0, LoopPatterns.nestedLoops(0));
        assertEquals(4, LoopPatterns.nestedLoops(2)); // 2×2
        assertEquals(100, LoopPatterns.nestedLoops(10)); // 10×10
    }

    @Test
    @DisplayName("O(n²): Triangular nested loops")
    void testTriangularNested() {
        assertEquals(0, LoopPatterns.triangularNested(0));
        assertEquals(3, LoopPatterns.triangularNested(2)); // 2+1
        assertEquals(55, LoopPatterns.triangularNested(10)); // 10+9+...+1
    }

    @Test
    @DisplayName("O(log n): Doubling loop")
    void testLogarithmicDoubling() {
        assertEquals(0, LoopPatterns.logarithmicDoubling(1));
        assertEquals(1, LoopPatterns.logarithmicDoubling(2)); // i=1
        assertEquals(3, LoopPatterns.logarithmicDoubling(8)); // i=1,2,4
        assertEquals(10, LoopPatterns.logarithmicDoubling(1024)); // log₂(1024)=10
    }

    @Test
    @DisplayName("O(log n): Halving loop")
    void testLogarithmicHalving() {
        assertEquals(1, LoopPatterns.logarithmicHalving(1)); // i=1
        assertEquals(2, LoopPatterns.logarithmicHalving(2)); // i=2,1
        assertEquals(11, LoopPatterns.logarithmicHalving(1024)); // log₂(1024)+1
    }

    @Test
    @DisplayName("O(n log n): Linear × Logarithmic")
    void testLinearLogarithmic() {
        assertEquals(0, LoopPatterns.linearLogarithmic(1));
        // n=10: outer=10 iterations, inner: j=1,2,4,8 → 4 iterations each
        // Total: 10 × 4 = 40
        assertEquals(40, LoopPatterns.linearLogarithmic(10));
    }

    @Test
    @DisplayName("O(√n): Square root loop")
    void testSquareRootLoop() {
        assertEquals(0, LoopPatterns.squareRootLoop(0));
        assertEquals(1, LoopPatterns.squareRootLoop(1)); // i=0: 0*0<1 ✓; i=1: 1*1<1 ✗ → count=1
        // n=10: i=0,1,2,3 satisfy i*i<10 → count=4
        assertEquals(4, LoopPatterns.squareRootLoop(10));
        assertEquals(10, LoopPatterns.squareRootLoop(100)); // √100 = 10 → i=0..9 → count=10
    }

    @Test
    @DisplayName("Complexity guide prints without error")
    void testPrintComplexityGuide() {
        assertDoesNotThrow(LoopPatterns::printComplexityGuide);
    }
}