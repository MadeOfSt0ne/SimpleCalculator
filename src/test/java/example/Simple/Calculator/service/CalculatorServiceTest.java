package example.Simple.Calculator.service;

import example.Simple.Calculator.model.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CalculatorServiceTest {

    private final CalculatorService calculator;

    @Autowired
    CalculatorServiceTest(CalculatorService calculator) {
        this.calculator = calculator;
    }

    @Test
    void testMapperWithPow() {
        Operation operation = new Operation("pow", 2.0, 2.0);
        String[] str = {"pow", "2", "2"};
        assertEquals(operation, CalculatorService.mapToOperation(str));
    }

    @Test
    void testMapperWithSin() {
        Operation operation = new Operation("sin", 2.0, 0.0);
        String[] str = {"sin", "2"};
        assertEquals(operation, CalculatorService.mapToOperation(str));
    }

    @Test
    void testSin() {
        final String str = "sin(-90)";
        assertEquals("-0,89", calculator.calculate(str));
    }

    @Test
    void testCos() {
        final String str = "cos(-45)";
        assertEquals("0,53", calculator.calculate(str));
    }

    @Test
    void testPow() {
        final String str = "pow(5, 5)";
        assertEquals("3125,00", calculator.calculate(str));
    }

    @Test
    void testSqrt() {
        final String str = "sqrt(144)";
        assertEquals("12,00", calculator.calculate(str));
    }

    @Test
    void testSqrtFromNegative() {
        final String str = "sqrt(-144)";
        assertThrows(UnsupportedOperationException.class, () -> calculator.calculate(str));
    }

    @Test
    void divisionPositivePositive() {
        final String str = "(11 / 3)";
        assertEquals("3,67", calculator.calculate(str));
    }

    @Test
    void divisionPositiveNegative() {
        final String str = "33 / -5";
        assertEquals("-6,60", calculator.calculate(str));
    }

    @Test
    void divisionNegativePositive() {
        final String str = "-7 / 14";
        assertEquals("-0,50", calculator.calculate(str));
    }

    @Test
    void divisionNegativeNegative() {
        final String str = "-25 / -6";
        assertEquals("4,17", calculator.calculate(str));
    }

    @Test
    void divisionByZero() {
        final String str = "11 / 0";
        assertThrows(UnsupportedOperationException.class, () -> calculator.calculate(str));
    }

    @Test
    void multiplicationPositivePositive() {
        final String str = "5.11 * 3.23";
        assertEquals("16,51", calculator.calculate(str));
    }

    @Test
    void multiplicationPositiveNegative() {
        final String str = "(5 * -4.74)";
        assertEquals("-23,70", calculator.calculate(str));
    }

    @Test
    void multiplicationNegativeNegative() {
        final String str = "-5.99 * -7.15";
        assertEquals("42,83", calculator.calculate(str));
    }

    @Test
    void multiplicationWithZero() {
        final String str = "531 * 0";
        assertEquals("0,00", calculator.calculate(str));
    }

    @Test
    void additionPositivePositive() {
        final String str = "5.15 + 5.13";
        assertEquals("10,28", calculator.calculate(str));
    }

    @Test
    void additionPositiveNegative() {
        final String str = "-5.45 + 3";
        assertEquals("-2,45", calculator.calculate(str));
    }

    @Test
    void additionNegativeNegative() {
        final String str = "-15.5 + -5.6";
        assertEquals("-21,10", calculator.calculate(str));
    }

    @Test
    void additionZeroZero() {
        final String str = "0 + 0";
        assertEquals("0,00", calculator.calculate(str));
    }

    @Test
    void subtractionPositivePositive() {
        final String str = "(23.19 - 7.91)";
        assertEquals("15,28", calculator.calculate(str));
    }

    @Test
    void subtractionPositiveNegative() {
        final String str = "23 - -7";
        assertEquals("30,00", calculator.calculate(str));
    }

    @Test
    void subtractionNegativePositive() {
        final String str = "-23 - 7";
        assertEquals("-30,00", calculator.calculate(str));
    }

    @Test
    void subtractionNegativeNegative() {
        final String str = "-23 - -8";
        assertEquals("-15,00", calculator.calculate(str));
    }
}
