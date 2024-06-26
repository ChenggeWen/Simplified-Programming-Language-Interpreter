package csProject.ast;

import csProject.core.Value;

/**
 * Visitor for computing with expressions.
 * @param <T> type of value produced by the visitor's operation.
 */
public interface ExpressionVisitor<T> {
  /**
   * Visits an {@code AndExpression} node in an expression tree.
   *
   * @param andExpression the {@code AndExpression} to visit
   * @return the result of the operation performed on the {@code AndExpression}
   */
  T visit(AndExpression andExpression);
  /**
   * Visits a {@code BooleanLiteral} node in an expression tree.
   *
   * @param booleanLiteral the {@code BooleanLiteral} to visit
   * @return the result of the operation performed on the {@code BooleanLiteral}
   */
  T visit(BooleanLiteral booleanLiteral);
  /**
   * Visits a {@code FunctionCall} node in an expression tree.
   *
   * @param functionCall the {@code FunctionCall} to visit
   * @return the result of the operation performed on the {@code FunctionCall}
   */
  T visit(FunctionCall functionCall);
  /**
   * Visits an {@code IfExpression} node in an expression tree.
   *
   * @param ifExpression the {@code IfExpression} to visit
   * @return the result of the operation performed on the {@code IfExpression}
   */
  T visit(IfExpression ifExpression);

  /**
   * Visits an {@code IntLiteral} node in an expression tree.
   *
   * @param intLiteral the {@code IntLiteral} to visit
   * @return the result of the operation performed on the {@code IntLiteral}
   */
  T visit(IntLiteral intLiteral);

  /**
   * Visits a {@code LetExpression} node in an expression tree.
   *
   * @param letExpression the {@code LetExpression} to visit
   * @return the result of the operation performed on the {@code LetExpression}
   */
  T visit(LetExpression letExpression);
  /**
   * Visits an {@code OrExpression} node in an expression tree.
   *
   * @param orExpression the {@code OrExpression} to visit
   * @return the result of the operation performed on the {@code OrExpression}
   */
  T visit(OrExpression orExpression);
  /**
   * Visits a {@code VariableReference} node in an expression tree.
   *
   * @param reference the {@code VariableReference} to visit
   * @return the result of the operation performed on the {@code VariableReference}
   */
  T visit(VariableReference reference);
}
