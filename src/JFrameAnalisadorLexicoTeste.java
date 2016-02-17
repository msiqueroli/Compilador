import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class JFrameAnalisadorLexicoTeste {

	private JFrame frmAnalisadorLxico;
	Lexico AnalisadorLexico = new Lexico();
	private JLabel lblDadosArmazenadosNa;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameAnalisadorLexicoTeste window = new JFrameAnalisadorLexicoTeste();
					window.frmAnalisadorLxico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public JFrameAnalisadorLexicoTeste() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		System.out.print((char)34);
		frmAnalisadorLxico = new JFrame();
		frmAnalisadorLxico.setAlwaysOnTop(true);
		frmAnalisadorLxico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnalisadorLxico.setResizable(false);
		frmAnalisadorLxico.getContentPane().setBackground(Color.DARK_GRAY);
		frmAnalisadorLxico.setTitle("Analisador Léxico J#");
		frmAnalisadorLxico.setBounds(100, 100, 808, 455);
		frmAnalisadorLxico.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 287, 786, 124);
		frmAnalisadorLxico.getContentPane().add(scrollPane);
		
		final JTextArea textLexicoLog = new JTextArea();
		textLexicoLog.setFont(new Font("Consolas", Font.PLAIN, 14));
		textLexicoLog.setForeground(Color.WHITE);
		textLexicoLog.setBackground(Color.BLACK);
		scrollPane.setViewportView(textLexicoLog);
		textLexicoLog.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 29, 463, 231);
		frmAnalisadorLxico.getContentPane().add(scrollPane_1);
		
		final JTextArea textAreaLexico = new JTextArea();
		textAreaLexico.setForeground(Color.WHITE);
		textAreaLexico.setBackground(Color.BLACK);
		scrollPane_1.setViewportView(textAreaLexico);
		
		JLabel lblLog = new JLabel("Log Tipo Dados");
		lblLog.setForeground(Color.WHITE);
		lblLog.setBounds(390, 272, 125, 15);
		frmAnalisadorLxico.getContentPane().add(lblLog);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(485, 29, 311, 231);
		frmAnalisadorLxico.getContentPane().add(scrollPane_2);
		
		final JTextArea txtLogArray = new JTextArea();
		txtLogArray.setEditable(false);
		txtLogArray.setLineWrap(true);
		txtLogArray.setForeground(Color.WHITE);
		txtLogArray.setBackground(Color.BLACK);
		scrollPane_2.setViewportView(txtLogArray);
		
		lblDadosArmazenadosNa = new JLabel("Dados Armazenados na ArrayList:");
		lblDadosArmazenadosNa.setFont(new Font("Dialog", Font.BOLD, 10));
		lblDadosArmazenadosNa.setForeground(Color.WHITE);
		lblDadosArmazenadosNa.setBounds(484, 13, 219, 15);
		frmAnalisadorLxico.getContentPane().add(lblDadosArmazenadosNa);
		
		JLabel lblDigiteAqui = new JLabel("Digite Aqui:");
		lblDigiteAqui.setForeground(Color.WHITE);
		lblDigiteAqui.setFont(new Font("Dialog", Font.BOLD, 10));
		lblDigiteAqui.setBounds(12, 13, 219, 15);
		frmAnalisadorLxico.getContentPane().add(lblDigiteAqui);
		textAreaLexico.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				AnalisadorLexico.analisarTextField(textAreaLexico, textLexicoLog,txtLogArray);
			}
		});
	}
}
