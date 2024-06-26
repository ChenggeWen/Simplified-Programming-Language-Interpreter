package csProject.primitives;

import csProject.core.IntValue;
import csProject.core.Value;
import java.util.List;

public class DivideOperator extends AbstractPrimitive {
  /**
   * Applies the division operator to the given arguments.
   *
   * @param arguments a list of values to be divided, containing two integer values
   * @return the result of dividing the first integer value by the second as an IntValue object
   * @throws cs5004.evalExceptions.ArityMismatchException if the number of arguments is not exactly 2
   * @throws cs5004.core.TypeError if any of the arguments are not of type IntValue
   * @throws ArithmeticException if the second argument (divisor) is zero
   */
  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("/", 2, arguments.size());
    int rightOperand = arguments.get(1).asInteger();
    if (rightOperand == 0) {
      throw new ArithmeticException("Division by zero");
    }
    return new IntValue(arguments.get(0).asInteger() / rightOperand);
  }
}

