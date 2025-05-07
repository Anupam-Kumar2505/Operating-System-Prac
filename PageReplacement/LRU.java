import java.util.*;

public class LRU {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the number of pages: ");
    int p = sc.nextInt();
    System.out.println("Enter the number of frames: ");
    int f = sc.nextInt();
    int[] pages = new int[p];

    Set<Integer> containsVal = new HashSet<>();
    int[][] table = new int[p][f];
    for (int i = 0; i < p; i++) {
      for (int j = 0; j < f; j++) {
        table[i][j] = -1; // Initialize with -1 to indicate empty frames
      }
    }
    System.out.println("Enter the page numbers: ");
    for (int i = 0; i < p; i++) {
      pages[i] = sc.nextInt();
    }
    int hits = 0, faults = 0, index = 0;

    for (int i = 0; i < p; i++) {
      int page = pages[i];

      if (i > 0) {
        for (int j = 0; j < f; j++) {
          table[i][j] = table[i - 1][j];
        }
      }

      if (containsVal.contains(page)) {
        System.out.print("H ");
        hits++;
      } else {
        System.out.print("F ");
        faults++;

        if (containsVal.size() < f) {

          containsVal.add(page);
          containsVal.remove(table[i][index]);
          table[i][index] = page;
          index = (index + 1) % f;

        } else {
          int farthest = Integer.MAX_VALUE, indexToReplace = -1;
          for (int j = 0; j < f; j++) {
            int nextUse = Integer.MAX_VALUE;
            for (int k = i - 1; k >= 0; k--) {
              if (pages[k] == table[i][j]) {
                nextUse = k;
                break;
              }
            }

            // Find the farthest page to replace
            if (nextUse < farthest) {
              farthest = nextUse;
              indexToReplace = j;

            }

          }
          // Replace the page at indexToReplace with the new page
          containsVal.remove(table[i][indexToReplace]);
          table[i][indexToReplace] = page;
          containsVal.add(page);
        }

        // System.out.println("Replacing page " + table[i][indexToReplace] + " with " +
        // page);

      }

    }
    System.out.println("\nPage Table (frames as rows):");
    for (int j = 0; j < f; j++) {

      System.out.print("Frame " + j + 1 + ": ");
      for (int i = 0; i < p; i++) {
        if (table[i][j] == -1) {
          System.out.print("- ");
        } else {
          System.out.print(table[i][j] + " ");
        }
      }
      System.out.println();
    }

    System.out.println("\nTotal Page Hits: " + hits);
    System.out.println("Total Page Faults: " + faults);
  }
}