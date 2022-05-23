package coc.game;

public class Level {
    private int level;
    private int ySpeed;
    private int xSpeed;
    private int bulletSpeed;
    private int bulletLag;
    private int bulletLevel;
    private int bugLag;

    public Level(){
        this.level = 1;
        this.ySpeed = 7;
        this.xSpeed = 0;
        this.bulletSpeed = 4;
        this.bulletLag = 12;
        this.bulletLevel = 1;
        this.bugLag = 50; //50
    }

    public int getTargetScore(){
        return this.level*50+13; //back to 160
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

    public int getBugLag(){
        return this.bugLag;
    }

    public void setBugLag(int lag){
        this.bugLag = lag;
    }

    public void decrementBugLag(){
        this.bugLag--;
    }

    public void decrementBugLag(int decrement){
        this.bugLag -= decrement;
    }
}
