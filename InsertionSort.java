/* Patrick Raley
*  CSMC 451
*  Project 1
*/

import java.io.IOException;

public class InsertionSort implements  SortInterface{
    private long critOpCounter;
    private float elapsedTime;

    public void recursiveSort(int[] list) throws UnsortedException{
        //printArray(list);
        critOpCounter = -1;
        elapsedTime = -1;
        long startTimeRecurs = System.nanoTime();
        long criticalOpCounterRecurs;
        try {
            criticalOpCounterRecurs = InsertionSortAlgorithms.insertionSortRecursive(list, 0, list.length - 1, 0);
        } catch(Exception e){
            throw new UnsortedException();
        } catch(StackOverflowError e){
            throw new UnsortedException();
        }
        long estimatedTimeRecurs = System.nanoTime() - startTimeRecurs;
        float timeInMilliRecurse = ((float)estimatedTimeRecurs/1000000);
        //list[1] = 2;   // usually an error
        if(!checkArray(list)){
            throw new UnsortedException();
        }
        System.out.print("rec: ");
        printArray(list);
        critOpCounter = criticalOpCounterRecurs;
        elapsedTime = timeInMilliRecurse;
    }

    public void iterativeSort(int[] list) throws UnsortedException{
        critOpCounter = -1;
        elapsedTime = -1;
        //printArray(list);

        long startTimeIte = System.nanoTime();
        long criticalOpCounterIte;
        try {
            criticalOpCounterIte = InsertionSortAlgorithms.insertionSortIterative(list, 0, list.length - 1, 0);
        } catch (Exception e){
            throw new UnsortedException();
        } catch(StackOverflowError e){
            throw new UnsortedException();
        }
        long estimatedTimeIte = System.nanoTime() - startTimeIte;
        float timeInMilliIte = ((float)estimatedTimeIte/1000000);
        if(!checkArray(list)){
            throw new UnsortedException();
        }
        System.out.print("itr: ");
        printArray(list);
        critOpCounter = criticalOpCounterIte;
        elapsedTime = timeInMilliIte;
    }

    private boolean checkArray(int[] array) {
        boolean good = true;
        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            if (array[i] > array[i+1]) {
                good = false;
                break;
            }
        }
        return good;
    }

    public long getCount(){
        return critOpCounter;
    }

    public float getTime(){
        return elapsedTime;
    }

    private void printArray(int[] array) {
        if ( array.length <= 10000) {
            for (int i : array) {
                System.out.print(i + " ");
            }
        }
        System.out.println("");
    }

}
