package controlefuncionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.Conexao;

public class Funcionario {
	
	private int     idFunc;
	private String  nomeFunc;
	private int     idDepto;
	private double 	salHora;
	
	// Inclusão de Funcionario
		public boolean incluirFuncionario() throws ClassNotFoundException, SQLException {
			String sql  = "INSERT INTO funcionario (nomeFunc, idDepto, salHora) ";
			       sql += "VALUES (?, ?, ?)";
			Connection con = null;
			con = Conexao.conectar();
			try {
				PreparedStatement stm = con.prepareStatement(sql);
				stm.setString(1, this.getNomeFunc());
				stm.setInt(2, this.getIdDepto());
				stm.setDouble(3, this.getSalHora());
				stm.execute();
			} catch (SQLException e) {
				System.out.println("Erro na inclusão do funcionario" + e);
				return false;
			}
			return true;
		}

		//Listar Funcionarios
		public List<Funcionario> listarFuncionarios() throws ClassNotFoundException, SQLException {
			
			List<Funcionario> listaFuncionarios = new ArrayList<>();
			Connection con = Conexao.conectar();
			String sql ="Select * From Funcionario order by idfunc";
			try {
				PreparedStatement stm = con.prepareStatement(sql);
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					Funcionario fun = new Funcionario();
					fun.setNomeFunc(rs.getString("nomefunc"));
					fun.setIdDepto(rs.getInt("iddepto"));
					fun.setSalHora(rs.getDouble("salhora"));
					listaFuncionarios.add(fun);
				}
			} catch (SQLException e) {
				System.out.println("Erro na listagem de funcionarios");
				return null;
			}
			
		return listaFuncionarios;
		} 
		
		// listar um Funcionario
			public Funcionario consultaFuncionario() throws ClassNotFoundException {
				Connection con = Conexao.conectar();
				Funcionario fun = null;
				String sql = "SELECT nomefunc, iddepto, salhora FROM funcionario WHERE idFunc = ?";
				try {
					PreparedStatement stm = con.prepareStatement(sql);
					stm.setInt(1, this.getIdFunc());
					ResultSet rs = stm.executeQuery();
					if (rs.next()) {
						fun = new Funcionario();
						fun.setNomeFunc(rs.getString("nomefunc"));
						fun.setIdDepto(rs.getInt("iddepto"));
						fun.setSalHora(rs.getDouble("salhora"));
					}
				} catch (SQLException e) {
					System.out.println("Erro na consulta do funcionario" + e);
					return null;
				}
				return fun;
			}
			
		// alterar Funcionario
			public boolean alterarFuncionario() throws ClassNotFoundException {
				String  sql = "UPDATE funcionario ";
						sql += "SET nomeFunc = ?, idDepto = ?, salHora = ? ";
						sql += "WHERE idFunc = ? ";
				Connection con = Conexao.conectar();
				try {
					PreparedStatement stm = con.prepareStatement(sql);
					stm.setString(1, this.getNomeFunc());
			        stm.setInt(2, this.getIdDepto());
			        stm.setDouble(3, this.getSalHora());
			        stm.setInt(4, this.getIdFunc()); 
			        stm.execute();
				} catch (SQLException e) {
					System.out.println("Erro na alteração do funcionario" + e);
					return false;
				}
				return true;
			}
		
		// excluir Funcionario
				public boolean excluirFuncionario() throws ClassNotFoundException {
					String  sql  = "DELETE FROM funcionario ";
							sql += "WHERE idfunc = ? ";
					Connection con = Conexao.conectar();
					try {
						PreparedStatement stm = con.prepareStatement(sql);
						stm.setInt(1, this.getIdFunc());
						stm.execute();
					} catch (SQLException e) {
						System.out.println("Erro na exclusão do funcionario"+e);
						return false;
					}
					return true;
				}
	
	
	
	
	//getter setters
	public int getIdFunc() {
		return idFunc;
	}
				public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}
	public String getNomeFunc() {
		return nomeFunc;
	}
	public void setNomeFunc(String nomeFunc) {
		this.nomeFunc = nomeFunc;
	}
	public int getIdDepto() {
		return idDepto;
	}
	public void setIdDepto(int idDepto) {
		this.idDepto = idDepto;
	}
	public double getSalHora() {
		return salHora;
	}
	public void setSalHora(double salHora) {
		this.salHora = salHora;
	}
	
	
}
