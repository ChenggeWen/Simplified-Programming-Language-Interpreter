package csProject.ast;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a function-call expression, such as <code>f(3, y, g(x))</code>
 */
public class FunctionCall extends Expression {
  /**
   * The name of the function being called.
   */
  public final String functionName;
  /**
   * The list of argument expressions passed to the function. The list is ordered
   * from left to right and represents the parameters that the function receives.
   */
  public final List<Expression> arguments;

  /**
   * Constructs a function-call expression
   * @param functionName the name of the function
   * @param arguments the argument expressions, in order from left to right.
   *                  Must not be null; can be empty.
   */
  public FunctionCall(String functionName, List<Expression> arguments) {
    this.functionName = functionName;
    this.arguments = arguments;
  }
  /**
   * Returns the name of the function being called.
   *
   * @return the function name
   */
  public String getFunctionName() {
    return functionName;
  }
  /**
   * Returns an unmodifiable list of argument expressions passed to the function.
   *
   * @return the arguments as a list of {@code Expression}
   */
  public List<Expression> getArguments() {
    return arguments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FunctionCall that = (FunctionCall) o;
    return Objects.equals(functionName, that.functionName)
      && Objects.equals(arguments, that.arguments);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public String format() {
    String args = arguments.stream().map(Expression::format).collect(Collectors.joining(", "));
    return functionName + "(" + args + ")";
  }
}
