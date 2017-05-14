package hr.ferit.mdudjak.healthdiary;

/**
 * Created by Mario on 13.5.2017..
 */

public class BodyLog {
    int mHeartRate, mUpperPressure, mLowerPressure, mID;
    float mWeight, mBloodSugar;
    public BodyLog(float weight, int heartRate, float bloodSugar, int upperPressure, int lowerPressure){
        this.mWeight=weight;
        this.mHeartRate=heartRate;
        this.mBloodSugar=bloodSugar;
        this.mUpperPressure=upperPressure;
        this.mLowerPressure=lowerPressure;
    }
    public BodyLog(float weight, int heartRate, float bloodSugar, int upperPressure, int lowerPressure,int ID){
        this.mWeight=weight;
        this.mHeartRate=heartRate;
        this.mBloodSugar=bloodSugar;
        this.mUpperPressure=upperPressure;
        this.mLowerPressure=lowerPressure;
        this.mID=ID;
    }
    public Float getWeight(){return mWeight;}
    public int getHeartRate(){return mHeartRate;}
    public Float getBloodSugar(){return mBloodSugar;}
    public int getUpperPressure(){ return mUpperPressure;}
    public int getLowerPressure(){return mLowerPressure;}
    public int getID(){ return mID;}
}
