package model.persistence;

import model.FoundItem;

/**
 * Created by Bryce Watson on 7/14/2017.
 * Command for adding a found item
 */

public class AddFoundItemCommand extends AbstractCommand {
    private final FoundItem item;
    /**
     *constructor
     * @param i found item
     */
    public AddFoundItemCommand(FoundItem i) {
        item = i;
    }
    /**
     *add item
     * @return true when completed
     */
    @Override
    public boolean execute() {
        ManagementFacade mf = ManagementFacade.getInstance();
        mf.addFoundItem(item);
        return true;
    }
    /**
     *removes item
     * @return true when completed
     */
    @Override
    public boolean undo() {
        ManagementFacade mf = ManagementFacade.getInstance();
        mf.removeFoundItem(item);
        return true;
    }
}
