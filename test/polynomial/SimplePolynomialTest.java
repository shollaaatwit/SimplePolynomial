package polynomial;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class for testing methods in the SimplePolynomial class methods.
 */
public class SimplePolynomialTest {
  private SimplePolynomial simplePolynomial;
  private SimplePolynomial simplePolynomial2;
  private SimplePolynomial incomingPolynomial;
  private SimplePolynomial incomingPolynomial2;
  private SimplePolynomial incomingPolynomial3;
  private SimplePolynomial multiplyPolynomial1;
  private SimplePolynomial multiplyPolynomial2;
  private SimplePolynomial simplePolynomialDupe;

  @Before
  public void setUp() throws Exception {
    simplePolynomial = new SimplePolynomial();
    simplePolynomial.addTerm(5, 3);
    simplePolynomial.addTerm(3, 2);
    simplePolynomial.addTerm(2, 1);
    simplePolynomial.addTerm(1, 0);

    simplePolynomial2 = new SimplePolynomial();
    simplePolynomial2.addTerm(5, 3);
    simplePolynomial2.addTerm(3, 2);
    simplePolynomial2.addTerm(2, 1);
    simplePolynomial2.addTerm(1, 0);
    simplePolynomial2.addTerm(2, 6);

    incomingPolynomial = new SimplePolynomial();
    incomingPolynomial.addTerm(3, 3);
    incomingPolynomial.addTerm(2, 2);

    incomingPolynomial2 = new SimplePolynomial();
    incomingPolynomial2.addTerm(3, 6);
    incomingPolynomial2.addTerm(2, 1);

    incomingPolynomial3 = new SimplePolynomial();
    incomingPolynomial3.addTerm(3, 3);
    incomingPolynomial3.addTerm(2, 3);
    incomingPolynomial3.addTerm(-1, 2);
    incomingPolynomial3.addTerm(-7, 1);
    incomingPolynomial3.addTerm(-1, 0);
    incomingPolynomial3.addTerm(-2, 5);

    multiplyPolynomial1 = new SimplePolynomial();
    multiplyPolynomial1.addTerm(3, 3);
    multiplyPolynomial1.addTerm(2, 2);
    multiplyPolynomial1.addTerm(1, 1);
    multiplyPolynomial1.addTerm(3, 0);

    multiplyPolynomial2 = new SimplePolynomial();
    multiplyPolynomial2.addTerm(5, 2);
    multiplyPolynomial2.addTerm(2, 1);
    multiplyPolynomial2.addTerm(6, 7);
    multiplyPolynomial2.addTerm(5, 0);

    simplePolynomialDupe = new SimplePolynomial();
    simplePolynomialDupe.addTerm(5, 3);
    simplePolynomialDupe.addTerm(3, 2);
    simplePolynomialDupe.addTerm(2, 1);
    simplePolynomialDupe.addTerm(1, 0);

  }


  @Test
  public void testAddTerm() {
    assertEquals("5x^3+3x^2+2x+1", simplePolynomial.toString());
  }

  @Test
  public void testGetDegree() {
    assertEquals(3, simplePolynomial.getDegree());
  }

  @Test
  public void testGetCoefficient() {
    assertEquals(8, simplePolynomial.add(incomingPolynomial).getCoefficient(3));
  }

  @Test
  public void testAddPolynomial() {
    assertEquals("8x^3+5x^2+2x+1", simplePolynomial.add(incomingPolynomial).toString());
  }

  @Test
  public void testAddPolynomial2() {
    assertEquals("3x^6+5x^3+3x^2+4x+1", simplePolynomial.add(incomingPolynomial2).toString());
  }

  @Test
  public void testAddPolynomial3() {
    assertEquals("-2x^5+10x^3+2x^2-5x", simplePolynomial.add(incomingPolynomial3).toString());
  }

  @Test
  public void testMultiplyPolynomial() {
    assertEquals("18x^10+12x^9+6x^8+18x^7+15x^5+16x^4+24x^3+27x^2+11x+15",
            multiplyPolynomial1.multiply(multiplyPolynomial2).toString());
  }

  @Test
  public void testDerivative() {
    assertEquals("15x^2+6x+2", simplePolynomial.derivative().toString());
  }

  @Test
  public void testEvaluate() {
    assertEquals(-113.0, simplePolynomial.evaluate(-3), 0);
  }

  @Test
  public void testMultipleCommands() {
    SimplePolynomial addPolynomials = new SimplePolynomial();
    addPolynomials.addTerm(3, 6);
    addPolynomials.addTerm(2, 4);
    assertEquals("162x^8+96x^7+84x^6+132x^5+10x^4+48x^3+12x^2+4x+6",
            addPolynomials.add(incomingPolynomial2).multiply(
                    multiplyPolynomial1).derivative().toString());

  }

  @Test
  public void testNegativeMultiPositive() {
    assertEquals("-6x^8-4x^7+13x^6+1x^5-18x^4-3x^3-12x^2-22x-3",
            incomingPolynomial3.multiply(multiplyPolynomial1).toString());
  }

  @Test
  public void testNegativeMultiPositiveEvaluate() {
    assertEquals(-409563.0,
            incomingPolynomial3.multiply(multiplyPolynomial1).evaluate(4), 0);
  }

  @Test
  public void testNegativeMultiPositiveEvaluateDecimal() {
    assertEquals(-1056580.5703125,
            incomingPolynomial3.multiply(multiplyPolynomial1).evaluate(4.5), 0);
  }

  @Test
  public void testEqualsFalse() {
    assertFalse(simplePolynomial.equals(incomingPolynomial));
  }

  @Test
  public void testEqualsFalse1() {
    Object obj2 = new Object();
    assertFalse(simplePolynomial.equals(obj2));
  }

  @Test
  public void testEqualsFalse2() {
    assertFalse(simplePolynomial.equals(multiplyPolynomial2));
  }

  @Test
  public void testEqualsFalse3() {
    assertFalse(simplePolynomial.equals(simplePolynomial2));
  }

  @Test
  public void testEqualsTrue() {
    assertTrue("Polynomials were not equal", simplePolynomial.equals(simplePolynomialDupe));
  }

  @Test
  public void testHashCode() {
    assertEquals(simplePolynomial.hashCode(), simplePolynomialDupe.hashCode());

  }

  @Test
  public void testInvalidAddTerm() throws IllegalArgumentException {
    try {
      simplePolynomial.addTerm(4, -3);
      fail("Supposed to catch illegal argument!");
    } catch (IllegalArgumentException e) {
      //do nothing, catch succeeded
    }
  }

  @Test
  public void testGetCoefficient0() {
    assertEquals(0, simplePolynomial.getCoefficient(-3));

  }
}