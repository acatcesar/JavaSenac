
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import au.Cidadao;
import au.Ocorrencia;
public class DAOMySQ {
	
	private String getConfigValueByKey(String key) throws IOException  {
		File f  = new File("admin/config.ini"); 
		FileInputStream fis = new FileInputStream(f);
		byte[] conteudo = fis.readAllBytes();
		String fileContent = new String(conteudo);
		String[] variaveis = fileContent.split("\r\n");
		for(String variavel: variaveis) {
			int indexSeparator = variavel.indexOf("=");
			String keyTemp = variavel.substring(0,indexSeparator);
			if(keyTemp.equals(key)) {
				return variavel.substring(indexSeparator+1,variavel.length());
			}
		}
		return null;
	}
	
	public java.sql.Connection conectaBD() throws SQLException, ClassNotFoundException, IOException {		
		String url = this.getConfigValueByKey("url");
		String user = this.getConfigValueByKey("user");
		String passwd = this.getConfigValueByKey("password");
		java.sql.Connection con = DriverManager.getConnection(url, user, passwd);	
		return con ;
		
	}	
	public List<Cidadao> buscaCidadao() throws ClassNotFoundException, SQLException, IOException, ArrayStoreException {
		List<Cidadao> l = new ArrayList<Cidadao>();
		String sql ="SELECT id_cidadao, nome, numdoc, endereco, telefone, email  FROM cidadao";
		Connection c = this.conectaBD();
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {			
			Cidadao cliente = new Cidadao();
			cliente.setid_cidadao(rs.getInt(1));
			cliente.setnome(rs.getString(2));
			cliente.setnumdoc(rs.getString(3));
			cliente.setendereco (rs.getString(4));
			cliente.settelefone(rs.getString(5));
			cliente.setemail(rs.getString(6));
			l.add(cliente);
		}
		return l ;
}
	
	public Object[][] buscarOcorrencia(Long id_cidadao) throws ClassNotFoundException, SQLException, IOException {
		String sql ="SELECT id_ocorrencia, id_cidadao, dataa, descricao, loc FROM ocorrencia where id_cidadao=?";
		Connection c = this.conectaBD();
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1, id_cidadao);
		ResultSet rs = ps.executeQuery();
		ArrayList<String[]> list = new ArrayList<String[]>();
		while (rs.next()) {			
			String[] linha = {rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5)};
			list.add(linha);
		}
		Object [][] array = new Object[list.size()][5];
		int i = 0;
		for(String[] tupla: list) {
			array[i] = list.get(i) ;
			i++;
		}
		return  array ;
}
	
	public int insereCidadao(String nome, String numdoc, String endereco, String telefone, String email ) {
		try {
			String sql ="INSERT INTO cidadao (nome, numdoc, endereco, telefone, email) values (?,?,?,?,?)";
			Connection c = this.conectaBD();
			PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nome);
			ps.setString(2,numdoc);
			ps.setString(3,endereco);
			ps.setString(4,telefone);
			ps.setString(5,email);
			int atualizacao = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			return id;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return -1;
	}
	public int insereOcorrencia( int id_cidadao, String dataa, String descricao, String loc ) {
		try {
			String sql ="INSERT INTO ocorrencia (id_cidadao,dataa,descricao, loc) values (?,?,?,?)";
			Connection c = this.conectaBD();
			PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id_cidadao);
			ps.setString(2,dataa);
			ps.setString(3,descricao);
			ps.setString(4,loc);
			int atualizacao = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			return id;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return -1;
	}
	
	public boolean atualizaCidadao(int id_cidadao, String nome, String numdoc, String endereco, String telefone,String email ) {
		try {
			String sql ="UPDATE cidadao SET id_cidadao=?, nome=?,numdoc =?, endereco=?, telefone =?, email=? where id_cidadao=?";
			Connection c = this.conectaBD();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id_cidadao);
			ps.setString(2, nome);
			ps.setString(3, numdoc);
			ps.setString(4, endereco);
			ps.setString(5, telefone);
			ps.setString(6,email);
			ps.setInt(7, id_cidadao);
			int linhasAfetadas = ps.executeUpdate();
			return linhasAfetadas > 0 ? true : false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	public boolean atualizaOcorrencia(Integer id_ocorrencia, Integer id_cidadao, String dataa, String descricao,String loc) {
		try {
			String sql ="UPDATE ocorrencia SET id_cidadao =?, dataa=?,descricao=?, loc=? where id_ocorrencia=?";
			Connection c = this.conectaBD();
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, id_cidadao);
			ps.setString(2, dataa);
			ps.setString(3, descricao);
			ps.setString(4, loc);
			ps.setInt(5, id_ocorrencia);
			int linhasAfetadas = ps.executeUpdate();
			return linhasAfetadas > 0 ? true : false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean deleteRegistro(String id_cidadao) {
		try {
			String sql ="delete from cidadao where id_cidadao =?";
			Connection c = this.conectaBD();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, id_cidadao);
			int resultado = ps.executeUpdate();
			return resultado >0 ? true : false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	public List<Ocorrencia> buscaOcorrenciaPorCidadao(long cidadao) throws ClassNotFoundException, SQLException, IOException{
		List<Ocorrencia> ocorrencia = new ArrayList<Ocorrencia>();
		String sql = "SELECT id_ocorrencia,id_cidadao dataa, descricao, loc, id_cidadao FROM ocorrencia WHERE  id_cidadao=?";
		Connection c = this.conectaBD();
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1,cidadao);
		ResultSet rs = ps.executeQuery();
		try {
			
		while(rs.next()) {
			Ocorrencia ocorrenciaLinha = new Ocorrencia();
			ocorrenciaLinha.setId_ocorrencia(rs.getInt(1));
			ocorrenciaLinha.setDataa(rs.getString(2));
			ocorrenciaLinha.setDescricao(rs.getString(3));
			ocorrenciaLinha.setLoc(rs.getString(4));
			ocorrenciaLinha.setCidadao(rs.getInt(5));
			ocorrencia.add(ocorrenciaLinha);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
		return ocorrencia;
	}
	public boolean deleteOcorrencia(String id) {
		try {
		String sql = "DELETE FROM ocorrencia WHERE id_ocorrencia =?";
		Connection c = this.conectaBD();
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, id);
		int resultado = ps.executeUpdate();
		return resultado >0 ? true: false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public Object [][] buscaDados() throws ClassNotFoundException, SQLException, IOException{
		String sql = "SELECT id_ocorrencia, id_cidadao, dataa, descricao, loc FROM ocorrencia";
		Connection c = this.conectaBD();
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<String[]> list = new ArrayList<String[]>();
		while(rs.next()) {
			String[]linha = {rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6)};
			list.add(linha);
		}
		Object[][] array = new Object[list.size()][3];
		int i = 0;
		for(String[] tupla: list) {
			array[i] = list.get(i);
			i++;
		}
		return array;
	}


}

