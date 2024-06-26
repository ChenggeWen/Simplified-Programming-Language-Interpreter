package csProject.primitives;

import csProject.core.BooleanValue;
import csProject.core.Value;
import java.util.List;

/**
 * Implements the NOT operator.
 */
public class NotOperator extends AbstractPrimitive {
  /**
   * Applies the logical NOT operator to the given argument.
   *
   * @param arguments a list containing a single BooleanValue argument
   * @return a BooleanValue object representing the result of the logical NOT operation
   * @throws cs5004.evalExceptions.ArityMismatchException if the number of arguments is not exactly 1
   * @throws cs5004.core.TypeError if the argument is not of type BooleanValue
   */

  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("not", 1, arguments.size());
    return new BooleanValue(!(arguments.get(0).asBoolean()));
  }
}
