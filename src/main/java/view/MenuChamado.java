package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.ChamadoController;
import model.bo.ChamadoBO;
import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

public class MenuChamado {
	private static Set<Integer> set;

	JFrame frame;
	JTable table;
	DefaultTableModel model;
	JTable table2;
	DefaultTableModel model2;

	public void apresentarMenuChamado(UsuarioVO usuarioVO) {
		final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Object[][] dados = null;
		final String[] colunasChamado = { "IdChamado", "Titulo", "Descricao", "DataHoraAbertura", "DataHoraFechamento", "IdUsuario", "SOLUCAO" };

		try {

			List<ChamadoVO> chamado = new ChamadoController().listarChamadosAbertosController();
			dados = new String[chamado.size()][6];
			int i = 0;

			for (ChamadoVO c : chamado) {

				dados[i][0] = String.valueOf(c.getIdChamado());
				dados[i][1] = c.getTitulo();
				dados[i][2] = c.getDescricao();
				dados[i][3] = c.getDataHoraAbertura();
				dados[i][4] = c.getDataHoraFechamento();
				dados[i][5] = String.valueOf(c.getIdUsuario());
				dados[i][6] = c.getSolucao();
				
				i++;
		


			}

		} catch (Exception e) {
			System.out.println(e);
		}

		frame = new JFrame();
		frame.setTitle("Chamado");

		frame.setResizable(true);

		frame.setSize(720, 700);

		FlowLayout fl = new FlowLayout();

		model = new DefaultTableModel(dados, colunasChamado);
		table = new JTable(model);
		table.setPreferredSize(new Dimension(690, 350));

		JButton bt2 = new JButton();
		table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		bt2.setPreferredSize(new Dimension(130, 20));
		bt2.setText("Novo Chamado");
		bt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				model2.addRow(new String[colunasChamado.length]);
			}
		});
		frame.add(bt2);
		
		JButton btSalva2 = new JButton();
		btSalva2.setPreferredSize(new Dimension(220, 20));
		btSalva2.setText("Editar Chamado");

		table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		btSalva2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ArrayList<Integer> linhasIncluir = new ArrayList<Integer>();
				// ArrayList<Integer> linhasAutualizar = new ArrayList<Integer>();

				int qtdLinhas = table2.getRowCount();
				// int qtdColunas = table.getColumnCount();
		JButton btSalva2 = new JButton();
		btSalva2.setPreferredSize(new Dimension(220, 20));
		btSalva2.setText("Editar alterações de Ocorrencia");

		table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		btSalva2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ArrayList<Integer> linhasIncluir = new ArrayList<Integer>();
				// ArrayList<Integer> linhasAutualizar = new ArrayList<Integer>();

				int qtdLinhas = table2.getRowCount();
				// int qtdColunas = table.getColumnCount();
				int id = 0;
				String idChamado = "";
				String titulo = "";
				String descricao = "";
				String dataHoraAbertura= "";
				String dataHoraFechamento = "";
				String idUsuario = "";
				String solucao = "";
		
				for (int i = 0; i < qtdLinhas; i++) {
					idChamado = (String ) table2.getValueAt(i, 1);
					titulo = (String) table2.getValueAt(i, 2);
					descricao = (String) table2.getValueAt(i, 3);
					dataHoraAbertura = (String) table2.getValueAt(i, 4);
					dataHoraFechamento = (String) table2.getValueAt(i, 5);
					idUsuario = (String) table2.getValueAt(i, 6);
					solucao = (String) table2.getValueAt(i, 7);
			
					
					linhasIncluir.add(i);

				}
				ChamadoVO chamadoVO = new ChamadoVO();
				idChamado = ChamadoController.atualizarChamadoController(chamadoVO);
				System.out.println(idChamado);
				table2.setValueAt(idChamado, table2.getRowCount()-1, 0);
				
		
	}
		});
		frame.add(btSalva2);
			}
		});
	}
}
