package edu.grinnell.csc207.blocks;

/**
 * A trimmed ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class Trimmed implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  /**
   * Which part of the block do we keep horizontally?
   */
  HAlignment halign;

  /**
   * Which part of the block do we keep vertically?
   */
  VAlignment valign;

  /**
   * How much of the block do we keep horizontally?
   */
  int width;

  /**
   * How much of the block do we keep vertically?
   */
  int height;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   * @param horiz
   *   How the trimmed block is horizontally aligned on the original.
   * @param vert
   *   How the trimmed block is vertically aligned on the original.
   * @param trimmedWidth
   *   The width of the trimmed block.
   * @param trimmedHeight
   *   The height of the trimmed block.
   */
  public Trimmed(AsciiBlock original, HAlignment horiz, VAlignment vert,
      int trimmedWidth, int trimmedHeight) {
    this.block = original;
    this.halign = horiz;
    this.valign = vert;
    this.width = trimmedWidth;
    this.height = trimmedHeight;
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
    switch(halign) {
      case LEFT:
        switch (valign) {
          case TOP:
            if(i < this.height){
              row = block.row(i).substring(0,this.width);
            }
            break;
        
          case BOTTOM:
            if(i > block.height() - this.height){
              row = block.row(i + (block.height() - this.height)).substring(0,this.width);
            }
            break;

          default: // CENTER
            if (i >= (block.height() - this.height()) / 2 && i < ((block.height() - this.height()) / 2)) {
              row = block.row(i + ((block.height() - this.height)) / 2).substring(0,this.width);
            }
            break;
        }
        break;

      case RIGHT:
        switch (valign) {
          case TOP:
            if(i < this.height){
              row = block.row(i).substring(this.width, block.width());
            }
            break;
        
          case BOTTOM:
            if(i > block.height() - this.height){
              row = block.row(i + (block.height() - this.height)).substring(this.width, block.width());
            }
            break;

          default: // CENTER
            if (i >= (block.height() - this.height()) / 2 && i < ((block.height() - this.height()) / 2)) {
              row = block.row(i + ((block.height() - this.height)) / 2).substring(this.width, block.width());
            }
            break;
        }
    
      default: // CENTER
        switch (valign) {
          case TOP:
            if(i < this.height){
              row = block.row(i).substring(((block.width() - this.width) / 2), ((block.width() + this.width) / 2));
            }
            break;
        
          case BOTTOM:
            if(i > block.height() - this.height){
              row = block.row(i + (block.height() - this.height)).substring(((block.width() - this.width) / 2), ((block.width() + this.width) / 2));
            }
            break;

          default: // CENTER
            if (i >= (block.height() - this.height()) / 2 && i < ((block.height() - this.height()) / 2)) {
              row = block.row(i + ((block.height() - this.height)) / 2).substring(((block.width() - this.width) / 2), ((block.width() + this.width) / 2));
            }
            break;
        }
    }
    return row;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.width;
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
    return ((other instanceof Trimmed) && (this.eqv((Trimmed) other)));
  } // eqv(AsciiBlock)

  public boolean eqv(Trimmed other) {
    return (this.block == other.block) && (this.halign == other.halign)
     && (this.valign == other.valign)
     && (this.width == other.width) 
     && (this.height == other.height) ;
  } // eqv(AsciiBlock)
} // class Trimmed
