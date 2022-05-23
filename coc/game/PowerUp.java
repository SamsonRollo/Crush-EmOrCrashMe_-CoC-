package coc.game;

import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


public class PowerUp extends GameObject implements Runnable{
    private int selectedPowerUp = 0;
    private COC coc;

    public PowerUp(COC coc){
        this.coc = coc;
        IMG_PATH = "coc/src/powerup.png";
        setGameObject("powerup", randomizeXPos(), 85);
        
        selectedPowerUp = new Random().nextInt(101);
        
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(coc.isPlay() && coc.isEnter())
                    executePowerUp(selectedPowerUp);
            }
        });
    }

    public void runPowerUp(){
        Thread powerupThread = new Thread(this);
        powerupThread.start();
    }

    @Override
    public void run() {
        coc.add(getObject());
        coc.setComponentZOrder(getObject(), 0);
        while(isAlive()){
            updatePowerUp();
            try{
                Thread.sleep(80);
            }catch(Exception e){}
        }
        coc.remove(getObject());
    }

    public void executePowerUp(int selectedPowerUp){
        //insert question pop up here
        if(selectedPowerUp%11==0){
            System.out.println("all");
            coc.getDen().killAllBugs();
        } else if(selectedPowerUp%5==0){
            System.out.println("freeze");
            coc.getDen().setFreeze(true);
        }else{
            System.out.println("level");
            coc.getLevel().setBulletLevel(2);
        }
        setAlive(false);
    }

    private int randomizeXPos(){
        return (new Random().nextInt(coc.getRightBound() + 1 - coc.getLeftBound())+coc.getLeftBound());
    }

    public void updatePowerUp(){
        int y = getY()+velocity;
        moveCurrentPoint(getX(), y);
        setLocation(getX(), y);
    }

    protected boolean calculateAlive() {
        if(getY()+getH()>=450)
            return false;
        return true;
    }

    public GameObject getObject(){
        return this;
    }
}
