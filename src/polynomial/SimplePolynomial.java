package polynomial;

import java.util.ArrayList;

/**
 * SimplyPolynomial class implements the Polynomial interface,
 * Creates an empty polynomial on construction, add terms, polynomials,
 * and manipulate them.
 */
public class SimplePolynomial implements Polynomial {

  private final ArrayList<Integer> realPolynomialList = new ArrayList<>();
  private int maxDegree = 0;

  /**
   * Constructor for SimplePolynomial class.
   * It creates a string "0" to represent an empty polynomial.
   */
  public SimplePolynomial() {
    String polynomialString = "0";
  }

  /**
   * Add this polynomial with another and return the result as another
   * polynomial. All implementations must ensure that neither of the two operand
   * polynomials are changed as a result of this operation.
   *
   * @param other the other polynomial to be added
   * @return the resulting polynomial
   */
  @Override
  public Polynomial add(Polynomial other) {
    SimplePolynomial newPolynomial = new SimplePolynomial();
    for (int i = getDegree(); i >= 0; i--) {
      newPolynomial.addTerm(getCoefficient(i), i);
    }
    for (int i = other.getDegree(); i >= 0; i--) {
      newPolynomial.addTerm(other.getCoefficient(i), i);
    }
    return newPolynomial;
  }

  /**
   * Multiply this polynomial to another and return the result as another
   * polynomial. All implementations must ensure that neither of the two operand
   * polynomials are changed as a result of this operation.
   *
   * @param other the other polynomial to be multiplied
   * @return the resulting polynomial
   */
  @Override
  public Polynomial multiply(Polynomial other) {
    SimplePolynomial newPolynomial = new SimplePolynomial();
    for (int i = getDegree(); i >= 0; i--) {
      for (int j = other.getDegree(); j >= 0; j--) {
        if (getCoefficient(i) != 0 && other.getCoefficient(j) != 0) {
          int newCoefficient = getCoefficient(i) * other.getCoefficient(j);
          int newDegree = i + j;
          newPolynomial.addTerm(newCoefficient, newDegree);
        }
      }
    }
    return newPolynomial;
  }

  /**
   * Compute and return the first derivative of this polynomial. All implementations
   * must ensure that the calling polynomial is not changed as a result of this
   * operation.
   *
   * @return the polynomial that is the first derivative of this polynomial
   */
  @Override
  public Polynomial derivative() {
    SimplePolynomial newPolynomial = new SimplePolynomial();
    for (int i = getDegree(); i >= 0; i--) {
      int newCoefficient = getCoefficient(i) * i;
      int newDegree = i - 1;
      newPolynomial.addTerm(newCoefficient, Math.max(newDegree, 0));
    }
    return newPolynomial;
  }

  /**
   * Add a term to this polynomial with the specified coefficient and power.
   *
   * @param coefficient the coefficient of the term to be added
   * @param power       the power of the term to be added
   * @throws IllegalArgumentException if the power is negative
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    realPolynomialList.add(0);
    if (power < 0) {
      throw new IllegalArgumentException();
    }
    while (realPolynomialList.size() <= power) {
      realPolynomialList.add(0);
    }
    //set the index to correspond to the exponent
    realPolynomialList.set(power, realPolynomialList.get(power) + coefficient);
    if (coefficient != 0) {
      maxDegree = Math.max(maxDegree, power);

    }
  }

  /**
   * Get the degree of this polynomial. The degree of a polynomial is defined as the
   * highest power in the polynomial.
   *
   * @return the degree of this polynomial as a whole number
   */
  @Override
  public int getDegree() {
    return maxDegree;
  }

  /**
   * Evaluate the value of this polynomial at the given value of the variable.
   *
   * @param x the value at which the polynomial is to be evaluated.
   * @return the value of the polynomial at x
   */
  @Override
  public double evaluate(double x) {
    double sum = 0;
    for (int i = getDegree(); i >= 0; i--) {
      sum += getCoefficient(i) * Math.pow(x, i);
    }
    return sum;
  }

  /**
   * Return the coefficient of the term with the given power.
   *
   * @param power the power whose coefficient is sought
   * @return the coefficient at the given power
   */
  @Override
  public int getCoefficient(int power) {
    int coefficient = 0;
    if (power >= realPolynomialList.size()) {
      return 0;
    }
    if (power < 0) {
      return 0;
    }
    coefficient = realPolynomialList.get(power);
    return coefficient;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof SimplePolynomial)) {
      return false;
    }
    SimplePolynomial other2 = (SimplePolynomial) other;
    if (getDegree() != other2.getDegree()) {
      return false;
    }
    for (int i = getDegree(); i >= 0; i--) {
      if (getCoefficient(i) != other2.getCoefficient(i)) {
        return false;
      }
    }
    for (int i = other2.getDegree(); i >= 0; i--) {
      if (other2.getCoefficient(i) != getCoefficient(i)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return (int) evaluate(5);
  }

  @Override
  public String toString() { //make a case for if negative, swap to subtraction
    StringBuilder resultString = new StringBuilder();
    for (int i = getDegree(); i >= 0; i--) {
      int coefficient = getCoefficient(i);
      int nextDegree = i - 1;
      if (nextDegree <= 0) {
        nextDegree = 0;
      }
      int nextCoefficient = getCoefficient(nextDegree);
      if (coefficient != 0) {
        if (i == getDegree()) {
          resultString.append(coefficient);
        } else {
          resultString.append(Math.abs(coefficient));
        }
        if (i > 0) {
          resultString.append("x");
        }
        if (i > 1) {
          resultString.append("^").append(i);
        }
        if (nextCoefficient >= 0) {
          resultString.append("+");
        } else {
          resultString.append("-");
        }
      }
    }
    if (resultString.charAt(resultString.length() - 1) == '+'
            || resultString.charAt(resultString.length() - 1) == '-') {
      resultString.deleteCharAt(resultString.length() - 1);
    }
    return resultString.toString();
  }
}
