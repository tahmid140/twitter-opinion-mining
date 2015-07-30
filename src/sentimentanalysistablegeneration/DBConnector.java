package sentimentanalysistablegeneration;


import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DBConnector {
    
     private static DBConnector instance;
    
     public static synchronized DBConnector getInstance() {
        if (instance == null) 
            instance = new DBConnector();        
        return instance;
     }
        
     
    public Connection connectTODB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/tdata","root","");
          //  JOptionPane.showMessageDialog(null, "Connection Establishment");
            return conn;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Connection Establishment Failed");
            return null;
        }
        
        
    }
    
}
