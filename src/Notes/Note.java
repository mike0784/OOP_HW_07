package Notes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements nodeInterface {
    protected int id;
    protected String heading;
    protected String text;
    protected Date date;


    public Note(int id, String heading, String text, Date date) {
        this.id = id;
        this.heading = heading;
        this.text = text;
        this.date = date;
    }

    public Note(String heading, String text) {
        this.heading = heading;
        this.text = text;
    }

    public Note(int id, String heading, String text) {
        this.id = id;
        this.heading = heading;
        this.text = text;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getHeading() {
        return this.heading;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nНазвание: %s\nТекст: %s\nДата: %s\n", this.id, this.heading, this.text, this.date);
    }
}
