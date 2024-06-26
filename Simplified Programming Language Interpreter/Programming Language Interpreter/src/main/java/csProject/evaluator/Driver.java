package csProject.evaluator;

import csProject.ast.Definition;
import csProject.ast.Program;
import csProject.core.Environment;
import csProject.core.Value;
import csProject.parser.Parser;
import java.util.ArrayList;
import java.util.List;
/**
 * The {@code Driver} class encapsulates methods for evaluating programs from source text
 * and directly from program structures. It orchestrates the parsing, setting up the execution
 * environment, and performing the evaluation of expressions based on the defined syntax and
 * semantics of the language.
 */
public class Driver {
  /**
   * Evaluates a program from its source text representation.
   * This method parses the provided source text into a program structure and then evaluates it.
   *
   * @param sourceText the source text of the program to be evaluated
   * @return the value resulting from evaluating the parsed program
   */
  public Value evaluateFromSource(String sourceText) {
    return evaluateProgram(Parser.parseProgram("input", sourceText));
  }
  /**
   * Evaluates a parsed program.
   * This method sets up the necessary environments for definitions and execution, and then
   * evaluates the program's main expression in this context.
   *
   * @param program the parsed program to be evaluated, consisting of definitions and an expression
   * @return the value resulting from the evaluation of the program's expression
   */
  public Value evaluateProgram(Program program) {
    List<Definition> definitionList = program.getDefinitions();
    List<String> definitionNames = new ArrayList<>(definitionList.size());
    for (Definition d: definitionList) {
      definitionNames.add(d.getName());
    }
    Environment<Definition> definitions =
      new Environment<>(definitionNames, definitionList);
    return program.getExpression().accept(
      new Evaluator(definitions, new Environment<>())
    );
  }
}
