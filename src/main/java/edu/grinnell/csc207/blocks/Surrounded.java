package edu.grinnell.csc207.blocks;

/**
 * A text block surrounded by a single character.
 * 
 * @author Your Name Here
 */
public class Surrounded implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The contents of the block.
   */
  AsciiBlock contents;

  /**
   * The character we use to surround the block.
   */
  String surroundChar;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents and surround character.
   *
   * @param blockContents
   *   The contents of the block.
   *
   * @param theChar
   *   The character that we use to surround the block.
   */
  public Surrounded(AsciiBlock blockContents, char theChar) {
    this.contents = blockContents;
    this.surroundChar = Character.toString(theChar);
  } // Surrounded(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   If the row is invalid.
   */
  @Override
  public String row(int i) throws Exception {
    int h = this.contents.height();  // Height of the inner content
    int w = this.contents.width();   // Width of the inner content

    if (i == 0) {
      // The top border (row 0)
      return surroundChar.repeat(w + 2);
    } else if (i == h + 1) {
      // The bottom border (last row)
      return surroundChar.repeat(w + 2);
    } else if (i > 0 && i <= h) {
      // Rows within the box, surrounded by the character
      return surroundChar + this.contents.row(i - 1) + surroundChar;
    } else {
      throw new Exception("Invalid row " + i);
    }
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  @Override
  public int height() {
    // The height is the height of the content plus two (top and bottom borders)
    return 2 + this.contents.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  @Override
  public int width() {
    // The width is the width of the content plus two (left and right borders)
    return 2 + this.contents.width();
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  @Override
  public boolean eqv(AsciiBlock other) {
    // Step 1: Check if other is an instance of Surrounded
    if (!(other instanceof Surrounded)) {
        return false;
    }

    // Step 2: Cast to Surrounded and check if the surrounding characters are the same
    Surrounded otherSurrounded = (Surrounded) other;
    if (!this.surroundChar.equals(otherSurrounded.surroundChar)) {
        return false;
    }

    // Step 3: Compare the contents of both blocks for structural equivalence
    return this.contents.eqv(otherSurrounded.contents);
}
} // class Surrounded
