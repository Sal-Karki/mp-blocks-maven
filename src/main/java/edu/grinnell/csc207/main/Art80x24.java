package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Hollow;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.HComp;
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
    

    AsciiBlock wheel = new Hollow(new Rect('O',6,3));
    AsciiBlock emptyWheel = new Hollow(new Rect(' ',20,3));
    AsciiBlock sky = new Rect(' ', 78,12);

    AsciiBlock[] arrayOfWheels = {wheel,emptyWheel,wheel};

    AsciiBlock bottomCar = new Rect('#',40,3);
    AsciiBlock topCar = new Rect('#', 30,2);
    AsciiBlock[] arrayOfCarParts = {topCar,bottomCar};
    AsciiBlock ground = new Rect('-',78,2);

    //Composing images with Vcomp and HComp
    AsciiBlock carBody = new VComp(HAlignment.LEFT, arrayOfCarParts);

    AsciiBlock wheels = new HComp(VAlignment.CENTER, arrayOfWheels);

    AsciiBlock[] carArray = {sky,carBody,wheels};
    AsciiBlock car = new VComp(HAlignment.CENTER, carArray);

    AsciiBlock[] carAndGroundArray = {car,ground};
    AsciiBlock carAndGround = new VComp(HAlignment.CENTER, carAndGroundArray);

    AsciiBlock art = new Surrounded(carAndGround, '*');
    

    AsciiBlock.print(pen, art);

    pen.close();
  } // main(String[])
} // class Art80x24
