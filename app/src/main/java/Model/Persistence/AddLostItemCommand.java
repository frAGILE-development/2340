package Model.Persistence;

import Model.Item;
import Model.LostItem;

/**
 * Created by Bryce Watson on 7/14/2017.
 */

public class AddLostItemCommand extends AbstractCommand {
     LostItem item;

    public AddLostItemCommand(LostItem i) {
        item = i;
    }

    @Override
    public boolean execute() {
        ManagementFacade mf = ManagementFacade.getInstance();
        mf.addLostItem(item);
        return true;
    }

    @Override
    public boolean undo() {
        ManagementFacade mf = ManagementFacade.getInstance();
        mf.removeLostItem(item);
        return true;
    }

}
