
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class managepokemon extends JFrame implements ActionListener,MouseListener{

    JPanel mainPanel,insertpanel,updatepanel,deletepanel,editorpanel, deletedpanel, updtpanel;
//    JPanel editorPanel;

    JTable table;
    DefaultTableModel dtm;

    JLabel insertLbl, quantityLbl;
    JTextField insertTf, quantityTf;
    JButton insertBtn, backBtn;
    
    JLabel insName, inslvl, instype;
    JLabel dltid;
    JLabel updtid, updtname, updtlvl, updttype;
    
    JTextField insnametxt, inslvltxt, instypetxt;
    JTextField dltidtxt;
	JScrollPane scrollPane;
	JButton insertbtn,deletebutton,updatebutton;
	JTextField updtnametxt,updtidtxt,updtlvltxt,updttypetxt;
    
	JLabel k1,k2,k3,k4;

    Connect con = Connect.getConnection();
    static int useradam;
    
    public managepokemon(int admin2) {
    	this.useradam = admin2;
        
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.cyan);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 
		insertpanel = new JPanel(new GridLayout(4,2,20,10));
		
		//Insert
		insName = new JLabel("Pokemon Name : ");
		insnametxt = new JTextField();
		inslvl = new JLabel("Pokemon Level : ");
		inslvltxt = new JTextField();
		instype = new JLabel("Pokemon Type : ");
		instypetxt = new JTextField();
		k1 = new JLabel("");
		insertbtn = new JButton("Insert");
		insertpanel.add(insName);
		insertpanel.add(insnametxt);
		insertpanel.add(inslvl);
		insertpanel.add(inslvltxt);
		insertpanel.add(instype);
		insertpanel.add(instypetxt);
		insertpanel.add(k1);
		insertpanel.add(insertbtn);
		JPanel editorpanel = new JPanel(new BorderLayout());
		editorpanel.add(insertpanel, BorderLayout.WEST);
		mainPanel.add(editorpanel,BorderLayout.SOUTH);
		insertpanel.setBorder(new EmptyBorder(5,5,40,5));
		insertpanel.setBackground(Color.cyan);
		insertbtn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				insertData();
				System.out.println("Success");
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//delete
		JLabel dltid = new JLabel("POkemonId");
		dltidtxt = new JTextField();
		backBtn = new JButton("Back to Main");
		k4 = new JLabel("");
		k2 = new JLabel("");
		deletebutton = new JButton("Delete");
		deletedpanel = new JPanel(new GridLayout(4,2,5,5));
		deletedpanel.add(dltid);
		deletedpanel.add(dltidtxt);
		deletedpanel.add(k2);
		deletedpanel.add(deletebutton);
		deletedpanel.add(k4);
		deletedpanel.add(backBtn);
		editorpanel.add(deletedpanel, BorderLayout.CENTER);
		deletedpanel.setBorder(new EmptyBorder(5,10,100,10));
		deletedpanel.setBackground(Color.cyan);
		
		deletebutton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				deleteData();
				System.out.println("GG");
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		backBtn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				welcomeAdmin admin = new welcomeAdmin(useradam);
				actionPerformed(null);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//update
		JLabel updtid = new JLabel("PokemonId : ");
		JLabel updtname = new JLabel("PokemonName : ");
		JLabel updtlvl = new JLabel("PokemonLevel : ");
		JLabel updttype = new JLabel("PokemonType : ");
		k3 = new JLabel("");
		updtidtxt = new JTextField();
		updtnametxt = new JTextField();
		updtlvltxt = new JTextField();
		updttypetxt = new JTextField();
		updatebutton = new JButton("UPDATE");
		updtpanel = new JPanel(new GridLayout(5,2,20,10));
		updtpanel.add(updtid);
		updtpanel.add(updtidtxt);
		updtpanel.add(updtname);
		updtpanel.add(updtnametxt);
		updtpanel.add(updtlvl);
		updtpanel.add(updtlvltxt);
		updtpanel.add(updttype);
		updtpanel.add(updttypetxt);
		updtpanel.add(k3);
		updtpanel.add(updatebutton);
		editorpanel.add(updtpanel, BorderLayout.EAST);
		updtpanel.setBorder(new EmptyBorder(5,5,30,5));
		updtpanel.setBackground(Color.cyan);
		
		updatebutton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				updateData();
				System.out.println("Yes");
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		

//        editorPanel = new JPanel(new GridLayout(2, 2));
//        insertLbl = new JLabel("Insert Pokemon Id");
//        quantityLbl = new JLabel("Quantity");
//        insertTf = new JTextField();
//        quantityTf = new JTextField();

//        insertBtn = new JButton("Insert");

        

        String[] header = {"PokemonId", "PokemonName", "PokemonLevel","PokemonType"};
//        Object[][] data = { {"1", "test", 20000, 10} };

        dtm = new DefaultTableModel(header, 0);

        String query = "SELECT * FROM pokemon"; //Query untuk ambil data
        ResultSet rs = con.executeQuery(query); // ambil data dari database

        ResultSetMetaData rms = null; // mengambil metadata(informasi) mengenai results

        try {
            rms = (ResultSetMetaData) rs.getMetaData();
            while (rs.next()){
                Vector<Object> data = new Vector<>(); // ambil semua datanya (looping)

                for(int i = 1; i<= rms.getColumnCount(); i++){
                    data.add(rs.getObject(i)); //ambil semua data di columnnya
                }
                dtm.addRow(data); //tambahkan data ke table (per row)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
//        paneltombol.add(insertBtn);
        
        table = new JTable(dtm);

        JScrollPane pane = new JScrollPane(table);

//        editorPanel.add(insertLbl);
//        editorPanel.add(insertTf);
//        editorPanel.add(quantityLbl);
//        editorPanel.add(quantityTf);

        mainPanel.add(pane);
//        mainPanel.add(editorPanel);

        this.add(mainPanel);


        //this.add(backBtn, BorderLayout.SOUTH);

        init();
    }
    
    void clearField() {
		insnametxt.setText("");
		inslvltxt.setText("");
		instype.setText("");
		dltidtxt.setText("");
	}
	
    
	void insertData() {
		
		String tes = insnametxt.getText().toString();
		String  lvl = inslvltxt.getText();
		String type = instypetxt.getText();
		
		System.out.println(tes);
		
//		con.executeQuery("INSERT INTO pokemon VALUES ("+  null + "\""+ nameins +"\","+lvl+",\""+type+"\")");
		
        //prepare statement
		PreparedStatement ps = con.preparedStatement("INSERT INTO pokemon VALUES(null,?,?,?)");
		try {
			//ps.setString(tanda tanya ke berapa, valuenya maunya apa);
			ps.setString(1, tes);
			ps.setInt(2, Integer.parseInt(lvl));
			ps.setString(3, type);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clearField();
//		
	}
	

	void deleteData() {
		String id =  dltidtxt.getText();
			
			//versi biasa
			//DELETE FROM product where id = id
//			con.executeUpdate("DELETE FROM product WHERE id =" + id);
			
			//vesi prepared statement 
			PreparedStatement ps = con.preparedStatement("DELETE FROM pokemon WHERE PokemonId = ?");
			try {
				ps.setInt(1, Integer.parseInt(id));
				ps.execute();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	void updateData() {
			String id = updtidtxt.getText();
			String name = updtnametxt.getText();
			String lvl = updtlvltxt.getText();
			String type = updttypetxt.getText();
			
			
			//versi biasa 
			//UPADTE product SET name = "food", price=price, cuisine="cuisine" where Id = id
			con.executeUpdate("UPDATE pokemon SET PokemonName =\""+name+"\",PokemonLevel = "+lvl+",PokemonType =\""+type+"\"WHERE PokemonId = "+id);
			
			//Prepeare Statement
//			PreparedStatement ps = con.preparedStatement("UPDATE pokemon SET PokemonName =?, PokemonLevel =?,PokemonType =?,where PokemonId=?");
//			try {
//				ps.setString(1,name);
//				ps.setInt(2, Integer.parseInt(lvl));
//				ps.setString(3, type);
//				ps.setInt(4, Integer.parseInt(id));
//				ps.execute();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}catch (NumberFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			clearField();
		}
	

		
    void init() {
        this.setSize(800,600);
        this.setTitle("Welcome Admin");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new managepokemon(useradam);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
		
	}


}
