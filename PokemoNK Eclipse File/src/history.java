import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;
import javax.swing.table.DefaultTableModel;

public class history extends JFrame implements ActionListener, MouseListener{
	
	JPanel mainpanel,contentpanel,titlepanel;
	JLabel title,pokeid,kosong;
	JTextField poketxt;
	JButton back;
	
	Connect con;
	JTable table,table1;
	DefaultTableModel dtm;
	DefaultTableModel dtm1;

	static int userhis;
	
	public history(int user) {
		// TODO Auto-generated constructor stub
		this.userhis = user;
		con = con.getConnection();
		
        mainpanel = new JPanel(new BorderLayout());
		mainpanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        mainpanel.setBackground(Color.cyan);
        contentpanel = new JPanel((new BorderLayout()));
        contentpanel.setBackground(Color.cyan);
        titlepanel = new JPanel(new BorderLayout());
        titlepanel.setBackground(Color.cyan);
       
        
        //title
        title = new JLabel("Transaction History");
        title.setFont(new Font("MV Boli",Font.BOLD,16));
        titlepanel.add(title, BorderLayout.CENTER);
        titlepanel.setBorder(new EmptyBorder(10,300,10,200));
        
        this.add(titlepanel, BorderLayout.NORTH);
        mainpanel.add(contentpanel);
        
        //tombol
        back = new JButton("Back To Main");
        contentpanel.add(back);
        this.add(contentpanel,BorderLayout.SOUTH);
        contentpanel.setBorder(new EmptyBorder(0,200,10,200));
        back.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				welcomeUser user  = new welcomeUser(userhis);
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
                
		//generate table
		String[] header= {"TransactionId", "Date"};
		
        dtm = new DefaultTableModel(header, 0);
        
		String query = "SELECT headertransaction.TransactionId, Time"
				+ " FROM headertransaction JOIN detailtransaction ON "
				+ "headertransaction.TransactionId = detailtransaction.TransactionId"; //Query untuk ambil data
	    ResultSet rs = con.executeQuery(query); 
	    
	    ResultSetMetaData rms = null;
	    
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
	    
	    
	    
	    table = new JTable(dtm);
	    table1 = new JTable(dtm1);
	    table.setBackground(Color.cyan);
	    table.setBackground(Color.cyan);
        JScrollPane pane = new JScrollPane(table);
        pane.setBackground(Color.cyan);
        
        JScrollPane pane1 = new JScrollPane(table1);
        pane1.setBackground(Color.cyan);

        
        mainpanel.add(pane, BorderLayout.NORTH);
        mainpanel.add(pane1, BorderLayout.CENTER);
        this.add(mainpanel);
        
        
		
		frame();
	}
	
	void table1() {
		String[] header1 = {"Transaction Id", "PoekmonId", "Pokemon Name", "Pokemon Level", "PokemonType", "Quantity"};
		
        dtm1 = new DefaultTableModel(header1, 0);
        
        String query = "SELECT dt.TransactionId,PokemonId,PokemonName,PokemonLevel,PokemonType,Quantity"
        		+ "FROM detailtransaction dt JOIN pokemon ON detailtransaction.PokemonId = pokemon.PokemonId "; //Query untuk ambil data
	    ResultSet rs = con.executeQuery(query); 
	    
	    ResultSetMetaData rms = null;
	    
	    try {
            rms = (ResultSetMetaData) rs.getMetaData();
            while (rs.next()){
                Vector<Object> data = new Vector<>(); // ambil semua datanya (looping)

                for(int i = 1; i<= rms.getColumnCount(); i++){
                    data.add(rs.getObject(i)); //ambil semua data di columnnya
                }
                dtm1.addRow(data); //tambahkan data ke table (per row)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	void frame() {
		this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new history(userhis);

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
