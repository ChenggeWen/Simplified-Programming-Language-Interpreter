package csProject.ast;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents an IF expression.
 */
public class IfExpression extends Expression {
  private final Expression condition;
  private final Expression consequent;
  private final Expression alternative;

  /**
   * Constructs an <code>if</code> expression.
   * @param condition the condition expression
   * @param consequent the expression to be evaluated when condition is true
   * @param alternative the expression to be evaluated when condition is false
   */
  public IfExpression(
    Expression condition,
    Expression consequent,
    Expression alternative
  ) {
    this.condition = condition;
    this.consequent = consequent;
    this.alternative = alternative;
  }
  /**
   * Returns the condition expression of this if-else structure.
   *
   * @return the condition expression
   */
  public Expression getCondition() {
    return condition;
  }
  /**
   * Returns the expression to be evaluated if the condition is true.
   *
   * @return the consequent expression
   */
  public Expression getConsequent() {
    return consequent;
  }
  /**
   * Returns the expression to be evaluated if the condition is false.
   *
   * @return the alternative expression
   */
  public Expression getAlternative() {
    return alternative;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IfExpression that = (IfExpression) o;
    return Objects.equals(condition, that.condition)
      && Objects.equals(consequent, that.consequent)
      && Objects.equals(alternative, that.alternative);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }


  @Override
  public String format() {
    return "(" + condition.format() + " ? " + consequent.format() + " : " + alternative.format() + ")";
  }
}
