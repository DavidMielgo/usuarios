package usuario.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import usuario.beans.Usuario;

public class UsuarioPeer {
	
  public static boolean getUser(DataManager dataManager,
      String user, String pass) {
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    Connection connection = dataManager.getConnection();
    boolean isIn= false;
    if (connection != null) {
      try {
        Statement s = connection.createStatement();
        String sql = "select usuario from usuarios where usuario = '" + user + "' and clave = '" + pass + "'";
        System.out.println(sql);
        try {
          ResultSet rs = s.executeQuery(sql);
          try {
            if(rs.next() != false) {
            	isIn = true;
              }
            System.out.println(isIn);
            }
          finally { rs.close(); }
          }
        finally { s.close(); }
        }
      catch (SQLException e) {
        System.out.println("Could not search for Users:" + e.getMessage());
        }
      finally {
        dataManager.putConnection(connection);
        }
      }
    return isIn;
    }
  }

