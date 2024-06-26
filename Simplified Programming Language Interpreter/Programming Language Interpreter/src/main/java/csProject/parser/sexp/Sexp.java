package csProject.parser.sexp;

import csProject.parser.Posn;
import java.util.Objects;

/**
 * Root of the s-expression class hierarchy.
 */
public abstract class Sexp {
  private final Posn posn;

  /**
   * Constructs a new {@code Sexp} with the specified position.
   *
   * @param p the position of the s-expression, must not be {@code null}
   * @throws NullPointerException if the provided position is {@code null}
   */
  public Sexp(Posn p) {
    posn = Objects.requireNonNull(p);
  }
  /**
   * Returns the position of this s-expression.
   *
   * @return the position of this s-expression
   */
  public Posn getPosn() { return posn; }
  /**
   * Provides a string representation of the fields in this s-expression,
   * primarily used for building string representations in derived classes.
   *
   * @return a string representation of the essential fields (e.g., position)
   */
  protected String fieldsToString() {
    return posn.toString();
  }

  /**
   * Abstract method to be implemented by subclasses to return a string
   * containing equivalent concrete syntax to this s-expression, suitable
   * for unparsing or for generating output that can be re-parsed to an equivalent
   * s-expression.
   *
   * @return the unparsed string of the s-expression
   */
  public abstract String unparse();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sexp sexp = (Sexp) o;
    return Objects.equals(posn, sexp.posn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(posn);
  }
}
