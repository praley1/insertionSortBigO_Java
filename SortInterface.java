/* Patrick Raley
*  CSMC 451
*  Project 1
*/
public interface SortInterface {

    void recursiveSort(int[] list) throws UnsortedException;

    void iterativeSort(int[] list) throws UnsortedException;

    long getCount();

    float getTime();

}
