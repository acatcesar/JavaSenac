package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

public class ChamadoDAO {

	DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public ArrayList<ChamadoVO> buscarChamadosDAO(UsuarioVO usuarioVO, int opcaoDeBusca) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String queryValidacao = "";
		String query = "";
		ArrayList<ChamadoVO> chamados = new ArrayList<ChamadoVO>();
		switch (opcaoDeBusca) {
		case 1:
			queryValidacao = " AND C.DATAFECHAMENTO IS NULL";
			break;
		case 2:
			queryValidacao = " AND C.DATAFECHAMENTO IS NOT NULL";
			break;
		}

		if(opcaoDeBusca != 3) {
		query = "SELECT C.IDCHAMADO,C.IDUSUARIO,C.IDTECNICO, C.TITULO,"
				+ "C.DESCRICAO,C.DATAABERTURA,C.SOLUCAO,C.DATAFECHAMENTO " + "FROM CHAMADOS C,USUARIO U "
				+ "WHERE C.IDUSUARIO = U.IDUSUARIO AND C.IDUSUARIO = " + usuarioVO.getIdUsuario()+queryValidacao;
		}else {
		query = "SELECT C.IDCHAMADO,C.IDUSUARIO,C.IDTECNICO, C.TITULO,"
					+ "C.DESCRICAO,C.DATAABERTURA,C.SOLUCAO,C.DATAFECHAMENTO " + "FROM CHAMADOS C,USUARIO U "
					+ "WHERE C.IDUSUARIO = U.IDUSUARIO AND DATAFECHAMENTO IS NULL";
		}
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				ChamadoVO chamadoVO = new ChamadoVO();
				chamadoVO.setIdChamado(Integer.parseInt(resultado.getString(1)));
				chamadoVO.setTitulo(resultado.getString(2));
				chamadoVO.setDescricao(resultado.getString(3));
				
				
				if (resultado.getString(3) != null) {
					chamadoVO.setIdTecnico(Integer.parseInt(resultado.getString(3)));
				}
				chamadoVO.setTitulo(resultado.getString(4));
				chamadoVO.setDescricao(resultado.getString(5));
				chamadoVO.setDataHoraAbertura(LocalDate.parse(resultado.getString(6), formaterDate));
				if (resultado.getString(7) != null) {
					chamadoVO.setSolucao(resultado.getString(7));
				} else {
					chamadoVO.setSolucao("");
				}
				if (resultado.getString(8) != null) {
					chamadoVO.setDataHoraFechamento(LocalDate.parse(resultado.getString(8), formaterDate));
				}
				chamados.add(chamadoVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query que busca o chamado.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return chamados;
	}

	public void cadastrarNovoChamadoDAO(UsuarioVO usuarioVO, ChamadoVO chamadoVO) throws SQLException {
		Connection conn = Banco.getConnection();
		// Statement stmt = Banco.getStatement(conn);
		String query = "INSERT INTO CHAMADOS (IDUSUARIO,TITULO,DESCRICAO,DATAHORAABERTURA) VALUES(?,?,?,?)";
		PreparedStatement pstm = Banco.getPreparedStatement(conn, query);
		pstm.setInt(1, usuarioVO.getIdUsuario());
		pstm.setString(2, chamadoVO.getTitulo());
		pstm.setString(3, chamadoVO.getDescricao());
		Date date = java.sql.Date.valueOf(chamadoVO.getDataHoraAbertura());
		pstm.setDate(4, date);

		try {
			pstm.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de cadastro de chamado.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// Banco.closeStatement(stmt);
			Banco.closePreparedStatement(pstm);
			Banco.closeConnection(conn);
			System.out.println("Chamado cadastrado!");
		}

	}

	public boolean verificarExistenciaRegistroPorIDChamadoDAO(int idChamado) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;

		String query = "SELECT IDCHAMADO FROM CHAMADOS WHERE IDCHAMADO =" + idChamado;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query que verifica a existencia de chamado por idchamado.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public boolean verificarFechamentoPorIDChamadoDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;

		String query = "SELECT DATAHORAFECHAMENTO FROM CHAMADOS WHERE IDCHAMADO =" + chamadoVO.getIdChamado()
				+ " AND DATAHORAFECHAMENTO is not null";

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query que verifica o fechamento do chamado por idchamado.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public String atualizarChamadoDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String retorno = "";
		String query = "UPDATE chamados set titulo = '" + chamadoVO.getTitulo() + "', descricao = '"
				+ chamadoVO.getDescricao() + "', datahoraabertura = '" + chamadoVO.getDataHoraAbertura()
				+ "' WHERE IDCHAMADO = " + chamadoVO.getIdChamado();
		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = "";
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de atualização do chamado.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public boolean excluirChamadoDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		String query = "DELETE FROM CHAMADOS WHERE IDCHAMADO = " + chamadoVO.getIdChamado() + " AND IDUSUARIO = "
				+ chamadoVO.getIdUsuario();
		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query que deleta o usuario.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public boolean verificarIdUsuarioNoChamado(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		String query = "SELECT IDCHAMADO FROM CHAMADOS WHERE IDUSUARIO = " + chamadoVO.getIdUsuario()
				+ " AND IDCHAMADO = " + chamadoVO.getIdChamado();
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query que verifica idusuario no chamado.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public void atenderChamadoDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		Date date = java.sql.Date.valueOf(chamadoVO.getDataHoraFechamento());
		String query = "UPDATE CHAMADOS SET IDTECNICO = '"+chamadoVO.getIdTecnico()+"', SOLUCAO = '"+chamadoVO.getSolucao()+"', DATAFECHAMENTO = '"+date+"' WHERE IDCHAMADO = " + chamadoVO.getIdChamado();
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query que realiza o atendimento do chamado.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
	}

	public ArrayList<ChamadoVO> listarChamadosFechadosTecnicoDAO(UsuarioVO usuarioVO) {
		return null;
	}

	public ArrayList<ChamadoVO> listarChamadosAbertosDAO() {
		return null;
	}

}
