package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.HAlignment;


import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Your Name Here
 * @author Your Name Here
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    AsciiBlock base1 = new Rect('^', 2, 4);
    AsciiBlock base2 = new Rect('*', 3, 3);
    AsciiBlock base3 = new Rect('#', 5, 2);
    AsciiBlock[] list = {base1,base2,base3};
    AsciiBlock art = new VComp(HAlignment.RIGHT, list);
    //AsciiBlock art = new Rect('^', 80, 24);
    AsciiBlock.print(pen, art);
    pen.close();
  } // main(String[])
} // class Art80x24
