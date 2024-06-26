package csProject.primitives;

import csProject.core.IntValue;
import csProject.core.Value;
import java.util.List;

public class SubtractOperator extends AbstractPrimitive {
  /**
   * Applies the subtraction operator to the given arguments.
   *
   * @param arguments a list of values to be subtracted, containing two integer values
   * @return the result of subtracting the second integer value from the first as an IntValue object
   * @throws cs5004.evalExceptions.ArityMismatchException if the number of arguments is not exactly 2
   * @throws cs5004.core.TypeError if any of the arguments are not of type IntValue
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("-", 2, arguments.size());
    return new IntValue(arguments.get(0).asInteger() - arguments.get(1).asInteger());
  }
}