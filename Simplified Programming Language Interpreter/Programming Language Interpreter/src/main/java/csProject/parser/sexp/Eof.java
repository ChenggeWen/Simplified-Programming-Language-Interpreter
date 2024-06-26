package csProject.parser.sexp;

import csProject.parser.Posn;
/**
 * Represents an "end of file" (EOF) marker as a s-expression. This class extends the {@code Sexp}
 * class and is typically used to signify the end of input in parsers or other input processing streams
 * where s-expressions are used to represent data or code.
 */
public class Eof extends Sexp {
  /**
   * Constructs a new {@code Eof} with the specified position.
   * The position typically represents where the EOF was encountered in the input stream.
   *
   * @param p the position of the EOF marker, must not be {@code null}
   * @throws NullPointerException if the position is {@code null}
   */
  public Eof(Posn p) {
    super(p);
  }
  /**
   * Returns a string representation of the EOF marker in the form of an s-expression.
   * The string "eof" is typically used in symbolic expressions to indicate the end of file.
   *
   * @return the string "eof", representing the EOF marker
   */
  @Override
  public String unparse() { return "#<eof>"; }
  /**
   * Provides a string representation of this EOF marker, including its position.
   * The output format is "Eof(posn)", where posn is the output from the {@code fieldsToString} method of the superclass.
   *
   * @return a string representation of this {@code Eof} instance
   */
  @Override
  public String toString() {
    return String.format("Eof(%s)", super.fieldsToString());
  }
}
