package Model.Persistence;

/**
 * Created by robertwaters on 3/11/16.
 */
@SuppressWarnings("SameReturnValue")
abstract class AbstractCommand {
    public final static CommandManager manager = new CommandManager();
    public abstract boolean execute();
    public abstract boolean undo();
}
