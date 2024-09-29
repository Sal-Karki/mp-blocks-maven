package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.Hollow;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;

/**
 * Tests of the new block.
 */
public class TestNewBlock {

   static final AsciiBlock newRect(char ch, int width, int height) {
    try {
      return new Rect(ch, width, height);
    } catch (Exception e) {
      return new Line(String.format("Rect(%c, %d, %d)", ch, width, height));
    } // try/catch
  } // newRect

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  static AsciiBlock exesRect = newRect('X', 6, 4);
  /** Using Rect again. */
  static AsciiBlock exesSquare = newRect('X', 6, 6);

  static AsciiBlock OSquare = newRect('O', 6, 6);
  static AsciiBlock emptySquare = newRect(' ', 5, 5);
  static AsciiBlock hollowRect = new Hollow(exesRect);
  static AsciiBlock hollowRect2 = new Hollow(exesRect);
  static AsciiBlock hollowSquare = new Hollow(exesSquare);
  static AsciiBlock hollowOSquare = new Hollow(OSquare);

  static AsciiBlock surrounded = new Surrounded(emptySquare, 'X');

  static AsciiBlock hollowemptySquare = new Hollow(emptySquare);


  static AsciiBlock hollowGrid = new Grid(exesSquare, 3, 3);

  static AsciiBlock emptyBlock = new Empty();

  static AsciiBlock hollowEmpty = new Hollow(emptyBlock);

  /**
   * Makes sure that the eqv function works, expects true
   */
  @Test
  public void eqvTrue() {
    assertEquals(hollowRect.eqv(hollowRect2), true);
  } // eqvTruer()

  /**
   * Makes sure that the eqv function works, expects false
   */
  @Test
  public void eqvFalse() {
    assertEquals(hollowRect.eqv(hollowSquare), false);
  } // eqvFalse()

  /**
   * Makes sure that the eqv function doesn't only check dimensions but character as well.
   * AKA, making sure that it delegates the eqv to ASCII Blocks correctly.
   */
  @Test
  public void eqvFalseSameDimensions() {
    assertEquals(hollowSquare.eqv(hollowOSquare), false);
  } // eqvFalseSameDimensions()

  /**
   * Makes sure that it can hollow an empty square
   */
  @Test
  public void emptySquare() {
    assertEquals((null == hollowemptySquare), false);
  } // emptySquare()

   /**
   * Makes sure that an empty block isnt equal to a hollow empty block
   */
  @Test
  public void emptyBlock() {
    assertEquals(hollowEmpty.eqv(emptyBlock), false);
  } // emptyBlock()

  /**
   * Makes sure that a surrounded isn't equal, since surrounded uses chars instead of strings
   */
  @Test
  public void surroundedBlock() {
    assertEquals(hollowSquare.eqv(surrounded), false);
  } // surroundedBlock()

} // class TestNewBlock
