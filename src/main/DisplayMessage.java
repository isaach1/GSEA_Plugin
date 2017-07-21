package main;

import javax.swing.*;

/**
 * Created by Isaac on 7/3/2017.
 */
class DisplayMessage {

    private String[] options = {"Continue", "Cancel"};
    private int response = -1;

    DisplayMessage(String type, String message) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        type = type.toLowerCase();

        switch (type) {
            case "error":
                JOptionPane.showMessageDialog(panel, message, "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "disclaimer":
                JOptionPane.showMessageDialog(panel, message, "Disclaimer", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Prompt":
                response = JOptionPane.showOptionDialog(frame, message, "Prompt", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                break;
            default:
                break;
        }
    }

    int getResponse() {return response;}
}