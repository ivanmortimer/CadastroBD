/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Ivan
 */
public class PessoaJuridica extends Pessoa {
    // Atributo extra
    protected String cnpj;
    
    // Métodos construtores
    public PessoaJuridica() {
        super();
    }
    public PessoaJuridica(int id) {
        super(id);
    }
    public PessoaJuridica(String cnpj, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        super(nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }
    public PessoaJuridica(int id, String cnpj, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }
    
    // Métodos regulares da classe
    
    // Método tipo "exibir", que mostra no console de saída
    // todos os atributos de um objeto do tipo "PessoaJuridica".
    @Override
    public void exibir() {
        System.out.println("Id: " + Integer.toString(this.id) +
                           "\nCNPJ: " + this.cnpj +
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
    public String getCNPJ() {
        return this.cnpj;
    }
    
    // Métodos tipo 'setter'
    public void setCNPJ(String cnpj) {
        this.cnpj = cnpj;
    }
}
