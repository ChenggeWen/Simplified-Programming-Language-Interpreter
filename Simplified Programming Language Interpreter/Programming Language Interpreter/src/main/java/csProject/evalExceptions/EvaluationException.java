package csProject.evalExceptions;

/**
 * Base class for all errors that can be thrown during evaluation.
 */
public abstract class EvaluationException extends RuntimeException {
  /**
   * Constructs a new {@code EvaluationException} with the specified detail message.
   * The message can be used to provide more information about the error context and what
   * might have caused the exception.
   *
   * @param message the detail message. The detail message is saved for later retrieval
   *                by the {@link Throwable#getMessage()} method.
   */
  public EvaluationException(String message) {
    super(message);
  }
}
