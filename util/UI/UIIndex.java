
package parceldeliveryproject.util.UI;

import parceldeliveryproject.util.*;
import parceldeliveryproject.model.*;
import parceldeliveryproject.ds.*;

public class UIIndex extends UIBase {
    
    public UIIndex(Platform Scope) {
        super(Scope);
        menuTitle = "Index Menu";
        displayChoices = new String[] {
            "Show Delivery Log",
            "Show Current ZIP Index",
        };

        
    }
    
    @Override
    public void selectedChoice(int choice) {
        switch(choice) {
            case 1:
                Scope.deliveryLog.printWhole();
                
                break;
            case 2:
                Scope.ZIPIndex.printWhole();

                break;
        }
    }
}