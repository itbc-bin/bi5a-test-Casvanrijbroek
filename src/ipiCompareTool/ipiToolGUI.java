package ipiCompareTool;

import javax.swing.*;

public class ipiToolGUI {
    private JPanel mainPanel;
    private JPanel progressPanel;
    private JTextField fileText1, fileText2;
    private JLabel guiInfoLabel;
    private JLabel infoLabel;
    private JLabel filesLabel;
    private JButton fileButton1, fileButton2;
    private JButton compareButton;
    private JScrollPane infoScroll;
    private JTextArea infoArea;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ipiToolGUI");
        frame.setContentPane(new ipiToolGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
