
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
public class LoadImage extends Component{
    
    BufferedImage img;
    
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
    
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
    
    public LoadImage() throws IOException{
        img = ImageIO.read(new File("tring.jpg"));
    }
    
    public static void main(String[] args) throws IOException{
        
        JFrame f = new JFrame("Loading Image");
        f.addWindowListener(new WindowAdapter(){
            public void windowClosin(WindowEvent e){
                System.exit(0);
            }
        });
        
        f.add(new LoadImage());
        f.pack();
        f.setVisible(true);
    }
    
}
