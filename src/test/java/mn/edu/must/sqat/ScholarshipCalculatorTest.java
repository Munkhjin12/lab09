package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScholarshipCalculatorTest {
    private final ScholarshipCalculator calculator = new ScholarshipCalculator();

    @Test
    void gradeA() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void gradeC() {
        assertEquals("C", calculator.letterGrade(75));
    }

    @Test
    void gradeRejectsNegativeScore() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-1));
    }

    @Test
    void gradeRejectsAbove100() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(101));
    }

    @Test
    void gradeAt60() {
        assertEquals("D", calculator.letterGrade(60));
    }

    @ParameterizedTest
    @CsvSource({"59.99, F", "69.99, D", "0, F", "100, A", "89.99, B", "90, A"})
    void gradeAtOtherBoundaries(double score, String expected) {
        assertEquals(expected, calculator.letterGrade(score));
    }

    @Test
    void gpaAAndB() {
        assertEquals(3.5, calculator.gpa(new double[]{95, 85}));
    }

    @Test
    void gpaAllGrades() {
        assertEquals(2.0, calculator.gpa(new double[]{95, 85, 75, 65, 55}));
    }

    @Test
    void gpaRejectsEmptyScores() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[0]));
    }

    @Test
    void gpaRejectsNullScores() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
    }

    @Test
    void gpaWithOneScore() {
        assertEquals(4.0, calculator.gpa(new double[]{95}));
    }

    @Test
    void gpaWithMultipleScores() {
        assertEquals(2.5, calculator.gpa(new double[]{95, 85, 75, 65}));
    }

    @Test
    void scholarship100() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void scholarship70() {
        assertEquals(70, calculator.scholarship(3.5, 14, false));
    }

    @Test
    void rejectsNegativeGpa() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(-0.1, 15, false));
    }

    @Test
    void rejectsGpaAboveFour() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(4.1, 15, false));
    }

    @Test
    void rejectsNegativeCredits() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(3.9, -1, false));
    }

    @Test
    void violationReturnsZero() {
        assertEquals(0, calculator.scholarship(3.9, 16, true));
    }

    @Test
    void highTierCreditsShort() {
        assertEquals(70, calculator.scholarship(3.9, 14, false));
    }

    @Test
    void midTierCreditsShort() {
        assertEquals(50, calculator.scholarship(3.5, 11, false));
    }

    @Test
    void lowGpaReturnsZero() {
        assertEquals(0, calculator.scholarship(2.9, 16, false));
    }
}
