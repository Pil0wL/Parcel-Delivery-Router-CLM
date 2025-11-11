

package parceldeliveryproject.ds;

import parceldeliveryproject.model.Parcel;

public class Sorter {
    public static void bubbleSortName(Parcel[] parcels) {
        for (int i = 0; i < parcels.length - 1; i++) {
            for (int j = 0; j < parcels.length - i - 1; j++) {
                if (parcels[j].name.compareToIgnoreCase(parcels[j + 1].name) > 0) {
                    Parcel temp = parcels[j];
                    parcels[j] = parcels [j + 1];
                    parcels[j + 1] = temp;
                }
            }
        }
    }
    
    public static void insertionSortZIP(Parcel[] parcels) {
        for (int i = 1; i < parcels.length; i++) {
            Parcel key = parcels[i];
            int j = 1 - 1;
            while (j >= 0 && parcels [j].zip > key.zip) {
                parcels[j + 1] = parcels[j];
                j--;
            }
            parcels[j + 1] = key;
        }
    }
    
    public static void selectionSortWeight(Parcel[] parcels) {
        for (int i = 0; i < parcels.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < parcels.length; j++) {
                if (parcels[j].weight < parcels[min].weight) {
                min = j;
                }
            }
            Parcel temp = parcels[i];
            parcels[i] = parcels[min];
            parcels[min] = temp;
        }
    }
}
