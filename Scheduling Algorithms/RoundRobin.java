import java.util.Scanner;

public class RoundRobin {
  static int[] currentQueue = new int[10];
  static int front = -1, rear = -1;

  // Circular Queue operations
  static void enqueue(int process) {
    if (front == -1) {
      front++;
      rear++;
      currentQueue[rear] = process;
    } else if ((rear + 1) % 10 == front) {
      // System.out.println("\nQueue Full");
    } else {
      rear = (rear + 1) % 10;
      currentQueue[rear] = process;
    }
  }

  static int dequeue() {
    int val = -1;
    if (front == -1) {
      // System.out.println("\nQueue Empty");
    } else if (front == rear) {
      val = currentQueue[front];
      front = -1;
      rear = -1;
    } else {
      val = currentQueue[front];
      front = (front + 1) % 10;
    }
    return val;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n, time = 0, complete = 0, timeQuantum;
    float avgTAT = 0, avgWT = 0;
    // int[][] gantt = new int[100][3];
    // int ganttSize = 0;

    System.out.println("This is Round Robin process scheduling");
    System.out.print("Enter number of processes: ");
    n = sc.nextInt();
    System.out.print("Enter Time Quantum: ");
    timeQuantum = sc.nextInt();

    // Initialize arrays for process details
    int[] at = new int[n]; // Arrival Time
    int[] bt = new int[n]; // Burst Time
    int[] ct = new int[n]; // Completion Time
    int[] tat = new int[n]; // Turnaround Time
    int[] wt = new int[n]; // Waiting Time
    int[] rt = new int[n]; // Remaining Burst Time
    boolean[] isComplete = new boolean[n]; // Completion Status
    boolean[] inQueue = new boolean[n]; // Queue Status
    int[] pid = new int[n]; // Process ID

    // Input process details
    for (int i = 0; i < n; i++) {
      System.out.println("Enter process arrival time, burst time: ");
      at[i] = sc.nextInt();
      bt[i] = sc.nextInt();
      rt[i] = bt[i];
      pid[i] = i + 1; // Process ID
      isComplete[i] = false;
      inQueue[i] = false;
    }

    // Sort processes by arrival time using bubble sort
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (at[j] > at[j + 1]) {
          // Swap arrival time
          int temp = at[j];
          at[j] = at[j + 1];
          at[j + 1] = temp;

          // Swap burst time
          temp = bt[j];
          bt[j] = bt[j + 1];
          bt[j + 1] = temp;

          // Swap remaining burst time
          temp = rt[j];
          rt[j] = rt[j + 1];
          rt[j + 1] = temp;

          temp = pid[j];
          pid[j] = pid[j + 1];
          pid[j + 1] = temp;
        }
      }
    }

    // Initialize with first process
    // coz pehla process hi sabse pehle aayega
    // and it will be in queue

    if (at[0] > 0) {
      for (int i = 0; i < at[0]; i++) {
        System.out.print(" IDLE ");
      }
    }
    time = at[0];
    enqueue(0);
    inQueue[0] = true;

    // ab hum dekh rahe hai ki process kaise gantt banayenge
    while (complete < n) {
      int process = dequeue();

      //agar queue khali hai to idle state hai 
      if (process == -1) {

        System.out.print(" IDLE");
        time++;
        // check for new processes that have arrived 
        // ye important hai warna ye loop hote rahega idle state me 
        for (int i = 0; i < n; i++) {
          if (!inQueue[i] && !isComplete[i] && at[i] <= time) {
            enqueue(i);
            inQueue[i] = true;
          }
        }

        continue;
      }

      // Process execution
      if (rt[process] > timeQuantum) {
        time += timeQuantum;
        System.out.print(" P" + pid[process]);
        rt[process] -= timeQuantum;
      } else {
        time += rt[process];
        System.out.print(" P" + pid[process]);
        rt[process] = 0;
      }

      //look for new processes that have arrived and add them to the queue
      for (int i = 0; i < n; i++) {
        if (inQueue[i] != true && at[i] <= time && isComplete[i] != true) {
          enqueue(i);
          inQueue[i] = true;
        }
      }

      // check if the process is completed or not
      // if completed then calculate ct, tat, wt and mark it as complete
      if (rt[process] == 0) {
        ct[process] = time;
        tat[process] = ct[process] - at[process];
        wt[process] = tat[process] - bt[process];
        complete++;
        isComplete[process] = true;
      } else {
        enqueue(process);
        inQueue[process] = true;
      }
    }

    System.out.println("\nId\tAT\tBT\tCT\tTAT\tWT");
    for (int i = 0; i < n; i++) {
      avgTAT += tat[i];
      avgWT += wt[i];
      System.out.printf("%d\t%d\t%d\t%d\t%d\t%d%n",
          i + 1, at[i], bt[i], ct[i], tat[i], wt[i]);
    }
    avgTAT /= n;
    avgWT /= n;
    System.out.printf("Avg TAT: %.2f, Avg WT: %.2f%n", avgTAT, avgWT);

    sc.close();
  }
}
