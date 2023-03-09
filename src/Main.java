import Controllers.NotesController;
import Views.View;
import database.database;

import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        database db = new database("Note.db");
        NotesController notes = new NotesController(db);
        View note = new View(notes);
        note.Run();
    }
}