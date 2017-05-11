package hr.ferit.mdudjak.healthdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Mario on 10.5.2017..
 */

public class DBHelper extends SQLiteOpenHelper {


    private static DBHelper mDBHelper = null;

    static final String CREATE_TABLE_SYMPTOM_AREAS ="CREATE TABLE " + Schema.TABLE_SYMPTOMS_AREAS + " (" +Schema.AREA + " TEXT);" ;
    static final String DROP_TABLE_SYMPTOM_AREAS ="DROP TABLE IF EXISTS "+ Schema.TABLE_SYMPTOMS_AREAS;
    static final String SELECT_ALL_SYMPTOM_AREAS ="SELECT " + Schema.AREA + " FROM " + Schema.TABLE_SYMPTOMS_AREAS;

    static final String CREATE_TABLE_SYMPTOM_DESCRIPTIONS="CREATE TABLE " + Schema.TABLE_SYMPTOMS_DESCRIPTIONS + " (" +Schema.DESCRIPTION + " TEXT);" ;
    static final String DROP_TABLE_SYMPTOM_DESCRIPTIONS ="DROP TABLE IF EXISTS "+ Schema.TABLE_SYMPTOMS_DESCRIPTIONS;
    static final String SELECT_ALL_SYMPTOM_DESCRIPTIONS ="SELECT " + Schema.DESCRIPTION + " FROM " + Schema.TABLE_SYMPTOMS_DESCRIPTIONS;

    static final String CREATE_TABLE_SYMPTOMS = "CREATE TABLE " + Schema.TABLE_SYMPTOMS +
            " (" + Schema.ID + " INTEGER PRIMARY KEY AUTO_INCREMENT, " + Schema.AREA + " TEXT," + Schema.DESCRIPTION + " TEXT," + Schema.INTENSITY + " INTEGER);";
    static final String DROP_TABLE_SYMPTOMS = "DROP TABLE IF EXISTS " + Schema.TABLE_SYMPTOMS;
    static final String SELECT_ALL_SYMPTOMS = "SELECT " + Schema.ID + "," + Schema.AREA + "," + Schema.DESCRIPTION + ","
            +
            Schema.INTENSITY + " FROM " + Schema.TABLE_SYMPTOMS;

    private DBHelper (Context context){
        super(context.getApplicationContext(),Schema.DATABASE_NAME,null,Schema.SCHEMA_VERSION);
    }
    public static synchronized DBHelper getInstance(Context context){
        if(mDBHelper == null){
            mDBHelper = new DBHelper(context);
        }
        return mDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SYMPTOM_AREAS);
        db.execSQL(CREATE_TABLE_SYMPTOM_DESCRIPTIONS);
        db.execSQL(CREATE_TABLE_SYMPTOMS);
        ContentValues areaValues = new ContentValues();
        ContentValues descriptionValues = new ContentValues();
        areaValues.put(Schema.AREA,"Head");
        db.insert(Schema.TABLE_SYMPTOMS_AREAS,Schema.AREA,areaValues);
        areaValues.put(Schema.AREA,"Neck");
        db.insert(Schema.TABLE_SYMPTOMS_AREAS,Schema.AREA,areaValues);
        areaValues.put(Schema.AREA,"Back");
        db.insert(Schema.TABLE_SYMPTOMS_AREAS,Schema.AREA,areaValues);
        descriptionValues.put(Schema.DESCRIPTION,"Buzzing");
        db.insert(Schema.TABLE_SYMPTOMS_DESCRIPTIONS,Schema.DESCRIPTION,descriptionValues);
        descriptionValues.put(Schema.DESCRIPTION,"Pricking");
        db.insert(Schema.TABLE_SYMPTOMS_DESCRIPTIONS,Schema.DESCRIPTION,descriptionValues);
        descriptionValues.put(Schema.DESCRIPTION,"Dull");
        db.insert(Schema.TABLE_SYMPTOMS_DESCRIPTIONS,Schema.DESCRIPTION,descriptionValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_SYMPTOM_AREAS);
        db.execSQL(DROP_TABLE_SYMPTOM_DESCRIPTIONS);
        db.execSQL(DROP_TABLE_SYMPTOMS);
        this.onCreate(db);
    }

    public static class Schema{
        private static final int SCHEMA_VERSION = 1;
        private static final String DATABASE_NAME = "healthDiaryDatabase.db";
        static final String TABLE_SYMPTOMS_AREAS = "symptom_areas";
        static final String TABLE_SYMPTOMS_DESCRIPTIONS = "symptom_descriptions";
        static final String AREA = "area";
        static final String DESCRIPTION ="description";
        static final String TABLE_SYMPTOMS ="symptoms";
        static final String INTENSITY ="intensity";
        static final String ID ="id";
    }

    public void insertArea(String area){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.AREA,area);
        SQLiteDatabase writeableDatabse = this.getWritableDatabase();
        writeableDatabse.insert(Schema.TABLE_SYMPTOMS_AREAS,Schema.AREA,contentValues);
        writeableDatabse.close();
    }

    public void insertSymptom(Symptom symptom){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.ID,symptom.getID());
        contentValues.put(Schema.AREA,symptom.getArea());
        contentValues.put(Schema.DESCRIPTION,symptom.getDescription());
        contentValues.put(Schema.INTENSITY,symptom.getIntensity());
        SQLiteDatabase writableDatabse = this.getWritableDatabase();
        writableDatabse.insert(Schema.TABLE_SYMPTOMS,Schema.AREA,contentValues);  //Provjeriti columnHack, je li pametno ID staviti na null?
        writableDatabse.close();
    }


    public void insertDescription(String description){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.AREA,description);
        SQLiteDatabase writeableDatabse = this.getWritableDatabase();
        writeableDatabse.insert(Schema.TABLE_SYMPTOMS_DESCRIPTIONS,Schema.DESCRIPTION,contentValues);
        writeableDatabse.close();
    }

    public ArrayList<String> getAllAreas(){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor areaCursor = writableDatabase.rawQuery(SELECT_ALL_SYMPTOM_AREAS,null);
        ArrayList<String> areas = new ArrayList<String>();
        if (areaCursor.moveToFirst()) {
                do {
                    String area = areaCursor.getString(0);
                    areas.add(area);
                } while (areaCursor.moveToNext());
        }
        areaCursor.close();
        writableDatabase.close();
        return areas;
    }

    public ArrayList<String> getAllDescriptions(){

        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor descriptionCursor = writableDatabase.rawQuery(SELECT_ALL_SYMPTOM_DESCRIPTIONS,null);
        ArrayList<String> descriptions = new ArrayList<String>();
        if(descriptionCursor.moveToFirst()){
            do{
                String description = descriptionCursor.getString(0);
                descriptions.add(description);
            }while(descriptionCursor.moveToNext());
        }
        descriptionCursor.close();
        writableDatabase.close();
        return descriptions;
    }

    public ArrayList<Symptom> getAllSymptoms(){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor taskCursor = writableDatabase.rawQuery(SELECT_ALL_SYMPTOMS,null);
        ArrayList<Symptom> symptoms = new ArrayList<>();
        if(taskCursor.moveToFirst()){
            do{
                int ID = taskCursor.getInt(0);
                String area = taskCursor.getString(1);
                String description = taskCursor.getString(2);
                int intensity = taskCursor.getInt(3);
                symptoms.add(new Symptom(area,description,intensity,ID));
            }while(taskCursor.moveToNext());
        }
        taskCursor.close();
        writableDatabase.close();
        return symptoms;
    }

}
