package drawing.command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private static Command command; //command da se dohvati frame
    static final List<Command> commandStack = new ArrayList<>();
    static int count = 0;

    public static void addCommand(Command command) {
        if(count < commandStack.size()) {
            commandStack.clear();
            count = 0;
        }
        CommandManager.command = command;
        commandStack.add(command);
        doCommand();
    }

    public static void doCommand() {
        if(count < commandStack.size()){
            command.pnlDrawing.getFrame().getBtnUndo().setEnabled(true);
            commandStack.get(count++).doCommand();
        }
        if(count==commandStack.size()){
            command.pnlDrawing.getFrame().getBtnDo().setEnabled(false);
        }

    }

    public static void undoCommand(){
        if(count > 0){
            command.pnlDrawing.getFrame().getBtnDo().setEnabled(true);
            commandStack.get(--count).undoCommand();
        }
        if(count==0){
            command.pnlDrawing.getFrame().getBtnUndo().setEnabled(false);
        }
    }
}
