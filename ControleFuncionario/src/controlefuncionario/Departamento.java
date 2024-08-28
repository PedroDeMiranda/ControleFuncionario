package controlefuncionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.Conexao;

public class Departamento {
	private int        idDepto;
	private String   descDepto;
	
	// Inclusão de Departamento
	public boolean incluirDepartamento() throws ClassNotFoundException, SQLException {
		String sql  = "INSERT INTO departamento (descdepto) ";
		       sql += "VALUES (?)";
		Connection con = null;
		con = Conexao.conectar();
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, this.getDescDepto());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro na inclusão do departamento");
			return false;
		}
		return true;
	}

	//Listar departamentos
	public List<Departamento> listarDeptos() throws ClassNotFoundException, SQLException {
		
		List<Departamento> listaDepto = new ArrayList<>();
		Connection con = Conexao.conectar();
		String sql ="Select iddepto, descdepto From departamento order by iddepto";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Departamento dep = new Departamento();
				dep.setIdDepto(rs.getInt("iddepto"));
				dep.setDescDepto(rs.getString("descdepto"));
				listaDepto.add(dep);
			}
		} catch (SQLException e) {
			System.out.println("Erro na listagem de departamentos");
			return null;
		}
		
	return listaDepto;
	} 
	
	// consulta um departamento
		public Departamento consultaDepto() throws ClassNotFoundException {
			Connection con = Conexao.conectar();
			Departamento dep = null;
			String sql = "SELECT descdepto FROM departamento where iddepto = ?";
			try {
				PreparedStatement stm = con.prepareStatement(sql);
				stm.setInt(1, this.getIdDepto());
				ResultSet rs = stm.executeQuery();
				if (rs.next()) {
					dep = new Departamento();
					dep.setDescDepto(rs.getString("descdepto"));
				}
			} catch (SQLException e) {
				System.out.println("Erro na consulta do departamento");
				return null;
			}
			return dep;
		}
		
	// alterar departamento
		public boolean alterarDepartamento() throws ClassNotFoundException {
			String  sql = "UPDATE departamento ";
					sql += "SET descdepto = ? ";
					sql += "WHERE iddepto = ? ";
			Connection con = Conexao.conectar();
			try {
				PreparedStatement stm = con.prepareStatement(sql);
				stm.setString(1, this.getDescDepto());
				stm.setInt(2, this.getIdDepto());
				stm.execute();
			} catch (SQLException e) {
				System.out.println("Erro na alteração do departamento");
				return false;
			}
			return true;
		}
	
	// excluir departamento
			public boolean excluirDepartamento() throws ClassNotFoundException {
				String  sql  = "DELETE FROM departamento ";
						sql += "WHERE iddepto = ? ";
				Connection con = Conexao.conectar();
				try {
					PreparedStatement stm = con.prepareStatement(sql);
					stm.setInt(1, this.getIdDepto());
					stm.execute();
				} catch (SQLException e) {
					System.out.println("Erro na exclusão do departamento");
					return false;
				}
				return true;
			}
	

	// area de getters e setters
	public int getIdDepto() {
		return idDepto;
	}
	public void setIdDepto(int idDepto) {
		this.idDepto = idDepto;
	}
	public String getDescDepto() {
		return descDepto;
	}
	public void setDescDepto(String descDepto) {
		this.descDepto = descDepto;
	}
	
		
		
}
