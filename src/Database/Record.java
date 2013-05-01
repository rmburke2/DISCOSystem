
package Database;

import java.util.Date;
/**
 *
 * @author Rich
 */
public class Record {
    
    public Record(){}
    
    private int ID; //should be unique
    private String name;
    private Date date = new Date();
    private String lessonName;
    private int correctChoice;
    private int actualChoice;
    private int reward;
    private String audioReward;
    private String videoReward;
    private int responseTime;
    private java.awt.Color border1;
    private java.awt.Color border2;
    private java.awt.Color border3;
    private java.awt.Color border4;
    private java.awt.Image image1;
    private String image1path;
    private java.awt.Image image2;
    private String image2path;
    private java.awt.Image image3;
    private String image3path;
    private java.awt.Image image4;
    private String image4path;
    private String comments;
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }

    public int getActualChoice() {
        return actualChoice;
    }

    public void setActualChoice(int actualChoice) {
        this.actualChoice = actualChoice;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getAudioReward() {
        return audioReward;
    }

    public void setAudioReward(String audioReward) {
        this.audioReward = audioReward;
    }

    public String getVideoReward() {
        return videoReward;
    }

    public void setVideoReward(String videoReward) {
        this.videoReward = videoReward;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public java.awt.Color getBorder1() {
        return border1;
    }

    public void setBorder1(java.awt.Color border1) {
        this.border1 = border1;
    }

    public java.awt.Color getBorder2() {
        return border2;
    }

    public void setBorder2(java.awt.Color border2) {
        this.border2 = border2;
    }

    public java.awt.Color getBorder3() {
        return border3;
    }

    public void setBorder3(java.awt.Color border3) {
        this.border3 = border3;
    }

    public java.awt.Color getBorder4() {
        return border4;
    }

    public void setBorder4(java.awt.Color border4) {
        this.border4 = border4;
    }

    public java.awt.Image getImage1() {
        return image1;
    }

    public void setImage1(java.awt.Image image1) {
        this.image1 = image1;
    }

    public java.awt.Image getImage2() {
        return image2;
    }

    public void setImage2(java.awt.Image image2) {
        this.image2 = image2;
    }

    public java.awt.Image getImage3() {
        return image3;
    }

    public void setImage3(java.awt.Image image3) {
        this.image3 = image3;
    }

    public java.awt.Image getImage4() {
        return image4;
    }

    public void setImage4(java.awt.Image image4) {
        this.image4 = image4;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImage4path() {
        return image4path;
    }

    public void setImage4path(String image4path) {
        this.image4path = image4path;
    }

    public String getImage3path() {
        return image3path;
    }

    public void setImage3path(String image3path) {
        this.image3path = image3path;
    }

    public String getImage2path() {
        return image2path;
    }

    public void setImage2path(String image2path) {
        this.image2path = image2path;
    }

    public String getImage1path() {
        return image1path;
    }

    public void setImage1path(String image1path) {
        this.image1path = image1path;
    }
}
