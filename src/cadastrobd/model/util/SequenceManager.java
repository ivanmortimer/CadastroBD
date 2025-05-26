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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    // Atributo
    private final ConectorBD conector;
    
    // Construtor padrão
    public SequenceManager() {
        this.conector = new ConectorBD();
    }

    public int getValue(String sequence_name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT NEXT VALUE FOR " + sequence_name;
        
        try (
            Connection connection = conector.getConnection();
            PreparedStatement ps = conector.getPrepared(connection, sql);
            ResultSet rs = conector.getSelect(ps)
        ) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Nenhum valor foi retornado pela sequência: " + sequence_name);
            }
        }
    }
}
