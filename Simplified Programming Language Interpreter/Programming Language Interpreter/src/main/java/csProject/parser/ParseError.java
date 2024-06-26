package csProject.parser;

public class ParseError extends RuntimeException {
  private final Posn posn;

  public ParseError(Posn p, String msg) {
    super(p.toString() + ": " + msg);
    posn = p;
  }

  public Posn getPosn() {
    return posn;
  }
}
