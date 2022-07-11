import java.util.*;
import java.util.stream.Collectors;

class LinkdListsandArrayList {

    public static void main(String [] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            linkedList.add(i);
            arrayList.add(i);
        }

        final long startTimeLL = System.nanoTime();
        linkedList.get(100);
        final long endTimeLL = System.nanoTime();

        final long startTimeAL = System.nanoTime();
        arrayList.get(100);
        final long endTimeAL = System.nanoTime();

        long totalTimeLL = endTimeLL - startTimeLL;
        long totalTimeAL = endTimeAL - startTimeAL;

        System.out.println("Total time for Linked List: " + totalTimeLL);
        System.out.println("Total time for Array List: " + totalTimeAL);
        arrayList.remove(index: 5000);
        linkedList.remove(index: 5000);
    }
}
