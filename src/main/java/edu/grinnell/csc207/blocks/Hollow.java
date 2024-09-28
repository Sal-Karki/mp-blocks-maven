package edu.grinnell.csc207.blocks;

/**
 * A hollow version of an ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class Hollow implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   */
  public Hollow(AsciiBlock original) {
    this.block = original;
  } // Trimmed(AsciiBlock, HAlignment, VAlignment, int, int)

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
  public String row(int i) throws Exception {
    String row = new String("");
    if (i == 0){
      row = block.row(i);
    } else if (i == block.height() - 1) {
      row = block.row(i);
    } else {
      row = block.row(i).substring(0,1).concat(" ".repeat(block.width() - 2)).concat(block.row(i).substring(block.width() - 1));
    }
    return row;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return block.width();
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
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof Hollow) && (this.eqv((Hollow) other)));
  } // eqv(AsciiBlock)

  public boolean eqv(Hollow other) {
    return (this.block == other.block);
  } // class Trimmed
}
