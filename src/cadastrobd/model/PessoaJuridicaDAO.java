/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class PessoaJuridicaDAO {
    // Atributos
    private final ConectorBD conector;

    // Construtor padrão
    public PessoaJuridicaDAO() {
        conector = new ConectorBD();
    }

    // Métodos do tipo 'getter'
    public PessoaJuridica getPessoa(int id) throws ClassNotFoundException, SQLException {
        PessoaJuridica pj = null;
        String sql_statement = 
                "SELECT * FROM dbo.Pessoa p " +
                "INNER JOIN dbo.PessoaJuridica pj ON (p.idPessoa = pj.idPessoa) " +
                "WHERE idPessoa = ?";

        try (Connection connection = conector.getConnection();
             PreparedStatement ps = conector.getPrepared(connection, sql_statement)) {
            ps.setInt(1, id);

            try (ResultSet rs = conector.getSelect(ps)) {
                if (rs.next()) {
                    pj = new PessoaJuridica(rs.getInt("idPessoa"), rs.getString("cnpj"), rs.getString("nome"),
                                          rs.getString("logradouro"), rs.getString("cidade"), rs.getString("estado"), 
                                          rs.getString("telefone"), rs.getString("email"));
                } else {
                    throw new SQLException("Pessoa jurídica com id " + id + " não encontrada.");
                }
            }
        } 
        return pj;
    }
    public List<PessoaJuridica> getPessoas() throws ClassNotFoundException, SQLException {
        List<PessoaJuridica> lista_pj = new ArrayList<>();
        String sql_statement =
                "SELECT * FROM dbo.Pessoa p " +
                "INNER JOIN dbo.PessoaJuridica pj ON (p.idPessoa = pj.idPessoa)";

        try (Connection connection = conector.getConnection();
             PreparedStatement ps = conector.getPrepared(connection, sql_statement)) {
            try (ResultSet rs = conector.getSelect(ps)) {
                while(rs.next()) {
                    lista_pj.add(new PessoaJuridica(rs.getInt("idPessoa"), rs.getString("cnpj"), rs.getString("nome"),
                                                  rs.getString("logradouro"), rs.getString("cidade"), rs.getString("estado"),
                                                  rs.getString("telefone"), rs.getString("email")));

                }
            }
        }
        return lista_pj;
    }

    // Métodos para executar operações CRUD
    public void incluir(PessoaJuridica pj) throws ClassNotFoundException, SQLException {
        String sql_statement_1 = "INSERT INTO dbo.Pessoa VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql_statement_2 = "INSERT INTO dbo.Pessoa_Juridica VALUES (?, ?)";

        try (Connection connection = conector.getConnection()) {
            try (PreparedStatement ps = conector.getPrepared(connection, sql_statement_1)) { 
                ps.setInt(1, pj.getId());
                ps.setString(2, pj.getNome());
                ps.setString(3, pj.getLogradouro());
                ps.setString(4, pj.getCidade());
                ps.setString(5, pj.getEstado());
                ps.setString(6, pj.getTelefone());
                ps.setString(7, pj.getEmail());
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conector.getPrepared(connection, sql_statement_2)) { 
                ps.setInt(1, pj.getId());
                ps.setString(2, pj.getCNPJ());
                ps.executeUpdate();
            }
        }
    }
    public void alterar(PessoaJuridica pj) throws ClassNotFoundException, SQLException {
        String sql_statement_1 =
                "UPDATE dbo.Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? " +
                "WHERE idPessoa = ?";
        String sql_statement_2 =
                "UPDATE dbo.Pessoa_Juridica SET cnpj = ? " +
                "WHERE idPessoa = ?";                
                
        try (Connection connection = conector.getConnection()) {
            try (PreparedStatement ps = conector.getPrepared(connection, sql_statement_1)) { 
                ps.setString(1, pj.getNome());
                ps.setString(2, pj.getLogradouro());
                ps.setString(3, pj.getCidade());
                ps.setString(4, pj.getEstado());
                ps.setString(5, pj.getTelefone());
                ps.setString(6, pj.getEmail());
                ps.setInt(7, pj.getId());
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conector.getPrepared(connection, sql_statement_2)) {
                ps.setString(1, pj.getCNPJ());
                ps.setInt(2, pj.getId());
                ps.executeUpdate();
            }
        }
    }
    public void excluir(PessoaJuridica pj) throws ClassNotFoundException, SQLException {
        String sql1 = "DELETE FROM dbo.Pessoa_Juridica WHERE idPessoa = ?";
        String sql2 = "DELETE FROM dbo.Pessoa WHERE idPessoa = ?";

        try (Connection connection = conector.getConnection()) {
            try (PreparedStatement ps = conector.getPrepared(connection, sql1)) {
                ps.setInt(1, pj.getId());
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conector.getPrepared(connection, sql2)) {
                ps.setInt(1, pj.getId());
                ps.executeUpdate();
            }
        }
    }
}
