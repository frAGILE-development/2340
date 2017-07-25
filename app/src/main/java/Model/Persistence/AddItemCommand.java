package Model.Persistence;
import Model.*;

/**
 * Created by Bryce Watson on 7/14/2017.
 * Command for adding an item
 */

public class AddItemCommand extends AbstractCommand{

    private final Item item;
    /**
     *constructor
     * @param i item to be added
     *
     */
    public AddItemCommand(Item i) {
            item = i;
        }
    /**
     *add item
     * @return true when completed
     */
    @Override
    public boolean execute() {
        ManagementFacade mf = ManagementFacade.getInstance();
        mf.addItem(item);
        return true;
    }
    /**
     *removes item
     * @return true when completed
     */
    @Override
    public boolean undo() {
        ManagementFacade mf = ManagementFacade.getInstance();
        mf.removeItem(item);
        return true;
    }



}
