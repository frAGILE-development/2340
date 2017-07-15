package Model.Persistence;

import Model.FoundItem;
import Model.LostItem;

/**
 * Created by Bryce Watson on 7/14/2017.
 */

public class AddFoundItemCommand extends AbstractCommand {
    FoundItem item;

    public AddFoundItemCommand(FoundItem i) {
        item = i;
    }

    @Override
    public boolean execute() {
        ManagementFacade mf = ManagementFacade.getInstance();
        mf.addFoundItem(item);
        return true;
    }

    @Override
    public boolean undo() {
        ManagementFacade mf = ManagementFacade.getInstance();
        mf.removeFoundItem(item);
        return true;
    }
}
