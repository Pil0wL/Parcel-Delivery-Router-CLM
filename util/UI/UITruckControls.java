
package parceldeliveryproject.util.UI;

import parceldeliveryproject.util.*;
import parceldeliveryproject.model.*;
import parceldeliveryproject.ds.*;

public class UITruckControls extends UIBase {
    
    public UITruckControls(Platform Scope) {
        super(Scope);
        menuTitle = "Truck Controls";
        displayChoices = new String[] {
            "(Iterate Delivery Transport)",
            "Display Current cargo",
        };

        
    }
    
    @Override
    public void selectedChoice(int choice) {
        switch(choice) {
            case 1:
                Scope.Truck.iterate();
                
                break;
            case 2:
                Scope.Truck.displayCargo();

                break;
        }
    }
}