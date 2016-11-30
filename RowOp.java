package operations;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
  
  private static JEditorPane EditorPane = new JEditorPane();
  private JTextField JTField1 = new JTextField(3);
  private JTextField JTField2 = new JTextField(3);
  private JTextField JTField3 = new JTextField(3);
  private JTextField JTField4 = new JTextField(3);
  private JTextField JTField5 = new JTextField(3);
  private JTextField JTField6 = new JTextField(3);
  private JTextField JTField7 = new JTextField(3);
  private JTextField JTField8 = new JTextField(3);
  private JTextField JTField9 = new JTextField(3);
  private JTextField JTField10 = new JTextField(3);
  private JTextField JTField11 = new JTextField(3);
  private JTextField JTField12 = new JTextField(3);
  
  boolean reset = false;
  public boolean solved = false;
 
  public RowOp(boolean reset) {
    super("Row Operations");
    setPreferredSize(new Dimension(500, 450));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    this.setResizable(false);
    
    // Editor Panel
    JPanel operation = new JPanel();
    operation.setLayout(new BoxLayout(operation, BoxLayout.Y_AXIS));
    EditorPane.setEditable(false);
    EditorPane.setContentType("text/html");
    EditorPane.setText("<center><h1>Basic Row Operations</h1></center>");
    EditorPane.setPreferredSize(new Dimension(400,150));
    operation.setVisible(true);
    JScrollPane scrollPane = new JScrollPane(EditorPane);
    this.add(scrollPane);
    this.add(Box.createVerticalStrut(15));
    
    // City Label Pane
    JLabel enterNumberLabel = new JLabel("Enter integers into the matrix:", JLabel.CENTER);
    JPanel matrixPanel = new JPanel();
    matrixPanel.add(enterNumberLabel);
    operation.add(matrixPanel);
    
    // Creating AM
    JPanel augMatrix = new JPanel(new GridLayout(3,4));
    JPanel row1 = new JPanel(new FlowLayout());
    JPanel tJPanel = new JPanel();
    JPanel row2 = new JPanel(new FlowLayout());
    JPanel tJPanel2 = new JPanel();
    JPanel row3 = new JPanel(new FlowLayout());
    JPanel tJPanel3 = new JPanel();
    JLabel added = new JLabel();

    // Solve/Reset Pane
    JPanel solvePanel = new JPanel();
    JButton solveButton = new JButton("Solve!");
    JButton resetButton = new JButton("Reset");
    
    solveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        double x1 = Double.parseDouble(JTField1.getText());
        double x2 = Double.parseDouble(JTField2.getText());
        double x3 = Double.parseDouble(JTField3.getText());
        double x4 = Double.parseDouble(JTField4.getText());
        double x5 = Double.parseDouble(JTField5.getText());
        double x6 = Double.parseDouble(JTField6.getText());
        double x7 = Double.parseDouble(JTField7.getText());
        double x8 = Double.parseDouble(JTField8.getText());
        double x9 = Double.parseDouble(JTField9.getText());
        double x10 = Double.parseDouble(JTField10.getText());
        double x11 = Double.parseDouble(JTField11.getText());
        double x12 = Double.parseDouble(JTField12.getText());
        
        double[][] problem1 = {
            { x1,  x2, x3, x4 },    // x11 + x12 + x13 = x14
            { x5, x6, x7,  x8 },    // x21 + x22 + x23 = x24
            { x9, x10, x11, x12 }   // x31 + x32 + x33 = x34
        };
        
        if (JTField1 != null && JTField2 != null && JTField3 != null && JTField4 != null &&
            JTField5 != null && JTField6 != null && JTField7 != null && JTField8 != null &&
            JTField9 != null && JTField10 != null && JTField11 != null && JTField12 != null) {
          
          doProblem(problem1, "RREF");
          solved = true;
        } else {
          // NullCheck
          EditorPane.setText("<center><h1><b>ERROR! - Not all fields have a value!</b></h1></center>");
        }
      }
    });
    
    resetButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        if (solved == true)
          reset();
      }   
    });
    
    tJPanel.add(JTField1);
    tJPanel.add(JTField2);
    tJPanel.add(JTField3);
    tJPanel.add(JTField4);
    tJPanel.add(added);
    
    tJPanel2.add(JTField5);
    tJPanel2.add(JTField6);
    tJPanel2.add(JTField7);
    tJPanel2.add(JTField8);
    tJPanel2.add(added);
    
    tJPanel3.add(JTField9);
    tJPanel3.add(JTField10);
    tJPanel3.add(JTField11);
    tJPanel3.add(JTField12);
    tJPanel3.add(added);
    
    solvePanel.add(solveButton);
    solvePanel.add(resetButton); 
    
    row1.add(tJPanel);
    row2.add(tJPanel2);
    row3.add(tJPanel3);
    row3.add(solvePanel); 
    
    augMatrix.add(row1);
    augMatrix.add(row2);
    augMatrix.add(row3);
    
    operation.add(augMatrix);
    operation.add(solvePanel);
    operation.add(Box.createVerticalStrut(20));


    add(operation);
    pack();
    setVisible(true);
  }

    public static void gaussJordan(double[][] c, int row) {
        int rows = c.length;
        int cols = rows + 1;
        
        // 1. set lead variables equal to 1
        double factor = c[row][row];
        for (int col = 0; col < cols; col++)
            c[row][col] /= factor;

        // 2. set everything else equal to 0
        for (int row2=0; row2 < rows; row2++)
            if (row2 != row) {
                factor = -c[row2][row];
                for (int col=0; col < cols; col++)
                    c[row2][col] += factor * c[row][col];
            }
    }

    public static void solve(double[][] c) {
        int rows = c.length;
        for (int row = 0; row < rows; row++)  // Move through each row
            gaussJordan(c,row);
    }

    public static void print(double[][] c) {
        int rows = c.length;
        int cols = rows + 1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++)
                System.out.printf("%5.1f ", c[row][col]); // prints each variable in Reduced Echelon Form
            System.out.println();
        }
        System.out.println();
    }

    public static void printSolution(double[][] c) {
        int rows = c.length, cols = rows + 1;
        System.out.println("Solution:\n");
        
        for (int row = 0; row < rows; row++) {
            System.out.printf("   x" + (row + 1) + " = " + "%1.1f\n", c[row][cols-1]);
        }
        System.out.println();
    }

    public static void doProblem(double[][] problem, String description) {
        System.out.printf("******* %s ********\n", description);
        System.out.println("Original Equations:");
        print(problem);
        solve(problem);
        System.out.println("Solved (reduced row echelon form):");
        print(problem);
        printSolution(problem);
        EditorPane.setText("<center><h1><b>Solved! (Reduced Row Echelon Form)</b></h1><h2>Check console for results</h2></center>");
    }

    public static void main(String[] args) throws IOException {
      boolean reset = false;
      new RowOp(reset);
    }
    
 // Reset method
    public void reset() {
      reset = true;
      new RowOp(reset);
    }
}
