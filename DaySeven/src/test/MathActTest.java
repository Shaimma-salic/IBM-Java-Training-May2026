package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.MathAct;

class MathActTest {

    @Test
    void testAddition() {
        // GIVEN
        float a = 10.5f;
        float b = 5.0f;

        // WHEN
        float sum = MathAct.add(a, b);

        // THEN
        assertEquals(15.5f, sum);
    }

    @Test
    void testSubtraction() {
    	//GIVEN
    	float a = 10.5f;
    	float b = 5.0f;
    	
    	//when
        float result = MathAct.subtract(a, b);
        //then
        	assertEquals(5.5f, result);
    }

    @Test
    void testMultiplication() {
    	//given
    	float a = 10.5f;
    	float b = 5.0f;
    	//when
        float result = MathAct.multiply(a,b);
        // then
        assertEquals(52.5f, result);
    }

    @Test
    void testDivision() {
    	//given
    	float a = 10.5f;
    	float b = 5.0f;
    	//when
    	float result = MathAct.divide(a, b);
    	//then
        assertEquals(2.1f, result, 0.001f);
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            MathAct.divide(10.5f, 0.0f);
        });
    }

}
