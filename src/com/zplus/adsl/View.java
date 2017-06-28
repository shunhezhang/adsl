package com.zplus.adsl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class View extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 412687917274423924L;

	private JLabel connectNameLabel;
	JLabel accountLabel;
	JLabel passwordLabel;
	private JLabel innerIPLabel;
	private JLabel outerIPLabel;
	private JLabel logLabel;

	private JLabel connectNameTextField;
	private JLabel accountTextField;
	private JLabel passwordTextField;
	private JLabel innerIPTextField;
	private JLabel outerIPTextField;
	private JTextArea logTextField;

	JButton connectButton;
	JButton disConnectButton;
	JButton clearLogButton;

	public View() {}

	public void createFrame () {

		this.setVisible(true);
		this.setSize(350, 450);
		this.setVisible(true);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ADSL管理器");

		connectNameLabel = new JLabel("ADSL连接名：");
		connectNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		setConnectNameTextField(new JLabel());

		accountLabel = new JLabel("账号：");
		accountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		setAccountTextField(new JLabel());

		passwordLabel = new JLabel("密码：");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		setPasswordTextField(new JLabel());

		innerIPLabel = new JLabel("内网IP：");
		innerIPLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		setInnerIPTextField(new JLabel());

		outerIPLabel = new JLabel("外网IP：");
		outerIPLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		setOuterIPTextField(new JLabel());

		logLabel = new JLabel("日志：");
		logLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		setLogTextField(new JTextArea(10, 18));
		logTextField.setEnabled(false);
		JScrollPane logScrollPane = new JScrollPane(logTextField);
		logScrollPane.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		logScrollPane.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 

		connectButton = new JButton("联网");
		disConnectButton = new JButton("断网");
		clearLogButton = new JButton("清理日志");

		JPanel jPanel = new JPanel();
		jPanel.add(connectButton);
		jPanel.add(disConnectButton);
		jPanel.add(clearLogButton);

		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(connectNameLabel, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagLayout.setConstraints(connectNameTextField, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagLayout.setConstraints(accountLabel, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagLayout.setConstraints(accountTextField, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagLayout.setConstraints(passwordLabel, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagLayout.setConstraints(passwordTextField, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagLayout.setConstraints(innerIPLabel, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagLayout.setConstraints(innerIPTextField, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagLayout.setConstraints(outerIPLabel, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagLayout.setConstraints(outerIPTextField, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagLayout.setConstraints(logLabel, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.weighty = 3;
		gridBagLayout.setConstraints(logScrollPane, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 2;
		gridBagLayout.setConstraints(jPanel, gridBagConstraints);
		
		this.add(connectNameLabel);
		this.add(connectNameTextField);
		
		this.add(accountLabel);
		this.add(accountTextField);
		
		this.add(passwordLabel);
		this.add(passwordTextField);
		
		this.add(innerIPLabel);
		this.add(innerIPTextField);
		
		this.add(outerIPLabel);
		this.add(outerIPTextField);
		
		this.add(logLabel);
		this.add(logScrollPane);
		
		this.add(jPanel);

		//联网按钮点击事件监听
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ADSL.connAdsl(ADSL.getConnectName(), ADSL.getAccount(), ADSL.getPassword());

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		//断网按钮点击事件监听
		disConnectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ADSL.cutAdsl(ADSL.getConnectName());

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		//重新连接按钮点击事件监听
		clearLogButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getLogTextField().setText("");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public JLabel getAccountTextField() {
		return accountTextField;
	}

	public void setAccountTextField(JLabel accountTextField) {
		this.accountTextField = accountTextField;
	}

	public JLabel getPasswordTextField() {
		return passwordTextField;
	}

	public void setPasswordTextField(JLabel passwordTextField) {
		this.passwordTextField = passwordTextField;
	}

	public JLabel getConnectName() {
		return connectNameLabel;
	}

	public void setConnectName(JLabel connectName) {
		this.connectNameLabel = connectName;
	}

	public JLabel getConnectNameTextField() {
		return connectNameTextField;
	}

	public void setConnectNameTextField(JLabel connectNameTextField) {
		this.connectNameTextField = connectNameTextField;
	}

	public JLabel getInnerIPLabel() {
		return innerIPLabel;
	}

	public void setInnerIPLabel(JLabel innerIPLabel) {
		this.innerIPLabel = innerIPLabel;
	}

	public JLabel getOuterIPLabel() {
		return outerIPLabel;
	}

	public void setOuterIPLabel(JLabel outerIPLabel) {
		this.outerIPLabel = outerIPLabel;
	}

	public JLabel getInnerIPTextField() {
		return innerIPTextField;
	}

	public void setInnerIPTextField(JLabel innerIPTextField) {
		this.innerIPTextField = innerIPTextField;
	}

	public JLabel getOuterIPTextField() {
		return outerIPTextField;
	}

	public void setOuterIPTextField(JLabel outerIPTextField) {
		this.outerIPTextField = outerIPTextField;
	}

	public JLabel getLogLabel() {
		return logLabel;
	}

	public void setLogLabel(JLabel logLabel) {
		this.logLabel = logLabel;
	}

	public JTextArea getLogTextField() {
		return logTextField;
	}

	public void setLogTextField(JTextArea logTextField) {
		this.logTextField = logTextField;
	}
}
