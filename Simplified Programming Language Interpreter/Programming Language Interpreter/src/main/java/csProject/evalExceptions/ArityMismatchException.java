package csProject.evalExceptions;

import java.util.Objects;

/**
 * Exception thrown to indicate an arity mismatch: a function or primitive
 * is applied to the wrong number of arguments.
 */
public class ArityMismatchException extends EvaluationException {

  /**
   * string represent function name
   */
  private final String functionName;
  /**
   * an integer represent the expected number of argument in the function
   */
  private final int expectedArity;
  /**
   * an integer represent the actual number of argument in the function
   */
  private final int actualArity;

  /**
   * Constructs a new {@code ArityMismatchException} with the specified details.
   *
   * @param functionName the name of the function with the arity mismatch
   * @param expectedArity the expected number of arguments for the function
   * @param actualArity the actual number of arguments provided to the function
   */
  public ArityMismatchException(
    String functionName,
    int expectedArity,
    int actualArity
  ) {
    super(
      String.format(
        "arity error: %s expects %d argument%s, got %d",
        functionName,
        expectedArity,
        expectedArity == 1 ? "" : "s",
        actualArity
      )
    );
    this.functionName = Objects.requireNonNull(functionName);
    this.expectedArity = expectedArity;
    this.actualArity = actualArity;
  }
  /**
   * Returns the name of the function involved in the arity mismatch.
   *
   * @return the function name
   */
  public String getFunctionName() {
    return functionName;
  }
  /**
   * Returns the expected number of arguments for the function.
   *
   * @return the expected arity
   */
  public int getExpectedArity() {
    return expectedArity;
  }
  /**
   * Returns the actual number of arguments that were provided to the function.
   *
   * @return the actual arity
   */
  public int getActualArity() {
    return actualArity;
  }
}
