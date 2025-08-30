package edu.tudai.arq.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static volatile ConnectionManager instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/integrador1";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private ConnectionManager(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexión establecida");
        } catch (ClassNotFoundException  e) {
            System.err.println("No se encuentra el Driver de MySql");
            e.printStackTrace();
        } catch(SQLException e){
            System.err.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance(){
        if (instance==null){
              synchronized (ConnectionManager.class){
                  if(instance==null){
                      instance = new ConnectionManager();
                  }
              }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
