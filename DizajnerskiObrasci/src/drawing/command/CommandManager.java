package drawing.command;

import drawing.view.PnlDrawing;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private static Command command; //command da se dohvati frame
    static final List<Command> commandStack = new ArrayList<>();
    static int count = 0;

    public static void clearCommands() {
        commandStack.clear();
        count = 0;
    }

    public static void addCommand(Command command) {
        while(commandStack.size() > count) {
            commandStack.remove(commandStack.size()-1);
        }

        CommandManager.command = command;
        commandStack.add(command);
        doCommand();
    }

    public static void doCommand() {
        if(count < commandStack.size()){
            command.pnlDrawing.getFrame().getBtnUndo().setEnabled(true);
            Command command = commandStack.get(count++);
            command.doCommand();
            command.pnlDrawing.getFrame().getLogger().setLogger(command);
            command.pnlDrawing.getFrame().getLogger().doLogging();
        }
        if(count==commandStack.size()){
            command.pnlDrawing.getFrame().getBtnDo().setEnabled(false);
        }

    }

    public static void undoCommand(){
        if(count > 0){
            command.pnlDrawing.getFrame().getBtnDo().setEnabled(true);
            Command command = commandStack.get(--count);
            command.undoCommand();
            command.pnlDrawing.getFrame().getLogger().setLogger(command);
            command.pnlDrawing.getFrame().getLogger().doLogging();
        }
        if(count==0){
            command.pnlDrawing.getFrame().getBtnUndo().setEnabled(false);
        }
    }
}