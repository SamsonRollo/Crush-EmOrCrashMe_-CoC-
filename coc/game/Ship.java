
package coc.game;

import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ship extends GameObject {
    private COC coc;
    private int LEFT_BOUND;
    private int RIGHT_BOUND;
    private int speed = 21;
    private ArrayList<Bullet> bullets;

    public Ship(COC coc, int x, int y){
        this.coc = coc;
        this.LEFT_BOUND = coc.getLeftBound();
        this.RIGHT_BOUND = coc.getRightBound();
        IMG_PATH = "coc/src/weapon.png";
        setGameObject("bug", x, y);
        setInitPoint(x, y);

        bullets = new ArrayList<Bullet>();

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                if(coc.isPlay()){
                    if(e.getX()+getInitX()>LEFT_BOUND && e.getX()+getInitX()<RIGHT_BOUND){
                        setCurrentPoint(getInitX()+e.getX()-1, getInitY());
                        coc.updateUI();
                    }
                }       
            }
        });

        addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e){
                updateShipPosition();
            }
        });

        produceBullets();
    }

    public void produceBullets(){
        Thread bulleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int accumLag = coc.getLevel().getBulletLag();
                while(coc.isPlay()){
                    updateBullets(coc.getLevel().getBulletSpeed());
                    if(accumLag>=coc.getLevel().getBulletLag()){
                        createBullet();
                        accumLag = 0;
                    }
                    removeBullets();
                    coc.updateUI();
                    accumLag++;
                    try{
                        Thread.sleep(20);
                    }catch(Exception e){};
                }
            }
        });
        bulleThread.start();
    }

    public void updateBullets(int bulletSpeed){
        for(Bullet b : bullets)
            b.updateBullet(bulletSpeed);
    }

    public void createBullet(){
        Bullet b = new Bullet(coc.getLevel().getBulletLevel(), getX()+15, 415);
        bullets.add(b);
        coc.add(b);
    }

    public void removeBullets(){
        for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext();) {
            Bullet bullet = iterator.next();
            if(!bullet.isAlive()){
                iterator.remove();
                coc.remove(bullet);
                bullet = null;
            }
        }
    }

    private void updateShipPosition(){;
        setInitPoint(getX(), getY());
        setLocation(getX(), getY());
    }

    public void moveLeft(){
        if(getX()-speed>LEFT_BOUND)
            keyBoardMove(getX()-speed);
        else
            keyBoardMove(LEFT_BOUND);
    }

    public void moveRight(){
        if(getX()+speed<RIGHT_BOUND-5)
            keyBoardMove(getX()+speed);
        else
            keyBoardMove(RIGHT_BOUND-5);
    }

    private void keyBoardMove(int delX){
        setInitPoint(delX, getY());
        setCurrentPoint(delX, getY());
        setLocation(delX, getY());
    }

    @Override
    protected boolean calculateAlive() {
        return true;
    }
    
}
