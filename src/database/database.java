package database;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.Date;

public class database implements dbInterface {
    private static Connection connect;
    public static Statement statmt;
    public static ResultSet resSet;
    private static String tableName = "note";
    private String[] fields = {"id", "heading", "text", "date"};
    private String fileDB;

    public database(String file) throws SQLException, ClassNotFoundException {
        this.connect = null;
        this.fileDB = file;
        Class.forName("org.sqlite.JDBC");
        this.connect = DriverManager.getConnection("jdbc:sqlite:" + this.fileDB);
        System.out.println("База данных подключена!");
        this.CreateTable();
    }

    protected void CreateTable() throws ClassNotFoundException, SQLException {
        String query = String.format("CREATE TABLE if not exists '%s' ('%s' INTEGER PRIMARY KEY AUTOINCREMENT, '%s' TEXT, '%s' TEXT, '%s' TIMESTAMP DEFAULT CURRENT_TIMESTAMP)",
                this.tableName,
                this.fields[0],
                this.fields[1],
                this.fields[2],
                this.fields[3]);
        statmt = connect.createStatement();
        statmt.execute(query);
        System.out.println("Таблица создана или она уже существует");
    }


    @Override
    public List<Dictionary> readAll() throws SQLException, ParseException {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Dictionary> result = new ArrayList<>();

        String query = String.format("SELECT * FROM %s",
                this.tableName);
        resSet = statmt.executeQuery(query);

        while(resSet.next())
        {
            Dictionary<String, Object> lst = new Hashtable<>();
            lst.put(fields[0], resSet.getInt(fields[0]));
            lst.put(fields[1], resSet.getString(fields[1]));
            lst.put(fields[2], resSet.getString(fields[2]));
            lst.put(fields[3], form.parse(resSet.getString(fields[3])));
            result.add(lst);
        }
        return result;
    }

    @Override
    public void insert(Dictionary<String, String> lst) throws SQLException {
        String query = String.format("INSERT INTO '%s' ('%s', '%s') VALUES ('%s', '%s')",
                this.tableName,
                this.fields[1],
                this.fields[2],
                lst.get(this.fields[1]),
                lst.get(this.fields[2]));
        statmt.execute(query);
    }

    @Override
    public void update(Dictionary<String, String> lst) throws SQLException {
        String query = String.format("UPDATE '%s' SET %s='%s', %s='%s' WHERE %s=%s",
                this.tableName,
                this.fields[1],
                lst.get(this.fields[1]),
                this.fields[2],
                lst.get(this.fields[2]),
                this.fields[0],
                lst.get(this.fields[0]));
        statmt.execute(query);
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE id=%d",
                this.tableName,
                id);
        statmt.execute(query);
    }

    @Override
    public Dictionary<String, Object> readId(int id) throws SQLException, ParseException {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Dictionary<String, Object> lst = new Hashtable<>();
        String query = String.format("SELECT * FROM %s WHERE id=%d",
                this.tableName,
                id);
        resSet = statmt.executeQuery(query);
        while(resSet.next())
        {
            lst.put(this.fields[0], resSet.getInt(this.fields[0]));
            lst.put(this.fields[1], resSet.getString(this.fields[1]));
            lst.put(this.fields[2], resSet.getString(this.fields[2]));
            lst.put(this.fields[3], form.parse(resSet.getString(this.fields[3])));
        }
        return lst;
    }

    public void CloseDB() throws SQLException {
        this.connect.close();
        this.statmt.close();
        this.resSet.close();

    }
}
