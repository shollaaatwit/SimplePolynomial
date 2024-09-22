package polynomial;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimplePolynomialTest {
  private String polyString;
  private SimplePolynomial simplePolynomial;
  private SimplePolynomial incomingPolynomial;
  private SimplePolynomial incomingPolynomial2;
  private SimplePolynomial incomingPolynomial3;
  @Before
  public void setUp() throws Exception {
    simplePolynomial = new SimplePolynomial();
    simplePolynomial.addTerm(5, 3);
    simplePolynomial.addTerm(3, 2);
    simplePolynomial.addTerm(2, 1);
    simplePolynomial.addTerm(1, 0);

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
    incomingPolynomial3.addTerm(-2, 1);
    incomingPolynomial3.addTerm(-1, 0);
    incomingPolynomial3.addTerm(-2, 5);
  }


  @Test
  public void testSimplePolynomialConstructor() {
    assertEquals("0", simplePolynomial.getPolyNomialString());
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
    assertEquals(5, simplePolynomial.add(incomingPolynomial).getCoefficient(1));
  }

  @Test
  public void testAddPolynomial() {
    assertEquals("", simplePolynomial.add(incomingPolynomial).toString());
  }
  @Test
  public void testAddPolynomial2() {
    assertEquals("", simplePolynomial.add(incomingPolynomial2).toString());
  }

  @Test
  public void testAddPolynomial3() {
    assertEquals("", simplePolynomial.add(incomingPolynomial3).toString());
  }
}