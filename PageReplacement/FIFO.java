
// FIFO Page Replacement Algorithm Implementation in Java
import java.util.*;

public class FIFO {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of pages: ");
    int p = sc.nextInt();
    System.out.print("Enter the number of frames: ");
    int f = sc.nextInt();
    int[] pages = new int[p];

    int table[][] = new int[p][f];
    int hits = 0, faults = 0;
    for (int i = 0; i < p; i++) {
      for (int j = 0; j < f; j++) {
        table[i][j] = -1;
      }
    }

    System.out.println("Enter the page numbers: ");
    for (int i = 0; i < p; i++) {
      pages[i] = sc.nextInt();
    }

    Set<Integer> containsVal = new HashSet<>();

    int index = 0;

    for (int i = 0; i < p; i++) {
      int page = pages[i];

      // Copy previous frame content
      if (i > 0) {
        for (int j = 0; j < f; j++) {
          table[i][j] = table[i - 1][j];
        }
      }
      // Check if page is already in frames

      if (containsVal.contains(page)) {
        System.out.print("H ");
        hits++;
      } else {
        System.out.print("F ");
        faults++;
        containsVal.add(page);
        containsVal.remove(table[i][index]);
        table[i][index] = page;
        index = (index + 1) % f;
      }

    }

    System.out.println("\nPage Table (frames as rows):");

    System.out.print("Page   ");
    for (int i = 0; i < p; i++) {
      System.out.print(" " + pages[i]);
    }
    System.out.println();

    for (int frame = 0; frame < f; frame++) {
      System.out.print("Frame" + frame + 1 + " ");
      for (int i = 0; i < p; i++) {
        if (table[i][frame] == -1)
          System.out.print("- ");
        else
          System.out.print(table[i][frame] + " ");
      }
      System.out.println();
    }

    System.out.println("\nTotal Page Hits: " + hits);
    System.out.println("Total Page Faults: " + faults);

    sc.close();
  }
}