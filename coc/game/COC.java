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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Font;

import gen.GameButton;
import gen.ImageLoader;
import gen.MainClass;
import gen.Score;

public class COC extends JPanel{
    public final String LEFT = "LEFT";
    public final String RIGHT = "RIGHT"; 
    public final String ENTER = "ENTER";
    private int LEFT_BOUND = 70;
    private int RIGHT_BOUND = 590;//inclusive of width
    private MainClass mainClass;
    private BufferedImage BG_IMG, CUR_SC_IMG=null, TOT_SC_IMG;
    private Score score;
    private Level level;
    private Font font;
    private Ship ship;
    private BugDen den;
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
        il.reloadImage("coc/src/progressbar.png", "progress");
        TOT_SC_IMG = il.getBuffImage();

        font = new Font("sans_serif", Font.BOLD, 21);
        score = new Score();

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
                playingStatus(!isPlay());
            }
        });

        helpBut.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

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
            ship = new Ship(getCOC(), score, 330, 450);
            den = new BugDen(getCOC(), level, 4); //change later based on level
            getCOC().add(ship);
            updateScoreIMG();
            updateUI();
            newGame = false;
            bindENTER(ENTER);
        }
    }

    public void gameThreads(){
        Thread shipThread = new Thread(ship);
        Thread denThread = new Thread(den);
        shipThread.start();
        denThread.start();
    }

    public void updateScoreIMG(){
        int curLine = (int)Math.floor((Double.valueOf(score.getScore())/level.getTargetScore())*TOT_SC_IMG.getHeight());
        if(curLine<TOT_SC_IMG.getHeight())
            CUR_SC_IMG = TOT_SC_IMG.getSubimage(0, TOT_SC_IMG.getHeight()-curLine-1, TOT_SC_IMG.getWidth(), curLine+1);
        else
            levelUp(); 
    }

    public void levelUp(){
        System.out.println("levelUO");
    }

    public void setKeyBindings(){
        ActionMap am = getActionMap();
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), LEFT);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), RIGHT);
        
        am.put(LEFT, new KeyAction(LEFT));
        am.put(RIGHT, new KeyAction(RIGHT));
    }

    public void bindENTER(String command){
        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), command);
        if(!command.equals("none")){
            ActionMap am = getActionMap();
            am.put(command, new KeyAction(command));
        }
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
                if((String.valueOf(e.getActionCommand()).equals(ENTER)) && den!=null){
                    gameThreads();
                    bindENTER("none");
                }
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

    public int getRightBound(){
        return this.RIGHT_BOUND;
    }

    public int getLeftBound(){
        return this.LEFT_BOUND;
    }

    public COC getCOC(){
        return this;
    }

    public BugDen getDen(){
        return this.den;
    }

    public Ship getShip(){
        return this.ship;
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
        if(CUR_SC_IMG!=null)
            g.drawImage(CUR_SC_IMG, 656, 98+TOT_SC_IMG.getHeight()-CUR_SC_IMG.getHeight(), null); 
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(String.valueOf(score.getScore()), 44, 50);
    }
    
}
