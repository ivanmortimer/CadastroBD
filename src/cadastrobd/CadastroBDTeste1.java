/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastrobd.model.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan
 */
// 6. Criar uma classe principal de testes com o nome CadastroBDTeste(1), efetuando as
//    operações seguintes no método main:
public class CadastroBDTeste1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // a. Instanciar uma pessoa física e persistir no banco de dados.
        PessoaFisica jose_silva = new PessoaFisica("123456789", "José Silva", "Rua 10, casa 5, Lapinha da Serra", "Santana do Riacho", "MG", "5555-5555", "josesilva@santana.com");
        PessoaFisicaDAO pf_dao = new PessoaFisicaDAO();
        try {
            pf_dao.incluir(jose_silva);
            System.out.println("\nPessoa física instanciada e persistida no banco de dados 'loja':");
            PessoaFisica js = pf_dao.getPessoa(jose_silva.getId());
            js.exibir();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroBDTeste1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // b. Alterar os dados da pessoa física no banco.
        jose_silva.setLogradouro("Rua 12, casa 6, Lapinha da Serra");
        jose_silva.setTelefone("6666-6666");
        jose_silva.setEmail("jose_silva@santana.com");
        try {
            System.out.println("\nPessoa física alterada no banco de dados 'loja':");
            PessoaFisica js = pf_dao.getPessoa(jose_silva.getId());
            js.exibir();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroBDTeste1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            pf_dao.alterar(jose_silva);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroBDTeste1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // c. Consultar todas as pessoas físicas do banco de dados e listar no console.
        List<PessoaFisica> lista_pf;
        try {
            lista_pf = pf_dao.getPessoas();
            System.out.println("\nLista de pessoas físicas presentes no banco de dados 'loja':");
            for (PessoaFisica pf : lista_pf) {
                pf.exibir();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroBDTeste1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // d. Excluir a pessoa física criada anteriormente no banco.
        try {
            String nome = jose_silva.getNome();
            int id = jose_silva.getId();
            pf_dao.excluir(jose_silva);
            System.out.println("\nPessoa física '" + nome + "' (id: " + id + ") foi excluída com sucesso  do banco de dados 'loja'.\n");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroBDTeste1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // e. Instanciar uma pessoa jurídica e persistir no banco de dados.
        PessoaJuridica companhia_do_ferro = new PessoaJuridica("12345678901234", "Companhia do Ferro", "Rua 20, prédio 10, Cardoso", "Itabirito", "MG", "7575-7575", "companhiadoferro@itabirito.com");
        PessoaJuridicaDAO pj_dao = new PessoaJuridicaDAO();
        try {
            pj_dao.incluir(companhia_do_ferro);
            System.out.println("\nPessoa jurídica instanciada e persistida no banco de dados 'loja':");
            PessoaJuridica cdf = pj_dao.getPessoa(companhia_do_ferro.getId());
            cdf.exibir();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroBDTeste1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // f. Alterar os dados da pessoa jurídica no banco.
        companhia_do_ferro.setLogradouro("Rua 22, prédio 11, São José");
        companhia_do_ferro.setTelefone("8686-8686");
        companhia_do_ferro.setEmail("companhia_do_ferro@itabirito.com");
        try {
            System.out.println("\nPessoa jurídica alterada no banco de dados 'loja':");
            PessoaJuridica cdf = pj_dao.getPessoa(companhia_do_ferro.getId());
            cdf.exibir();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroBDTeste1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // g. Consultar todas as pessoas jurídicas do banco e listar no console.
        List<PessoaJuridica> lista_pj;
        try {
            lista_pj = pj_dao.getPessoas();
            System.out.println("\nLista de pessoas jurídicas presentes no banco de dados 'loja':");
            for (PessoaJuridica pj : lista_pj) {
                pj.exibir();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroBDTeste1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // h. Excluir a pessoa jurídica criada anteriormente no banco.
        try {
            String nome = companhia_do_ferro.getNome();
            int id = companhia_do_ferro.getId();
            pj_dao.excluir(companhia_do_ferro);
            System.out.println("\nPessoa jurídica '" + nome + "' (id: " + id + ") foi excluída com sucesso  do banco de dados 'loja'.\n");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroBDTeste1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
