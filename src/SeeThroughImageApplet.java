

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
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
class SeeThroughComponent extends Component{
    private BufferedImage bi;
    float[] scales = { 1f, 1f, 1f, 0.5f };
    float[] offsets = new float[4];
    RescaleOp rop;
    
    public SeeThroughComponent(URL imagesrc){
        try {
            BufferedImage img = ImageIO.read(imagesrc);
            int w = img.getWidth(null);
            int h = img.getHeight(null);
            bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();
            g.drawImage(img, 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(SeeThroughComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

public class SeeThroughImageApplet extends JApplet{
    
    static String imageFileName = "duke_skateboard.jpg";
    private URL imageSrc;
 
    public SeeThroughImageApplet () {
    }
 
    public SeeThroughImageApplet (URL imageSrc) {
        this.imageSrc = imageSrc;
    }
    
    public void buildUI(){
        
    }
    
    public static void main(String[] args){
        JFrame f = new JFrame("See Through Image");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        URL imageSrc = null;
        try {
             imageSrc = ((new File(imageFileName)).toURI()).toURL();
        } catch (MalformedURLException e) {
        }
        SeeThroughImageApplet sta = new SeeThroughImageApplet(imageSrc);
    }
    
}
