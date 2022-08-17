package view;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.UsuarioController;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class Login {
	Scanner teclado = new Scanner(System.in);
	private static final int OPCAO_MENU_LOGIN = 1;
	private static final int OPCAO_MENU_CADASTRO = 2;
	private static final int OPCAO_MENU_SAIR = 3;
	private JPanel painelLogin;
	
	public void apresentarMenuLogin() throws SQLException {
	
		
		JDialog frameLogin = new JDialog(null, "Login", Dialog.ModalityType.DOCUMENT_MODAL);
	
		frameLogin.setSize(new Dimension(400, 200));
		painelLogin = new JPanel();
		JTextField usuario = new JTextField(34);
		JLabel nomeUsuario = new JLabel("Usuario");
		frameLogin.add(nomeUsuario);
		frameLogin.add(usuario);
		painelLogin.add(new JTextField(34));
		JTextField senha = new JTextField(34);
		JLabel senhaUsuario = new JLabel("Senha");
		frameLogin.add(senha);
		frameLogin.add(senhaUsuario);
		
		painelLogin.add(new JTextField(34));
		JButton BtLogin = new JButton("Entrar");
		painelLogin.add(new JButton("Cancelar"));
		frameLogin.add(painelLogin);
		frameLogin.setVisible(true);
		BtLogin.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
			UsuarioVO realizarLogin = new UsuarioVO();
			UsuarioController usuarioController = new UsuarioController();
			
			realizarLogin = usuarioController.realizarLoginController(realizarLogin);
			
			//realizarLogin.equals(BtLogin)
			
		}
		});
		
		JFrame janela = new JFrame("Tela Inicial");
		  
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
		janela.setVisible(true);
			
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int frameWidth =  frameLogin.getSize().width;
		int frameHeight =  frameLogin.getSize().height;
		int locationX = (int)(width - frameWidth) / 2;
		int locationY = (int)(height - frameHeight) / 2;
		frameLogin.setLocation(locationX, locationY);

		
		Integer opcao = 0;
		while(true) {
		try {
		opcao = apresentarOpcaoMenu();
		if(opcao instanceof Integer) {
			break;
		}
		}catch(Exception e) {
			System.out.println("\nErro: Insira o numero da opção!");
		}
		}
		while (opcao != OPCAO_MENU_SAIR) {
			switch(opcao) {
			case OPCAO_MENU_LOGIN:
				UsuarioVO usuarioVO = this.realizarLogin();
				if(usuarioVO.getIdUsuario() != 0 && usuarioVO.getDataExpiracao() == null) {
					System.out.println("Usuario Logado: "+ usuarioVO.getLogin());
					System.out.println("Perfil: "+usuarioVO.getTipoUsuarioVO());
					
					Menu menu = new Menu();
	//				menu.apresentarMenu(usuarioVO);
				}
				 System.out.println("\nRealizando o login...");
				break;
			case OPCAO_MENU_CADASTRO:
				this.cadastrarUsuario();
				break;
			default:
				System.out.println("\nOpção invalida!");
				break;
			}
			opcao = this.apresentarOpcaoMenu();
		}
	}



	private void cadastrarUsuario() throws SQLException {
		System.out.println("\n---- Cadastro de Usuario----");
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setTipoUsuarioVO(TipoUsuarioVO.USUARIO);
	// MenuUsuario menuUsuario = new MenuUsuario();
	//	menuUsuario.cadastrarNovoUsuario(usuarioVO);
	}



	private int apresentarOpcaoMenu() {
		System.out.println("\n---- Sistema Socrro Desk ----");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_LOGIN+"-- Entrar");
		System.out.println(OPCAO_MENU_CADASTRO+"-- Criar conta");
		System.out.println(OPCAO_MENU_SAIR+"-- Sair");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private UsuarioVO realizarLogin() {
		UsuarioVO usuarioVO = new UsuarioVO();
		System.out.println("\n---- Informações ----\n");
		System.out.print("Login: ");
		usuarioVO.setLogin(teclado.nextLine());
		System.out.print("Senha: ");
		usuarioVO.setSenha(teclado.nextLine());
		
		if(usuarioVO.getLogin().isEmpty() || usuarioVO.getSenha().isEmpty()) {
			System.out.println("Os campos de login e senha são Obrigatórios");
		} else {
			UsuarioController usuarioController = new UsuarioController();
			usuarioVO = usuarioController.realizarLoginController(usuarioVO);
			
			if(usuarioVO.getNome() == null || usuarioVO.getNome().isEmpty()) {
				System.out.println("\nUsuario não encotrado!");
			}
			if(usuarioVO.getDataExpiracao() != null) {
				System.out.println("\nUsuario expirado!");
			}
		}
		return usuarioVO;
	}

}
