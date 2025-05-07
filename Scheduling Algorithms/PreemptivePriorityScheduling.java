import java.util.*;

public class PreemptivePriorityScheduling {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of processes: ");
    int n = sc.nextInt();

    int[] at = new int[n], bt = new int[n], pr = new int[n], rt = new int[n], ct = new int[n], tat = new int[n],
        wt = new int[n], pid = new int[n];

    for (int i = 0; i < n; i++) {
      System.out.println("Enter arrival time, burst time, and priority for process " + (i + 1) + ": ");
      pid[i] = i + 1; // Process ID
      at[i] = sc.nextInt();
      bt[i] = sc.nextInt();
      pr[i] = sc.nextInt();
      rt[i] = bt[i];
    }

    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - 1 - i; j++) {
        if (at[j] > at[j + 1] || (at[j] == at[j + 1] && pr[j] > pr[j + 1])) {

          int temp = at[j];
          at[j] = at[j + 1];
          at[j + 1] = temp;

          temp = bt[j];
          bt[j] = bt[j + 1];
          bt[j + 1] = temp;

          temp = pr[j];
          pr[j] = pr[j + 1];
          pr[j + 1] = temp;

          temp = rt[j];
          rt[j] = rt[j + 1];
          rt[j + 1] = temp;

          temp = pid[j];
          pid[j] = pid[j + 1];
          pid[j + 1] = temp;

        }
      }
    }

    int time = 0, completed = 0;
    boolean[] isCompleted = new boolean[n];

    for (int i = 0; i < n; i++) {
      isCompleted[i] = false;
    }

    while (completed < n) {
      int idx = -1;

      // Find the process with the highest (0=high, N=low) priority that has arrived
      // and is not completed
      for (int i = 0; i < n; i++) {
        if (at[i] <= time && !isCompleted[i])
          if (idx == -1 || pr[i] < pr[idx]) {
            idx = i;
          }

      }

      if (idx == -1) {
        time++;

        continue;
      } else {
        rt[idx]--;
        System.out.print(" P" + pid[idx]); // Print the process ID being executed
        time++;
      }

      if (rt[idx] == 0) {
        ct[idx] = time;
        tat[idx] = ct[idx] - at[idx];
        wt[idx] = tat[idx] - bt[idx];
        isCompleted[idx] = true;
        completed++;
      }
    }

    int sumT = 0, sumW = 0;

    for (int i = 0; i < n; i++) {
      sumT += tat[i];
      sumW += wt[i];
    }
    System.out.println("\nAverage Turnaround Time: " + (float) sumT / n);
    System.out.println("Average Waiting Time: " + (float) sumW / n);

    System.out.println("\nProcess\tAT\tBT\tPriority\tCT\tTAT\tWT");
    for (int i = 0; i < n; i++) {
      System.out
          .println((i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + pr[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
    }

    sc.close();
  }
}

// Input:
// Enter the number of processes: 3
// Enter arrival time, burst time, and priority for process 1:
// 0 5 2
// Enter arrival time, burst time, and priority for process 2:
// 1 3 1
// Enter arrival time, burst time, and priority for process 3:
// 2 8 3

// Output:
// P1 P2 P2 P2 P1 P1 P1 P1 P3 P3 P3 P3 P3 P3 P3 P3
// Process AT BT Priority CT TAT WT
// 1 0 5 2 9 9 4
// 2 1 3 1 4 3 0
// 3 2 8 3 16 14 6
