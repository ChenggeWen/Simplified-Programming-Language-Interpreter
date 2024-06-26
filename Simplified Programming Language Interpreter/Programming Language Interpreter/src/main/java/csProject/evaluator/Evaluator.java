package csProject.evaluator;

import csProject.ast.AndExpression;
import csProject.ast.BooleanLiteral;
import csProject.ast.Definition;
import csProject.ast.Expression;
import csProject.ast.ExpressionVisitor;
import csProject.ast.FunctionCall;
import csProject.ast.IfExpression;
import csProject.ast.IntLiteral;
import csProject.ast.LetExpression;
import csProject.ast.OrExpression;
import csProject.ast.VariableReference;
import csProject.core.BooleanValue;
import csProject.core.Environment;
import csProject.core.IntValue;
import csProject4.core.Value;
import csProject.evalExceptions.ArityMismatchException;
import csProject.evalExceptions.UndefinedFunctionException;
import csProject.evalExceptions.UndefinedVariableException;
import csProject.primitives.Primitive;
import csProject.primitives.PrimitiveTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 * An evaluator for expressions in a custom programming language.
 * This class implements the `ExpressionVisitor` interface to evaluate
 * various expression types and return a `Value`.
 */
public class Evaluator implements ExpressionVisitor<Value> {
  private final Environment<Definition> definitions;
  private final Environment<Value> variables;
  private final PrimitiveTable primitiveTable;

  /**
   * Constructs an `Evaluator` with the given environments for definitions and variables,
   * and a `PrimitiveTable` for primitive operations.
   *
   * @param definitions The environment containing function definitions.
   * @param variables The environment containing variable bindings.
   * @param primitiveTable The table of primitive operations and functions.
   */
  public Evaluator(Environment<Definition> definitions, Environment<Value> variables, PrimitiveTable primitiveTable) {
    this.definitions = Objects.requireNonNull(definitions);
    this.variables = Objects.requireNonNull(variables);
    this.primitiveTable = Objects.requireNonNull(primitiveTable);
  }
  /**
   * Constructs an `Evaluator` with given environments for definitions and variables,
   * using a default `PrimitiveTable`.
   *
   * @param definitions The environment containing function definitions.
   * @param variables The environment containing variable bindings.
   */
  public Evaluator(Environment<Definition> definitions, Environment<Value> variables) {
    this(definitions, variables, new PrimitiveTable());
  }

  /**
   * Returns a new `Evaluator` with an additional variable binding.
   *
   * @param variableName The name of the variable to bind.
   * @param variableValue The value to bind to the variable.
   * @return A new `Evaluator` with the specified variable binding.
   */
  public Evaluator withVariableBinding(String variableName, Value variableValue) {
    return new Evaluator(definitions, variables.extend(variableName, variableValue), primitiveTable);
  }

  /**
   * Visits and evaluates an `AndExpression`.
   *
   * @param andExpression The `AndExpression` to evaluate.
   * @return A `BooleanValue` indicating the result of the "and" operation.
   */
  @Override
  public Value visit(AndExpression andExpression) {
    Value leftValue = andExpression.getLeftOperand().accept(this);
    if (!leftValue.asBoolean()) {
      return new BooleanValue(false);
    }
    Value rightValue = andExpression.getRightOperand().accept(this);
    return new BooleanValue(leftValue.asBoolean() && rightValue.asBoolean());
  }
  /**
   * Visits and evaluates a `BooleanLiteral`.
   *
   * @param booleanLiteral The `BooleanLiteral` to evaluate.
   * @return A `BooleanValue` based on the literal's boolean value.
   */
  @Override
  public Value visit(BooleanLiteral booleanLiteral) {
    return new BooleanValue(booleanLiteral.isValue());
  }
  /**
   * Visits and evaluates a `FunctionCall`.
   *
   * @param functionCall The `FunctionCall` to evaluate.
   * @return The result of the function call.
   * @throws UndefinedFunctionException If the function is not found in definitions or primitives.
   * @throws ArityMismatchException If the number of arguments does not match the expected arity.
   */
  @Override
  public Value visit(FunctionCall functionCall) {

    List<Value> argValues = new ArrayList<>();
    for (final Expression eva: functionCall.getArguments()) {
      argValues.add(eva.accept(this));
    }

    Primitive primitive = primitiveTable.lookup(functionCall.getFunctionName());
    if (primitive != null) {
      return primitive.apply(argValues);
    }

    Definition def = definitions.lookup(functionCall.getFunctionName());
    if (def != null) {
      if (def.getArguments().size() != argValues.size()) {
        throw new ArityMismatchException(functionCall.getFunctionName(), def.getArguments().size(), argValues.size());
      }

      Environment<Value> newEnv = variables;
      for (int i = 0; i < def.getArguments().size(); i++) {
        newEnv = newEnv.extend(def.getArguments().get(i), argValues.get(i));
      }
      Evaluator newEvaluator = new Evaluator(definitions, newEnv, primitiveTable);
      return def.getBody().accept(newEvaluator);
    }

    throw new UndefinedFunctionException(functionCall.getFunctionName());
  }
  /**
   * Visits and evaluates an `IfExpression`.
   *
   * @param ifExpression The `IfExpression` to evaluate.
   * @return The result of the evaluated branch, either the consequent or the alternative.
   */
  @Override
  public Value visit(IfExpression ifExpression) {
    Value conditionValue = ifExpression.getCondition().accept(this);
    if (!conditionValue.asBoolean()) {
      return ifExpression.getAlternative().accept(this);
    }
    return ifExpression.getConsequent().accept(this);
  }
  /**
   * Visits and evaluates an `IntLiteral`.
   *
   * @param intLiteral The `IntLiteral` to evaluate.
   * @return An `IntValue` representing the literal's integer value.
   */
  @Override
  public Value visit(IntLiteral intLiteral) {
    return new IntValue(intLiteral.getValue());
  }


  /**
   * Visits and evaluates a `LetExpression`.
   *
   * @param letExpression The `LetExpression` to evaluate.
   * @return The result of the expression body with the new variable binding.
   */
  @Override
  public Value visit(LetExpression letExpression) {
    Value rhsValue = letExpression.getRhs().accept(this);
    Environment<Value> newEnv = variables.extend(letExpression.getVarName(), rhsValue);
    Evaluator newEvaluator = new Evaluator(definitions, newEnv, primitiveTable);
    return letExpression.getBody().accept(newEvaluator);
  }

  /**
   * Visits and evaluates an `OrExpression`.
   *
   * @param orExpression The `OrExpression` to evaluate.
   * @return A `BooleanValue` representing the result of the "or" operation.
   */
  @Override
  public Value visit(OrExpression orExpression) {
    Value leftValue = orExpression.getLeft().accept(this);
    if (leftValue.asBoolean()) {
      return new BooleanValue(true);
    }
    Value rightValue = orExpression.getRight().accept(this);
    return new BooleanValue(rightValue.asBoolean());
  }
  /**
   * Visits and evaluates a `VariableReference`.
   *
   * @param reference The `VariableReference` to evaluate.
   * @return The value of the referenced variable.
   * @throws UndefinedVariableException If the variable is not defined in the current environment.
   */
  @Override
  public Value visit(VariableReference reference) {
    Value value = variables.lookup(reference.getVariableName());
    if (value == null) {
      throw new UndefinedVariableException(reference.getVariableName());
    }
    return value;
  }
}
