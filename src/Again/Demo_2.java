package Again;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class Demo_2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUrl;
	private JTextArea txtContent;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo_2 frame = new Demo_2();
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
	public Demo_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Url:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 6, 69, 39);
		contentPane.add(lblNewLabel);
		
		txtUrl = new JTextField();
		txtUrl.setBounds(89, 10, 498, 39);
		contentPane.add(txtUrl);
		txtUrl.setColumns(10);
		
		JButton btnNewButton = new JButton("Chooser");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mở hộp thoại
				JFileChooser fileChooser = new JFileChooser();
				// Mở folder
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				// Kiểm tra đã mở đc folder hay chưa
				int result = fileChooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION)
				{
					String path = fileChooser.getSelectedFile().getAbsolutePath();
					txtUrl.setText(path);
					txtContent.setText(listFile(path, result));
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(626, 10, 124, 35);
		contentPane.add(btnNewButton);
		
		txtContent = new JTextArea();
		txtContent.setBounds(50, 77, 691, 207);
		contentPane.add(txtContent);
		
		btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Xác định có muốn xóa hay không
				int choose = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa file?");
				// Xử lý confirm thôi
				if(choose == JOptionPane.NO_OPTION) return;
				// Nếu đồng ý thì xóa
				String path = txtUrl.getText();
				deleteFile(path);
				txtUrl.setText("");
				txtContent.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(617, 294, 124, 49);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Rename");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo biến chứa tên file mới
				String newFileName = JOptionPane.showInputDialog("Hãy nhập tên file mới.");
				try {
					// Xác định file cũ
					String path = txtUrl.getText();
					File myFile = new File(path);
					// xác định file mới
					String newPath = myFile.getParent() + "\\" + newFileName;
					File newFile = new File(newPath);
					// Xử lý đổi tên
					if(myFile.renameTo(newFile))
					{
						txtUrl.setText(newPath);
						txtContent.setText(newFileName);
						JOptionPane.showMessageDialog(null, "Đã đổi tên thành công.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Đổi tên file không thành công!");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1_1.setBounds(463, 294, 124, 49);
		contentPane.add(btnNewButton_1_1);
		
		btnNewButton_2 = new JButton("Copy");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mở hộp thoại để chọn nơi paste vào
				JFileChooser fileChooser = new JFileChooser();
				//Mở folder
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				// Kiểm tra đc mở hay không
				int result = fileChooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION)
				{
					// Path cũ
					String source = txtUrl.getText();
					// Path mới
					String destination = fileChooser.getSelectedFile().getAbsolutePath();
					// Gọi phương thức Copy
					copyFile(source, destination);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_2.setBounds(309, 294, 124, 49);
		contentPane.add(btnNewButton_2);
	}
	
	// Phương thức chứa danh sách các file con
	private String listFile(String path, int level)
	{
		File myFile = new File(path);
		// Kiểm tra xem đã tồn tại hay chưa
		if(!myFile.exists()) return "";
		String result = "";
		// xác định level của các tệp con
		for (int i = 0; i < level; i++) 
		{
			result+= "\t";
		}
		result+=myFile.getName() + "\n";
		// Nếu là file thì chỉ in ra thôi
		if(myFile.isFile()) return result;
		// Chạy vòng for đệ quy để hiển thị các tệp con
		for(File file : myFile.listFiles())
		{
			result += listFile(file.getAbsolutePath(), level + 1);
		}
		return result;
	}
	
	// Phương thức xóa File
	private void deleteFile(String path)
	{
		File myFile = new File(path);
		// Nếu là folder thì xóa các tệp con trước
		if(myFile.isDirectory())
		{
			for(File file : myFile.listFiles())
			{
				deleteFile(file.getAbsolutePath());
			}
		}
		// Xóa chính nó
		myFile.delete();
	}
	
	// Phương thức copy file
	private void copyFile(String source, String destination)
	{
		try {
			Path sourcePath = Path.of(source);
			Path destinationPath = Path.of(destination);
			// Tạo 1 đường dẫn mới dựa trên đường dẫn gốc và đích
			Path newPath = destinationPath.resolve(sourcePath.getFileName());
			// Copy
			Files.copy(sourcePath, newPath, StandardCopyOption.COPY_ATTRIBUTES);
			// Xử lý khi trong folder có chứa các tệp tin con
			File myFile = new File(source);
			if(myFile.isDirectory())
			{
				for(File file : myFile.listFiles())
				{
					copyFile(file.getAbsolutePath(), destination + "\\" + sourcePath.getFileName());
				}
			}
			JOptionPane.showMessageDialog(null, "Copy file thành công.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Copy file không thành công!");
		}
	}
}
