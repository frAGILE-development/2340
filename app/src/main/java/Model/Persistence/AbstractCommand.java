package Model.Persistence;

/**
 * Created by Bryce Watson on 7/01/2017
 */
@SuppressWarnings("SameReturnValue")
abstract class AbstractCommand {
    public final static CommandManager manager = new CommandManager();
    public abstract boolean execute();
    public abstract boolean undo();
}
