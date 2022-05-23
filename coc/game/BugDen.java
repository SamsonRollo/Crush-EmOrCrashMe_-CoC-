package coc.game;

import java.util.ArrayList;
import java.util.Iterator;

public class BugDen implements Runnable{
    private COC coc;
    private Level level;
    private ArrayList<Bug> bugs;
    private int colorIdx = 0;
    private int lastY = 85;

    public BugDen(COC coc, Level level, int initLayer){
        this.coc = coc;
        this.level = level;
        bugs = new ArrayList<Bug>();
        createBugs(initLayer);
    }

    @Override
    public void run() {
        int accumLag = 0;
        while(coc.isPlay()){

            if(accumLag>=level.getBugLag()){
                updateBugs(level);
                accumLag=0;
            }

            if(lastY>=133){
                createBugs(1);
                lastY = 85;
            }

            removeBugs();
            accumLag++;
            try{
                coc.updateUI();
            }catch (Exception e){}
            try{
                Thread.sleep(20);
            }catch(Exception e){};
        }
        
    }

    public void createBugs(int layer){
        for(int j=0, mulY = 48; j<layer; j++){
            int mulX = (int)Math.floor((520 - 11*40)/11)+40; //11 maust be randomize later
            int shift = 0; //if shift left or right to shoft row 
            for(int i=0; i<11; i++){
                Bug b = new Bug(coc, coc.getLeftBound()+mulX*i+shift, 85+mulY*j, (colorIdx%4)+1, shift);
                bugs.add(b);
                coc.add(b);
            }
            incrementColorIndex();
        }
    }

    public void updateBugs(Level level){
        try{
            for(Bug b: bugs)
                b.update(level);
        }catch(Exception e){}
        lastY+=level.getYSpeed();
    }

    public void removeBugs(){
        try{
            for (Iterator<Bug> iterator = bugs.iterator(); iterator.hasNext();) {
                Bug bug = iterator.next();
                if(!bug.isAlive()){
                    iterator.remove();
                    coc.remove(bug);
                    bug = null;
                }
            }
        }catch(Exception e){}
    }

    public void killAllBugs(){
        try{
            for(Bug b : bugs)
                b.setAlive(false);
        }catch(Exception e){}
        removeBugs();
    }

    public int getColorIndex(){
        return this.colorIdx;
    }

    public void incrementColorIndex(){
        this.colorIdx++;
    }

    public ArrayList<Bug> getBugs(){
        return this.bugs;
    }
}
