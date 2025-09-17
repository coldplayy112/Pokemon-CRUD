import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class cobalistbag extends JFrame{


    JPanel mainPanel;
    JPanel editorPanel;

    JTable table;
    DefaultTableModel dtm;

    JLabel insertLbl, quantityLbl;
    JTextField insertTf, quantityTf;
    JButton insertBtn, backBtn;

    Connect con;

    public cobalistbag() {
        con = con.getConnection();

        mainPanel = new JPanel(new GridLayout(2, 1));

        editorPanel = new JPanel(new GridLayout(2, 2));
        insertLbl = new JLabel("Insert Pokemon Id");
        quantityLbl = new JLabel("Quantity");
        insertTf = new JTextField();
        quantityTf = new JTextField();

        insertBtn = new JButton("Insert");

        backBtn = new JButton("Back to Main");

        String[] header = {"Pokemon Id", "Pokemon Name", "Pokemon Level", "Pokemon Type"};
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
        //panel tombol
        JPanel paneltombol = new JPanel(new GridLayout(1,2));
        paneltombol.setBackground(Color.pink);
        paneltombol.add(insertBtn);
        paneltombol.add(backBtn);

        table = new JTable(dtm);

        JScrollPane pane = new JScrollPane(table);

        editorPanel.add(insertLbl);
        editorPanel.add(insertTf);
        editorPanel.add(quantityLbl);
        editorPanel.add(quantityTf);

        mainPanel.add(pane);
        mainPanel.add(editorPanel);


        this.add(mainPanel);
        this.add(paneltombol, BorderLayout.SOUTH);


        //this.add(backBtn, BorderLayout.SOUTH);

        init();
    }

    void init() {
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new cobalistbag();
    }


}
