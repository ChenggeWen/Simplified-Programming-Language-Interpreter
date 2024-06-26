package csProject.ast;

import java.util.Objects;

/**
 * Represents an expression that introduces a new variable definition.
 */
public class LetExpression extends Expression {
  private final String varName;
  private final Expression rhs;
  private final Expression body;
  /**
   * Constructs a new <code>let</code> expression.
   * @param varName the name of the variable being defined
   * @param rhs the expression giving the value of the variable
   * @param body the expression within which the variable is defined
   */
  public LetExpression(String varName, Expression rhs, Expression body) {
    this.varName = varName;
    this.rhs = rhs;
    this.body = body;
  }
  /**
   * Returns the name of the variable defined by this 'let' expression.
   *
   * @return the variable name
   */
  public String getVarName() {
    return varName;
  }
  /**
   * Returns the right-hand side expression that defines the value of the variable.
   *
   * @return the expression that gives the value of the variable
   */
  public Expression getRhs() {
    return rhs;
  }
  /**
   * Returns the body of the expression within which the defined variable is accessible.
   * This expression represents the scope of the variable's binding.
   *
   * @return the body expression
   */
  public Expression getBody() {
    return body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LetExpression that = (LetExpression) o;
    return Objects.equals(varName, that.varName)
      && Objects.equals(rhs, that.rhs) && Objects.equals(body,
      that.body);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public String format() {
    return "let " + varName + " = " + rhs.format() + " in " + body.format();
  }
}
