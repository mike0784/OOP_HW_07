package Controllers;

import Notes.Note;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface NoteControllersInterface {
    void InsertNote(Note note) throws SQLException;
    void DeleteNote(int id) throws SQLException;
    void UpdateNote(Note note) throws SQLException;
    Note ReadNote(int id) throws SQLException, ParseException;
    List<Note> ReadAll() throws SQLException, ParseException;
}
