package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.dao.Banco;

import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MenuLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	UsuarioVO usuarioVO= new UsuarioVO();
	
	Banco conn = new Banco();
	private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuLogin frame = new MenuLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setBounds(90, 128, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(90, 153, 271, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(90, 191, 46, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(90, 216, 271, 20);
		contentPane.add(txtSenha);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 String txtNome;
			
			 try {
				 String sql = "select * from USUARIO where Login = ? and Senha = ?";
				 
				 PreparedStatement ps = conn.getConnection().prepareStatement(sql);
				 
				 ps.setString(1, txtLogin.getText());
				 ps.setString(2, txtSenha.getText());
				 
				 ResultSet rs = ps.executeQuery();
				 
				 if(rs.next()) {
					 
					 txtNome = (rs.getString("Nome"));
					 TelaPrincipalGabriel tela = new TelaPrincipalGabriel();
					 UsuarioVO usuarioVO= new UsuarioVO();
					 dispose();
					 JOptionPane.showMessageDialog(null, "Seja Bem-vindo "+txtNome+"");
		
						tela.apresentarTelaPrincipal(usuarioVO);
				 }else {
					 
					 JOptionPane.showMessageDialog(null, "Usuário Inválido!!");
				 }
				 
			 }catch (SQLException ex) {
				 JOptionPane.showMessageDialog(null, ex);
			 } catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnNewButton.setBounds(113, 247, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Fechar");
		btnNewButton_1.setBounds(229, 247, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
