package csProject.ast;

import csProject.core.Environment;
import csProject.core.Value;

/**
 * Base class of the hierarchy representing various kinds of expressions.
 */
public abstract class Expression {

  /**
   * Accept a Visitor.
   * @param visitor visitor to perform computation
   * @return result of visitor's computation
   * @param <T> the visitor's return type
   */
  public abstract <T> T accept(ExpressionVisitor<T> visitor);

  /**
   * Create a string representation of the expression, suitable for
   * printing to users.  This method should generate the same form that
   * the parser expects.
   * @return a string representation of the expression
   */
  public abstract String format();
}
