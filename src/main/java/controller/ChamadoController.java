package controller;

import java.util.ArrayList;

import model.bo.ChamadoBO;
import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

public class ChamadoController {
	public ArrayList<ChamadoVO> buscarChamadosController(UsuarioVO usuarioVO, int opcaoDeBusca) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.buscarChamadosBO(usuarioVO, opcaoDeBusca);
	}

	public void cadastrarNovoChamadoController(UsuarioVO usuarioVO,ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		chamadoBO.cadastrarNovoChamadoBO(usuarioVO,chamadoVO);
		
	}

	public static String atualizarChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.atualizarChamadoBO(chamadoVO);
	}

	public boolean excluirChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.excluirChamadoBO(chamadoVO);
	}

	public ChamadoVO atenderChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.atenderChamadoBO(chamadoVO);
	}

	public ArrayList<ChamadoVO> listarChamadosAbertosController() {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.listarChamadosAbertosBO();
	}

	public ArrayList<ChamadoVO> lsitarChamadosFechadosTecnicoController(UsuarioVO usuarioVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.lsitarChamadosFechadosTecnicoBO(usuarioVO);
	}
}
