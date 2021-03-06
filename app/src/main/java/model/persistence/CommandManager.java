package model.persistence;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bryce Watson on 7/01/2017
 */
public class CommandManager {

    /**
     * This maintains a list of commands executed in order
     * Front of list is oldest command, tail is newest
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<AbstractCommand> history = new LinkedList<>();

    /**
     * This maintains a list of commands that have been undone
     * Front of list is oldest command, tail is most recent
     */
    private final List<AbstractCommand> redoList = new LinkedList<> ();

    /**
     * Execute a command and add to history if the command returned true
     * The history is emptied if the command returns false
     *
     * @param command the command to execute
     */
    public void executeCommand(final AbstractCommand command) {
        if (command.execute()) {
            history.add(command);
        } else {
            history.clear();
        }
    }

//    /**
//     * undo the most recent command executed
//     */
//    public void undoCommand() {
//        assert history.size() > 0;
//        final AbstractCommand command = history.remove(history.size() - 1);
//        command.undo();
//        redoList.add(command);
//    }
//
//    /**
//     * execute the most recent command undone
//     */
//    public void redoCommand() {
//        assert redoList.size() > 0;
//        final AbstractCommand command = redoList.remove(redoList.size() - 1);
//        command.execute();
//        history.add(command);
//    }

}
