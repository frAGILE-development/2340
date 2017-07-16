package Model.Persistence;
import Model.*;
import java.io.Serializable;

/**
 * Created by Bryce Watson on 7/14/2017.
 */

public class AddItemCommand extends AbstractCommand{

    Item item;
    /**
     *constructor
     * @param i
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
