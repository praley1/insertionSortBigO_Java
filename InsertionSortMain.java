/* Patrick Raley
*  CSMC 451
*  Project 1
*/
public class InsertionSortMain {
    public static void main(String[] args) {

        int[] sizes = {10,50,100,500,1000,5000,10000,50000,75000,100000};
        BenchmarkSorts sort = new BenchmarkSorts(sizes);
        sort.runSorts();
        sort.displayReport();

    }
}
