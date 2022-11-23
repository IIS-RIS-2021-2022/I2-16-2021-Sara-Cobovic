package drawing.logging;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private ILogger logger;

    private List<String> logs = new ArrayList<>();

    public void doLogging() {
        logs.add(logger.log());
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogger(ILogger logger) {
        this.logger = logger;
    }
}