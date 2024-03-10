package Practice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class Import_File extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUrl;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Import_File frame = new Import_File();
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
	public Import_File() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(24, 25, 79, 46);
		contentPane.add(lblNewLabel);
		
		textUrl = new JTextField();
		textUrl.setBounds(105, 25, 392, 46);
		contentPane.add(textUrl);
		textUrl.setColumns(10);
		
		JButton btnNewButton = new JButton("Open");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				// Mở folder
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// tạo biến kiểm tra xem có folder đc chọn k
				int result = fileChooser.showOpenDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					String path = fileChooser.getSelectedFile().getAbsolutePath(); // gán đường dẫn
					textUrl.setText(path);
					textPane.setText(listAllFile(path, result));
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(534, 25, 97, 46);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 119, 602, 245);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
	}
	
	private String listAllFile(String path, int level)
	{
		File myFile = new File(path);
		// Kiểm tra tập tin không tồn tại
		if(myFile.exists()) return "";
		
		// Tập tin đã tồn tại
		String result = "";
		// Tab vào
		for (int i = 0; i < level; i++) {
			result+="\t";
		}
		result += myFile.getName() = "\n";
		
		// nếu là file thì không làm gì
		if(myFile.isFile()) return result;
		
		for(File file : myFile.getName())
		{
			result+=listAllFile(file.getAbsolutePath(), level + 1);
		}
		return result;
	}
}
