package hr.ferit.mdudjak.healthdiary;

/**
 * Created by Student on 11.5.2017..
 */

public class Symptom {
    String mArea, mDescription;
    int mIntensity, mID;
    public Symptom(String area, String description, int intensity){
        this.mArea=area;
        this.mDescription=description;
        this.mIntensity=intensity;

    }
    public Symptom(String area, String description, int intensity,int ID){
        this.mArea=area;
        this.mDescription=description;
        this.mIntensity=intensity;
        this.mID=ID;
    }
    public String getArea(){return mArea;}
    public String getDescription(){return mDescription;}
    public int getIntensity(){return mIntensity;}
    public int getID(){return mID;}
}
