package Model.Persistence;

import Model.Item;
import Model.LostItem;

/**
 * Created by Bryce Watson on 7/14/2017.
 */

public class AddLostItemCommand extends AbstractCommand {
     LostItem item;
    /**
     *constructor
     * @param i
     */
    public AddLostItemCommand(LostItem i) {
        item = i;
    }
    /**
     *add lost item
     * @return true when complteddd
     */
    @Override
    public boolean execute() {
        ManagementFacade mf = ManagementFacade.getInstance();
        mf.addLostItem(item);
        return true;
    }
    /**
     *remove item
     * @return true when completed
     */
    @Override
    public boolean undo() {
        ManagementFacade mf = ManagementFacade.getInstance();
        mf.removeLostItem(item);
        return true;
    }

}
