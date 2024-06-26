package csProject.parser.scanner;

import java.io.Serial;
import java.util.Objects;
import csProject.parser.Posn;

/**
 * Exception thrown upon encountering an invalid combination of characters,
 * like '#' right before the end of the file.
 */
public class InvalidTokenException extends ScannerException {
  @Serial
  private static final long serialVersionUID = 1L;

  /// Token string read from input
  private final String token;
  /**
   * Constructs a new {@code InvalidTokenException} with the specified position and token.
   * The exception message is formatted to include the invalid token, providing clarity
   * on the nature of the error.
   *
   * @param posn the position at which the invalid token was encountered in the input
   * @param token the invalid token that triggered the exception
   */
  public InvalidTokenException(Posn posn, String token) {
    super(String.format("Invalid token \"%s\"", token), posn);
    this.token = token;
  }
  /**
   * Returns the invalid token associated with this exception. This can be used to retrieve
   * further details about the context of the error, such as displaying the token in error
   * messages or logging.
   *
   * @return the invalid token
   */
  public String getToken() { return token; }

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
    InvalidTokenException that = (InvalidTokenException) o;
    return Objects.equals(token, that.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), token);
  }
}
