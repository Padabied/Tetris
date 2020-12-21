package command;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private static Map<Operation, Command> commands = new HashMap<>();

    static {
        //Загрузить в мапу все команды
    }

    public static void execute(Operation operation) {
        commands.get(operation).execute();
    }
}
