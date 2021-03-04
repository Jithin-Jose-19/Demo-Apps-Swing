import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StudentManagementApp extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String INSERT_QUERY = "INSERT INTO USERLIST VALUES(?,?,?)";
	private static final String LOGIN_QUERY = "SELECT COUNT(*) FROM USERLIST WHERE USERNAME=? AND PASSWORD=?";
	private static final String VIEW_QUERY = "SELECT SNAME,SADD,AVERAGE FROM STUDENT WHERE SNO=?";

	private static Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	static {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	private JTextField userTextField, snoTextField, nameTextField, addTextField, avgTextField;
	private JPasswordField passwordField;
	private JLabel userLabel, passwordLabel, registerLabel, waveMessage, nameLabel, emailLabel, lblEnterTheStudent;
	private JButton loginButton, registerButton, logoutButton, subregButton, okButton, backButton;
	private JLabel addLabel;
	private JLabel avgLabel;
	private JTextField emailTextField;

	private void homePage() {

		userLabel.setText("Username");
		userTextField.setVisible(true);
		passwordLabel.setText("Password");
		passwordField.setVisible(true);
		registerLabel.setText("Do yo want to register ??");
		registerButton.setVisible(true);
		loginButton.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManagementApp frame = new StudentManagementApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentManagementApp() {
		setTitle("Student Management App");
		setSize(900, 500);
		getContentPane().setLayout(null);

		userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		userLabel.setBounds(179, 124, 159, 26);
		getContentPane().add(userLabel);

		userTextField = new JTextField();
		userTextField.setFont(new Font("Calibri", Font.BOLD, 20));
		userTextField.setBounds(300, 116, 238, 46);
		getContentPane().add(userTextField);
		userTextField.setColumns(10);

		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		passwordLabel.setBounds(179, 185, 106, 34);
		getContentPane().add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri", Font.BOLD, 20));
		passwordField.setBounds(300, 185, 238, 46);
		getContentPane().add(passwordField);

		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();
			}
		});
		loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		loginButton.setForeground(Color.BLACK);
		loginButton.setBackground(Color.ORANGE);
		loginButton.setBounds(606, 145, 102, 46);
		getContentPane().add(loginButton);

		registerLabel = new JLabel("Do you want to Register ??");
		registerLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		registerLabel.setBounds(179, 329, 291, 46);
		getContentPane().add(registerLabel);

		registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				userTextField.setText("");
				passwordField.setText("");

				emailLabel.setText("Email");
				emailTextField.setVisible(true);
				registerLabel.setText("");
				registerButton.setVisible(false);
				subregButton.setVisible(true);
				loginButton.setVisible(false);
				backButton.setVisible(true);

			}
		});
		registerButton.setForeground(Color.BLACK);
		registerButton.setBackground(Color.PINK);
		registerButton.setFont(new Font("Calibri", Font.PLAIN, 22));
		registerButton.setBounds(422, 337, 127, 37);
		getContentPane().add(registerButton);

		lblEnterTheStudent = new JLabel("");
		lblEnterTheStudent.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblEnterTheStudent.setBounds(65, 69, 350, 26);
		getContentPane().add(lblEnterTheStudent);

		snoTextField = new JTextField();
		snoTextField.setFont(new Font("Calibri", Font.BOLD, 20));
		snoTextField.setColumns(10);
		snoTextField.setBounds(300, 62, 125, 33);
		getContentPane().add(snoTextField);
		snoTextField.setVisible(false);

		waveMessage = new JLabel("");
		waveMessage.setForeground(Color.DARK_GRAY);
		waveMessage.setFont(new Font("Arial Black", Font.BOLD, 20));
		waveMessage.setBounds(22, 10, 367, 34);
		getContentPane().add(waveMessage);

		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Calibri", Font.BOLD, 20));
		nameTextField.setColumns(10);
		nameTextField.setBounds(300, 116, 238, 46);
		getContentPane().add(nameTextField);
		nameTextField.setVisible(false);

		nameLabel = new JLabel("");
		nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		nameLabel.setBounds(118, 124, 172, 26);
		getContentPane().add(nameLabel);

		addLabel = new JLabel("");
		addLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		addLabel.setBounds(118, 199, 172, 26);
		getContentPane().add(addLabel);

		addTextField = new JTextField();
		addTextField.setFont(new Font("Calibri", Font.BOLD, 20));
		addTextField.setColumns(10);
		addTextField.setBounds(300, 185, 238, 46);
		getContentPane().add(addTextField);
		addTextField.setVisible(false);

		avgTextField = new JTextField();
		avgTextField.setFont(new Font("Calibri", Font.BOLD, 20));
		avgTextField.setBounds(300, 255, 238, 46);
		getContentPane().add(avgTextField);
		avgTextField.setVisible(false);

		avgLabel = new JLabel("");
		avgLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		avgLabel.setBounds(118, 267, 172, 26);
		getContentPane().add(avgLabel);

		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				waveMessage.setText("");
				lblEnterTheStudent.setText("");
				snoTextField.setText("");
				snoTextField.setVisible(false);
				nameLabel.setText("");
				nameTextField.setVisible(false);
				addLabel.setText("");
				addTextField.setVisible(false);
				avgLabel.setText("");
				avgTextField.setVisible(false);
				logoutButton.setVisible(false);
				okButton.setVisible(false);

				homePage();
			}
		});
		logoutButton.setBackground(Color.ORANGE);
		logoutButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		logoutButton.setBounds(559, 347, 126, 37);
		getContentPane().add(logoutButton);
		logoutButton.setVisible(false);

		emailLabel = new JLabel("");
		emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		emailLabel.setBounds(179, 259, 106, 34);
		getContentPane().add(emailLabel);

		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Calibri", Font.BOLD, 20));
		emailTextField.setColumns(10);
		emailTextField.setBounds(300, 255, 238, 46);
		getContentPane().add(emailTextField);
		emailTextField.setVisible(false);

		subregButton = new JButton("Submit & Register");
		subregButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		subregButton.setBounds(326, 338, 196, 37);
		getContentPane().add(subregButton);
		subregButton.setVisible(false);

		subregButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				userLabel.setText("Username");
				userTextField.setVisible(true);
				passwordLabel.setText("Password");
				passwordField.setVisible(true);

				try {
					if (con != null)
						ps = con.prepareStatement(INSERT_QUERY);

					if (ps != null) {
						ps.setString(1, userTextField.getText());
						ps.setString(2, Hash.hashing(new String(passwordField.getPassword())));
						ps.setString(3, emailTextField.getText());

						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Registration Successful ! Login to view the records", "",
								JOptionPane.INFORMATION_MESSAGE);
						homePage();
						emailLabel.setText("");
						emailTextField.setVisible(false);
						backButton.setVisible(false);
						subregButton.setVisible(false);
					}
				} catch (SQLException se) {

					if (se.getErrorCode() == 1) {
						JOptionPane.showMessageDialog(null, "Warning : Your username is already chosen ", "",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (emailLabel.getText().equals("") || userTextField.getText().equals("")
							|| new String(passwordField.getPassword()).equals(""))
						JOptionPane.showMessageDialog(null, "Fields cannot be empty", "",
								JOptionPane.INFORMATION_MESSAGE);
				}

				userTextField.setText("");
				passwordField.setText("");
				emailTextField.setText("");
			}
		});

		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sno = Integer.parseInt(snoTextField.getText());
				try {
					if (con != null)
						ps = con.prepareStatement(VIEW_QUERY);

					if (ps != null) {
						ps.setInt(1, sno);

						rs = ps.executeQuery();
					}

					if (rs != null) {
						if (rs.next()) {
							nameLabel.setText("Student Name");
							nameTextField.setVisible(true);
							nameTextField.setText(rs.getString(1));
							addLabel.setText("Student Address");
							addTextField.setVisible(true);
							addTextField.setText(rs.getString(2));
							avgLabel.setText("Student Average");
							avgTextField.setVisible(true);
							avgTextField.setText(rs.getString(3));
						} else {
							nameLabel.setText("");
							nameTextField.setVisible(false);
							addLabel.setText("");
							addTextField.setVisible(false);
							avgLabel.setText("");
							avgTextField.setVisible(false);
							JOptionPane.showMessageDialog(null, "No records found for your search", "",
									JOptionPane.INFORMATION_MESSAGE);

						}
					}

				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		});
		okButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		okButton.setBounds(606, 54, 72, 46);
		getContentPane().add(okButton);
		okButton.setVisible(false);

		backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userTextField.setText("");
				passwordField.setText("");
				homePage();
				emailLabel.setText("");
				emailTextField.setVisible(false);
				subregButton.setVisible(false);
				backButton.setVisible(false);
			}
		});
		backButton.setBackground(Color.ORANGE);
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(763, 19, 85, 34);
		getContentPane().add(backButton);
		backButton.setVisible(false);
	}

	void Login() {
		try {
			if (con != null)
				ps = con.prepareStatement(LOGIN_QUERY);

			if (ps != null) {
				ps.setString(1, userTextField.getText());
				ps.setString(2, Hash.hashing(new String(passwordField.getPassword())));

				rs = ps.executeQuery();
			}

			if (rs != null) {
				rs.next();
				if (rs.getInt(1) == 1) {
					userLabel.setText("");
					userTextField.setVisible(false);
					passwordLabel.setText("");
					passwordField.setVisible(false);

					waveMessage.setText("Welcome " + userTextField.getText());
					lblEnterTheStudent.setText("Enter the student number");
					snoTextField.setVisible(true);
					okButton.setVisible(true);
					logoutButton.setVisible(true);
					loginButton.setVisible(false);
					registerLabel.setText("");
					registerButton.setVisible(false);

				} else {
					JOptionPane.showMessageDialog(null, "Invalid username and password", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			userTextField.setText("");
			passwordField.setText("");
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}
}
