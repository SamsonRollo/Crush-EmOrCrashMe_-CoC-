package coc.game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import gen.GameButton;

public class LevelUpPanel extends MenuPanel {
    
    public LevelUpPanel(COC coc){
        this.coc = coc;
        this.path = "coc/src/gamelevelup.png";
        loadElements("over");

        GameButton ok = new GameButton((int)Math.floor(Double.valueOf(getWidth())/2-43), getHeight()-36 , 87, 30);

        autoSetIcons(ok, "ok");

        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                coc.remove(getPanel());
                coc.setButtonsEnabled(true);
                coc.showMiddler();
                coc.setLevelNotif(false);//move to coc
                //resetgame for new batch
                coc.updateUI();
            }
        });

        add(ok);
    }
}
