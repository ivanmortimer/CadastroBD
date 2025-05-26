/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastrobd.model.*;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan
 */
public class CadastroBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PessoaFisicaDAO pf_dao = new PessoaFisicaDAO();
            PessoaJuridicaDAO pj_dao = new PessoaJuridicaDAO();
            int opcao = -1;
            
            // Loop principal do menu em modo texto
            do {
                try {
                    System.out.println("\n==============================");
                    System.out.println("1 - Incluir Pessoa");
                    System.out.println("2 - Alterar Pessoa");
                    System.out.println("3 - Excluir Pessoa");
                    System.out.println("4 - Buscar pelo Id");
                    System.out.println("5 - Exibir Todos");
                    System.out.println("0 - Finalizar Programa");
                    System.out.println("==============================");
                    System.out.print("Digite uma opção (0-5): ");
                    
                    String entrada = scanner.nextLine();
                    opcao = Integer.parseInt(entrada);
                    
                    switch (opcao) {
                        case 1 -> incluirPessoa(scanner, pf_dao, pj_dao); // Incluir novo cadastro
                        case 2 -> alterarPessoa(scanner, pf_dao, pj_dao); // Alterar dados de cadastro existente
                        case 3 -> excluirPessoa(scanner, pf_dao, pj_dao); // Excluir pessoa por ID
                        case 4 -> buscarPessoa(scanner, pf_dao, pj_dao);  // Exibir uma pessoa pelo ID
                        case 5 -> exibirTodos(scanner, pf_dao, pj_dao);   // Exibir todos os cadastros
                        case 0 -> System.out.println("Encerrando o programa...");
                        default -> System.out.println("Opção inválida. Digite um número de 0 a 5.");
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, digite apenas números inteiros de 0 a 5.");
                } catch (InputMismatchException e) {
                    System.out.println("Erro de tipo de entrada. Digite apenas números inteiros.");
                    scanner.nextLine(); // limpa o buffer do scanner
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }
            } while (opcao != 0);
        }
    }

    // Função para incluir Pessoa Física ou Jurídica
    private static void incluirPessoa(Scanner scanner, PessoaFisicaDAO pf_dao, PessoaJuridicaDAO pj_dao) {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        String tipo = scanner.nextLine().toUpperCase();

        try {
            System.out.print("Digite o id da pessoa ou digite 0 se deseja que a id seja inserida automaticamente: ");
            int id = Integer.parseInt(scanner.nextLine());

            // Verifica duplicidade de ID antes de incluir
            if ("F".equals(tipo)) {
                if (id != 0) {
                    for (PessoaFisica pf : pf_dao.getPessoas()) {
                        if (pf.getId() == id) {
                            System.out.println("Erro ao incluir Pessoa Física: A 'id' inserida já se encontra presente na atual lista de Pessoas Físicas.");
                            System.out.println("Por favor escolher uma 'id' diferente, ou antes alterar a 'id' já existente.");
                            return;
                        }
                }
                }
                System.out.println("Insira o restante dos dados...");
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Logradouro: ");
                String logradouro = scanner.nextLine();
                System.out.print("Cidade: ");
                String cidade = scanner.nextLine();
                System.out.print("Estado: ");
                String estado = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                
                if (id == 0) {
                    PessoaFisica pf = new PessoaFisica(cpf, nome, logradouro, cidade, estado, telefone, email);
                    pf_dao.incluir(pf);
                } else {
                    PessoaFisica pf = new PessoaFisica(id, cpf, nome, logradouro, cidade, estado, telefone, email);
                    pf_dao.incluir(pf);                      
                }

            } else if ("J".equals(tipo)) {
                if (id != 0) {
                    for (PessoaJuridica pj : pj_dao.getPessoas()) {
                        if (pj.getId() == id) {
                            System.out.println("Erro ao incluir Pessoa Jurídica: A 'id' inserida já se encontra presente na atual lista de Pessoas Jurídicas.");
                            System.out.println("Por favor escolher uma 'id' diferente, ou antes alterar a 'id' já existente.");
                            return;
                        }
                    }
                }
                System.out.println("Insira o restante dos dados...");
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine();
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Logradouro: ");
                String logradouro = scanner.nextLine();
                System.out.print("Cidade: ");
                String cidade = scanner.nextLine();
                System.out.print("Estado: ");
                String estado = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();

                if (id == 0) {
                    PessoaJuridica pj = new PessoaJuridica(cnpj, nome, logradouro, cidade, estado, telefone, email);
                    pj_dao.incluir(pj);
                } else {
                    PessoaJuridica pj = new PessoaJuridica(id, cnpj, nome, logradouro, cidade, estado, telefone, email);
                    pj_dao.incluir(pj);                      
                }

            } else {
                System.out.println("Tipo inválido.");
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException ex) {
            System.out.println("Erro ao incluir pessoa: " + ex.getMessage());
        }
    }

    // Função para alterar uma pessoa com possibilidade de alterar o ID também
    private static void alterarPessoa(Scanner scanner, PessoaFisicaDAO pf_dao, PessoaJuridicaDAO pj_dao) {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        String tipo = scanner.nextLine().toUpperCase();

        try {
            System.out.print("Digite o id da pessoa: ");
            int id = Integer.parseInt(scanner.nextLine());

            if ("F".equals(tipo)) {
                PessoaFisica pf = pf_dao.getPessoa(id);
                pf.exibir();

                System.out.println("Digite os novos dados...");
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Logradouro: ");
                String logradouro = scanner.nextLine();
                System.out.print("Cidade: ");
                String cidade = scanner.nextLine();
                System.out.print("Estado: ");
                String estado = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                
                try {
                    pf.setCPF(cpf);
                    pf.setNome(nome);
                    pf.setLogradouro(logradouro);
                    pf.setCidade(cidade);
                    pf.setEstado(estado);
                    pf.setTelefone(telefone);
                    pf.setEmail(email);
                    pf_dao.alterar(pf);
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro ao alterar dados: " + ex.getMessage());
                }

            } else if ("J".equals(tipo)) {
                PessoaJuridica pj = pj_dao.getPessoa(id);
                pj.exibir();

                System.out.println("Digite os novos dados...");
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine();
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Logradouro: ");
                String logradouro = scanner.nextLine();
                System.out.print("Cidade: ");
                String cidade = scanner.nextLine();
                System.out.print("Estado: ");
                String estado = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();

                try {
                    pj.setCNPJ(cnpj);
                    pj.setNome(nome);
                    pj.setLogradouro(logradouro);
                    pj.setCidade(cidade);
                    pj.setEstado(estado);
                    pj.setTelefone(telefone);
                    pj.setEmail(email);
                    pj_dao.alterar(pj);
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro ao alterar dados: " + ex.getMessage());
                }

            } else {
                System.out.println("Tipo inválido.");
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException ex) {
            System.out.println("Erro ao alterar pessoa: " + ex.getMessage());
        }
    }

    // Excluir pessoa por ID
    private static void excluirPessoa(Scanner scanner, PessoaFisicaDAO pf_dao, PessoaJuridicaDAO pj_dao) {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        String tipo = scanner.nextLine().toUpperCase();

        try {
            System.out.print("Digite o id da pessoa: ");
            int id = Integer.parseInt(scanner.nextLine());

            if ("F".equals(tipo)) {
                pf_dao.excluir(pf_dao.getPessoa(id));
            } else if ("J".equals(tipo)) {
                pj_dao.excluir(pj_dao.getPessoa(id));
            } else {
                System.out.println("Tipo inválido.");
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException ex) {
            System.out.println("Erro ao excluir pessoa: " + ex.getMessage());
        }
    }

    // Buscar pessoa pelo ID e exibir seus dados
    private static void buscarPessoa(Scanner scanner, PessoaFisicaDAO pf_dao, PessoaJuridicaDAO pj_dao) {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        String tipo = scanner.nextLine().toUpperCase();

        try {
            System.out.print("Digite o id da pessoa: ");
            int id = Integer.parseInt(scanner.nextLine());

            if ("F".equals(tipo)) {
                pf_dao.getPessoa(id).exibir();
            } else if ("J".equals(tipo)) {
                pj_dao.getPessoa(id).exibir();
            } else {
                System.out.println("Tipo inválido.");
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println("Erro ao buscar pessoa: " + e.getMessage());
        }
    }

    // Exibe todos os registros de Pessoa Física ou Jurídica
    private static void exibirTodos(Scanner scanner, PessoaFisicaDAO pf_dao, PessoaJuridicaDAO pj_dao) {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        String tipo = scanner.nextLine().toUpperCase();

        if ("F".equals(tipo)) {
            try {
                for (PessoaFisica pf : pf_dao.getPessoas()) {
                    pf.exibir();
                    System.out.println();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CadastroBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("J".equals(tipo)) {
            try {
                for (PessoaJuridica pj : pj_dao.getPessoas()) {
                    pj.exibir();
                    System.out.println();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CadastroBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }    
}
