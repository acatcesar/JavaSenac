package view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

public class TelaPrincipal {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MenuChamado mc= new MenuChamado();
		frame = new JFrame();
		frame.setBounds(100, 100, 1876, 931);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		table = new JTable();
		table.setBounds(10, 36, 797, 845);
		frame.getContentPane().add(table);
		
		
		table_1 = new JTable();
		table_1.setBounds(820, 34, 997, 394);
		frame.getContentPane().add(table_1);
		
		table_2 = new JTable();
		table_2.setBounds(820, 439, 997, 442);
		frame.getContentPane().add(table_2);
	}

}
