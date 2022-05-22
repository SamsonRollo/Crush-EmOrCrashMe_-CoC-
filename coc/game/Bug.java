package coc.game;

import java.util.Random;

public class Bug extends GameObject {
    private int shift; //positive right shift, negative left shift
    private int shiftCount = 0;

    public Bug(int x, int y, int colorNum, int shift){
        this.shift = shift;
        IMG_PATH = "coc/src/bug"+colorNum+".png";
        setGameObject("bug", x, y);    
    }

    public int randomizer(int size){
        return new Random().nextInt(size)+1;
    }

    public void update(Level level){
        int deltaX = getX();
        if(shift!=0 && shift==shiftCount) //reverses shift to revers x shift
            shift = -shift;

        if(shift<0 && shift<shiftCount)
            deltaX = getX()-level.getXSpeed();
        else if(shift>0 && shift>shiftCount)
            deltaX = getX()+level.getXSpeed();

        moveCurrentPoint(deltaX, getY()+level.getYSpeed());
        // if(getY()>=someThreshold)
        //     notifyGameOver();
    }

    public void notifyGameOver(){
        //coc.isGameOver();
    }

    @Override
    protected boolean calculateAlive() {
        return true;
    }
    
}
