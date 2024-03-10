package Practice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Create_file extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Create_file frame = new Create_file();
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
	public Create_file() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(127, 10, 367, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Folder:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 94, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Open");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				// mở folder
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// tạo biến kiểm tra xem có chọn được file nào không
				int result = fileChooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION)// Đọc đc 1 đường dẫn thành công
				{
					String path = fileChooser.getSelectedFile().getAbsolutePath();// tức là đường dẫn tuyệt đối
					textField.setText(path);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(527, 10, 108, 40);
		contentPane.add(btnNewButton);
		
		JLabel lblFileName = new JLabel("File Name:");
		lblFileName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFileName.setBounds(10, 77, 129, 40);
		contentPane.add(lblFileName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(127, 77, 367, 40);
		contentPane.add(textField_1);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cần tạo đường dẫn
				String path = textField.getText() + "/" + textField_1.getText();
				File myFile = new File(path);
				// Kiểm tra đã nhập tên file cần tạo hay chưa
				if(textField_1.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên file");
				}
				else
				{
					// Kiểm tra file ni đã tồn tại hay chưa
					if(myFile.exists())
					{
						// thông báo lỗi
						JOptionPane.showMessageDialog(null, "Tập tin đã tồn tại");
					}
					else
					{
						// Tạo file
						// thông báo tạo file thành công
						// Các lỗi có thể xảy ra như: ký tự đặc biệt, hết dung lượng, khong có quyèn
						try {
							myFile.createNewFile();
						} catch (Exception e2) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, "Không thể tạo được tập tin");
						}
					}
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCreate.setBounds(527, 77, 108, 40);
		contentPane.add(btnCreate);
	}
}
