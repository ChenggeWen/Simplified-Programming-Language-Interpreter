package csProject.ast;

import java.util.Objects;

/**
 * Represents a use of the <code>or</code> operator, with short-circuiting.
 */
public class OrExpression extends Expression {
  private final Expression left;
  private final Expression right;

  /**
   * Constructs a new OR expression.
   * @param left OR expression's left-hand operand
   * @param right OR expression's right-hand operand
   */
  public OrExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }
  /**
   * Returns the left-hand operand of this OR expression.
   *
   * @return the left operand expression
   */
  public Expression getLeft() {
    return left;
  }
  /**
   * Returns the right-hand operand of this OR expression.
   *
   * @return the right operand expression
   */
  public Expression getRight() {
    return right;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrExpression that = (OrExpression) o;
    return Objects.equals(left, that.left) && Objects.equals(
      right, that.right);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public String format() {
    return "(" + left.format() + " || " + right.format() + ")";
  }
}
