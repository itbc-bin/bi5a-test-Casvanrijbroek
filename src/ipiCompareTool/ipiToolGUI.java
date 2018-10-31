package ipiCompareTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ipiToolGUI implements ActionListener {
    private final JFileChooser chooseFile = new JFileChooser();
    private File file1, file2;
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

    private ipiToolGUI() {
        fileButton1.addActionListener(this);
        fileButton2.addActionListener(this);
        compareButton.addActionListener(this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ipiToolGUI");
        frame.setContentPane(new ipiToolGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == fileButton1) {
                file1 = selectFile();
                fileText1.setText(file1.getName());
            } else if (e.getSource() == fileButton2) {
                file2 = selectFile();
                fileText2.setText(file2.getName());
            } else if (e.getSource() == compareButton) {
                IPIComparer comparer = new IPIComparer(file1, file2);
            }
        } catch (NoFileSelected nfs) {
            JOptionPane.showMessageDialog(null, nfs.getMessage());
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe.getMessage());
        } catch (NotValidIPIFormat nvif) {
            JOptionPane.showMessageDialog(null, nvif.getMessage());
        }
    }

    private File selectFile() throws NoFileSelected {
        int returnVal = chooseFile.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooseFile.getSelectedFile();
        } else {
            throw new NoFileSelected("The file selection process was canceled by the user");
        }
    }
}
