package csProject.primitives;

import csProject.core.IntValue;
import csProject.core.Value;
import java.util.List;

public class ModuloOperator extends AbstractPrimitive {
  /**
   * Applies the modulo operator to the given arguments.
   *
   * @param arguments a list of values to be used for modulo operation, containing two integer values
   * @return an IntValue object representing the result of the modulo operation
   * @throws cs5004.evalExceptions.ArityMismatchException if the number of arguments is not exactly 2
   * @throws cs5004.core.TypeError if any of the arguments are not of type IntValue
   * @throws ArithmeticException if the second argument (divisor) is zero
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("mod", 2, arguments.size());
    return new IntValue(arguments.get(0).asInteger() % arguments.get(1).asInteger());
  }
}
