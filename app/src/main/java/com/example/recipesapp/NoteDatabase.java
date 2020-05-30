package com.example.recipesapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class,version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance ;
    public abstract NoteDoe noteDao ();
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private  static  class PopulateDbAsyncTask extends AsyncTask<Void ,Void ,Void >{
        private NoteDoe noteDoe;
        private PopulateDbAsyncTask(NoteDatabase db){
            noteDoe = db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDoe.insert(new Note("Title1","Description 1",1));
            noteDoe.insert(new Note("Title2","Description 2",2));
            noteDoe.insert(new Note("Title3","Description 3",3));
            return null;
        }
    }
}
