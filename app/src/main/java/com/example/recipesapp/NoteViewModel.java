package com.example.recipesapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepsitory repsitory;
    private LiveData<List<Note>> allNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repsitory = new NoteRepsitory(application);
        allNotes  = repsitory.getAllNotes();
    }
    public void insert(Note note){
        repsitory.insert(note);
    }
    public void update(Note note){
        repsitory.update(note);
    }
    public void delete(Note note){
        repsitory.delete(note);
    }
    public void deleteAllNotes(){
        repsitory.deleteAllNotes();
    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}
