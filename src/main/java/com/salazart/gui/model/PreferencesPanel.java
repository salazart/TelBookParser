package com.salazart.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PreferencesPanel extends JPanel {
	private String MASK_FORMAT_IMEI = "####";
	
	private JTextField idSectionText;
	private JTextField textSection;

	/**
	 * Create the panel.
	 */
	public PreferencesPanel() {
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u041D\u0430\u043B\u0430\u0448\u0442\u0443\u0432\u0430\u043D\u043D\u044F \u0432\u0456\u0434\u0434\u0456\u043B\u0443:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 273, 114);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("id Відділу:");
		label.setBounds(10, 26, 76, 14);
		panel_1.add(label);
		
		idSectionText = new JTextField();
		idSectionText.setBounds(96, 23, 165, 20);
		idSectionText.setColumns(10);
		panel_1.add(idSectionText);
		
		JLabel label_1 = new JLabel("Відділ:");
		label_1.setBounds(10, 51, 76, 14);
		panel_1.add(label_1);
		
		textSection = new JTextField();
		textSection.setBounds(96, 48, 165, 20);
		panel_1.add(textSection);
		textSection.setColumns(20);
		
		JButton addSection = new JButton("Додати відділ");
		addSection.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
			}
			
		});
		
		addSection.setBounds(132, 80, 131, 23);
		panel_1.add(addSection);

	}
}
