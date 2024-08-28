package controlefuncionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.Conexao;

public class FolhaDePagamento {

	private int    referFolha;
	private int    idFunc;
	private double vlrBruto;
	private double vlrInss;
	private double vlrLiquido;
	
	
	// Inclusão de Funcionario
			public boolean incluirFolha() throws ClassNotFoundException, SQLException {
				String sql  = "INSERT INTO FolhaPagamento (referFolha, idFunc, vlrBruto, vlrInss, vlrLiquido) ";
				       sql += "VALUES (?, ?, ?, ?, ?)";
				Connection con = null;
				con = Conexao.conectar();
				try {
					PreparedStatement stm = con.prepareStatement(sql);
					stm.setInt(1, this.getReferFolha());
					stm.setInt(2, this.getIdFunc());
					stm.setDouble(3, this.getVlrBruto());
					stm.setDouble(4, this.getVlrInss());
					stm.setDouble(5, this.getVlrLiquido());
					stm.execute();
				} catch (SQLException e) {
					System.out.println("Erro na inclusão da Folha De Pagamento" + e);
					return false;
				}
				return true;
			}

			//Listar Funcionarios
			public List<FolhaDePagamento> listarFolha() throws ClassNotFoundException, SQLException {
				
				List<FolhaDePagamento> listaFolha = new ArrayList<>();
				Connection con = Conexao.conectar();
				String sql ="Select referFolha, idFunc, vlrBruto, vlrInss, vlrLiquido From Folhapagamento order by referfolha";
				try {
					PreparedStatement stm = con.prepareStatement(sql);
					ResultSet rs = stm.executeQuery();
					while (rs.next()) {
						FolhaDePagamento fol = new FolhaDePagamento();
						fol.setReferFolha(rs.getInt("referfolha"));
						fol.setIdFunc(rs.getInt("idFunc"));
						fol.setVlrBruto(rs.getDouble("vlrBruto"));
						fol.setVlrInss(rs.getDouble("vlrInss"));
						fol.setVlrLiquido(rs.getDouble("vlrLiquido"));
						listaFolha.add(fol);
					}
				} catch (SQLException e) {
					System.out.println("Erro na listagem de Folha");
					return null;
				}
				
			return listaFolha;
			} 
			
			// consultar um Funcionario
				public FolhaDePagamento consultaFolha() throws ClassNotFoundException {
					Connection con = Conexao.conectar();
					FolhaDePagamento fol = null;
					String sql = "SELECT referFolha, idFunc, vlrBruto, vlrInss, vlrLiquido FROM Folhapagamento WHERE referfolha = ?";
					try {
						PreparedStatement stm = con.prepareStatement(sql);
						stm.setInt(1, this.getReferFolha());
						ResultSet rs = stm.executeQuery();
						if (rs.next()) {
							fol = new FolhaDePagamento();
							fol.setIdFunc(rs.getInt("idFunc"));
							fol.setVlrBruto(rs.getDouble("vlrBruto"));
							fol.setVlrInss(rs.getDouble("vlrInss"));
							fol.setVlrLiquido(rs.getDouble("vlrLiquido"));
						}
					} catch (SQLException e) {
						System.out.println("Erro na consulta da Folha de pagamento" + e);
						return null;
					}
					return fol;
				}
				
			// alterar Funcionario
				public boolean alterarFolha() throws ClassNotFoundException {
					String  sql = "UPDATE Folhapagamento ";
							sql += "SET idFunc = ?, vlrBruto = ?, vlrInss = ?, vlrLiquido = ? ";
							sql += "WHERE referfolha = ? ";
					Connection con = Conexao.conectar();
					try {
						PreparedStatement stm = con.prepareStatement(sql);
						stm.setInt(1, this.getIdFunc());
						stm.setDouble(2, this.getVlrBruto());
						stm.setDouble(3, this.getVlrInss());
						stm.setDouble(4, this.getVlrLiquido());
						stm.setInt(5, this.getReferFolha());
				        stm.execute();
					} catch (SQLException e) {
						System.out.println("Erro na alteração da Folha de pagamento" + e);
						return false;
					}
					return true;
				}
			
			// excluir Funcionario
					public boolean excluirFolha() throws ClassNotFoundException {
						String  sql  = "DELETE FROM Folhapagamento ";
								sql += "WHERE referfolha = ? ";
						Connection con = Conexao.conectar();
						try {
							PreparedStatement stm = con.prepareStatement(sql);
							stm.setInt(1, this.getReferFolha());
							stm.execute();
						} catch (SQLException e) {
							System.out.println("Erro na exclusão da Folha de pagamento " +e);
							return false;
						}
						return true;
					}
		
		
		
		
		//getter setters
	
	public int getReferFolha() {
		return referFolha;
	}
	public void setReferFolha(int referFolha) {
		this.referFolha = referFolha;
	}
	public int getIdFunc() {
		return idFunc;
	}
	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}
	public double getVlrBruto() {
		return vlrBruto;
	}
	public void setVlrBruto(double vlrBruto) {
		this.vlrBruto = vlrBruto;
	}
	public double getVlrInss() {
		return vlrInss;
	}
	public void setVlrInss(double vlrInss) {
		this.vlrInss = vlrInss;
	}
	public double getVlrLiquido() {
		return vlrLiquido;
	}
	public void setVlrLiquido(double vlrLiquido) {
		this.vlrLiquido = vlrLiquido;
	}
	
	
}
