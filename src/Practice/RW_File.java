package Practice;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

public class RW_File extends JFrame {
	
	private File logFile;

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
					RW_File frame = new RW_File();
					frame.setVisible(true);
					frame.openLog();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 // Hàm mở log
	private void openLog()
	{
		try {
			// Quy định 1 fileName cố định để chứa log
			String fileName = "E:\\Test\\baitap.log";
			this.logFile = new File(fileName);
			// Nếu file chưa tồn tại
			if(!this.logFile.exists())
			{
				// Tạo 1 file mới nếu nó chưa tồn tại
				this.logFile.createNewFile();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// Hàm ghi log
	
	public void writeLog(String msg)
	{
		try {
			FileOutputStream fos = new FileOutputStream(this.logFile, true);
			// Muốn có dấu thì dùng UTF_8
			OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(osw);
			
			// Lấy ra time
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String time = currentDateTime.format(formatter);
			String user = "USER";
			bw.append(time + " " + user + " " + msg);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public RW_File() {
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
				writeLog("Mở file " + textUrl.getText());
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
				
				writeLog("Xóa file " + textUrl.getText());
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
						writeLog("Đã đổi tên file thành: " + textUrl.getText());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Rename file none!");
						writeLog("Không thể thay đổi tên file");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
							}
		});
		btnRename.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRename.setBounds(371, 388, 126, 46);
		contentPane.add(btnRename);
		
		JButton btnMove = new JButton("Move");
		btnMove.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnMove.setBounds(235, 388, 126, 46);
		contentPane.add(btnMove);
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION)
				{
					// Điểm đầu
					String source = textUrl.getText();
					// Điểm đến
					String destination = fileChooser.getSelectedFile().getAbsolutePath();
					copy(source, destination);
				}
				
				writeLog("Đã copy file: " + textUrl.getText());
			}
		});
		btnCopy.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCopy.setBounds(99, 388, 126, 46);
		contentPane.add(btnCopy);
		
		JButton btnLogs = new JButton("Logs");
		btnLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reader
				try {
					FileInputStream fis = new FileInputStream(logFile);
					InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
					BufferedReader br = new BufferedReader(isr);
					String result = "";
					String line = "";
					// Néu đọc lên đc và k bị null
					while((line=br.readLine())!=null)
					{
						result+=line;
						result+="\n";
					}
					txtTitle.setText(result);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					writeLog(e2.getMessage());
				}
			}
		});
		btnLogs.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogs.setBounds(0, 437, 69, 27);
		contentPane.add(btnLogs);
	}
	
	private void copy(String source, String destination)
	{
		try {  
			// Tạo ra 1 đối tượng
			Path sourcePath = Path.of(source);
			Path destinationPath = Path.of(destination);
			Path newPath = destinationPath.resolve(sourcePath.getFileName());
			Files.copy(sourcePath, newPath, StandardCopyOption.COPY_ATTRIBUTES);
			File myFile = new File(source);
			if(myFile.isDirectory())
			{
				for(File file : myFile.listFiles())
				{
					copy(file.getAbsolutePath(), destination + "\\" + sourcePath.getFileName());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	// Tạo phương thức chứa danh sách các file con
	private String listAllFile(String path, int level)
	{
		File myFile = new File(path);
		// Kiểm tra tập tin không tồn tại, exists(): tồn tại
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
