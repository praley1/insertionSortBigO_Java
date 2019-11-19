/* Patrick Raley
*  CSMC 451
*  Project 1
*/

import java.util.Random;

public class BenchmarkSorts {
    private int[] sizes;
    private float[] avgCritOpIte;
    private float[] avgRunTimeIte;
    private float[] stdDevOpIte;
    private float[] stdDevTimeIte;
    private float[] avgCritOpRecurse;
    private float[] avgRunTimeRecurse;
    private float[] stdDevOpRecurse;
    private float[] stdDevTimeRecurse;
    private SortInterface sorter;
    private float[] recursTime;
    private float[] iteTime;
    private long[] recursCritOp;
    private long[] iteCritOp;

    public BenchmarkSorts(int[] sizes){
        this.sizes = sizes;
        // CRITICAL OPERATION
        avgCritOpIte = new float[sizes.length];
        avgCritOpRecurse = new float[sizes.length];
        stdDevOpIte = new float[sizes.length];
        stdDevOpRecurse = new float[sizes.length];

        // RUNTIME
        avgRunTimeIte = new float[sizes.length];
        avgRunTimeRecurse = new float[sizes.length];
        stdDevTimeIte = new float[sizes.length];
        stdDevTimeRecurse = new float[sizes.length];

        sorter = new InsertionSort();
    }

    public void runSorts(){

       //for (int i = 0; i < 2; i++){
        for (int i = 0; i < sizes.length; i++){
            oneSizeSort(sizes[i]);
            calculateReportData(i);
        }
    }

    private void oneSizeSort(int size){
        recursTime = new float[50];
        iteTime = new float[50];
        recursCritOp = new long[50];
        iteCritOp = new long[50];

        for(int i = 0; i < 50; i++) {
            int[] array1 = getRandomArray(size);
            int[] array2 = copyArray(array1);

            try {
                sorter.recursiveSort(array1);
            } catch (UnsortedException e){
                System.out.println("RECURSIVE ARRAY NOT SORTED");
            }
            recursCritOp[i] = sorter.getCount();
            recursTime[i] = sorter.getTime();

            try {
                sorter.iterativeSort(array2);
            } catch (UnsortedException e){
                System.out.println("ITERATIVE ARRAY NOT SORTED");
            }
            iteCritOp[i] = sorter.getCount();
            iteTime[i] = sorter.getTime();
        }
    }

    private void calculateReportData(int i){

        for(int j = 0; j < 50; j++){
            avgCritOpRecurse[i] += recursCritOp[j];
            avgRunTimeRecurse[i] += recursTime[j];
            avgCritOpIte[i] += iteCritOp[j];
            avgRunTimeIte[i] += iteTime[j];
        }

        avgCritOpRecurse[i] = avgCritOpRecurse[i]/50;
        avgRunTimeRecurse[i] = avgRunTimeRecurse[i]/50;
        avgCritOpIte[i] = avgCritOpIte[i]/50;
        avgRunTimeIte[i] = avgRunTimeIte[i]/50;

        for(int j = 0; j < 50; j++){
            stdDevOpRecurse[i] += Math.pow((recursCritOp[j] - avgCritOpRecurse[i]), 2);
            stdDevTimeRecurse[i] += Math.pow((recursTime[j] - avgRunTimeRecurse[i]), 2);
            stdDevOpIte[i] += Math.pow((iteCritOp[j] - avgCritOpIte[i]), 2);
            stdDevTimeIte[i] += Math.pow((iteTime[j] - avgRunTimeIte[i]), 2);
        }
        stdDevOpRecurse[i] = stdDevOpRecurse[i]/50;
        stdDevOpRecurse[i] = (float)Math.pow(stdDevOpRecurse[i], 0.5);
        stdDevTimeRecurse[i] = stdDevTimeRecurse[i]/50;
        stdDevTimeRecurse[i] = (float)Math.pow(stdDevTimeRecurse[i], 0.5);
        stdDevOpIte[i] = stdDevOpIte[i]/50;
        stdDevOpIte[i] = (float)Math.pow(stdDevOpIte[i], 0.5);
        stdDevTimeIte[i] = stdDevTimeIte[i]/50;
        stdDevTimeIte[i] = (float)Math.pow(stdDevTimeIte[i], 0.5);

    }

    private int[] getRandomArray(int size) {
        int[] array = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(100);
        }
        return array;
    }

    private int[] copyArray(int[] array) {
        int[] copy = array.clone();
        return copy;
    }

    public void displayReport(){
        System.out.println("size\titerative:\tavg op\tstddev op\tavg time\tstddev time\trecursive:\tavg op\tstddev op\tavg time\tstddev time");
        for (int i = 0; i < sizes.length; i++){
            System.out.print("" + sizes[i]);
            if (sizes[i] < 100) System.out.print(" ");
            if (sizes[i] < 1000) System.out.print(" ");
            System.out.print("\t\t\t");
            System.out.print("\t" + avgCritOpIte[i]);
            System.out.print("\t" + stdDevOpIte[i]);
            System.out.print("\t" + avgRunTimeIte[i]);
            System.out.print("\t" + stdDevTimeIte[i]);
            System.out.print("\t\t");
            System.out.print("\t" + avgCritOpRecurse[i]);
            System.out.print("\t" + stdDevOpRecurse[i]);
            System.out.print("\t" + avgRunTimeRecurse[i]);
            System.out.print("\t" + stdDevTimeRecurse[i]);

            System.out.println();
        }

    }

}
