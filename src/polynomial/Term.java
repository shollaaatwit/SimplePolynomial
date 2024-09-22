package polynomial;

public class Term {
  private int degree;
  private int coefficient;


  public Term(int coefficient, int degree) {
    this.degree = degree;
    this.coefficient = coefficient;
  }

  public int getDegree() {
    return degree;
  }
  public int getCoefficient() {
    return coefficient;
  }

}
