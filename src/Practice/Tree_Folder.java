package Practice;

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
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class Tree_Folder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUrl;
	private JTextPane txtTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tree_Folder frame = new Tree_Folder();
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
	public Tree_Folder() {
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
		
		JButton btnNewButton = new JButton("Chooser");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				// Mở folder
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				// tạo biến kiểm tra xem có folder đc chọn k
				int result = fileChooser.showOpenDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					String path = fileChooser.getSelectedFile().getAbsolutePath(); // gán đường dẫn
					textUrl.setText(path);
					txtTitle.setText(listAllFile(path, result));
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(507, 25, 124, 46);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 119, 602, 245);
		contentPane.add(scrollPane);
		
		txtTitle = new JTextPane();
		txtTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane.setViewportView(txtTitle);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo hành động xác nhận muốn xóa file
				int chooser = JOptionPane.showConfirmDialog(null, "Are you sure detele items?");
				// Nếu không đồng ý xóa thì dừng
				if(chooser == JOptionPane.NO_OPTION)
					return;
				
				// Nếu đồng ý xóa
				String path = textUrl.getText();
				deleteFile(path);
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDelete.setBounds(510, 388, 126, 46);
		contentPane.add(btnDelete);
		
		JButton btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newFileName = JOptionPane.showInputDialog(null, "Input file name");
				try {
					// File cũ
					String path = textUrl.getText();
					File myFile = new File(path);
					
					// File mới
					String newPath = myFile.getParent() + "\\" + newFileName;
					File newFile = new File(newPath);
					
					// Thay đổi tên sang tập tin mới
					if(myFile.renameTo(newFile))
					{
						textUrl.setText(newFile.getAbsolutePath());
						JOptionPane.showMessageDialog(null, "Rename file success!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Rename file none!");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnRename.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRename.setBounds(342, 388, 126, 46);
		contentPane.add(btnRename);
	}
	
	
	// Tạo phương thức chứa danh sách các file con
	private String listAllFile(String path, int level)
	{
		File myFile = new File(path);
		// Kiểm tra tập tin không tồn tại
		if(!myFile.exists()) return "";
		
		// Tập tin đã tồn tại
		String result = "";
		// Tab vào
		for (int i = 0; i < level; i++) {
			result+="\t";
		}
		result += myFile.getName() + "\n";
		
		// nếu là file thì không làm gì
		if(myFile.isFile()) return result;
		
		for(File file : myFile.listFiles())
		{
			result+=listAllFile(file.getAbsolutePath(), level + 1);
		}
		return result;
	}
	
	// Tạo phương thức xóa file
	private void deleteFile(String path)
	{
		try {
			File myFile = new File(path);
			// Nếu là thư mục cần xóa các tập tin con trước
			if(myFile.isDirectory())
			{
				for(File file : myFile.listFiles())
				{
					deleteFile(file.getAbsolutePath());
				}
			}
			// Xóa bản thân nó
			myFile.delete();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
