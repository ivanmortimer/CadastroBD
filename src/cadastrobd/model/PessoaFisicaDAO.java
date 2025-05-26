/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ivan
 */
public class PessoaFisicaDAO {
    // Atributos
    ConectorBD conector;

    // Construtor padrão
    public PessoaFisicaDAO() {
        conector = new ConectorBD();
    }

    // Métodos do tipo 'getter'
    public PessoaFisica getPessoa(int id) throws ClassNotFoundException, SQLException {
        PessoaFisica pf = null;
        String sql_statement = 
                "SELECT * FROM dbo.Pessoa p " +
                "INNER JOIN dbo.PessoaFisica pf ON (p.idPessoa = pf.idPessoa) " +
                "WHERE idPessoa = ?";

        try (Connection connection = conector.getConnection();
             PreparedStatement ps = conector.getPrepared(connection, sql_statement)) {
            ps.setInt(1, id);

            try (ResultSet rs = conector.getSelect(ps)) {
                if (rs.next()) {
                    pf = new PessoaFisica(rs.getInt("idPessoa"), rs.getString("cpf"), rs.getString("nome"),
                                          rs.getString("logradouro"), rs.getString("cidade"), rs.getString("estado"), 
                                          rs.getString("telefone"), rs.getString("email"));
                } else {
                    throw new SQLException("Pessoa física com id " + id + " não encontrada.");
                }
            }
        } 
        return pf;
    }
    public List<PessoaFisica> getPessoas() throws ClassNotFoundException, SQLException {
        List<PessoaFisica> lista_pf = new ArrayList<>();
        String sql_statement =
                "SELECT * FROM dbo.Pessoa p " +
                "INNER JOIN dbo.PessoaFisica pf ON (p.idPessoa = pf.idPessoa)";

        try (Connection connection = conector.getConnection();
             PreparedStatement ps = conector.getPrepared(connection, sql_statement)) {
            try (ResultSet rs = conector.getSelect(ps)) {
                while(rs.next()) {
                    lista_pf.add(new PessoaFisica(rs.getInt("idPessoa"), rs.getString("cpf"), rs.getString("nome"),
                                                  rs.getString("logradouro"), rs.getString("cidade"), rs.getString("estado"),
                                                  rs.getString("telefone"), rs.getString("email")));

                }
            }
        }
        return lista_pf;
    }

    // Métodos para executar operações CRUD
    public void incluir(PessoaFisica pf) throws ClassNotFoundException, SQLException {
        String sql_statement_1 = "INSERT INTO dbo.Pessoa VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql_statement_2 = "INSERT INTO dbo.Pessoa_Fisica VALUES (?, ?)";

        try (Connection connection = conector.getConnection()) {
            try (PreparedStatement ps = conector.getPrepared(connection, sql_statement_1)) { 
                ps.setInt(1, pf.getId());
                ps.setString(2, pf.getNome());
                ps.setString(3, pf.getLogradouro());
                ps.setString(4, pf.getCidade());
                ps.setString(5, pf.getEstado());
                ps.setString(6, pf.getTelefone());
                ps.setString(7, pf.getEmail());
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conector.getPrepared(connection, sql_statement_2)) { 
                ps.setInt(1, pf.getId());
                ps.setString(2, pf.getCPF());
                ps.executeUpdate();
            }
        }
    }
    public void alterar(PessoaFisica pf) throws ClassNotFoundException, SQLException {
        String sql_statement_1 =
                "UPDATE dbo.Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? " +
                "WHERE idPessoa = ?";
        String sql_statement_2 =
                "UPDATE dbo.Pessoa_Fisica SET cpf = ? " +
                "WHERE idPessoa = ?";                
                
        try (Connection connection = conector.getConnection()) {
            try (PreparedStatement ps = conector.getPrepared(connection, sql_statement_1)) { 
                ps.setString(1, pf.getNome());
                ps.setString(2, pf.getLogradouro());
                ps.setString(3, pf.getCidade());
                ps.setString(4, pf.getEstado());
                ps.setString(5, pf.getTelefone());
                ps.setString(6, pf.getEmail());
                ps.setInt(7, pf.getId());
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conector.getPrepared(connection, sql_statement_2)) { 
                ps.setString(1, pf.getCPF());
                ps.setInt(2, pf.getId());
                ps.executeUpdate();
            }
        }
    }
    public void excluir(PessoaFisica pf) throws ClassNotFoundException, SQLException {
        String sql_statement_1 = "DELETE FROM dbo.Pessoa_Fisica WHERE idPessoa = ?";
        String sql_statement_2 = "DELETE FROM dbo.Pessoa WHERE idPessoa = ?";

        try (Connection connection = conector.getConnection()) {
            try (PreparedStatement ps = conector.getPrepared(connection, sql_statement_1)) {
                ps.setInt(1, pf.getId());
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conector.getPrepared(connection, sql_statement_2)) {
                ps.setInt(1, pf.getId());
                ps.executeUpdate();
            }
        }
    }
}
