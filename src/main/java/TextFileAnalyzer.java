
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class TextFileAnalyzer extends JDialog implements ActionListener {
    private JPanel contentPane;
    private JButton newButton;
    private JButton browseButton;
    private JButton editButton;
    private JButton analyzeButton;
    private JButton helpButton;
    private JButton averageButton;
    private JEditorPane editorPane1;
    private String filename;



    TextFileAnalyzer() {
        browseButton.addActionListener(this);
        newButton.addActionListener(this);
        editButton.addActionListener(this);
        analyzeButton.addActionListener(this);
        helpButton.addActionListener(this);
        averageButton.addActionListener(this);
        setContentPane(contentPane);
        setModal(true);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    public void actionPerformed(ActionEvent e) {
//        TODO: TRY AND GET THE SWITCH WORKING.It would look nicer =)
//        switch (e.getSource()) {
//            case browseButton:
//                System.out.println("Browse Pressed");
//                break;
//            case analyzeButton:
//                System.out.println("Analyze Pressed");
//        }
        if (e.getSource() == browseButton) {
           File[] files = browseButtonHandler();
        } else if (e.getSource() == analyzeButton) {
            analyzeButtonHandler();
        } else if (e.getSource() == newButton) {
            newButtonHandler();
        }
    }

    private void analyzeButtonHandler() {
        File[] filename = browseButtonHandler();
        Analysis text = new Analysis(filename[0]);
    }

    private void newButtonHandler() {
        String path = JOptionPane.showInputDialog("Filename: ");
        try {
            File newFile = new File("files/"+path);
            newFile.createNewFile();
            System.out.println("Created File at: " + newFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File[] browseButtonHandler() {
        JFileChooser chooser = new JFileChooser();
        File wd = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(wd);
        chooser.setMultiSelectionEnabled(true);

        // Show the dialog; wait until dialog is closed
        int returnVal = chooser.showOpenDialog(browseButton);
        File[] files = {};
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Retrieve the selected files.
            files = chooser.getSelectedFiles();
            editorPane1.setText(files[0].toString());
        }
        return files;
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }



}
