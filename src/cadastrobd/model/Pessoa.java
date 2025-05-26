/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

// Imports
import cadastrobd.model.util.SequenceManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan
 */
public class Pessoa {
    // Atributos (colunas no banco de dados "loja")
    protected SequenceManager sequence_manager;
    protected int id;
    protected String nome;
    protected String logradouro;
    protected String cidade;
    protected String estado;
    protected String telefone;
    protected String email;
    
    // Métodos construtores
    public Pessoa() {
        try {
            this.id = new SequenceManager().getValue("dbo.Seq_idPessoa");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Pessoa(int id) {
        this.id = id;
    }
    public Pessoa(String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        try {
            this.id = new SequenceManager().getValue("dbo.Seq_idPessoa");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade  = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }
    public Pessoa(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade  = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }
    
    // Métodos regulares da classe
    
    // Método tipo "exibir", que mostra no console de saída
    // todos os atributos de um objeto do tipo "Pessoa".
    public void exibir() {
        System.out.println("Id: " + Integer.toString(this.id) +
                           "\nNome: " + this.nome +
                           "\nLogradouro: " + this.logradouro +
                           "\nCidade: " + this.cidade +
                           "\nEstado: " + this.estado +
                           "\nTelefone: " + this.telefone +
                           "\nEmail: " + this.email +
                           "\n================================"
        );
    }
    
    // Métodos tipo 'getter'
    public int getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public String getLogradouro() {
        return this.logradouro;
    }
    public String getCidade() {
        return this.cidade;
    }
    public String getEstado() {
        return this.estado;
    }  
    public String getTelefone() {
        return this.telefone;
    }
    public String getEmail() {
        return this.email;
    }
    
    // Métodos tipo 'setter'
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }  
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
