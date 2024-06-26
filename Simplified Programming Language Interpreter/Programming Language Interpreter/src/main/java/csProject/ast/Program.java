package csProject.ast;

import csProject.core.Environment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an entire program.  A program consists of a series (possibly
 * empty) of definitions followed by an expression.  The value of the program is
 * the value of the expression.
 */
public class Program {
  private final List<Definition> definitions;
  private final Expression expression;

  /**
   * Constructs a new program
   * @param definitions a list of function definitions.  Must not be null;
   *                    may be empty.  Elements of the list must not be null.
   * @param expression the expression to be evaluated at runtime.
   */
  public Program(List<Definition> definitions, Expression expression) {
    Objects.requireNonNull(definitions);
    Objects.requireNonNull(expression);

    for (Definition d : definitions) {
      Objects.requireNonNull(d);
    }

    this.definitions = List.copyOf(definitions);
    this.expression = expression;
  }

  public List<Definition> getDefinitions() {
    return definitions;
  }

  public Expression getExpression() {
    return expression;
  }
}
