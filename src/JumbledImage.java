
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sampath
 */
public class JumbledImage extends Component{
    
    private BufferedImage bi;
    int w, h, cw, ch;//w,h are width and height of the image
    private int numlocs = 2;
    private int numcells = numlocs*numlocs;
    private int[] cells;
    
    public JumbledImage(URL imageSrc){
        try {
            bi = ImageIO.read(imageSrc);
            w = bi.getWidth(null);
            h = bi.getHeight(null);
        } catch (IOException ex) {
            Logger.getLogger(JumbledImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cw=w/numlocs;//width divided by number of parts to divide in width
        //column width
        ch = h/numlocs;//column height
        cells = new int[numcells];
        for (int i=0;i<numcells;i++) {
            cells[i] = i;
        }
    }
    
    void jumble() {
        Random rand = new Random();
        int ri;
        for (int i=0; i<numcells; i++) {
            while ((ri = rand.nextInt(numlocs)) == i);
 
            int tmp = cells[i];
            cells[i] = cells[ri];
            cells[ri] = tmp;
        }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }
    
    
    public void paint(Graphics g) {
 
        int dx, dy;
        for (int x=0; x<numlocs; x++) {
            int sx = x*cw;
            for (int y=0; y<numlocs; y++) {
                int sy = y*ch;
                int cell = cells[x*numlocs+y];
                dx = (cell / numlocs) * cw;
                dy = (cell % numlocs) * ch;
                g.drawImage(bi,
                            dx, dy, dx+cw, dy+ch,
                            sx, sy, sx+cw, sy+ch,
                            null);
            }
        }
    }
}
    

