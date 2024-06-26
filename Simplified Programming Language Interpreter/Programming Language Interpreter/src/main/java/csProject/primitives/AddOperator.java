package csProject.primitives;

import csProject.core.IntValue;
import csProject.core.Value;
import csProject.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * Represents an addition operator that adds two integer values.
 * This class extends AbstractPrimitive and provides the implementation
 * for the "+" operator.
 */
public class AddOperator extends AbstractPrimitive{
  /**
   * Applies the addition operator to a list of arguments.
   * Ensures the arguments list contains exactly two elements,
   * then adds them as integers.
   *
   * @param arguments The list of values to be added.
   * @return An IntValue representing the sum of the first two arguments.
   * @throws ArityMismatchException If the number of arguments is not 2.
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("+", 2, arguments.size());
    int left = arguments.get(0).asInteger();
    int right = arguments.get(1).asInteger();
    return new IntValue(left + right);
  }
}
