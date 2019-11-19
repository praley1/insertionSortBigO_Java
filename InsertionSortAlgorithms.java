/* Patrick Raley
*  CSMC 451
*  Project 1
*/

public class InsertionSortAlgorithms {

    public static long insertionSortRecursive( int[] a, int first, int last, long loopCounterRecurse)
    {
        if ( first < last)
        {
            //SORT ALL BUT LAST ELEMENT
            loopCounterRecurse = insertionSortRecursive( a, first, last -1, loopCounterRecurse );
            //INSERT LAST ELEMENT IN SORTED ORDER FROM FIRST THROUGH LAST POSITIONS
            loopCounterRecurse = insertInOrderRecursive(a[last], a, first, last-1, loopCounterRecurse);
        }
        return loopCounterRecurse;
    }

    public static long insertInOrderRecursive(int element, int[] a, int first, int last, long loopCounterRecurse) {
        if(element >= a[last]) {
            a[last + 1] = element;
        }
        else if (first < last) {
            loopCounterRecurse ++;
            a[last + 1] = a[last];
            loopCounterRecurse = insertInOrderRecursive(element, a, first, last - 1, loopCounterRecurse);
        } else // first == last and element < a[last]
        {
            loopCounterRecurse ++;
            a[last + 1] = a[last];
            a[last] = element;
        }
        return  loopCounterRecurse;
    }


    public static long insertionSortIterative(int[] a, int first, int last, long loopCounterIte) {

        // SORTS ARRAY ELEMENTS  a[first] through a[last] iteratively.
        for (int unsorted = first + 1; unsorted <= last; unsorted++) {
            int firstUnsorted = a[unsorted];
            loopCounterIte = insertInOrderIterative(firstUnsorted, a, first, unsorted - 1, loopCounterIte);
        }
        return loopCounterIte;
    }
    public static long insertInOrderIterative (int element, int[] a, int begin, int end, long loopCounterIte) {
        // INSERTS ELEMENTS INTO SORTED ARRAY  elements a[begin] through a[end].
        int index = end;
        while ((index >= begin) && (element < a[index]))
        {
            loopCounterIte ++;
            a[index + 1] = a[index]; // MAKE ROOM
            index --;
        }
        // Assertion: a[index+1] is available.
        a[index + 1] = element; // INSERT
        return loopCounterIte;
    }

}
