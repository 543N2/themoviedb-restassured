package RunTestFromJava;

import java.io.PrintWriter;

public class TestExecutionSummary {

    public static void main(String[] args) {
        RunJUnit5TestsFromJava runner = new RunJUnit5TestsFromJava();
//        runner.runAll();
        runner.runOne();

        org.junit.platform.launcher.listeners.TestExecutionSummary summary = runner.listener.getSummary();
        summary.printTo(new PrintWriter(System.out));
    }

}
