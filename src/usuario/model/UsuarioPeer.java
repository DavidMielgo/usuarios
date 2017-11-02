package usuario.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import usuario.beans.Usuario;

public class UsuarioPeer {
	
  public static ArrayList<Usuario> searchBooks(DataManager dataManager,
      String keyword) {
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    Connection connection = dataManager.getConnection();
    if (connection != null) {
      try {
        Statement s = connection.createStatement();
        String sql = "select * from usuarios";
        try {
          ResultSet rs = s.executeQuery(sql);
          try {
            while (rs.next()) {
              Usuario user = new Usuario();
              user.setId(rs.getString(1));
              user.setUser(rs.getString(2));
              user.setPass(rs.getString(3));
              usuarios.add(user);
              }
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
    return usuarios;
    }
  }

