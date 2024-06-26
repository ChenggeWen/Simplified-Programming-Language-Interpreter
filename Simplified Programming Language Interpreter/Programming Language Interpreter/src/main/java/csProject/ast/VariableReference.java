package csProject.ast;

import java.util.Objects;

/**
 * Represents an expression that refers to a variable.
 */
public class VariableReference extends Expression {
  private final String variableName;
  /**
   * Construct a VariableReference expression
   * @param variableName name of the variable to reference
   */
  public VariableReference(String variableName) {
    this.variableName = variableName;
  }

  public String getVariableName() {
    return variableName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VariableReference that = (VariableReference) o;
    return Objects.equals(variableName, that.variableName);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public String format() {
    return variableName;
  }
}
