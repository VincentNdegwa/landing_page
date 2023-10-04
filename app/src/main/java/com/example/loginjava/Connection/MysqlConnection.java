package com.example.loginjava.Connection;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    public interface ConnectionListener {
        void onConnection(Connection connection);

        void onFailure(String error);
    }

    public void getConnection(ConnectionListener listener) {
        new AsyncConnection(listener).execute();
    }

    public class AsyncConnection extends AsyncTask<Void, Void, Connection> {
        private ConnectionListener listener;

        AsyncConnection(ConnectionListener listener) {
            this.listener = listener;
        }

        @Override
        protected Connection doInBackground(Void... voids) {
            try {

                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java_login?"+"user=vincent&password=Vincent07$");
                return conn;
            } catch (SQLException sqe) {
                sqe.printStackTrace();
                System.out.println("SQLException: " + sqe.getMessage());
                System.out.println("SQLState: " + sqe.getSQLState());
                System.out.println("VendorError: " + sqe.getErrorCode());
                return null;
            }
        }

        @Override
        protected void onPostExecute(Connection connection) {
            if (connection != null) {
                listener.onConnection(connection);
            } else {
                listener.onFailure("Could not create connection to the database server.");
            }
        }
    }
}
