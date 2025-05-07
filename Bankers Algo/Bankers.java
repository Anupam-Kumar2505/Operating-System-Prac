
// total resources=3
// total processes=n
import java.util.*;

class Bankers {
  public static void main(String[] args) {
    int n;

    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the number of processes:");
    n = sc.nextInt();

    int all[][] = new int[n][3];
    int totalAll[] = new int[3];
    int total[] = new int[3];
    int avl[] = new int[3];
    int claim[][] = new int[n][3];
    int rem[][] = new int[n][3];

    System.out.println("Enter total resources:");

    for (int i = 0; i < 3; i++) {
      System.out.println("Resource " + (i + 1));
      total[i] = sc.nextInt();
    }

    System.out.println("Total Resouces:");
    System.out.println("1|2|3");
    for (int i = 0; i < 3; i++) {
      System.out.print(total[i] + "|");
    }

    System.out.println();

    System.out.println("Allocate resources for " + n + " processes :");

    for (int i = 0; i < n; i++) {
      System.out.print("Process " + (i + 1) + ": ");
      for (int j = 0; j < 3; j++) {
        all[i][j] = sc.nextInt();
        totalAll[j] += all[i][j];
      }
      System.out.println();
    }

    System.out.println("Allocated resources:");
    for (int i = 0; i < n; i++) {
      System.out.println("Process " + (i + 1) + ": ");
      for (int j = 0; j < 3; j++) {
        System.out.print(all[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("Available Resources:");

    for (int i = 0; i < 3; i++) {
      avl[i] = total[i] - totalAll[i];
      System.out.print(avl[i] + " ");
    }
    System.out.println();
    System.out.println("Claim resources for " + n + " processes :");

    for (int i = 0; i < n; i++) {
      System.out.print("Process " + (i + 1) + ": ");
      for (int j = 0; j < 3; j++) {
        claim[i][j] = sc.nextInt();
      }
      System.out.println();
    }

    System.out.println("Claimed resources:");
    for (int i = 0; i < n; i++) {
      System.out.print("Process " + (i + 1) + ": ");
      for (int j = 0; j < 3; j++) {
        System.out.print(claim[i][j] + " ");
      }
      System.out.println();
    }

    for (int i = 0; i < n; i++) {

      for (int j = 0; j < 3; j++) {
        rem[i][j] = claim[i][j] - all[i][j];
      }
    }

    System.out.println("Remaining resources:");
    for (int i = 0; i < n; i++) {
      System.out.print("Process " + (i + 1) + ": ");
      for (int j = 0; j < 3; j++) {
        System.out.print(rem[i][j] + " ");
      }
      System.out.println();
    }

    ArrayList<Integer> arr = new ArrayList<>();
    int count = 0;
    boolean isDone[] = new boolean[n];
    boolean allDone = false;
    while (count < n && allDone == false) {

      allDone = true;
      for (int i = 0; i < n; i++) {

        if (isDone[i] == false) {
          allDone = false;
          break;
        }
      }

      for (int i = 0; i < n; i++) {
        System.out.println("Available Resources after checking Process " + (i + 1) + ": ");
        for (int x = 0; x < 3; x++) {
          System.out.print(avl[x] + " ");
        }
        System.out.println();
        if (rem[i][0] <= avl[0] && rem[i][1] <= avl[1] && rem[i][2] <= avl[2]) {
          rem[i][0] = 0;
          rem[i][1] = 0;
          rem[i][2] = 0;
          if (isDone[i] == false) {
            arr.add(i);
            avl[0] += all[i][0];
            avl[1] += all[i][1];
            avl[2] += all[i][2];
            isDone[i] = true;
            count = 0;
          }
        } else {
          count++;
        }
      }

      for (int x = 0; x < n; x++) {
        System.out.print(isDone[x] + " ");
      }
      System.out.println();

    }
    boolean safe = true;
    for (int i = 0; i < n; i++) {

      if (isDone[i] == false) {
        safe = false;
        break;
      }
    }

    if (safe) {
      System.out.println("Safe. No Deadlock will occur.");
      for (Integer i : arr) {
        System.out.print("P" + (i + 1) + " ");
      }
    } else {
      System.out.println("Unsafe. Deadlock will occur");
    }

  }
}