package csProject.parser.scanner;

import csProject.parser.Posn;
import csProject.parser.SexpParserException;
import java.io.Serial;

/**
 * Ancestor of all exceptions that can be thrown by the scanner.
 */
public abstract class ScannerException extends SexpParserException {
  @Serial
  private static final long serialVersionUID = 1L;

  public ScannerException(String msg, Posn posn) {
    super(msg, posn);
  }

  public ScannerException(String msg, Exception cause) {
    super(msg, cause);
  }
}
