package csProject.parser.reader;

import csProject.parser.Posn;
import csProject.parser.SexpParserException;
import csProject.parser.scanner.Scanner;
import csProject.parser.scanner.TokenType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import cs5004.parser.sexp.*;

/**
 * Reads s-expressions from a given input source using a {@link Scanner}. This class handles
 * the parsing of tokens generated by the scanner into structured s-expression objects such as
 * lists, symbols, integers, and boolean literals.
 * The reader handles various types of brackets and parenthesis to support different list types
 * and ensures that the input conforms to expected formats, throwing exceptions for any syntactic
 * errors encountered during parsing.
 */
public class SexpReader {
  private final Scanner scanner;
  /**
   * Constructs a new {@code SexpReader} with an existing {@link Scanner}.
   *
   * @param scanner the {@link Scanner} to be used for reading input tokens
   * @throws NullPointerException if the scanner is {@code null}
   */
  public SexpReader(Scanner scanner) {
    this.scanner = Objects.requireNonNull(scanner);
  }
  /**
   * Constructs a new {@code SexpReader} with a specified input source.
   *
   * @param inputName a name for the input source, used for error reporting
   * @param input the {@link java.io.Reader} from which to read the input
   */
  public SexpReader(String inputName, java.io.Reader input) {
    this.scanner = new Scanner(inputName, input);
  }

  public static final String TEST_INPUT_NAME = Scanner.TEST_INPUT_NAME;
  /**
   * Creates a {@code SexpReader} for testing purposes from a string input.
   *
   * @param input the string containing the s-expressions to be parsed
   * @return a new {@code SexpReader} initialized with a {@link Scanner} for the specified string
   */
  public static SexpReader makeTestReader(String input) {
    return new SexpReader(Scanner.makeTestScanner(input));
  }
  /**
   * Reads and returns the next s-expression from the input. This method handles various s-expression
   * formats, directing the parsing based on the starting token and constructing the appropriate
   * s-expression object for each token type.
   *
   * @return the next s-expression parsed from the input
   * @throws SexpParserException if there is an error in parsing the input
   */
  public Sexp read() throws SexpParserException {
    while (true) {
      Scanner.Token token = scanner.getToken();
      switch (token.type()) {
        case L_PAREN: return readList(token.posn(), TokenType.R_PAREN);
        case R_PAREN: throw new UnexpectedTokenException(token);
        case L_BRACE: return readList(token.posn(), TokenType.R_BRACE);
        case R_BRACE: throw new UnexpectedTokenException(token);
        case L_BRACKET: return readList(token.posn(), TokenType.R_BRACKET);
        case R_BRACKET: throw new UnexpectedTokenException(token);
        case SYMBOL:
          return readSymbol(token.posn(), (String) token.value());
        case INT_LIT:
          return new IntLit(token.posn(), (Integer) token.value());
        case EOF:
          return new Eof(token.posn());
      }
    }
  }
  /**
   * Parses a symbol token to construct a {@link BoolLit}, {@link Symbol}, or other
   * relevant s-expression type based on the content of the token.
   *
   * @param posn the position of the symbol in the input
   * @param sourceText the text of the symbol
   * @return a {@link Sexp} representing the symbol
   */
  private Sexp readSymbol(Posn posn, String sourceText) {
    if (sourceText.equals("true")) {
      return new BoolLit(posn, true);
    } else if (sourceText.equals("false")) {
      return new BoolLit(posn, false);
    } else {
      return new Symbol(posn, sourceText);
    }
  }

  /**
   * Reads a list of s-expressions enclosed within specific bracket types, handling nested lists and
   * ensuring correct closure of the list with the expected token type.
   *
   * @param openPosn the position of the opening token of the list
   * @param closeToken the expected closing token type for the list
   * @return a {@link SexpList} containing the elements of the list
   * @throws SexpParserException if the list is malformed or incorrectly terminated
   */
  private Sexp readList(Posn openPosn, TokenType closeToken)
    throws SexpParserException {

    List<Sexp> elements = new ArrayList<>();

    while (true) {
      Scanner.Token token = scanner.peekToken();
      if (token.type() == closeToken) {
        scanner.getToken();  // consume the close character
        return new SexpList(openPosn, elements);
      }
      if (
        token.type() == TokenType.R_PAREN ||
        token.type() == TokenType.R_BRACE ||
        token.type() == TokenType.R_BRACKET
      ) {
        // separate exception for mismatched list?
        throw new UnexpectedTokenException(token);
      }
      if (token.type() == TokenType.EOF) {
        throw new UnterminatedListException(openPosn);
      }

      elements.add(read());
    }
  }
}
