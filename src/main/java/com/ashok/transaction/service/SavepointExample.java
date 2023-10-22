package com.ashok.transaction.service;

import java.sql.*;

public class SavepointExample {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password")) {

            // Turn off auto-commit to manage transactions manually
            connection.setAutoCommit(false);

            // Create a Savepoint
            Savepoint savepoint = connection.setSavepoint("mySavepoint");

            try {
                // Perform database operations within the transaction
                executeUpdate(connection, "INSERT INTO users (id, name, email) VALUES (1, 'John', 'john@example.com')");
                executeUpdate(connection, "INSERT INTO users (id, name, email) VALUES (2, 'Jane', 'jane@example.com')");

                // Simulate an exception or error
                throw new SQLException("Simulating an error");
            } catch (SQLException ex) {
                // Rollback to the savepoint if an exception occurs
                connection.rollback(savepoint);
                System.out.println("Rolled back to the savepoint: " + savepoint.getSavepointName());
            }

            // Additional logic after the savepoint (this part will execute)
            executeUpdate(connection, "INSERT INTO users (id, name, email) VALUES (3, 'Alice', 'alice@example.com')");

            // Commit the transaction
            connection.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Helper method to execute an update query
    private static void executeUpdate(Connection connection, String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }


//    The code starts a transaction and creates a savepoint named "mySavepoint."
//
//    Two insert operations are performed inside the transaction, adding two records to the "users" table.
//
//    The SQLException is thrown, simulating an error. Since there is a catch block for the exception, the control flow enters the catch block.
//
//    In the catch block, connection.rollback(savepoint) is called to roll back the transaction to the savepoint. This means that the additional
//    insert operation for "Alice" is undone, and the database state is restored to the point just after the savepoint.
//
//    After rolling back to the savepoint, the catch block prints "Rolled back to the savepoint: mySavepoint."
//
//    The code then proceeds to execute the additional insert operation for "Alice" again.
//
//        Finally, the transaction is committed with connection.commit(), which means the two records inserted before the savepoint
//        ("John" and "Jane") will be saved to the database, and the record for "Alice" will also be saved.
//
//    In summary, the rollback to the savepoint undoes changes made after the savepoint, and any logic executed after the savepoint will remain
//    unaffected by the rollback and will be committed to the database.
}
