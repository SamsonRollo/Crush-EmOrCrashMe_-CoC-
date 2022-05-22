package gen;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

//import aop.game.AOP;
import coc.game.COC;

public class MainClass extends JFrame{
    //private BufferedImage ICON_PATH = "src/tray_icon.png"

    public MainClass(){
    	//setTrayIcon();
        getContentPane().setMinimumSize(new Dimension(700,465));
        getContentPane().setPreferredSize(new Dimension(700,465));
        setResizable(false);
		getContentPane().setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loadPanels();
        pack();
        setLocationRelativeTo(null);
    }

    public void loadPanels(){
        COC coc = new COC(this);
        getContentPane().add(coc);
    }

    // private void setTrayIcon(){
	// 	ImageLoader il = new ImageLoader(ICON_PATH, "icon");
    //     setIconImage(il.getBuffImage());
	// }

    public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new MainClass().setVisible(true);
            }
        });
    }
}