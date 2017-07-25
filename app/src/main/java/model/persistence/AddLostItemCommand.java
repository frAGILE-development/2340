package model.persistence;

import model.LostItem;

/**
 * Created by Bryce Watson on 7/14/2017.
 * Command for adding a lost item
 */

public class AddLostItemCommand extends AbstractCommand {
     private final LostItem item;
    /**
     *constructor
     * @param i item to be added
     */
    public AddLostItemCommand(LostItem i) {
        item = i;
    }
    /**
     *add lost item
     * @return true when completed
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
