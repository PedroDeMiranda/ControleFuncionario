package controlefuncionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.Conexao;

public class ControleFuncionario {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//Instanciando classes
		Departamento dep1    = new Departamento();
		Funcionario fun      = new Funcionario();
		FolhaDePagamento fol = new FolhaDePagamento();
		String[] classes = {"Departamento", "Funcionario", "Folha de pagamento"};
		
		//Menu de escolha
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Menu:");
		System.out.println("1 - Departamento");
		System.out.println("2 - Funcionario");
		System.out.println("3 - Folha de Pagamento");
		int opcaoClas = sc.nextInt() - 1;  // Ajuste para usar o índice correto no array 'classes'
        
        int opcao = 0;
		while (opcao != 6) {
			System.out.println("\nMenu de Gerenciamento de " + classes[opcaoClas] + ":");
			System.out.println("1 - Inserir " + classes[opcaoClas]);
			System.out.println("2 - Listar " + classes[opcaoClas] + "s");
			System.out.println("3 - Consultar " + classes[opcaoClas]);
			System.out.println("4 - Alterar " + classes[opcaoClas]);
			System.out.println("5 - Deletar " + classes[opcaoClas]);
			System.out.println("6 - Sair");
			System.out.println("Escolha uma opção: ");
            
            opcao = sc.nextInt();

        	if (opcaoClas == 0) {  // Departamento
        		switch (opcao) {
        			case 1: 
        				// Inserir um Departamento
        				System.out.print("Informe o departamento: ");
        				dep1.setDescDepto(sc.next());
        				if (dep1.incluirDepartamento()) {
        					System.out.println("Inclusão de departamento efetuada com sucesso");
        				} else {
        					System.out.println("Ocorreram erros na inclusão do departamento");
        				}
        				break;
        			
        			case 2: 
        				// Listar departamentos
        				List<Departamento> lista = new ArrayList<>();
        				lista = dep1.listarDeptos();
        				for (Departamento d : lista) {
        					System.out.println("Departamento: " + d.getIdDepto() + " - " + d.getDescDepto());
        				}
        				break;
        			
        			case 3: 
        				// Consultar um departamento
        				System.out.println("Informe o id do departamento: ");
        				dep1.setIdDepto(sc.nextInt());
        				dep1 = dep1.consultaDepto(); 
        				if(dep1.getDescDepto() == null) {
        					System.out.println("Não encontrei o departamento");
        				} else {
        					System.out.println("Departamento: " + dep1.getDescDepto());
        				}
        				break;
        		
        			case 4: 
        				// Alterar um departamento
        				System.out.print("Informe o id do departamento: ");
        				dep1.setIdDepto(sc.nextInt());
        				System.out.print("Informe a nova descrição: ");
        				dep1.setDescDepto(sc.next());
        				if (dep1.alterarDepartamento()) {
        					System.out.println("Alteração do departamento efetuada com sucesso");
        				} else {
        					System.out.println("Erro ao alterar o departamento");
        				}
        				break;
        			
        			case 5: 
        				// Deletar um departamento
        				System.out.print("Informe o id do departamento para exclusão: ");
        				dep1.setIdDepto(sc.nextInt());
        				if (dep1.excluirDepartamento()) {
        					System.out.println("Exclusão do departamento efetuada com sucesso");
        				} else {
        					System.out.println("Erro ao excluir o departamento");
        				}
        				break;
        			
        			case 6: {
        				// Sair
        				System.out.println("Saindo...");
        				System.exit(0);
        				break;
        			}
        			default:
        				System.out.println("Valor inesperado: " + opcao);
        		}
        	} else if (opcaoClas == 1) {  // Funcionario
        		switch (opcao) {
        			case 1: 
        				// Inserir um Funcionario
        				System.out.print("Informe o nome: ");
        				fun.setNomeFunc(sc.next());
        				System.out.print("Informe o id do Departamento: ");
        				fun.setIdDepto(sc.nextInt());
        				System.out.print("Informe o salario: ");
        				fun.setSalHora(sc.nextDouble());
        				
        				if (fun.incluirFuncionario()) {
        					System.out.println("Inclusão de funcionario efetuada com sucesso");
        				} else {
        					System.out.println("Ocorreram erros na inclusão do funcionario");
        				}
        				break;
        			
        			case 2: 
        				// Listar Funcionarios
        				List<Funcionario> lista = new ArrayList<>();
        				lista = fun.listarFuncionarios();
        				for (Funcionario f : lista) {
        					System.out.println("Funcionario: " + f.getNomeFunc() + " id do departamento: " + f.getIdDepto() + " Salario: " + f.getSalHora());
        				}
        				break;
        			
        			case 3: 
        				// Consultar um Funcionario
        				System.out.println("Informe o id do Funcionario: ");
        				fun.setIdFunc(sc.nextInt());
        				fun = fun.consultaFuncionario();
        				if (fun.getNomeFunc() == null) {
        					System.out.println("Não encontrei o Funcionario");
        				} else {
        					System.out.println("Funcionario: " + fun.getNomeFunc() + "; id do departamento: " + fun.getIdDepto() + "; Salario: " + fun.getSalHora());
        				}
        				break;
        			
        			case 4: 
        				// Alterar um Funcionario
        				System.out.print("Informe o id do Funcionario: ");
        				fun.setIdFunc(sc.nextInt());
        				System.out.print("Informe o novo Nome: ");
        				fun.setNomeFunc(sc.next());
        				System.out.print("Informe o novo id do departamento: ");
        				fun.setIdDepto(sc.nextInt());
        				System.out.print("Informe o novo Salario: ");
        				fun.setSalHora(sc.nextDouble());
        				if (fun.alterarFuncionario()) {
        					System.out.println("Alteração do Funcionario efetuada com sucesso");
        				} else {
        					System.out.println("Erro ao alterar o Funcionario");
        				}
        				break;
        			
        			case 5: 
        				// Deletar um Funcionario
        				System.out.print("Informe o id do Funcionario para exclusão: ");
        				fun.setIdFunc(sc.nextInt());
        				if (fun.excluirFuncionario()) {
        					System.out.println("Exclusão do Funcionario efetuada com sucesso");
        				} else {
        					System.out.println("Erro ao excluir o Funcionario");
        				}
        				break;
        			
        			case 6: 
        				// Sair
        				System.out.println("Saindo...");
        				System.exit(0);
        				break;
        			
        			default:
        				System.out.println("Valor inesperado: " + opcao);
        		}
        	} else {
        		
        		
        		switch (opcao) {
				case 1:
					// Inserir um Folha
					System.out.print("Informe a Folha: ");
					fol.setReferFolha(sc.nextInt());
					System.out.print("Informe o id do Funcionario: ");
					fol.setIdFunc(sc.nextInt());
					System.out.print("Informe o Vlr Bruto: ");
					fol.setVlrBruto(sc.nextDouble());
					System.out.print("Informe o Vlr Inss: ");
					fol.setVlrInss(sc.nextDouble());
					System.out.print("Informe o Vlr Liquido: ");
					fol.setVlrLiquido(sc.nextDouble());
					
					if (fol.incluirFolha()) {
						System.out.println("Inclusão da Folha efetuada com sucesso");
					} else {
						System.out.println("Ocorreram erros na inclusão da Folha");
					}
					break;
				case 2:	
					// Listar Folha
					List<FolhaDePagamento> lista = new ArrayList<>();
					lista = fol.listarFolha();
					for (FolhaDePagamento f : lista) {
						System.out.println("Folha: " + f.getReferFolha() + "; id do Funcionario: " + f.getIdFunc() + "; Salario Bruto: " + f.getVlrBruto()
										  +"; Valor Inss: " + f.getVlrInss() + "; Salario liquido: " + f.getVlrLiquido());
					}
					break;
				case 3:	
					// Consultar um Funcionario
    				System.out.println("Informe a refer Folha: ");
    				fol.setReferFolha(sc.nextInt());
    				fol = fol.consultaFolha();
    				if (fol == null) {
    					System.out.println("Não encontrei a Folha de pagamento");
    				} else {
    					System.out.println("; id do Funcionario: " + fol.getIdFunc() + "; Salario Bruto: " + fol.getVlrBruto()
						  				  +"; Valor Inss: " + fol.getVlrInss() + "; Salario liquido: " + fol.getVlrLiquido());
    				}
					break;
				case 4:	
					// Alterar um Funcionario
					System.out.println("Informe a refer Folha: ");
    				fol.setReferFolha(sc.nextInt());
    				System.out.print("Informe o novo id do Funcionario: ");
    				fol.setIdFunc(sc.nextInt());
    				System.out.print("Informe o novo Salario Bruto: ");
    				fol.setVlrBruto(sc.nextDouble());
    				System.out.print("Informe o novo valor do inss: ");
    				fol.setVlrInss(sc.nextDouble());
    				System.out.print("Informe o novo Salario liquido: ");
    				fol.setVlrLiquido(sc.nextDouble());
    				if (fol.alterarFolha()) {
    					System.out.println("Alteração do Folha de pagamento efetuada com sucesso");
    				} else {
    					System.out.println("Erro ao alterar a Folha de pagamento");
    				}
					break;
				case 5:	
					// Deletar um Funcionario
    				System.out.print("Informe a refer Folha para exclusão: ");
    				fol.setReferFolha(sc.nextInt());
    				if (fol.excluirFolha()) {
    					System.out.println("Exclusão do Funcionario efetuada com sucesso");
    				} else {
    					System.out.println("Erro ao excluir o Funcionario");
    				}
					break;
				case 6:	
					// Sair
    				System.out.println("Saindo...");
    				System.exit(0);
					break;
				default:
    				System.out.println("Valor inesperado: " + opcao);
					break;
				}
				
        		
				
        	}
		}
	}
}
