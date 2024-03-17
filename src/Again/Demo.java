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
import java.awt.event.ActionEvent;

public class Demo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtURL;
	private JTextArea txtContent;
	private JButton btnDelete;
	private JButton btnRename;
	private JButton btnCopy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo frame = new Demo();
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
	public Demo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("URL:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(37, 10, 68, 28);
		contentPane.add(lblNewLabel);
		
		txtURL = new JTextField();
		txtURL.setBounds(103, 10, 520, 28);
		contentPane.add(txtURL);
		txtURL.setColumns(10);
		
		JButton btnNewButton = new JButton("Chooser");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Hiển thị hộp thoại
				JFileChooser fileChooser = new JFileChooser();
				// Mở các folder
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				// Tạo biến kiểm tra folder nào có được chọn không
				int result = fileChooser.showOpenDialog(null);
				// Kiểm tra bằng if else
				if(result == JFileChooser.APPROVE_OPTION)
				{
					// Lấy đường dẫn tuyêtj đối
					String path = fileChooser.getSelectedFile().getAbsolutePath();
					txtURL.setText(path);
					txtContent.setText(listFile(path, result));
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton.setBounds(647, 10, 143, 37);
		contentPane.add(btnNewButton);
		
		txtContent = new JTextArea();
		txtContent.setBounds(37, 65, 734, 246);
		contentPane.add(txtContent);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo option để xác nhận muốn xóa file
				int choose = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa file?");
				// Nếu Không thì kết thúc
				if(choose == JOptionPane.NO_OPTION) return;
				// Nếu đồng ý xóa
				String path = txtURL.getText();
				deleteFile(path);
				txtURL.setText("");
				txtContent.setText("");
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnDelete.setBounds(647, 321, 124, 48);
		contentPane.add(btnDelete);
		
		btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// tạo option để lấy biến chứa tên mới
				String newFileName = JOptionPane.showInputDialog(null, "Nhập tên file mới.");
				try {
					// xác định file cũ
					String path = txtURL.getText();
					File myFile = new File(path);
					// xác định file mới
					String newPath = myFile.getParent() + "\\" + newFileName;
					File newFile = new File(newPath);
					// Thay đổi tên sang file mới
					if(myFile.renameTo(newFile))
					{
						txtURL.setText(newFile.getAbsolutePath());
						txtContent.setText(newFileName);
						JOptionPane.showMessageDialog(null, "Đã đổi tên thành công.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Sửa tên file thất bại!");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnRename.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnRename.setBounds(480, 321, 143, 48);
		contentPane.add(btnRename);
		
		btnCopy = new JButton("Copy");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Phải hiện hộp thoại để chọn chỗ copy vào
				JFileChooser fileChooser = new JFileChooser();
				// Mở folder
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				// Kiểm tra folder đã đc mở hay chưa
				int result = fileChooser.showOpenDialog(null);
				// Kiểm tra
				if(result == JFileChooser.APPROVE_OPTION)
				{
					// path đi
					String source = txtURL.getText();
					// path đến
					String destination = fileChooser.getSelectedFile().getAbsolutePath();
					copyFile(source, destination);
				}
			}
		});
		btnCopy.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCopy.setBounds(335, 321, 124, 48);
		contentPane.add(btnCopy);
	}
	
	
	// Tạo phương thức chứa danh sách file
	private String listFile(String path, int level)
	{
		// Tạo 1 đối tượng file để lưu trữ
		File myFile = new File(path);
		// Nếu folder không tồn tại
		if(!myFile.exists()) return "";
		// Nếu folder đã tồn tại
		String result ="";
		// Chạy 1 vòng for để xác định level để cách dòng phù hợp
		for (int i = 0; i < level; i++) 
		{
			result+="\t";
		}
		result+=myFile.getName() + "\n";
		// Nếu là file thì không làm chi cả
		if(myFile.isFile()) return result;
		for(File file : myFile.listFiles())
		{
			result+=listFile(file.getAbsolutePath(), level + 1);
		}
		return result;
	}
	
	// Phương thức xóa file
	private void deleteFile(String path)
	{
		try {
			// Đàu tiên vẫn tạo đối tượng File
			File myFile = new File(path);
			//Nếu là folder thì cần xóa tập con trước
			if(myFile.isDirectory())
			{
				for(File file : myFile.listFiles())
				{
					deleteFile(file.getAbsolutePath());
				}
			}
			// Xóa chính nó
			myFile.delete();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// Phương thức Copy File
	private void copyFile(String source, String destination)
	{
		try {
			// Tạo 1 đối tượng đại diện
			Path sourcePath = Path.of(source);
			Path destinationPath = Path.of(destination);
			// Tạo 1 đường dẫn mới bằng cách kết hợp đường dẫn đích với tên tệp của đường dẫn nguồn
			Path newPath = destinationPath.resolve(sourcePath.getFileName());
			// copy source
			Files.copy(sourcePath, newPath, StandardCopyOption.COPY_ATTRIBUTES);
			// tạo đối tượng dựa trên source
			File myFile = new File(source);
			if(myFile.isDirectory())
			{
				for(File file : myFile.listFiles())
				{
					copyFile(file.getAbsolutePath(), destination + "\\" + sourcePath.getFileName());
				}
			}
			JOptionPane.showMessageDialog(null, "Đã copy file thành công.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Copy file không thành công.");
		}
	}
}
