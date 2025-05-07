import java.util.*;

public class MemoryAllocation {

   static void first(int p[], int m[]) {
      int temp[] = new int[m.length];// so that i can decrease the size of memory blocks as it is filled
      int block[] = new int[p.length];// to store index of block to which a process is allocated
      for (int i = 0; i < m.length; i++) {
         temp[i] = m[i];
      }
      for (int i = 0; i < block.length; i++) {
         block[i] = -1;
      }
      for (int i = 0; i < p.length; i++) {
         for (int j = 0; j < m.length; j++) {
            if (p[i] < temp[j]) {
               temp[j] -= p[i];
               block[i] = j;
               break;
            }
         }
      }
      System.out.println("Processes stored in blocks:");
      for (int i = 0; i < p.length; i++) {
         if (block[i] != -1) {
            System.out.println("Process " + i + " :" + m[block[i]] + "KB " +
                  "\tHole size: " + temp[block[i]]);
         } else {
            System.out.println("Process " + i + " :not allocated");
         }
      }
   }

   static void best(int p[], int m[]) {
      int temp[] = new int[m.length];
      int block[] = new int[p.length];
      for (int i = 0; i < m.length; i++) {
         temp[i] = m[i];
      }
      for (int i = 0; i < block.length; i++) {
         block[i] = -1;
      }
      for (int i = 0; i < p.length; i++) {
         int bestIdx = -1;
         int bestFit = Integer.MAX_VALUE;
         for (int j = 0; j < m.length; j++) {
            if (p[i] <= temp[j] && temp[j] < bestFit) {
               bestFit = temp[j];
               bestIdx = j;
            }
         }
         if (bestIdx != -1) {
            temp[bestIdx] -= p[i];
            block[i] = bestIdx;
         }
      }
      System.out.println("Processes stored in blocks:");
      for (int i = 0; i < p.length; i++) {
         if (block[i] != -1) {
            System.out.println("Process " + i + " :" + m[block[i]] + "KB " +
                  "\tHole size: " + temp[block[i]]);
         } else {
            System.out.println("Process " + i + " :not allocated");
         }
      }
   }

   static void worst(int p[], int m[]) {
      int temp[] = new int[m.length];
      int block[] = new int[p.length];
      for (int i = 0; i < m.length; i++) {
         temp[i] = m[i];
      }
      for (int i = 0; i < block.length; i++) {
         block[i] = -1;
      }
      for (int i = 0; i < p.length; i++) {
         int worstIdx = -1;
         int worstFit = Integer.MIN_VALUE;

         for (int j = 0; j < m.length; j++) {
            if (p[i] <= temp[j] && temp[j] > worstFit) {
               worstFit = temp[j];
               worstIdx = j;
            }
         }

         if (worstIdx != -1) {
            temp[worstIdx] -= p[i];
            block[i] = worstIdx;
         }
      }
      System.out.println("Processes stored in blocks:");
      for (int i = 0; i < p.length; i++) {
         if (block[i] != -1) {
            System.out.println("Process " + i + " :" + m[block[i]] + "KB " +
                  "\tHole size: " + temp[block[i]]);
         } else {
            System.out.println("Process " + i + " :not allocated");
         }
      }
   }

   static void next(int p[], int m[]) {
      int temp[] = new int[m.length];
      int block[] = new int[p.length];
      int currentIndex = 0;
      for (int i = 0; i < m.length; i++) {
         temp[i] = m[i];
      }
      for (int i = 0; i < block.length; i++) {
         block[i] = -1;
      }

      for (int i = 0; i < p.length; i++) {
         int j = 0;
         while (j < m.length) {

            if (p[i] <= temp[currentIndex]) {
               temp[currentIndex] -= p[i];
               block[i] = currentIndex;
               break;
            }
            j++;
            currentIndex = (currentIndex + 1) % m.length;

         }
      }
      System.out.println("Processes stored in blocks:");
      for (int i = 0; i < p.length; i++) {
         if (block[i] != -1) {
            System.out.println("Process " + i + " :" + m[block[i]] + "KB " +
                  "\tHole size: " + temp[block[i]]);
         } else {
            System.out.println("Process " + i + " :not allocated");
         }
      }

   }

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter number of processes: ");
      int numProcesses = sc.nextInt();
      int[] p = new int[numProcesses];
      System.out.println("Enter Processes Sizes:");
      for (int i = 0; i < numProcesses; i++) {
         p[i] = sc.nextInt();
      }
      System.out.print("Enter number of memory blocks: ");
      int numBlocks = sc.nextInt();
      int[] m = new int[numBlocks];
      System.out.println("Enter Memory Blocks Sizes:");
      for (int i = 0; i < numBlocks; i++) {
         m[i] = sc.nextInt();
      }
      boolean running = true;
      while (running) {
         System.out.println("\nChoose Memory Allocation Strategy:");
         System.out.println("1. First Fit");
         System.out.println("2. Best Fit");
         System.out.println("3. Worst Fit");
         System.out.println("4. Next Fit");
         System.out.println("5. Exit");
         System.out.print("Enter your choice: ");
         int choice = sc.nextInt();
         switch (choice) {
            case 1:
               first(p, m);
               break;
            case 2:
               best(p, m);
               break;
            case 3:
               worst(p, m);
               break;
            case 4:
               next(p, m);
               break;
            case 5:
               running = false;
               break;
            default:
               System.out.println("Invalid choice!");
         }
         System.out.println();
      }
   }
}

/*
Example Input:
Enter number of processes: 4
Enter Processes Sizes:
212
417
112
426
Enter number of memory blocks: 5
Enter Memory Blocks Sizes:
100
500
200
300
600

Example Output for First Fit:
Processes stored in blocks:
Process 0 :500KB 	Hole size: 288
Process 1 :600KB 	Hole size: 183
Process 2 :200KB 	Hole size: 88
Process 3 :not allocated

Example Output for Best Fit:
Processes stored in blocks:
Process 0 :300KB 	Hole size: 88
Process 1 :600KB 	Hole size: 183
Process 2 :200KB 	Hole size: 88
Process 3 :not allocated

Example Output for Worst Fit:
Processes stored in blocks:
Process 0 :600KB 	Hole size: 388
Process 1 :500KB 	Hole size: 83
Process 2 :300KB 	Hole size: 188
Process 3 :not allocated

Example Output for Next Fit:
Processes stored in blocks:
Process 0 :500KB 	Hole size: 288
Process 1 :600KB 	Hole size: 183
Process 2 :200KB 	Hole size: 88
Process 3 :not allocated
*/