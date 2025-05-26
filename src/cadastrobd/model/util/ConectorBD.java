/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.util;

/**
 *
 * @author Ivan
 */

// Imports
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
        
public class ConectorBD {
    // Atributos
    protected String driver_class_name;
    protected String connection_url;
    protected String user;
    protected String password;

    // Métodos construtores padrão e completo, respectivamente
    public ConectorBD() {
        this.driver_class_name = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        this.connection_url = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true";
        this.user = "loja";
        this.password = "loja";
    }
    public ConectorBD(String driver_class_name, String connection_url) {
        this.driver_class_name = driver_class_name;
        this.connection_url = connection_url;
    }
    
    // Métodos para efetuar conexão, criar declaração (e declaração preparada),
    // e para obter os resultados uma "query" (i.e., uma busca)
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver_class_name);
        return DriverManager.getConnection(this.connection_url, this.user, this.password);
    }
    public Statement getStatement(Connection conn) throws ClassNotFoundException, SQLException {
        return conn.createStatement();
    }
    public PreparedStatement getPrepared(Connection conn, String sql_string) throws ClassNotFoundException, SQLException {
        return conn.prepareStatement(sql_string);
    }
    public ResultSet getSelect(Statement st, String sql_string) throws ClassNotFoundException, SQLException {
        return st.executeQuery(sql_string);
    }
    public ResultSet getSelect(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }

    // Métodos do tipo 'close', sobrecarregados, para fechar connexão,  declaração,
    // declaração preparada, e resultado de query (i.e., busca)
    public void close(Connection conn) throws SQLException {
        conn.close();
    }
    public void close(Statement st) throws SQLException {
        st.close();
    }
    public void close(PreparedStatement ps) throws SQLException {
        ps.close();
    }
    public void close(ResultSet rs) throws SQLException {
        rs.close();
    }
    
    // Métodos do tipo 'getter' relacionados a atributos da classe 'ConectorBD'
    public String getDriverClassName() {
        return this.driver_class_name;
    }
    public String getConnectionURL() {
        return this.connection_url;
    }
    
    // Métodos do tipo 'setter' relacionados a atributos da classe 'ConectorBD'
    public void setDriverClassName(String driver_class_name) {
        this.driver_class_name = driver_class_name;
    }
    public void setConnectionURL(String connection_url) {
        this.connection_url = connection_url;
    }
}
