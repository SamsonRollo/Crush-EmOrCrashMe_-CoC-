package coc.game;

public class Bullet extends GameObject{

    public Bullet(int bulletLevel, int x, int y){
        IMG_PATH = "coc/src/bullet"+bulletLevel+".png";
        setGameObject("bullet", x, y);
    }

    public void updateBullet(int bulletSpeed){
        moveCurrentPoint(getX(), getY()-bulletSpeed);
    }

    @Override
    protected boolean calculateAlive() {
        if(getY() <= 85)
            return false;
        return true;
    }
}
