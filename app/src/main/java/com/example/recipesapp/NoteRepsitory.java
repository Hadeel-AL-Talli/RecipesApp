package com.example.recipesapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

public class NoteRepsitory {
    private NoteDoe noteDoe;
    private LiveData <List<Note>> allNotes;
    public NoteRepsitory(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDoe = database.noteDao();
        allNotes = noteDoe.getAllNotes();
    }
    public void insert(Note note){
        new InsertNoteAsyncTask(noteDoe).execute(note);


    }
    public void update(Note note){
        new UpdateNoteAsyncTask(noteDoe).execute(note);

    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDoe).execute(note);

    }
    public void deleteAllNotes(){
        new DeleteallNotesAsyncTask(noteDoe).execute();

    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }




    public static class InsertNoteAsyncTask extends AsyncTask<Note , Void , Void>{
       private NoteDoe noteDoe;

       private InsertNoteAsyncTask(NoteDoe noteDoe){
           this.noteDoe = noteDoe;

       }
        @Override
        protected Void doInBackground(Note... notes) {
           noteDoe.insert(notes[0]);

            return null;
        }
    }

    public static class UpdateNoteAsyncTask extends AsyncTask<Note , Void , Void>{
        private NoteDoe noteDoe;

        private UpdateNoteAsyncTask(NoteDoe noteDoe){
            this.noteDoe = noteDoe;

        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDoe.update(notes[0]);

            return null;
        }
    }
    public static class DeleteNoteAsyncTask extends AsyncTask<Note , Void , Void>{
        private NoteDoe noteDoe;

        private DeleteNoteAsyncTask(NoteDoe noteDoe){
            this.noteDoe = noteDoe;

        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDoe.delete(notes[0]);

            return null;
        }
    }

    public static class DeleteallNotesAsyncTask extends AsyncTask<Void , Void , Void>{
        private NoteDoe noteDoe;

        private DeleteallNotesAsyncTask(NoteDoe noteDoe){
            this.noteDoe = noteDoe;

        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDoe.deleteAllNotes();

            return null;
        }
    }
}
