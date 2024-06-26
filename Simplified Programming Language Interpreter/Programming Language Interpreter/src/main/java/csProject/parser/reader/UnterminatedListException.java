package csProject.parser.reader;

import csProject.parser.Posn;
import java.io.Serial;
import java.util.Objects;

/**
 * Exception that signals a list without closing punctuation.
 */
public class UnterminatedListException extends ReaderException {
  @Serial
  private static final long serialVersionUID = 1L;
  /**
   * Constructs a new {@code UnterminatedListException} indicating that an unterminated list
   * was found at a specific position in the input. The message for the exception is set to
   * "unterminated list" to clearly indicate the nature of the error.
   *
   * @param posn the position at which the unterminated list starts
   * @throws NullPointerException if the position is {@code null}
   */
  public UnterminatedListException(Posn posn) {
    super("unterminated list", Objects.requireNonNull(posn));
  }
}
