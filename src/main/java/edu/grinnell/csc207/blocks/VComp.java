package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Leonardo Alves Nunes
 * @author Sal Karki
 */
public class VComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment The way in which the blocks should be aligned.
   * @param topBlock The block on the top.
   * @param bottomBlock The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock, AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment The alignment of the blocks.
   * @param blocksToCompose The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

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
   * @exception Exception if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    String row = new String("");
    int previousHeight = 0;
    int currentHeight = 0;

    switch (align) {
      case LEFT:


        for (AsciiBlock block : blocks) {
          currentHeight = previousHeight + block.height();
          if (i < currentHeight) {
            String spaces = " ".repeat(this.width() - block.width());
            row = block.row(i - previousHeight).concat(spaces);
            break;
          } else {
            previousHeight = currentHeight;
          } // if
        } // for
        break;

      case RIGHT:
        for (AsciiBlock block : blocks) {
          currentHeight = previousHeight + block.height();
          if (i < currentHeight) {
            String spaces = " ".repeat(this.width() - block.width());
            row = spaces.concat(block.row(i - previousHeight));
            break;
          } else {
            previousHeight = currentHeight;
          } // if else
        } // for
        break;

      default: // CENTER
        for (AsciiBlock block : blocks) {
          currentHeight = previousHeight + block.height();
          if (i < currentHeight) {
            String spaces = " ".repeat((this.width() - block.width()) / 2);

            row = spaces.concat(block.row(i - previousHeight)).concat(spaces);
            break;
          } else {
            previousHeight = currentHeight;
          } // if else
        } // for
    } // switch
    return row;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int totalHeight = 0;

    // Sum the height of the blocks to set as height
    for (AsciiBlock block : blocks) {
      totalHeight += block.height();
    } // for
    return totalHeight;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int maxWidth = 0;
    // find the maximum width among blocks to set as width

    for (AsciiBlock block : blocks) {
      if (block.width() > maxWidth) {
        maxWidth = block.width();
      } // if
    } // for
    return maxWidth;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof VComp) && (this.eqv((VComp) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(VComp other) {
    // Check if both VComp instances have the same number of blocks
    if (this.blocks.length != other.blocks.length) {
      return false;
    } // if

    // Compare each corresponding block
    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(other.blocks[i])) {
        return false; // If any block is not equal, return false
      } // if
    } // for

    // Compare the alignment
    return this.align == other.align;
  } // eqv(VComp)
} // class VComp
