package after;

import java.util.ArrayDeque;
import java.util.Queue;

public class JobScheduler {
    private final Queue<Command> queue = new ArrayDeque<>();

    public void schedule(Command command) {
        queue.add(command);
        System.out.println("Queued job: " + command.getName());
    }

    public void runAll() {
        while (!queue.isEmpty()) {
            runNext();
        }
    }

    private void runNext() {
        Command command = queue.poll();
        if (command == null) {
            System.out.println("No jobs to run.");
            return;
        }

        System.out.println("Running job: " + command.getName());
        command.execute();
    }
}
