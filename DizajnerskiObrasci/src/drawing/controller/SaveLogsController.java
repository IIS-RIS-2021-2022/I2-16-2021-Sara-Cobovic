package drawing.controller;

import drawing.view.FrmDrawing;
import drawing.view.PnlDrawing;

import javax.swing.*;
import java.io.*;

public class SaveLogsController {

    private PnlDrawing pnlDrawing;

    public SaveLogsController(PnlDrawing pnlDrawing) {
        this.pnlDrawing = pnlDrawing;
    }

    public void saveLogs() {
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