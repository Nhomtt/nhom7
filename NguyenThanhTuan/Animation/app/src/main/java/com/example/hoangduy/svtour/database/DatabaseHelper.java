package com.example.hoangduy.svtour.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import com.example.hoangduy.svtour.model.Image;
import com.example.hoangduy.svtour.model.Note;
import com.example.hoangduy.svtour.model.Tour;
import com.example.hoangduy.svtour.model.Video;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoangDuy on 5/8/2018.
 */

public class DatabaseHelper{

    // sử dụng database file lưu ở thư mục assets
    public static final String DATABASE_NAME = "SVTour.sqlite";
    public static final String DATABASE_LOCATION = "/databases/";
    private Context context;
    private SQLiteDatabase database;

    // constructter
    public DatabaseHelper(Context context) {
        this.context = context;
        openDatabase();
    }
    // mở database
    public SQLiteDatabase openDatabase() {
        File databasebFile = context.getDatabasePath(DATABASE_NAME);
        if (!databasebFile.exists()) {
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(databasebFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }
    // coppy database file qua database của app
    private void copyDatabase() throws IOException {
        try{
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DATABASE_NAME);
            String outputStrean = context.getApplicationInfo().dataDir+DATABASE_LOCATION+DATABASE_NAME;

            File file = new File(context.getApplicationInfo().dataDir+DATABASE_LOCATION);
            if(!file.exists()){
                file.mkdir();
            }

            OutputStream outputStream = new FileOutputStream(outputStrean);

            byte [] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff))>0)
            {
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    // HOME ACTIVITY///////////////////////////////////////////
    // lấy danh sách các tour
    public List<Tour> getListTour(){
        List<Tour> TourList = new ArrayList<>();
        database = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("select * from tours order by tour_id desc",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Tour tour = new Tour(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getBlob(3));
            TourList.add(tour);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return TourList;
    }

    // ADD ACTIVITY///////////////////////////////////////////
    // thêm tour có image
    public void insertTour(String tour_name, String tour_date, byte[] tour_avatar){
        // thêm tour
        database = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE,null);
        SQLiteStatement sqLiteStatement = database.compileStatement("insert into tours (tour_name, tour_date, tour_avatar) values (?,?,?)");
        sqLiteStatement.bindString(1, tour_name);
        sqLiteStatement.bindString(2, tour_date);
        sqLiteStatement.bindBlob(3, tour_avatar);
        sqLiteStatement.executeInsert();
        sqLiteStatement.close();
        database.close();
    }
    // thêm tour không image
    public void insertTourNoPic(String tour_name, String tour_date){
        // thêm tour
        database = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE,null);
        SQLiteStatement sqLiteStatement = database.compileStatement("insert into tours (tour_name, tour_date) values (?,?)");
        sqLiteStatement.bindString(1, tour_name);
        sqLiteStatement.bindString(2, tour_date);
        sqLiteStatement.executeInsert();
        sqLiteStatement.close();
        database.close();
    }
    // lấy id tour vừa thêmm
    public int getTourJustInsertId(){
        int tour_id = 0;
        database = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("select max(tour_id) from tours",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            tour_id = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return tour_id;
    }
    // thêm note
    public void insertNote(int tour_id, String note_title, String note_description) {
        database = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE,null);
        SQLiteStatement sqLiteStatement = database.compileStatement("insert into notes (tour_id, note_title,note_description) values (?,?,?)");
        sqLiteStatement.bindLong(1, tour_id);
        sqLiteStatement.bindString(2, note_title);
        sqLiteStatement.bindString(3, note_description);
        sqLiteStatement.execute();
        sqLiteStatement.close();
        database.close();
    }
    // thêm image
    public void insertImage(int tour_id,byte[] image_byte) {
        database = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE,null);
        SQLiteStatement sqLiteStatement = database.compileStatement("insert into images (tour_id,image_byte) values (?,?)");
        sqLiteStatement.bindLong(1, tour_id);
        sqLiteStatement.bindBlob(2, image_byte);
        sqLiteStatement.execute();
        sqLiteStatement.close();
        database.close();
    }
    // thêm video url
    public void insertVideo(int tour_id,String video_url) {
        database = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE,null);
        SQLiteStatement sqLiteStatement = database.compileStatement("insert into videos (tour_id,video_url) values (?,?)");
        sqLiteStatement.bindLong(1, tour_id);
        sqLiteStatement.bindString(2, video_url);
        sqLiteStatement.execute();
        sqLiteStatement.close();
        database.close();
    }

    // DETAIL ACTIVITY//////////////////////////////////////////
    // lấy các ảnh theo tour
    public ArrayList<Image> getListImageByTouId(int tour_id){
        ArrayList<Image> ImageList = new ArrayList<>();
        database = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("select * from images where tour_id="+tour_id,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Image image = new Image(cursor.getInt(0), cursor.getInt(1), cursor.getBlob(2));
            ImageList.add(image);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return ImageList;
    }
    // lấy các video theo tour
    public ArrayList<Video> getListVideoByTouId(int tour_id){
        ArrayList<Video> VideoList = new ArrayList<>();
        database = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("select * from videos where tour_id="+tour_id,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Video video = new Video(cursor.getInt(0), cursor.getInt(1), cursor.getString(2));
            VideoList.add(video);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return VideoList;
    }
    // lấy các video theo tour
    public ArrayList<Note> getListNoteByTouId(int tour_id){
        ArrayList<Note> NoteList = new ArrayList<>();
        database = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("select * from notes where tour_id="+tour_id,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Note note = new Note(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),cursor.getString(3));
            NoteList.add(note);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return NoteList;
    }
}
