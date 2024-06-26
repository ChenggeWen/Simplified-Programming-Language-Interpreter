package csProject.core;

import java.util.Objects;

/**
 * Represents a boolean value as an object. This class provides methods to
 * retrieve the boolean value and to represent it as a string.
 * It extends the {@code Value} class, suggesting it is part of a type hierarchy
 * for representing different types of values, typically in contexts like scripting languages
 * or configuration systems.
 */
public class BooleanValue extends Value {
  private final boolean value;
  /**
   * Constructs a new {@code BooleanValue} with the specified boolean value.
   *
   * @param value the boolean value to store
   */
  public BooleanValue(boolean value) {
    this.value = value;
  }
  /**
   * Returns the boolean value stored in this {@code BooleanValue} object.
   *
   * @return the stored boolean value
   */
  public boolean getValue() {
    return value;
  }
  /**
   * Formats the stored boolean value as a string "true" or "false".
   *
   * @return the formatted string representation of the boolean value
   */
  @Override
  public String format() {
    if (value) {
      return "true";
    } else {
      return "false";
    }
  }
  /**
   * Returns a string representation of this {@code BooleanValue} instance,
   * formatted as "BooleanValue(true)" or "BooleanValue(false)".
   *
   * @return a string representation of this object
   */
  @Override
  public String toString() {
    return String.format("BooleanValue(%s)", format());
  }

  /**
   * Compares this object to another object to determine equality.
   * Two {@code BooleanValue} objects are considered equal if they both
   * contain the same boolean value.
   *
   * @param o the object to compare with this {@code BooleanValue}
   * @return {@code true} if the specified object is equal to this {@code BooleanValue}; {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BooleanValue that = (BooleanValue) o;
    return value == that.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
  /**
   * Returns the boolean value of this {@code BooleanValue}.
   * This method allows {@code BooleanValue} to be used in contexts
   * where a boolean value is needed directly, effectively unwrapping
   * the boolean value stored inside.
   *
   * @return the stored boolean value
   */

  @Override
  public boolean asBoolean() {
    return value;
  }
}
