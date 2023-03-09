package Views;

import Controllers.NotesController;
import Notes.Note;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class View implements viewInterface {
    protected NotesController notes;

    public View(NotesController notes) {
        this.notes = notes;
    }

    public enum mode {
        EXIT,
        INSERT,
        READ,
        READALL,
        DELETE,
        UPDATE;
    }
    public void Run()
    {
        mode com;
        while (true)
        {
            String command = prompt("Введите команду: ");

            try {
                com = mode.valueOf(command.toUpperCase());
                switch(com)
                {
                    case EXIT: {
                        this.notes.CloseDB();
                        return;
                    }
                    case INSERT:
                        this.Insert();
                        break;
                    case READ:
                        this.Read();
                        break;
                    case READALL:
                        this.ReadAll();
                        break;
                    case DELETE:
                        this.Delete();
                        break;
                    case UPDATE:
                        this.Update();
                        break;
                }
            }catch (Exception e) {
                System.out.println("Error: " + e);
            }

        }


    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    @Override
    public void Insert() throws SQLException {
        String head = this.prompt("Название: ");
        String text = this.prompt("Текст: ");

        this.notes.InsertNote(new Note(head, text));
    }

    @Override
    public void Read() throws SQLException, ParseException {
        int id = Integer.parseInt(this.prompt("Введите номер id записи: "));
        System.out.print(this.notes.ReadNote(id));
    }

    @Override
    public void ReadAll() throws SQLException, ParseException {
        List<Note> lst = this.notes.ReadAll();
        //System.out.println(lst);
        for (Note note: lst) System.out.println(note);
    }

    @Override
    public void Delete() throws SQLException {
        int id = Integer.parseInt(this.prompt("Введите id записи для удаления: "));
        this.notes.DeleteNote(id);
    }

    @Override
    public void Update() throws SQLException {
        int id = Integer.parseInt(this.prompt("Введите id редактируемой записи: "));
        String heading = this.prompt("Введите название записи: ");
        String text = this.prompt("Введите текст: ");
        this.notes.UpdateNote(new Note(id, heading, text));
    }

    @Override
    public void ErrorInput() {
        System.out.println("Вы ввели неизвестное значение! Попробуйте снова.");
    }
}
