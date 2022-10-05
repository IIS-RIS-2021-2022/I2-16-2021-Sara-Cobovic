package drawing.logging;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static List<String> logs = new ArrayList<>();

    public static void addLog(String log) {
        logs.add(log);
    }

    public static List<String> getLogs() {
        return logs;
    }
}