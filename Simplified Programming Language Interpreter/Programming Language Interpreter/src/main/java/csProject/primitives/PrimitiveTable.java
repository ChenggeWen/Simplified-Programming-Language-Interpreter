package csProject.primitives;

import java.util.HashMap;
import java.util.Map;

/**
 * Lookup table for primitive operations.
 */
public class  PrimitiveTable {

  private final Map<String, Primitive> primitives;

  /**
   * Private constructor to prevent instantiation.
   */
  public PrimitiveTable() {
    primitives = new HashMap<>();
    initializePrimitives();
  }

  /**
   * Looks up a primitive function or operator by name.
   * @param name name of the function to look up
   * @return Primitive object defining function, or null if not defined.
   */
  public Primitive lookup(String name) {
    return primitives.get(name);
  }

  private void initializePrimitives() {
    primitives.put("not", new NotOperator());

    primitives.put("==", new EqualsOperator());
    primitives.put("!=", new NotEqualsOperator());
    primitives.put("<", new LessThanOperator());
    primitives.put("<=", new LessThanOrEqualOperator());
    primitives.put(">", new GreaterThanOperator());
    primitives.put(">=", new GreaterThanOrEqualOperator());

    primitives.put("+", new AddOperator());
    primitives.put("-", new SubtractOperator());
    primitives.put("*", new MultiplyOperator());
    primitives.put("/", new DivideOperator());
    primitives.put("mod", new ModuloOperator());
  }
}
