package edu.tudai.arq.factory;

import edu.tudai.arq.dao.*;
import edu.tudai.arq.repository.mysql.MySQLDAOFactory;

public abstract class DaoFactory {

    private static volatile DaoFactory instance;

    public static DaoFactory getInstance(DBType type){
        if (instance==null){
            synchronized (DaoFactory.class){
                if (instance==null){
                    switch (type){
                        case MYSQL: instance = new MySQLDAOFactory();
                            break;
                        default:
                            throw new IllegalArgumentException("DTBType no soportado: " + type);
                    }
                }
            }

        }
        return instance;
    }

    public static DaoFactory getInstance(){
        String v = System.getProperty("db.type", "MYSQL");
        DBType type = DBType.valueOf(v.toUpperCase());
        return getInstance(type);
    }

    public abstract ClienteDAO getClienteDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract FacturaProductoDAO getFacturaProductoDAO();
    public abstract ProductoDAO getProductoDAO();

}
