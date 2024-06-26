package csProject.ast;

import java.util.Objects;

/**
 * Represents a use of the boolean AND operator, with short-circuiting.
 */
public class AndExpression extends Expression {
  private final Expression leftOperand;
  private final Expression rightOperand;

  /**
   * Construct a new AND expression.
   * @param left AND expression's left-hand argument
   * @param right AND expression's right-hand argument
   */
  public AndExpression(Expression left, Expression right) {
    leftOperand = left;
    rightOperand = right;
  }

  /**
   * Return the left operand.
   * @return left operand
   */
  public Expression getLeftOperand() {
    return leftOperand;
  }

  /**
   * Return the right operand.
   * @return right operand
   */
  public Expression getRightOperand() {
    return rightOperand;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AndExpression that = (AndExpression) o;
    return Objects.equals(leftOperand, that.leftOperand)
      && Objects.equals(rightOperand, that.rightOperand);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public String format() {
    return "and(" + leftOperand.format() + ", " + rightOperand.format() + ")";
  }
}
