package csProject.parser.sexp;

import csProject.parser.Posn;
import java.util.Objects;
/**
 * Represents an integer literal as a symbolic expression (s-expression). This class extends
 * {@code Sexp} to include functionality specific to handling integer values. It is typically
 * used to represent numeric literals within a programming language or a data format that
 * utilizes s-expressions.
 */
public final class IntLit extends Sexp {
  private final int value;
  /**
   * Constructs a new {@code IntLit} with the specified position and integer value.
   *
   * @param posn the position of the integer literal within its context, usually related
   *             to its location in source code or data input
   * @param value the integer value of the literal
   */
  public IntLit(Posn posn, int value) {
    super(posn);
    this.value = value;
  }
  /**
   * Returns the integer value of this literal.
   *
   * @return the integer value
   */
  public int getValue() { return value; }

  @Override
  public String toString() {
    return String.format("IntLit(%s, %s)", super.fieldsToString(), value);
  }

  @Override
  public String unparse() { return String.format("%d", value); }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    IntLit intLit = (IntLit) o;
    return value == intLit.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }
}
