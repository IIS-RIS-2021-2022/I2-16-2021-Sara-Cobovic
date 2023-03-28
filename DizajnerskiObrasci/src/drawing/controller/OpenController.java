package drawing.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.*;

import drawing.command.AddCommand;
import drawing.command.BringToBackCommand;
import drawing.command.BringToFrontCommand;
import drawing.command.ChangeColorCommand;
import drawing.command.ChangeInnerColorCommand;
import drawing.command.Command;
import drawing.command.CommandManager;
import drawing.command.DeleteCommand;
import drawing.command.ModifyCommand;
import drawing.command.ToBackCommand;
import drawing.command.ToFrontCommand;
import drawing.model.Circle;
import drawing.model.Donut;
import drawing.model.DrawingModel;
import drawing.model.Line;
import drawing.model.Point;
import drawing.model.Rectangle;
import drawing.model.Shape;
import drawing.model.SurfaceShape;
import drawing.model.adapter.HexagonAdapter;
import drawing.view.PnlDrawing;

public class OpenController {

    private static final String DELIMITER = ":";
    private static final String ADD = "Add";
    private static final String DELETE = "Delete";
    private static final String MODIFY = "Modify";
    private static final String CHANGE_COLOR = "Change color";
    private static final String CHANGE_INNER_COLOR = "Change inner color";
    private static final String TO_BACK = "To back";
    private static final String TO_FRONT = "To front";
    private static final String BRING_TO_BACK = "Bring to back";
    private static final String BRING_TO_FRONT = "Bring to front";

    private DrawingModel drawingModel;
    private PnlDrawing pnlDrawing;

    public OpenController(DrawingModel drawingModel, PnlDrawing pnlDrawing) {
        this.drawingModel = drawingModel;
        this.pnlDrawing = pnlDrawing;
    }

    public void open() {
        JFileChooser jfc = new JFileChooser(System.getProperty("user.home") + File.separator + "Desktop");
        File file;
        if(jfc.showDialog(pnlDrawing, "Open") == JFileChooser.APPROVE_OPTION) {
            if(!jfc.getSelectedFile().getName().endsWith(".txt")) {
                JOptionPane.showMessageDialog(pnlDrawing, "Only .txt files can be opened!");
                return;
            }
            file = jfc.getSelectedFile();

            CommandManager.clearCommands();
            pnlDrawing.getFrame().getLogger().clearLogs();
            drawingModel.getShapes().clear();
            drawingModel.setStartPoint(new Point(0, 0));
            pnlDrawing.getFrame().getColorChange().setBackground(drawingModel.getColor());
            pnlDrawing.getFrame().getInnerColorChange().setBackground(drawingModel.getInnerColor());
            pnlDrawing.repaint();

            // Ucitavanje komandi
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if(line.startsWith("Undo")) {
                        CommandManager.undoCommand();
                    } else {
                        CommandManager.addCommand(readCommand(line));
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(pnlDrawing, "Something went wrong!");
                e.printStackTrace();;
            }
        }
    }

    private Command readCommand(String line) {
        String commandCode = readCommandCode(line);

        if(MODIFY.equals(commandCode)) {
            StringTokenizer tokenizer = new StringTokenizer(line, DELIMITER);
            tokenizer.nextToken(); // command
            Shape shape = readShape(tokenizer.nextToken());
            Shape newShape = readShape(tokenizer.nextToken());
            return new ModifyCommand(findExistingShape(shape), newShape, pnlDrawing, drawingModel);
        } else {
            Shape shape = readShape(line);
            switch (commandCode) {
                case ADD:
                    return new AddCommand(shape, pnlDrawing, drawingModel);
                case DELETE:
                    return new DeleteCommand(findExistingShape(shape), pnlDrawing, drawingModel);
                case CHANGE_COLOR:
                    return new ChangeColorCommand(findExistingShape(shape), pnlDrawing, shape.getColor(), drawingModel);
                case CHANGE_INNER_COLOR:
                    return new ChangeInnerColorCommand(findExistingShape(shape), pnlDrawing, ((SurfaceShape) shape).getInnerColor(), drawingModel);
                case BRING_TO_BACK:
                    return new BringToBackCommand(findExistingShape(shape), pnlDrawing, drawingModel);
                case BRING_TO_FRONT:
                    return new BringToFrontCommand(findExistingShape(shape), pnlDrawing, drawingModel);
                case TO_BACK:
                    return new ToBackCommand(findExistingShape(shape), pnlDrawing, drawingModel);
                case TO_FRONT:
                    return new ToFrontCommand(findExistingShape(shape), pnlDrawing, drawingModel);
            }
        }
        return null;
    }

    private Shape readShape(String line) {
        if(line.contains("Line")) return Line.fromLogs(line);
        if(line.contains("Circle")) return Circle.fromLogs(line);
        if(line.contains("Rectangle")) return Rectangle.fromLogs(line);
        if(line.contains("Donut")) return Donut.fromLogs(line);
        if(line.contains("Hexagon")) return HexagonAdapter.fromLogs(line);
        if(line.contains("Point")) return Point.fromLogs(line);
        return null;
    }

    private Shape findExistingShape(Shape shape) {
        int index = drawingModel.getShapes().indexOf(shape);
        if(index == -1) {
            return shape;
        }
        return drawingModel.getShapes().get(index);
    }

    private String readCommandCode(String line) {
        int separatorIndex = line.indexOf(DELIMITER);
        return line.substring(0, separatorIndex);
    }
}