package base.exception;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

class LoggingException extends Exception {
    private static Logger logger = Logger.getLogger("LoggerException:");

    public LoggingException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}

public class LoggingExceptions {
    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.out.println("e:" + e);
            System.err.println("caught :" + e);
        }

//        try {
//            throw new LoggingException();
//        } catch (LoggingException e) {
//            System.err.println("caught :" + e);
//        }
    }
}
