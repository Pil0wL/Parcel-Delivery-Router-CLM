
package parceldeliveryproject.util.UI;
import parceldeliveryproject.util.Platform;


public class UIBase {

    protected Platform Scope;
    public String[] displayChoices;
    public UIBase(Platform Scope) {
        this.Scope = Scope;
    }
    
    public void selectedChoice(int choice) {}


    public static final String choiceFormat = "[%d] %s";
    public static int readChoices(UIBase selected) {
        int index;
        String[] displayChoices = selected.displayChoices;
        int max = displayChoices.length;
        for (index = 0; index < max; index++) {
            String toPrint = String.format(choiceFormat, index + 1, displayChoices[index]);
            System.out.println(toPrint);
        }

        return index; // gives the last choice; an array of size zero gives 0; size of one gives 1;
    }

}