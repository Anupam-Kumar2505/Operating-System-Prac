import java.util.*;

public class LFU {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter number of pages: ");
    int p = sc.nextInt();
    System.out.println("Enter number of frames: ");
    int f = sc.nextInt();
    int[] pages = new int[p];
    System.out.println("Enter page numbers: ");
    for (int i = 0; i < p; i++) {
      pages[i] = sc.nextInt();
    }

    int[][] table = new int[p][f];
    for (int i = 0; i < p; i++) {
      for (int j = 0; j < f; j++) {
        table[i][j] = -1; // Initialize with -1 to indicate empty frames
      }
    }
    int pageFaults = 0;
    int pageHits = 0;

    Set<Integer> containsVal = new HashSet<>();
    Map<Integer, Integer> pageFrequency = new HashMap<>();
    int index = 0;
    int lfuPointer = 0;

    for (int i = 0; i < p; i++) {

      // Copy previous frame content
      if (i > 0) {
        for (int j = 0; j < f; j++) {
          table[i][j] = table[i - 1][j];
        }
      }

      int page = pages[i];
      //ye toh pata hi hai hit condition hai
      if (containsVal.contains(page)) {
        pageHits++;
        pageFrequency.put(page, pageFrequency.get(page) + 1);
        System.out.print("H ");
      } else {
        //ye hai page fault condition
        pageFaults++;
        System.out.print("F ");
        pageFrequency.put(page, pageFrequency.getOrDefault(page, 0) + 1);
        // Check if frame has space
        if (containsVal.size() < f) {
          // Frame has space
          containsVal.add(page);
          table[i][index] = page;
          //ye coz frame cyclic rakh rahe hai , waise farak padta nahi hai but rakhdo
          index = (index + 1) % f;
        } else {
          // ab yaha important cheez ho raha hai , pehle humko min frequency nikalna hai
          // irrespective of kitne logo ke paas wo min freq hai , bus for now hume min freq chahiye
          int minFreq = Integer.MAX_VALUE;
          //ye hum set ko iterate kar rahe hai 
          for (int val : containsVal) {
            // dekh rahe hai if some other page in frame has lower freq than current page
            minFreq = Math.min(minFreq, pageFrequency.getOrDefault(val, 0));
          }

          //ab yaha hum dekhenge ki konse replace kare
          int pageToReplace = -1;
          for (int j = lfuPointer; j < i; j++) {
            int candidate = pages[j];
            // yaha  lfu pointer basically humara woh cross wala hain , last replace jahaa se kiya tha waha cross laga do 
            // and usko +1 kardo ki next page se dekhe agli baar replace karne ke liye

            // ye basically if condition bol raha hai ki kya aapke paas minfreq hai and aap frame me ho , jo bhi pages array
            // jo ki input array hai usme ye condition after lfupointer se first sahi karega , usko replace kar do
            if (containsVal.contains(candidate) &&
                pageFrequency.getOrDefault(candidate, 0) == minFreq) {
              pageToReplace = candidate;
              lfuPointer = j + 1; // update pointer for next round
              break;
            }
          }

          // Remove and replace
          containsVal.remove(pageToReplace);
          pageFrequency.put(pageToReplace, 0);
          containsVal.add(page);

          // Update frame table with the new page
          for (int k = 0; k < f; k++) {
            if (table[i][k] == pageToReplace) {
              table[i][k] = page;
              break;
            }
          }
        }
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

    System.out.println("\nTotal Page Faults: " + pageFaults);
    System.out.println("Total Page Hits: " + pageHits);

    for (int i : pageFrequency.keySet()) {
      System.out.println("Page " + i + " Frequency: " + pageFrequency.get(i));
    }
  }
}
