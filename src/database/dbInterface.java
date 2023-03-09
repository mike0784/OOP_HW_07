package database;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public interface dbInterface {
    List<Dictionary> readAll() throws SQLException, ParseException;
    void insert(Dictionary<String, String> lst) throws SQLException;
    void update(Dictionary<String, String> lst) throws SQLException;
    void delete(int id) throws SQLException;
    Dictionary<String, Object> readId(int id) throws SQLException, ParseException;
}
