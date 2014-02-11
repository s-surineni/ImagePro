
import java.applet.Applet;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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
public class SeeThroughImageApplet extends Applet{
    
    static String imageFileName = "duke_skateboard.jpg";
    private URL imageSrc;
 
    public SeeThroughImageApplet () {
    }
 
    public SeeThroughImageApplet (URL imageSrc) {
        this.imageSrc = imageSrc;
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
