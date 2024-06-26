package csProject.primitives;

import csProject.core.BooleanValue;
import csProject.core.Value;
import java.util.List;
public class NotEqualsOperator extends AbstractPrimitive {
  /**
   * Applies the not equals operator to the given arguments.
   *
   * @param arguments a list of values to be compared, containing two integer values
   * @return a BooleanValue object representing the result of the not equals operation
   * @throws cs5004.evalExceptions.ArityMismatchException if the number of arguments is not exactly 2
   * @throws cs5004.core.TypeError if any of the arguments are not of type IntValue
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("!=", 2, arguments.size());
    return new BooleanValue(arguments.get(0).asInteger() != arguments.get(1).asInteger());
  }
}
