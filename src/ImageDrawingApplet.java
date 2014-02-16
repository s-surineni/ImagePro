
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
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
class ImageDrawingComponent extends Component{
    
    static String descs[] = {
        "Simple Copy",
        "Scale Up",
        "Scale Down",
        "Scale Up : Bicubic",
        "Convolve : LowPass",
        "Convolve : Sharpen",
        "RescaleOp",
        "LookupOp",
    };
    
    int opIndex;
    private BufferedImage bi;
    int w, h;
    
    public static final float[] SHARPEN3x3 = { // sharpening filter kernel
        0.f, -1.f,  0.f,
       -1.f,  5.f, -1.f,
        0.f, -1.f,  0.f
    };
 
    public static final float[] BLUR3x3 = {
        0.1f, 0.1f, 0.1f,    // low-pass filter kernel
        0.1f, 0.2f, 0.1f,
        0.1f, 0.1f, 0.1f
    };
    
    public ImageDrawingComponent(URL imageSrc){
        try {
            bi= ImageIO.read(imageSrc);
            w = bi.getWidth(null);
            h = bi.getHeight(null);
            if (bi.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi2 =
                    new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi2.getGraphics();
                big.drawImage(bi, 0, 0, null);
                bi = bi2;
            }
        } catch (IOException ex) {
            Logger.getLogger(ImageDrawingComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



public class ImageDrawingApplet extends JApplet{
    
    static String imageFileName = "bld.jpg";
    private URL imageSrc;
    
    public ImageDrawingApplet () {
    }
    
    public ImageDrawingApplet (URL imageSrc) {
        this.imageSrc = imageSrc;
    }
    
    public void buildUI() {
        final ImageDrawingComponent id = new ImageDrawingComponent(imageSrc);
        add("Center", id);
        JComboBox choices = new JComboBox(id.getDescriptions());
        choices.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox)e.getSource();
                    id.setOpIndex(cb.getSelectedIndex());
                    id.repaint();
                };
            });
        add("South", choices);
    }
    
    public static void main(String[] args){
        JFrame f = new JFrame("ImageDrawing");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            //    System.exit(0);
            }
        });
        URL imageSrc = null;
        try {
             imageSrc = ((new File(imageFileName)).toURI()).toURL();
        } catch (MalformedURLException e) {
        }
        ImageDrawingApplet id = new ImageDrawingApplet(imageSrc);
    }
    
}
