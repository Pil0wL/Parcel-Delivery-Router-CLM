

package parceldeliveryproject.ds;

import parceldeliveryproject.model.Parcel;

public class Sorter {
    public static void bubbleSortName(Parcel[] parcels) {
        for (int i = 0; i < parcels.length - 1; i++) {
            for (int j = 0; j < parcels.length - i - 1; j++) {
                if (parcels[j].Name.compareToIgnoreCase(parcels[j + 1].Name) > 0) {
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

            while (j >= 0 && parcels[j].ZIP > key.ZIP) {
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
                if (parcels[j].Weight < parcels[min].Weight) {
                min = j;
                }
            }
            Parcel temp = parcels[i];
            parcels[i] = parcels[min];
            parcels[min] = temp;
        }
    }
}
