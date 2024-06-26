package csProject.evaluator;

import csProject.ast.*;
import csProject.core.BooleanValue;
import csProject.core.Environment;
import csProject.core.IntValue;
import csProject.core.TypeError;
import csProject.core.Value;
import csProject.evalExceptions.ArityMismatchException;
import csProject.evalExceptions.UndefinedFunctionException;
import csProject.evalExceptions.UndefinedVariableException;
import csProject.parser.Parser;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EvaluatorTests {

  @Test
  public void testLetExpressionFormatting() {
    LetExpression letExpr = new LetExpression(
        "x",
        new IntLiteral(7),
        new VariableReference("x")
    );
    String expected = "let x = 7 in x";
    Assertions.assertEquals(expected, letExpr.format());
  }

  @Test
  public void testOrExpressionFormatting() {
    OrExpression orExpr = new OrExpression(
        new BooleanLiteral(true),
        new BooleanLiteral(false)
    );
    String expected = "(true || false)";
    Assertions.assertEquals(expected, orExpr.format());
  }

  @Test
  public void testIfExpressionFormatting() {
    IfExpression ifExpr = new IfExpression(
        new BooleanLiteral(true),
        new IntLiteral(1),
        new IntLiteral(2)
    );
    String expected = "(true ? 1 : 2)";
    Assertions.assertEquals(expected, ifExpr.format());
  }

  @Test
  public void testVariableReference() {
    Assertions.assertEquals(
        new IntValue(4),
        evaluateExpr("x", new Environment<Value>().extend("x", new IntValue(4)))
    );

    try {
      evaluateExpr("x", new Environment<Value>());
      Assertions.fail("expected exception");
    } catch (UndefinedVariableException e) {
      Assertions.assertEquals("x", e.getVariableName());
    }
  }

  @Test
  public void testFunctionCall() {
    Assertions.assertEquals(
        new IntValue(3),
        evaluateExpr("(+ 1 2)", new Environment<Value>())
    );

    Assertions.assertEquals(
        new IntValue(3),
        evaluateExpr(
            "(f)",
            new Environment<Definition>().extend(
                "f",
                new Definition("f", List.of(), new IntLiteral(3))
            ),
            new Environment<Value>()
        )
    );

    Assertions.assertEquals(
        new IntValue(12),
        evaluateExpr(
            "(f 3 4)",
            new Environment<Definition>().extend(
                "f",
                new Definition(
                    "f",
                    List.of("x", "y"),
                    Parser.parseExpression("test", "(* x y)")
                )
            ),
            new Environment<Value>()
        )
    );
  }

  @Test
  public void testIfExpression() {
    Assertions.assertEquals(
        new IntValue(1),
        evaluateExpr(
            "(if (> x 0) 1 2)",
            new Environment<Value>().extend("x", new IntValue(3))
        )
    );

    Assertions.assertEquals(
        new IntValue(2),
        evaluateExpr(
            "(if (> x 0) 1 2)",
            new Environment<Value>().extend("x", new IntValue(-2))
        )
    );

    try {
      evaluateExpr(
          "(if x 1 2)",
          new Environment<Value>().extend("x", new IntValue(45))
      );
      Assertions.fail("expected exception");
    } catch (TypeError e) {
      // NOP
    }
  }

  @Test
  public void testRecursiveFunctionCall() {
    Assertions.assertEquals(
        new IntValue(720),
        evaluateExpr(
            "(fact 6)",
            new Environment<Definition>(
                List.of("fact"),
                List.of(
                    new Definition(
                        "fact",
                        List.of("x"),
                        Parser.parseExpression(
                            "test",
                            "(if (== x 0) 1 (* x (fact (- x 1))))"
                        )
                    )
                )
            ),
            new Environment<Value>()
        )
    );
  }

  @Test
  public void testFunctionCallErrors() {
    try {
      evaluateExpr(
          "(f 1)",
          new Environment<Definition>().extend(
              "f",
              new Definition(
                  "f",
                  List.of("x", "y"),
                  new IntLiteral(3)
              )
          ),
          new Environment<Value>()
      );
      Assertions.fail("expected arity error");
    } catch (ArityMismatchException e) {
      // NOP
    }

    try {
      evaluateExpr(
          "(f 3)",
          new Environment<Definition>(),
          new Environment<Value>()
      );
      Assertions.fail("expected undefined-function error");
    } catch (UndefinedFunctionException e) {
      // NOP
    }
  }

  @Test
  public void testAndExpression() {
    Assertions.assertEquals(
        new BooleanValue(true),
        evaluateExpr("(and true true)", new Environment<Value>())
    );

    Assertions.assertEquals(
        new BooleanValue(false),
        evaluateExpr("(and true false)", new Environment<Value>())
    );

    // should evaluate cleanly -- short-circuiting means that we never
    // evaluate the second argument
    Assertions.assertEquals(
        new BooleanValue(false),
        evaluateExpr("(and false (/ 3 0))", new Environment<Value>())
    );

    // Here, though, we do have to evaluate the second argument, so we
    // expect to fail with an exception.
    try {
      evaluateExpr("(and true (/ 3 0))", new Environment<Value>());
      Assertions.fail("expected exception");
    } catch (ArithmeticException e) {
      // NOP
    }
  }

  @Test
  public void testOrExpression() {
    Assertions.assertEquals(
        new BooleanValue(true),
        evaluateExpr("(or false true)", new Environment<Value>())
    );

    Assertions.assertEquals(
        new BooleanValue(false),
        evaluateExpr("(or false false)", new Environment<Value>())
    );

    // should evaluate cleanly -- short-circuiting means that we never
    // evaluate the second argument
    Assertions.assertEquals(
        new BooleanValue(true),
        evaluateExpr("(or true (/ 3 0))", new Environment<Value>())
    );

    // Here, though, we do have to evaluate the second argument, so we
    // expect to fail with an exception.
    try {
      evaluateExpr("(or false (/ 3 0))", new Environment<Value>());
      Assertions.fail("expected exception");
    } catch (ArithmeticException e) {
      // NOP
    }
  }

  @Test
  public void testLetExpression() {
    Assertions.assertEquals(
        new IntValue(14),
        evaluateExpr(
            "(let x 7 (* x 2))",
            new Environment<Value>()
        )
    );

    // and test variable shadowing
    Assertions.assertEquals(
        new IntValue(12),
        evaluateExpr(
            "(let x 3 (* x y))",
            new Environment<Value>(
                List.of("x", "y"),
                List.of(new IntValue(1), new IntValue(4))
            )
        )
    );
  }

  private Value evaluateExpr(
      String exprSource,
      Environment<Value> variableBindings
  ) {
    return evaluateExpr(
        exprSource,
        new Environment<Definition>(),
        variableBindings
    );
  }

  private Value evaluateExpr(
      String exprSource,
      Environment<Definition> definitions,
      Environment<Value> variableBindings
  ) {
    return Parser.parseExpression("test data", exprSource).accept(
        new Evaluator(definitions, variableBindings)
    );
  }
}
