import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class SQLParser {
    private Map<String, Table> tables = new HashMap<>();

    public void execute(String sql) {
        sql = sql.trim();
        if (sql.startsWith("CREATE TABLE")) {
            createTable(sql);
        } else if (sql.startsWith("INSERT INTO")) {
            insertInto(sql);
        } else {
            System.out.println("Unsupported SQL statement");
        }
    }

    private void createTable(String sql) {
        Pattern pattern = Pattern.compile("CREATE TABLE (\\w+) \\((.+)\\)");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group(1);
            String columnsStr = matcher.group(2);
            List<String> columns = Arrays.asList(columnsStr.split(", "));
            tables.put(tableName, new Table(tableName, columns));
            System.out.println("Table " + tableName + " created.");
        }
    }

    private void insertInto(String sql) {
        Pattern pattern = Pattern.compile("INSERT INTO (\\w+) \\((.+)\\) VALUES \\((.+)\\)");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group(1);
            String columnsStr = matcher.group(2);
            String valuesStr = matcher.group(3);

            List<String> columns = Arrays.asList(columnsStr.split(", "));
            List<String> values = Arrays.asList(valuesStr.split(", "));

            Map<String, String> row = new HashMap<>();
            for (int i = 0; i < columns.size(); i++) {
                row.put(columns.get(i), values.get(i));
            }

            Table table = tables.get(tableName);
            if (table != null) {
                table.insertRow(row);
                System.out.println("Row inserted into " + tableName + ".");
            } else {
                System.out.println("Table " + tableName + " does not exist.");
            }
        }
    }

    public void printTable(String tableName) {
        Table table = tables.get(tableName);
        if (table != null) {
            System.out.println("Table: " + tableName);
            System.out.println("Columns: " + String.join(", ", table.getColumns()));
            for (Map<String, String> row : table.getRows()) {
                System.out.println(row);
            }
        } else {
            System.out.println("Table " + tableName + " does not exist.");
        }
    }
}
