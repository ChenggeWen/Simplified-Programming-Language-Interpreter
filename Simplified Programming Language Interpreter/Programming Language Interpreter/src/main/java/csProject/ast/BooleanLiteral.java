package csProject.ast;

import java.util.Objects;

/**
 * Represents a literal boolean value -- i.e., the constants
 * <code>true</code> and <code>false</code>.
 */
public class BooleanLiteral extends Expression {
  private final boolean value;

  /**
   * Construct a new boolean literal expression.
   * @param value the value of the literal expression
   */
  public BooleanLiteral(boolean value) {
    this.value = value;
  }

  /**
   * check and return the value of the object.
   * @return value
   */
  public boolean isValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BooleanLiteral that = (BooleanLiteral) o;
    return value == that.value;
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }


  @Override
  public String format() {
    return String.valueOf(value);
  }
}
