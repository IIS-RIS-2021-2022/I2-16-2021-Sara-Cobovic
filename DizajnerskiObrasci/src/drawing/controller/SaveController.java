package drawing.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import drawing.view.PnlDrawing;

public class SaveController {
    private PnlDrawing pnlDrawing;

    public SaveController(PnlDrawing pnlDrawing) {
        this.pnlDrawing = pnlDrawing;
    }

    public void save() {
        JFileChooser jfc = new JFileChooser(System.getProperty("user.home") + File.separator + "Desktop");
        String filename;
        if(jfc.showDialog(pnlDrawing, "Save") == JFileChooser.APPROVE_OPTION) {
            if(!jfc.getSelectedFile().getAbsolutePath().endsWith(".txt")) {
                filename = jfc.getSelectedFile().getAbsolutePath() + ".txt";
            } else {
                filename = jfc.getSelectedFile().getAbsolutePath();
            }
            FileWriter fw;
            try {
                fw = new FileWriter(filename);
                fw.write(makeStringOutOfLogs());
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String makeStringOutOfLogs() {
        StringBuilder sb = new StringBuilder();
        for(String log : pnlDrawing.getFrame().getLogger().getLogs()) {
            sb.append(log).append("\n");
        }
        return sb.toString();
    }
}