import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SQLParser parser = new SQLParser();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter SQL commands (type 'exit' to quit):");
        while (true) {
            System.out.print("> ");
            String sql = scanner.nextLine();
            if (sql.equalsIgnoreCase("exit")) {
                break;
            }
            parser.execute(sql);

            // For demonstration purposes, print table contents after each command
            String tableName = sql.split(" ")[2]; // crude way to get table name
            parser.printTable(tableName);
        }
        scanner.close();
    }
}

