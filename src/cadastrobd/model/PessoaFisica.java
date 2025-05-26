/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Ivan
 */
public class PessoaFisica extends Pessoa {
    // Atributo extra
    String cpf;
    
    // Métodos construtores
    public PessoaFisica() {
        super();
    }
    public PessoaFisica(int id) {
        super(id);
    }
    public PessoaFisica(String cpf, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        super(nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }
    public PessoaFisica(int id, String cpf, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }
    
    // Métodos regulares da classe
    
    // Método tipo "exibir", que mostra no console de saída
    // todos os atributos de um objeto do tipo "PessoaFisica".
    @Override
    public void exibir() {
        System.out.println("Id: " + Integer.toString(this.id) +
                           "\nCPF: " + this.cpf +
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
    public String getCPF() {
        return this.cpf;
    }
    
    // Métodos tipo 'setter'
    public void setCPF(String cpf) {
        this.cpf = cpf;
    }
}
