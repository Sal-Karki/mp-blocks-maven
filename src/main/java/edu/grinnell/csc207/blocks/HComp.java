package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class HComp implements AsciiBlock {
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
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    String row = new String("");

    switch (align) {
      case TOP:
        for (AsciiBlock block : blocks) {
          if (i > block.height()) {
            row = row.concat(" ".repeat(block.width()));
          } else {
            row = row.concat(block.row(i));
          } // if else
        } // for   
        break;
    
      case BOTTOM:
      for (AsciiBlock block : blocks) {
        if (i < this.height() - block.height()) {
          row = row.concat(" ".repeat(block.width()));
        } else {
          row = row.concat(block.row(i - (this.height() - block.height())));
        } // if else
      } // for   
      break;

      default: // CENTER
        for (AsciiBlock block : blocks) {
          if (i < (this.height() - block.height()) / 2 || i >= ((this.height() + block.height()) / 2)) {
            row = row.concat(" ".repeat(block.width()));
          } else {
            row = row.concat(block.row(i - ((this.height() - block.height()) / 2)));
          } // if else
        } // for 
    }
    return row;  // STUB
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int maxHeight = 0;
    //find the maximum height among blocks to set as height

    for(AsciiBlock block : blocks) {
      if (block.height() > maxHeight) {
        maxHeight = block.height();
      }
    } // for
    return maxHeight;   // STUB
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int totalWidth = 0;

    //Sum the height of the blocks to set as width
    for(AsciiBlock block : blocks) {
      totalWidth += block.width();
    } // for
    return totalWidth; 
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
    return false;       // STUB
  } // eqv(AsciiBlock)


} // class HComp
