package operations;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class RowOp extends JFrame {
  
  private JEditorPane EditorPane= new JEditorPane();
  private JTextField JTField1 = new JTextField(3);
  private JTextField JTField2 = new JTextField(3);
  private JTextField JTField3 = new JTextField(3);
  private JTextField JTField4 = new JTextField(3);
  private JTextField JTField5 = new JTextField(3);
  private JTextField JTField6 = new JTextField(3);
  private JTextField JTField7 = new JTextField(3);
  private JTextField JTField8 = new JTextField(3);
  
  boolean reset = false;
 
  public RowOp(boolean reset) {
    super("Row Operations");
    setPreferredSize(new Dimension(800, 600));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    
    // Editor Panel
    JPanel operation = new JPanel();
    operation.setLayout(new BoxLayout(operation, BoxLayout.Y_AXIS));
    EditorPane.setEditable(false);
    EditorPane.setContentType("text/html");
    EditorPane.setText("<center><h1>Basic Row Operations</h1></center>");
    EditorPane.setPreferredSize(new Dimension(500,400));
    operation.setVisible(true);
    JScrollPane scrollPane = new JScrollPane(EditorPane);
    this.add(scrollPane);
    this.add(Box.createVerticalStrut(15));
    
    // City Label Pane
    JLabel enterNumberLabel = new JLabel("Enter integers into the matrix.", JLabel.CENTER);
    JPanel matrixPanel = new JPanel();
    matrixPanel.add(enterNumberLabel);
    operation.add(matrixPanel);
    
    JLabel augMatLabel = new JLabel("AM =");
    
    JPanel augMatrix = new JPanel(new GridLayout(3,4));
    JPanel row1 = new JPanel(new FlowLayout());
    JPanel tJPanel = new JPanel();
    
    JPanel row2 = new JPanel(new FlowLayout());
    JPanel tJPanel2 = new JPanel();
    JLabel added = new JLabel();
    

    // Solve/Reset Pane
    JPanel solvePanel = new JPanel();
    JButton solveButton = new JButton("Solve!");
    JButton resetButton = new JButton("Reset");
    
    solveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        
      }
    });
    
    resetButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        
      }   
    });
    
    tJPanel.add(JTField1);
    tJPanel.add(JTField2);
    tJPanel.add(JTField3);
    tJPanel.add(JTField4);
    tJPanel.add(added);
    
    tJPanel.add(JTField5);
    tJPanel.add(JTField6);
    tJPanel.add(JTField7);
    tJPanel.add(JTField8);
    tJPanel2.add(added);
    
    solvePanel.add(solveButton);
    solvePanel.add(resetButton); 
    
    row1.add(tJPanel);
    row2.add(tJPanel2);
    row2.add(solvePanel); 
    
    augMatrix.add(row1);
    augMatrix.add(row2);
    
    operation.add(augMatLabel);
    operation.add(augMatrix);
//    operation.add(row1);
//    operation.add(row2);
    operation.add(Box.createVerticalStrut(20));

      
    JPanel cityButtons = new JPanel(new GridLayout(20,1,5,10));   

    add(operation);
    pack();
    setVisible(true);
  }

    // This is the problem we solved in class
    private static double[][] problem1 = {
        // x = 1, y = 2, z = 3
        { 1,  2, 3, 14 },  // 1x + 2y + 3z = 14
        { 1, -1, 1,  2 },  // 1x - 1y + 1z = 2
        { 4, -2, 1,  3 }   // 4x - 2y + 1z = 3
    };

    public static void solve(double[][] c, int row) {
        int rows = c.length;
        int cols = rows + 1;
        // 1. set c[row][row] equal to 1
        double factor = c[row][row];
        for (int col=0; col<cols; col++)
            c[row][col] /= factor;

        // 2. set c[row][row2] equal to 0
        for (int row2=0; row2<rows; row2++)
            if (row2 != row) {
                factor = -c[row2][row];
                for (int col=0; col<cols; col++)
                    c[row2][col] += factor * c[row][col];
            }
    }

    public static void solve(double[][] c) {
        int rows = c.length;
        for (int row=0; row<rows; row++)
            solve(c,row);
    }

    public static void print(double[][] c) {
        int rows = c.length;
        int cols = rows + 1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++)
                System.out.printf("%5.1f ",c[row][col]);
            System.out.println();
        }
        System.out.println();
    }

    public static void printSolution(double[][] c) {
        int rows = c.length, cols = rows + 1;
        char variable = (char)((rows > 3) ? ('z' - (rows-1)) : 'x');
        System.out.println("Solution:\n");
        for (int row=0; row<rows; row++)
            System.out.printf("  %c = %1.1f\n",(char)variable++,c[row][cols-1]);
        System.out.println();
    }

    public static void doProblem(double[][] problem, String description) {
        System.out.printf("******* %s ********\n",description);
        System.out.println("Original Equations:");
        print(problem);
        solve(problem);
        System.out.println("Solved (reduced row echelon form):");
        print(problem);
        printSolution(problem);
    }

    public static void main(String[] args) throws IOException {
      boolean reset = false;
      new RowOp(reset);
    }
}
