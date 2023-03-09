package Controllers;

import Notes.Note;
import database.database;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class NotesController implements NoteControllersInterface {
    private database db;
    private String[] fields = {"id", "heading", "text", "date"};


    public NotesController(database db) {
        this.db = db;
    }


    @Override
    public void InsertNote(Note note) throws SQLException {
        Dictionary<String, String> lst = new Hashtable<>();
        lst.put(this.fields[1], note.getHeading());
        lst.put(this.fields[2], note.getText());
        db.insert(lst);
    }

    @Override
    public void DeleteNote(int id) throws SQLException {
        db.delete(id);
    }

    @Override
    public void UpdateNote(Note note) throws SQLException {
        Dictionary<String, String> lst = new Hashtable<>();
        lst.put(this.fields[0], Integer.toString(note.getID()));
        lst.put(this.fields[1], note.getHeading());
        lst.put(this.fields[2], note.getText());
        db.update(lst);
    }

    @Override
    public Note ReadNote(int id) throws SQLException, ParseException {
        Dictionary<String, Object> lst = db.readId(id);
        Note note = new Note((Integer) lst.get(this.fields[0]), (String) lst.get(this.fields[1]), (String) lst.get(this.fields[2]), (Date) lst.get(this.fields[3]));
        System.out.println("Label2");
        return note;
    }

    @Override
    public List<Note> ReadAll() throws SQLException, ParseException {
        List<Dictionary> lst = db.readAll();
        List<Note> result = new ArrayList<>();
        for(Dictionary line: lst)
        {
            Note obj = new Note((Integer) line.get(this.fields[0]), (String) line.get(this.fields[1]), (String) line.get(this.fields[2]), (java.util.Date) line.get(this.fields[3]));
            result.add(obj);
        }
        return result;
    }

    public void CloseDB() throws SQLException {
        this.db.CloseDB();
    }
}
