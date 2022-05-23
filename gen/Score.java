package gen;

public class Score {
    private int score;
    private int totalScore;

    public Score(){
        this.score = 0;
        this.totalScore = 0;
    }

    public Score(int totalScore){
        this.totalScore = totalScore;
        this.score = 0;
    }

    public void setTotalScore(int totalScore){
        this.totalScore = totalScore;
    }

    public void incrementTotalScore(int score){
        this.totalScore += score;
    }

    public int getTotalScore(){
        return this.totalScore;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void incrementScore(int score){
        this.score+=score;
    }

    public int getScore(){
        return this.score;
    }

    public void resetCurrentScore(){
        this.score = 0;
    }
}
