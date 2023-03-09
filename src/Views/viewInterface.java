package Views;

import java.sql.SQLException;
import java.text.ParseException;

public interface viewInterface {
    void Insert() throws SQLException;
    void Read() throws SQLException, ParseException;
    void ReadAll() throws SQLException, ParseException;
    void Delete() throws SQLException;
    void Update() throws SQLException;
    void ErrorInput();
}
