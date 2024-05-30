import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// need to store , view of table with printf 
public class Table {
    private String name;
    private List<String> columns;
    private List<Map<String, String>> rows;

    public Table(String name, List<String> columns) {
        this.name = name;
        this.columns = columns;
        this.rows = new ArrayList<>();
    }

    public void insertRow(Map<String, String> row) {
        rows.add(row);
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

    public String getName() {
        return name;
    }

    public List<String> getColumns() {
        return columns;
    }
}
