package csProject.primitives;

import csProject.core.BooleanValue;
import csProject.core.IntValue;
import csProject.core.TypeError;
import csProject.evalExceptions.ArityMismatchException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RelationalOperatorTests {
  private final PrimitiveTable primitives;

  public RelationalOperatorTests() {
    primitives = new PrimitiveTable();
  }

  @Test
  public void testRelationalOperatorsOnGoodArguments() {
    Primitive eq = primitives.lookup("==");
    Primitive ne = primitives.lookup("!=");
    Primitive lt = primitives.lookup("<");
    Primitive le = primitives.lookup("<=");
    Primitive gt = primitives.lookup(">");
    Primitive ge = primitives.lookup(">=");

    Assertions.assertEquals(
        new BooleanValue(false),
        eq.apply(List.of(new IntValue(3), new IntValue(4)))
    );
    Assertions.assertEquals(
        new BooleanValue(true),
        ne.apply(List.of(new IntValue(3), new IntValue(4)))
    );
    Assertions.assertEquals(
        new BooleanValue(true),
        lt.apply(List.of(new IntValue(3), new IntValue(4)))
    );
    Assertions.assertEquals(
        new BooleanValue(true),
        le.apply(List.of(new IntValue(3), new IntValue(4)))
    );
    Assertions.assertEquals(
        new BooleanValue(false),
        gt.apply(List.of(new IntValue(3), new IntValue(4)))
    );
    Assertions.assertEquals(
        new BooleanValue(false),
        ge.apply(List.of(new IntValue(3), new IntValue(4)))
    );
  }

  @Test
  public void testRelationalOperatorArityMismatch() {
    try {
      primitives.lookup("==").apply(List.of(new IntValue(3)));
      Assertions.fail("expected exception");
    } catch (ArityMismatchException e) {
      // NOP
    }
  }

  @Test
  public void testRelationalOperatorTypeError() {
    try {
      primitives.lookup("==").apply(
          List.of(new IntValue(3), new BooleanValue(false))
      );
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }
  }
}
