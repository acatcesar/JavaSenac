package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import model.vo.UsuarioVO;
import model.vo.TipoUsuarioVO;
import model.vo.ChamadoVO;
import model.dao.*;

public class TelaPrincipalGabriel {

	private JFrame framePrincipal;
	private JTable tabela01;
	private JPanel painelDireit;
	private DefaultTableModel model;
	private FlowLayout fy, fyDireita;
	private JScrollPane sp;
	private JCheckBox checkbox;
	private JDesktopPane painelDireita;
	private JInternalFrame in, in2;
	private JButton b, b1, b2;

	public void apresentarTelaPrincipal(view.UsuarioVO usuarioVO) throws ClassNotFoundException {
		framePrincipal = new JFrame("Tela Principal");
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setSize(new Dimension(1350, 720));

		fy = new FlowLayout();
		framePrincipal.setLayout(fy);

		apresentarTabela01();
		painelDireita();

		checkbox = new JCheckBox();
		checkbox.setText("Exibir Todos os Chamados");
		framePrincipal.add(checkbox);
		checkbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println(checkbox.isSelected());
				
				JButton bt = new JButton();
				bt = new JButton();
				bt.setPreferredSize(new Dimension(100, 25));
				bt.setText("incluir ocorrencia");
			}
		});

		dimensao();
		framePrincipal.setVisible(true);

	}

	private void painelDireita() {
		painelDireita = new JDesktopPane();
		painelDireita.setBackground(Color.DARK_GRAY);
		painelDireita.setPreferredSize(new Dimension(630, 600));
		framePrincipal.add(painelDireita);
		painelDireita.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//painel = new JDesktopPane();
		//painelDireita.setBackground(Color.LIGHT_GRAY);
		//framePrincipal.setContentPane(painelDireita);
		in = new JInternalFrame("Chamados Meus");
		fyDireita = new FlowLayout();
		in.setLayout(fyDireita);
		in.setClosable(true);
		in.setResizable(true);
		in.setMaximizable(true);
		in.setIconifiable(true);
		in.setPreferredSize(new Dimension(600,290));
		in.setVisible(true);
		in.pack();
		painelDireita.add(in);
		
		in2 = new JInternalFrame("Chamados Abertos");
		fyDireita = new FlowLayout();
		in2.setLayout(fyDireita);
		in2.setClosable(true);
		in2.setResizable(true);
		in2.setMaximizable(true);
		in2.setIconifiable(true);
		in2.setPreferredSize(new Dimension(600,290));
		in2.setVisible(true);
		in2.pack();
		painelDireita.add(in2);
		
		
	}

	private void apresentarTabela01() throws ClassNotFoundException {
		Object[][] linhasDeDados = null;
		String[] colunas = { "Codigo","Usuario", "Tecnico","Titulo","Descricao", "Data Abertura","Data Fechamaento", "Solucao"  };
/*
		try {
		
			List<ChamadoVO> l = new ChamadoDAO().buscarChamadosDAO()
						linhasDeDados = new String[l.size()][6];
							int i = 0;
							for (ChamadoVO c : l) {
				linhasDeDados[i][0] = String.valueOf(c.getIdChamado());
				linhasDeDados[i][1] = c.getTitulo();
				linhasDeDados[i][2] = c.getDescricao();
				linhasDeDados[i][3] = c.getDataHoraAbertura();
				linhasDeDados[i][4] = c.getDataHoraFechamento();
				linhasDeDados[i][5] = c.getSolucao();
				i++;
			
 						
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 */
		
		
		
		
		
		
		
		
		
		
		
		
		tabela01 = new JTable(linhasDeDados, colunas);
		model = new DefaultTableModel(linhasDeDados, colunas);
		tabela01 = new JTable(model);
		framePrincipal.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		tabela01.getTableHeader().setReorderingAllowed(false); // TRAVA AS COLUNAS

		sp = new JScrollPane(tabela01);
		sp.setPreferredSize(new Dimension(640, 600));
		framePrincipal.add(sp);

		
	}
	
	

	public void dimensao() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int frameWidth = framePrincipal.getSize().width;
		int frameHeight = framePrincipal.getSize().height;
		int locationX = (int) (width - frameWidth) / 2;
		int locationY = (int) (height - frameHeight) / 2;
		framePrincipal.setLocation(locationX, locationY);
	}

}
