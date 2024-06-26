package csProject.ast;

/**
 * Represents a literal integer, such as the constant <code>17</code>.
 */
public class IntLiteral extends Expression {
  private final int value;
  /**
   * Constructs a new integer literal.
   * @param value the value of the literal
   */
  public IntLiteral(int value) {
    this.value = value;
  }

  /**
   * return the object value
   * @return the value
   */
  public int getValue() {
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
    IntLiteral that = (IntLiteral) o;
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
