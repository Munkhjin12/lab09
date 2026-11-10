package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void gpaAAndB() {
        assertEquals(3.5, calculator.gpa(new double[]{95, 85}));
    }

    @Test
    void scholarship100() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void scholarship70() {
        assertEquals(70, calculator.scholarship(3.5, 14, false));
    }
}
