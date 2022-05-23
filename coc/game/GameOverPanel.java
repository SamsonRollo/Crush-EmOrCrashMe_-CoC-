package coc.game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import gen.GameButton;

public class GameOverPanel extends MenuPanel {
    
    public GameOverPanel(COC coc){
        this.coc = coc;
        this.path = "coc/src/gameover.png";
        loadElements("over");

        GameButton ok = new GameButton((int)Math.floor(Double.valueOf(getWidth())/2-43), getHeight()-36 , 87, 30);

        autoSetIcons(ok, "ok");

        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                coc.remove(getPanel());
                coc.setButtonsEnabled(true);
                coc.cleanGame();
                coc.updateUI();
            }
        });

        add(ok);
    }
}
