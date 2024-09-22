package polynomial;

import java.util.ArrayList;

public class SimplePolynomial implements Polynomial {

  private String polynomialString;
  private ArrayList<Term> polynomialList = new ArrayList<Term>();

  public SimplePolynomial() {
    polynomialString = "0";
  }

  public String getPolyNomialString() { //dont need this
    return polynomialString;
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
    for(int i = getDegree(); i >= 0; i--) {
      newPolynomial.addTerm(getCoefficient(i), i);
//      simplify();
    }
    for(int i = other.getDegree(); i >= 0; i--) {
      newPolynomial.addTerm(other.getCoefficient(i), i);
//      simplify();
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
    return null;
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
    return null;
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
    ArrayList<Term> polynomialListNew = new ArrayList<Term>();
    if(power < 0) {
      throw new IllegalArgumentException();
    }
    Term term = new Term(coefficient, power);
//    for(Term termFind : polynomialList) {
//      if(termFind.getDegree() == power) {
//        int newCoefficient = termFind.getCoefficient() + coefficient;
//        term = new Term(newCoefficient, power);
//      }
//    }
    polynomialList.add(term);

  }

  /**
   * Get the degree of this polynomial. The degree of a polynomial is defined as the
   * highest power in the polynomial.
   *
   * @return the degree of this polynomial as a whole number
   */
  @Override
  public int getDegree() {
    int highestNumber = 0;
    for(Term term : polynomialList) {
      if(term.getDegree() > highestNumber) {
        highestNumber = term.getDegree();
      }
    }
    return highestNumber;
  }

  /**
   * Evaluate the value of this polynomial at the given value of the variable.
   *
   * @param x the value at which the polynomial is to be evaluated.
   * @return the value of the polynomial at x
   */
  @Override
  public double evaluate(double x) {
    return 0;
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
    for(Term term : polynomialList) {
      if (term.getDegree() == power) {
        coefficient += term.getCoefficient();
      }
    }
    return coefficient;
  }

  @Override
  public String toString() { //make a case for if negative, swap to subtraction
    StringBuilder resultString = new StringBuilder();
//    for(int i = 0; i < polynomialList.size(); i++) {
    for(int i = getDegree(); i >= 0; i--) {
      int coefficient = getCoefficient(i);
      if(coefficient != 0) {
        resultString.append(coefficient);
//        if(polynomialList.get(i).getDegree() > 0) {
        if(i > 0) {
          resultString.append("x");
        }
//        if(polynomialList.get(i).getDegree() > 1) {
        if(i > 1) {
//          resultString.append("^" + polynomialList.get(i).getDegree());
          resultString.append("^" + i);
        }
//        if(i != polynomialList.size() - 1) {
        if(i != 0) {
          resultString.append("+");
        }
      }
    }
    if(resultString.charAt(resultString.length() - 1) == '+'
            || resultString.charAt(resultString.length() - 1) == '-') {
      resultString.deleteCharAt(resultString.length() - 1);
    }
    return resultString.toString();
  }
}
