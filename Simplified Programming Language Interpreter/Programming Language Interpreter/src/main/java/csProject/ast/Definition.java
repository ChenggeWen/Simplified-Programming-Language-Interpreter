package csProject.ast;

import java.util.List;
import java.util.Objects;

/**
 * Represents a top-level function definition.
 */
public class Definition {
  private final String name;
  private final List<String> arguments;
  private final Expression body;

  /**
   * Constructs a new function definition.
   *
   * @param name      the name of the function
   * @param arguments the name of the function's arguments, in order from left
   *                  to right. Must not be null; can be empty.
   * @param body      the function's body expression
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public Definition(String name, List<String> arguments, Expression body) {
    if (sanityCheck(name, arguments, body)){
      this.name = name;
      this.arguments = arguments;
      this.body = body;
    } else {
      throw new IllegalArgumentException("please pass in non-null arguments");
    }
  }
  /**
   * Checks if the arguments are valid.
   *
   * @param name      the name of the function
   * @param arguments the list of arguments
   * @param body      the function's body expression
   * @return true if the arguments are valid, false otherwise
   */
  private boolean sanityCheck(String name, List<String> arguments, Expression body){
    return name != null && arguments != null && body != null;
  }
  /**
   * Gets the name of the function.
   *
   * @return the name of the function
   */
  public String getName() {
    return name;
  }
  /**
   * Gets the list of arguments of the function.
   *
   * @return the list of arguments of the function
   */
  public List<String> getArguments() {
    return arguments;
  }
  /**
   * Gets the body expression of the function.
   *
   * @return the body expression of the function
   */
  public Expression getBody() {
    return body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Definition that = (Definition) o;
    return Objects.equals(name, that.name) && Objects.equals(
      arguments, that.arguments) && Objects.equals(body, that.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, arguments, body);
  }

  @Override
  public String toString() {
    return String.format("Definition(%s, %s, %s)", name, arguments, body);
  }
}
