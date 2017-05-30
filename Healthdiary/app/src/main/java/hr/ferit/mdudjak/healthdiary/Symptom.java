package hr.ferit.mdudjak.healthdiary;

/**
 * Created by Student on 11.5.2017..
 */

public class Symptom {
    String mArea, mDescription,mDate,mMonth,mYear;
    int mIntensity, mID;

    public Symptom(String area, String description, int intensity,String date, String month, String year){
        this.mArea=area;
        this.mDescription=description;
        this.mIntensity=intensity;
        this.mDate=date;
        this.mYear=year;
        this.mMonth=month;

    }
    public Symptom(String area, String description, int intensity,int ID,String date, String month, String year){
        this.mArea=area;
        this.mDescription=description;
        this.mIntensity=intensity;
        this.mID=ID;
        this.mDate=date;
        this.mYear=year;
        this.mMonth=month;
    }
    public String getArea(){return mArea;}
    public String getDescription(){return mDescription;}
    public int getIntensity(){return mIntensity;}
    public int getID(){return mID;}
    public String getDate(){return mDate;}
    public String getMonth(){return mMonth;}
    public String getYear(){return mYear;}
}
