
import java.util.*;

class FCFS {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        int n = sc.nextInt();

        System.out.println("Enter arrival time and burst time of each process:");
        int pid[] = new int[n];
        int arrivalTime[] = new int[n];
        int burstTime[] = new int[n];
        int completionTime[] = new int[n];
        int turnAroundTime[] = new int[n];
        int waitingTime[] = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = i + 1; // Process ID
            System.out.println("Process " + (i + 1) + " Arrival Time: ");
            arrivalTime[i] = sc.nextInt();
            System.out.println("Process " + (i + 1) + " Burst Time: ");
            burstTime[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
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
                }
            }
        }

        int currentTime = 0;

        for (int i = 0; i < n; i++) {
            if (currentTime < arrivalTime[i]) {
                
                for(int j = currentTime; j < arrivalTime[i]; j++) {
                    System.out.print(" IDLE");
                }

                currentTime = arrivalTime[i];
            }

            for(int j=1;j<=burstTime[i];j++){
                System.out.print(" P" + pid[i]);
            }

           
            currentTime += burstTime[i];
            completionTime[i] = currentTime;
            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - burstTime[i];
        }
        
        System.out.println();

        int sumT = 0;
        int sumW = 0;

        for (int i = 0; i < n; i++) {
            sumT += turnAroundTime[i];
            sumW += waitingTime[i];
        }

        System.out.println("Average Turnaround Time: " + (double) sumT /
                n);
        System.out.println("Average Waiting Time: " + (double) sumW / n);

        sc.close();
    }
}

