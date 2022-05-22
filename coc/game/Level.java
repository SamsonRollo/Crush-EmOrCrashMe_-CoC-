package coc.game;

public class Level {
    private int level;
    private int ySpeed;
    private int xSpeed;
    private int bulletSpeed;
    private int bulletLag;
    private int bulletLevel;

    public Level(){
        this.level = 1;
        this.ySpeed = 2;
        this.xSpeed = 0;
        this.bulletSpeed = 5;
        this.bulletLag = 10;
        this.bulletLevel = 1;
    }

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void incrementLevel(){
        this.level++;
    }

    public int getYSpeed(){
        return this.ySpeed;
    }

    public void setYSpeed(int ySpeed){
        this.ySpeed = ySpeed;
    }

    public void incrementYSpeed(){
        this.ySpeed++;
    }

    public void incrementYSpeed(int increment){
        this.ySpeed += increment;
    }

    public int getXSpeed(){
        return this.xSpeed;
    }

    public void setXSpeed(int xSpeed){
        this.xSpeed = xSpeed;
    }

    public void incrementXSpeed(){
        this.xSpeed++;
    }

    public void incrementXSpeed(int increment){
        this.xSpeed += increment;
    }

    public int getBulletSpeed(){
        return this.bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed){
        this.bulletSpeed = bulletSpeed;
    }

    public void incrementBulletSpeed(){
        this.bulletSpeed++;
    }

    public void incrementBulletSpeed(int increment){
        this.bulletSpeed += increment;
    }

    public int getBulletLag(){
        return this.bulletLag;
    }

    public void setBulletLag(int bulletLag){
        this.bulletLag = bulletLag;
    }

    public void decrementBulletLag(){
        this.bulletLag--;
    }

    public void decrementBulletLag(int decrement){
        this.bulletLag -= decrement;
    }

    public int getBulletLevel(){
        return this.bulletLevel;
    }

    public void setBulletLevel(int level){
        this.level = level;
    }
}
