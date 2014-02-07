
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JButton;
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
public class JumbledImageApplet extends JApplet{
    
    static String imageFileName = "examples/duke_skateboard.jpg";
    private URL imageSrc;
    
    public JumbledImageApplet (URL imageSrc) {
        this.imageSrc = imageSrc;
    }
    
    public void buildUI(){
        
        final JumbledImage ji = new JumbledImage(imageSrc);
        add("Center", ji);
        JButton jumbleButton = new JButton("Jumble");
        jumbleButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton b = (JButton)e.getSource();
                    ji.jumble();
                    ji.repaint();
                };
            });
        Dimension jumbleSize = ji.getPreferredSize();
    }
    
    public static void main(String[] args){
        
        JFrame f = new JFrame("Jumbled Image");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        URL imageSrc = null;
        try {
             imageSrc = ((new File(imageFileName)).toURI()).toURL();
        } catch (MalformedURLException e) {
        }
        JumbledImageApplet jumbler = new JumbledImageApplet(imageSrc);
        jumbler.buildUI();
    }
    
}
