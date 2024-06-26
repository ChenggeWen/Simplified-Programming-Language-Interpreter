package csProject.parser.reader;

import csProject.parser.Posn;
import csProject.parser.SexpParserException;
import java.io.Serial;

/**
 * Represents a base class for exceptions that occur during the reading phase
 * of parsing s-expressions. This class extends {@code SexpParserException} to
 * provide exception handling specifically related to errors encountered while
 * reading input data before it is parsed into s-expressions.
 * This class is abstract and intended to be subclassed by exceptions that need
 * to specify more detailed error contexts or additional properties related to
 * specific read errors.
 */
public abstract class ReaderException extends SexpParserException {
  @Serial
  private static final long serialVersionUID = 1L;
  /**
   * Constructs a new {@code ReaderException} with a specific message and position.
   * The message should provide details about the error context and the position
   * indicates where in the input data the error occurred.
   *
   * @param msg the detailed message about the exception
   * @param posn the position in the input data where the exception occurred
   */
  public ReaderException(String msg, Posn posn) {
    super(msg, posn);
  }
}
