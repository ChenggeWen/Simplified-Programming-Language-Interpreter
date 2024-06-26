package csProject.core;

import java.util.Objects;

/**
 * Represents an integer value.
 */
public class IntValue extends Value {
  private final int value;
  /**
   * Constructs a new {@code IntValue} with the specified integer value.
   *
   * @param value the integer value to store
   */
  public IntValue(int value) {
    this.value = value;
  }
  /**
   * Returns the integer value stored in this {@code IntValue} object.
   *
   * @return the stored integer value
   */
  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.format("IntValue(%d)", value);
  }

  @Override
  public String format() {
    return String.format("%d", value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntValue intValue = (IntValue) o;
    return value == intValue.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public int asInteger() { return value; }
}
