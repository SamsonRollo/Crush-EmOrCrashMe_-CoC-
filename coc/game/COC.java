package coc.game;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Font;

import gen.GameButton;
import gen.ImageLoader;
import gen.MainClass;
import gen.Score;

public class COC extends JPanel{
    public final String LEFT = "LEFT";
    public final String RIGHT = "RIGHT"; 
    private int LEFT_BOUND = 70;
    private int RIGHT_BOUND = 590;//inclusive of width
    private int colorIdx = 0;
    private MainClass mainClass;
    private BufferedImage BG_IMG;
    private Score score;
    private Level level;
    private Font font;
    private ArrayList<Bug> bugs;
    private Ship ship;
    private GameButton playBut;
    private boolean play = false, newGame = true;

    public COC(MainClass mainClass){
        this.mainClass = mainClass;
        setPreferredSize(new Dimension(700,500));
        setLayout(null);
        setBounds(0,0,700,500);
        loadElements();
    }

    public void loadElements(){
        ImageLoader il =  new ImageLoader("coc/src/panel.png", "panel");
        BG_IMG = il.getBuffImage();

        font = new Font("sans_serif", Font.BOLD, 18);
        score = new Score();
        bugs = new ArrayList<Bug>();

        int butX= 394;
        int xMult = 102;
        playBut = new GameButton(butX, 11, 87, 30);
        GameButton helpBut = new GameButton(butX+xMult, 11, 87, 30);
        GameButton quitBut = new GameButton(butX+xMult*2, 11, 87, 30);

        autoSetIcons(playBut, "play");
        autoSetIcons(helpBut, "help");
        autoSetIcons(quitBut, "quit");

        playBut.setName("play");
        playBut.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                playingStatus(true);
                //ship y = 437, x=330
            }
        });

        helpBut.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(level.getBulletLag()>0)
                    level.decrementBulletLag();
            }
        });

        quitBut.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //record score and back to main menu
            }
        });

        setKeyBindings();

        add(playBut);
        add(helpBut);
        add(quitBut);
    }

    public void playingStatus(boolean status){
        if(playBut.getName().equals("play"))
            playBut.setName("pause");
        else
            playBut.setName("play");
        autoSetIcons(playBut, playBut.getName());
        setPlay(status);

        if(newGame){
            level = new Level();
            ship = new Ship(getCOC(), 330, 415);
            getCOC().add(ship);
            updateUI();
            //craete a layer of bugs initially
            newGame = false;
        }

        //runGame(); //for ship
    }

    public void runGame(){ 
        Thread cocThread = new Thread(new Runnable() {
            @Override
            public void run(){

                //while(isPlay())
                    updateBugs(level);
                    
                    //if possible create a pattern of bugs
                    //if()//row is cleared create a batch of bugs
                    createBugs(1);
                    //removeBugs();
                    updateUI();
                    try{
                        Thread.sleep(80);
                    }catch(Exception e){};
                //}
            }
        });
        cocThread.start();
    }

    public void createBugs(int layer){
        for(int j=0; j<layer; j++){
            int mult = (int)Math.floor((520 - 11*40)/11)+40; //11 maust be randomize later
            int shift = 0; //if shift left or right to shoft row 
            for(int i=0; i<11; i++){
                Bug b = new Bug(LEFT_BOUND+mult*i, 90, (colorIdx%4)+1, shift);
                bugs.add(b);
                getCOC().add(b);
            }
            incrementColorIndex();
        }
    }

    public void updateBugs(Level level){
        for(Bug b: bugs)
            b.update(level);
    }

    public void removeBugs(){
        for (Iterator<Bug> iterator = bugs.iterator(); iterator.hasNext();) {
            Bug bug = iterator.next();
            if(!bug.isAlive()){
                iterator.remove();
                getCOC().remove(bug);
                bug = null;
            }
        }
    }

    public void setKeyBindings(){
        ActionMap am = getActionMap();
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        String vkLeft = "LEFT";
        String vkRight = "RIGHT";

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), vkLeft);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0), vkLeft);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), vkRight);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0), vkRight);
        
        am.put(vkLeft, new KeyAction(vkLeft));
        am.put(vkRight, new KeyAction(vkRight));
    }

    private class KeyAction extends AbstractAction{
        private KeyAction(String action){
            putValue(ACTION_COMMAND_KEY, action);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ship!=null && isPlay()){
                if(String.valueOf(e.getActionCommand()).equals(LEFT))
                    ship.moveLeft();
                if(String.valueOf(e.getActionCommand()).equals(RIGHT))
                    ship.moveRight();
            }
        }

    }

    public boolean isPlay(){
        return this.play;
    }

    public void setPlay(boolean play){
        this.play = play;
    }

    public Level getLevel(){
        return this.level;
    }

    public int getColorIndex(){
        return this.colorIdx;
    }

    public void incrementColorIndex(){
        this.colorIdx++;
    }

    public int getRightBound(){
        return this.RIGHT_BOUND;
    }

    public int getLeftBound(){
        return this.LEFT_BOUND;
    }

    public COC getCOC(){
        return this;
    }

    public MainClass getMainClass(){
        return this.mainClass;
    }
    
    private void autoSetIcons(GameButton button, String name){
        button.setIcons(
            "coc/src/normal/"+name+".png",
            "coc/src/hilite/h_"+name+".png",
            name.toUpperCase()
        );
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(BG_IMG, 0, 0, null);
    }
    
}
