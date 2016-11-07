package budget;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GuiMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -205935214689282413L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 *            An array of String arguments. Currently unused.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMain frame = new GuiMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create window and all elements.
	 */
	public GuiMain() {
		// Set up the Budget
		Budget main = new Budget();

		// All frames
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(.40);
		JList<Object> list = new JList<Object>(main.categoryNames());
		// model is contained in the Budget main.
		JTable table = new JTable(main.getModel());
		JScrollPane scrollPane = new JScrollPane(table);

		// Build the list listener
		ListSelectionListener sel = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					// Change the table model to the corresponding model
					if (list.getSelectedIndex() == -1) {
						// DO NOTHING.
					} else if (list.getSelectedIndex() == 0)
						table.setModel(main.getModel());
					else {
						// main.
						table.setModel(main.getCategoryModel(list
								.getSelectedIndex()));
					}
				}// End if.
			}// End valueChanged.
		};
		list.addListSelectionListener(sel);

		// Build the window
		this.setTitle("Budget Manager " + main.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 900, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setLeftComponent(list);
		splitPane.setRightComponent(scrollPane);

		// Setup the menu system
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New Budget");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Budget newB = new Budget();
				main.clone(newB);
				final JPanel panel = new JPanel();
				final JRadioButton button1 = new JRadioButton("Yes");
				final JRadioButton button2 = new JRadioButton("No");
				panel.add(new JLabel("Do you want this to be a family account?"));
				panel.add(button1);
				panel.add(button2);

				JOptionPane.showMessageDialog(null, panel);
				if (button1.isSelected()) {
					main.setFamilyAcc(true);
				} else {
					main.setFamilyAcc(false);
				}
				table.setModel(main.getModel());
				list.setListData(main.categoryNames());

				System.out.println("New budget loaded.");
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save Budget");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveFile(main);
			}
		});

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Load Budget");
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadFile(main);
				main.changedData();
				table.setModel(main.getModel());
				list.setListData(main.categoryNames());
				setTitle("Budget Manager " + main.getName());
			}
		});

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Import CSV");
		mnNewMenu.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportCSV(main, list);
				main.changedData();
				table.setModel(main.getModel());
				// setTitle("Budget Manager " + main.getName());
			}
		});

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Export to PDF");
		mnNewMenu.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportFile(main);

			}
		});

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_1 = new JMenu("Add");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmCategoryMenuItem = new JMenuItem("Income Category");
		mnNewMenu_1.add(mntmCategoryMenuItem);
		mntmCategoryMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String check = JOptionPane
						.showInputDialog("Input a new category name.");
				if (check != null)
					main.addIncome(check);
				list.setListData(main.categoryNames());
			}
		});

		JMenuItem mntmAddMenuItem = new JMenuItem("Expense Category");
		mnNewMenu_1.add(mntmAddMenuItem);
		mntmAddMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String check = JOptionPane
						.showInputDialog("Input a new category name.");
				if (check != null)
					main.addExpense(check);
				list.setListData(main.categoryNames());

			}
		});

		JMenuItem mntmAddRowMenuItem = new JMenuItem("Sub-Category");
		mnNewMenu_1.add(mntmAddRowMenuItem);
		mntmAddRowMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String check = JOptionPane
						.showInputDialog("Input a new row name.");
				if (check != null) {
					if (list.getSelectedIndex() != -1
							|| list.getSelectedIndex() != 0)
						main.addCategoryRow(list.getSelectedIndex(), check);
				}
			}
		});

		JMenuItem mntmTransactionem = new JMenuItem("Transaction");
		mnNewMenu_1.add(mntmTransactionem);
		mntmTransactionem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
<<<<<<< .mine
				if (main.getFamilyAcc() != true) {
					String check = JOptionPane
							.showInputDialog("Input a new transaction amount.");
					if (check != null) {
						if (list.getSelectedIndex() != -1
								&& list.getSelectedIndex() != 0)
							if (table.getSelectedRow() != -1
									&& table.getSelectedRow() != 0) {
								main.addRowTransaction(list.getSelectedIndex(),
										table.getSelectedRow(),
										Double.parseDouble(check));
								table.setModel(main.getCategoryModel(list
										.getSelectedIndex()));
							}
					}
				} else {
=======
				if(main.getFamilyAcc()!=true){
				String check = JOptionPane.showInputDialog("Input a new transaction amount.");
				if(check!=null)
				{
					if(list.getSelectedIndex() != -1 && list.getSelectedIndex() != 0)
						if(table.getSelectedRow() != -1 && table.getSelectedRow() != 0) {
							main.addRowTransaction(list.getSelectedIndex(), table.getSelectedRow(), check);
							table.setModel(main.getCategoryModel(list.getSelectedIndex()));
						}
				}
				}
				else{
>>>>>>> .r233
					JPanel p = new JPanel();
					JTextField userName = new JTextField(10);
					JTextField transactionAmount = new JTextField(10);

<<<<<<< .mine
					p.add(new JLabel("Input a new transaction amount:"));
					p.add(transactionAmount);
					p.add(new JLabel("Who is making this transaction?"));
					p.add(userName);

					JOptionPane.showConfirmDialog(null, p,
							"Family and first name : ",
							JOptionPane.OK_CANCEL_OPTION);
					System.out.println(userName.getText());
					if (userName.getText() != null
							&& transactionAmount.getText() != null) {
						if (list.getSelectedIndex() != -1
								&& list.getSelectedIndex() != 0)
							if (table.getSelectedRow() != -1
									&& table.getSelectedRow() != 0) {
								main.addRowTransaction(list.getSelectedIndex(),
										table.getSelectedRow(), Double
												.parseDouble(transactionAmount
														.getText()));
								table.setModel(main.getCategoryModel(list
										.getSelectedIndex()));
							}
					}
=======
					  p.add(new JLabel("Input a new transaction amount:"));
					  p.add(transactionAmount);
					  p.add(new JLabel("Who is making this transaction?"));
					  p.add(userName);
                     
					  JOptionPane.showConfirmDialog(null, p, "Family and first name : ", JOptionPane.OK_CANCEL_OPTION);
					  System.out.println(userName.getText());
					  if(userName.getText()!=null && transactionAmount.getText()!=null){
						  if(list.getSelectedIndex() != -1 && list.getSelectedIndex() != 0)
								if(table.getSelectedRow() != -1 && table.getSelectedRow() != 0) {
									main.addRowTransaction(list.getSelectedIndex(), table.getSelectedRow(), transactionAmount.getText());
									table.setModel(main.getCategoryModel(list.getSelectedIndex()));
								}
					  }
>>>>>>> .r233
				}

			}
		});

		JMenuItem editEstimateItem = new JMenuItem("Estimate");
		mnNewMenu_1.add(editEstimateItem);
		editEstimateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String check = JOptionPane
						.showInputDialog("Input a new estimate amount.");
				if (check != null) {
					if (list.getSelectedIndex() != -1
							&& list.getSelectedIndex() != 0)
						if (table.getSelectedRow() != -1
								&& table.getSelectedRow() != 0) {
							main.changeRowEstimate(list.getSelectedIndex(),
									table.getSelectedRow(),
									Double.parseDouble(check));
							table.setModel(main.getCategoryModel(list
									.getSelectedIndex()));
						}
				}
			}
		});

		JMenu removeMenu = new JMenu("Remove");
		menuBar.add(removeMenu);

		JMenuItem removeCategory = new JMenuItem("Remove Category");
		removeMenu.add(removeCategory);
		removeCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != 0
						&& list.getSelectedIndex() != -1) {
					main.removeCategory(list.getSelectedIndex());
					main.changedData();
					list.setListData(main.categoryNames());
					table.setModel(main.getModel());
				}
			}
		});
		JMenu AmountSavedMenu = new JMenu("Savings");
		menuBar.add(AmountSavedMenu);

		JMenuItem SaveCategory = new JMenuItem("Travel Saving");
		AmountSavedMenu.add(SaveCategory);
		SaveCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal amount = main.calcIncomeTotals();
				if (amount.doubleValue() <= 0) {
					JOptionPane.showMessageDialog(null,
							"Sorry!No amount can be saved ");
				} else {
					String check = JOptionPane
							.showInputDialog("What percentage of your total income would you like to save?.");
					BigDecimal saving = main.calcSavings(check);
					main.changedData();
					list.setListData(main.categoryNames());
					table.setModel(main.getModel());
					JOptionPane.showMessageDialog(null,
							"According to your income you can save $" + saving
									+ "");
				}
			}

		});

	}

	public void ImportCSV(Budget b, JList<Object> list) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System
				.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile != null) {
				// System.out.println("Selected file: " +
				// selectedFile.getAbsolutePath());
				FileOps.CSVImport(b, selectedFile, list);
				// System.out.println("File saved.");
			}
		}
	}

	public void ExportFile(Budget b) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System
				.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile != null) {
				// System.out.println("Selected file: " +
				// selectedFile.getAbsolutePath());
				FileOps.PDFExport(b, selectedFile);
				// System.out.println("File saved.");
			}
		}
	}

	public void LoadFile(Budget b) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System
				.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile != null) {
				System.out.println("Selected file: "
						+ selectedFile.getAbsolutePath());
				FileOps.load(b, selectedFile);
				// b.load(selectedFile);
				System.out.println("New file loaded.");
			}
		}
	}

	public void SaveFile(Budget b) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System
				.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile != null) {
				System.out.println("Selected file: "
						+ selectedFile.getAbsolutePath());
				FileOps.save(b, selectedFile);
				// b.saveBudget(selectedFile);
				JOptionPane.showMessageDialog(null,
						"Budget saved successfully!!");
				System.out.println("File saved.");
			} else {
				JOptionPane.showMessageDialog(null,
						"Sorry!Unable to save budget!");
			}
		}
	}
}
