import java.util.*;

class SJF {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter number of processes:");
    int n = sc.nextInt();

    int[] pid = new int[n]; // Process IDs
    int[] arrivalTime = new int[n]; // Arrival times
    int[] burstTime = new int[n]; // Burst times
    int[] completionTime = new int[n]; // Completion times
    int[] turnAroundTime = new int[n]; // Turnaround times
    int[] waitingTime = new int[n]; // Waiting times
    int[] remainingTime = new int[n]; // Remaining burst times

    System.out.println("Enter arrival time and burst time of each process:");

    for (int i = 0; i < n; i++) {
      pid[i] = i + 1; // Process ID
      System.out.println("Process " + (i + 1) + " Arrival Time: ");
      arrivalTime[i] = sc.nextInt();
      System.out.println("Process " + (i + 1) + " Burst Time: ");
      burstTime[i] = sc.nextInt();
      remainingTime[i] = burstTime[i]; // Initialize remaining time to burst time
    }

    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - 1 - i; j++) {
        if (arrivalTime[j] > arrivalTime[j + 1]) {
          // Swap arrival times
          int temp = arrivalTime[j];
          arrivalTime[j] = arrivalTime[j + 1];
          arrivalTime[j + 1] = temp;

          // Swap burst times
          temp = burstTime[j];
          burstTime[j] = burstTime[j + 1];
          burstTime[j + 1] = temp;

          // Swap process IDs
          temp = pid[j];
          pid[j] = pid[j + 1];
          pid[j + 1] = temp;

          temp = remainingTime[j];
          remainingTime[j] = remainingTime[j + 1];
          remainingTime[j + 1] = temp;
        }
      }
    }

    int currentTime = 0;
    int completed = 0;
    boolean isCompleted[] = new boolean[n];

    for (int i = 0; i < n; i++) {
      isCompleted[i] = false;
    }

    while (completed < n) {
      int shortest = -1;
      // If process is available and not completed or if current time greater than
      // arrival time or if remaining time is greater than some other process's
      // remaining time toh woh chhote wale hi process ko run karo , find the shortest
      // remaining time

      // basically looping through all the processes to find the minium remaining
      // burst time process and which also follows the conditions
      for (int i = 0; i < n; i++) {

        if (arrivalTime[i] <= currentTime && isCompleted[i] == false) {
          if (shortest == -1 || remainingTime[i] < remainingTime[shortest]) {

            shortest = i;
          }
        }
      }

      if (shortest == -1) {
        // If no process is available, increment current time
        System.out.print(" IDLE");
        currentTime++;
        continue;
      } else {
        remainingTime[shortest]--; // Decrease remaining time of the selected process
        System.out.print(" P" + pid[shortest]); // Print the process ID being executed
        currentTime++; // Increment current time
      }

      // If remaining time of the selected process is 0, it is completed
      if (remainingTime[shortest] == 0) {

        completionTime[shortest] = currentTime;
        turnAroundTime[shortest] = completionTime[shortest] - arrivalTime[shortest];
        waitingTime[shortest] = turnAroundTime[shortest] - burstTime[shortest];
        isCompleted[shortest] = true;
        completed++;

      }

    }

    int sumT = 0, sumW = 0;

    for (int i = 0; i < n; i++) {
      sumT += turnAroundTime[i];
      sumW += waitingTime[i];
    }

    System.out.println();

    System.out.println("Average Turnaround Time: " + (float) sumT / n);
    System.out.println("Average Waiting Time: " + (float) sumW / n);

    sc.close();
  }
}

/*
 * Sample Input:
 * Enter number of processes: 3
 * Enter arrival time and burst time of each process:
 * Process 1 Arrival Time: 0
 * Process 1 Burst Time: 8
 * Process 2 Arrival Time: 1
 * Process 2 Burst Time: 4
 * Process 3 Arrival Time: 2
 * Process 3 Burst Time: 9
 * 
 * Sample Output:
 * P1 P1 P1 P1 P1 P1 P1 P1 P2 P2 P2 P2 P3 P3 P3 P3 P3 P3 P3 P3 P3
 * Average Turnaround Time: 15.666666
 * Average Waiting Time: 8.666666
 */
