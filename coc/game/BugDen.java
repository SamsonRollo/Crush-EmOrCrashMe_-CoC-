package coc.game;

import java.util.ArrayList;
import java.util.Iterator;

public class BugDen implements Runnable{
    private COC coc;
    private Level level;
    private ArrayList<Bug> bugs;
    private int colorIdx = 0;

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
            //if possible create a pattern of bugs
            //if()//row is cleared create a batch of bugs
            //createBugs(1); //create if already possible
            removeBugs();
            accumLag++;
            coc.updateUI();
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
                Bug b = new Bug(coc.getLeftBound()+mulX*i+shift, 85+mulY*j, (colorIdx%4)+1, shift);
                bugs.add(b);
                coc.add(b);
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
                coc.remove(bug);
                bug = null;
            }
        }
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
