package csProject.parser.sexp;

import csProject.parser.Posn;
import java.util.Objects;
/**
 * Represents a boolean literal in the form of an s-expression (symbolic expression).
 * This class extends {@code Sexp} and encapsulates a boolean value, providing methods
 * to interact with this value and represent it as a string in a symbolic format.
 */
public final class BoolLit extends Sexp {
  private final boolean value;
  /**
   * Constructs a new {@code BoolLit} with the specified position and boolean value.
   *
   * @param posn  the position of the boolean literal in the source code or context
   *              where it is used, provided by the {@code Posn} object
   * @param value the boolean value of the literal
   */
  public BoolLit(Posn posn, boolean value) {
    super(posn);
    this.value = value;
  }
  /**
   * Returns the boolean value of this {@code BoolLit}.
   *
   * @return the boolean value
   */
  public boolean getValue() { return value; }
  /**
   * Returns the boolean value in symbolic expression format.
   * "#t" represents true, and "#f" represents false.
   *
   * @return the unparsed string of the boolean value suitable for s-expression
   */
  @Override
  public String unparse() {
    return value ? "#t" : "#f";
  }
  /**
   * Returns a string representation of this {@code BoolLit} which includes its
   * position and value. The output is in the format "BoolLit(posn, value)".
   *
   * @return a string representation of this object
   */
  @Override
  public String toString() {
    return String.format("BoolLit(%s, %s)", super.fieldsToString(), value);
  }

  /**
   * Compares this object to another object to determine if they are equal.
   * Two {@code BoolLit} objects are considered equal if they are in the same position
   * and contain the same boolean value.
   *
   * @param o the object to compare with this {@code BoolLit}
   * @return {@code true} if the specified object is equal to this {@code BoolLit}; {@code false} otherwise
   */
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
    BoolLit boolLit = (BoolLit) o;
    return value == boolLit.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }
}
