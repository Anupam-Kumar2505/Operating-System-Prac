import java.util.*;

public class DiskScheduling {

  static void fcfs(int req[], int head) {
    int sum = 0, lastOp = -1;
    for (int i = 0; i < req.length; i++) {
      System.out.println("Access Sequeunce: " + req[i]);

      if (lastOp == -1) {
        sum += Math.abs(head - req[i]);
      } else {
        sum += Math.abs(req[i] - lastOp);
      }
      lastOp = req[i];

      System.out.println("Total Head Movement:" + sum);

    }
  }

  static void sstf(int req[], int head) {
    boolean[] visited = new boolean[req.length];
    int totalHeadMovement = 0;
    int currentHead = head;

    System.out.println("Access Sequence:");
    System.out.println(currentHead + " ");
    for (int i = 0; i < req.length; i++) {
      int minDistance = Integer.MAX_VALUE;
      int minIndex = -1;

      for (int j = 0; j < req.length; j++) {
        if (!visited[j] && Math.abs(req[j] - currentHead) < minDistance) {
          minDistance = Math.abs(req[j] - currentHead);
          minIndex = j;
        }
      }

      visited[minIndex] = true;
      totalHeadMovement += minDistance;
      currentHead = req[minIndex];
      System.out.println(req[minIndex] + " ");
    }

    System.out.println("Total Head Movement: " + totalHeadMovement);
  }

  static void scan(int req[], int head, int maxCylinder, int direction) {
    int totalHeadMovement = 0;
    int currentHead = head;
    int[] temp = new int[req.length];
    int headIndex = 0;

    for (int i = 0; i < temp.length; i++) {
      temp[i] = req[i];
    }
    Arrays.sort(temp);

    System.out.println("Access Sequence:");
    System.out.println(currentHead + " ");

    for (int i = 0; i < temp.length; i++) {
      if (temp[i] < head) {
        headIndex++;

      }
    }

    if (direction == 0) {
      for (int i = headIndex - 1; i >= 0; i--) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
      totalHeadMovement += Math.abs(currentHead - 0);
      currentHead = 0;
      System.out.println(0 + " ");
      for (int i = headIndex; i < temp.length; i++) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
    } else {

      for (int i = headIndex; i < temp.length; i++) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
      totalHeadMovement += Math.abs(currentHead - maxCylinder);
      currentHead = maxCylinder;
      System.out.println(maxCylinder + " ");

      for (int i = headIndex - 1; i >= 0; i--) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
    }
    System.out.println("Total Head Movement: " + totalHeadMovement);

  }

  static void cscan(int req[], int head, int maxCylinder, int direction) {
    int totalHeadMovement = 0;
    int currentHead = head;
    int[] temp = new int[req.length];
    int headIndex = 0;

    for (int i = 0; i < temp.length; i++) {
      temp[i] = req[i];
    }
    Arrays.sort(temp);

    System.out.println("Access Sequence:");
    System.out.println(currentHead + " ");

    for (int i = 0; i < temp.length; i++) {
      if (temp[i] < head) {
        headIndex++;

      }
    }

    if (direction == 0) {
      for (int i = headIndex - 1; i >= 0; i--) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
      totalHeadMovement += Math.abs(currentHead - 0);
      currentHead = 0;
      System.out.println(0 + " ");
      System.out.println(maxCylinder + " ");
      totalHeadMovement += Math.abs(currentHead - maxCylinder);
      currentHead = maxCylinder;
      for (int i = temp.length - 1; i >= headIndex; i--) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
    } else {

      for (int i = headIndex; i < temp.length; i++) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
      totalHeadMovement += Math.abs(currentHead - maxCylinder);
      currentHead = maxCylinder;
      System.out.println(maxCylinder + " ");
      System.out.println(0 + " ");
      totalHeadMovement += Math.abs(currentHead - 0);
      currentHead = 0;
      for (int i = 0; i < headIndex; i++) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
    }
    System.out.println("Total Head Movement: " + totalHeadMovement);
  }

  static void look(int req[], int head, int direction) {
    int totalHeadMovement = 0;
    int currentHead = head;
    int[] temp = new int[req.length];
    int headIndex = 0;

    for (int i = 0; i < temp.length; i++) {
      temp[i] = req[i];
    }
    Arrays.sort(temp);

    System.out.println("Access Sequence:");
    System.out.println(currentHead + " ");

    for (int i = 0; i < temp.length; i++) {
      if (temp[i] < head) {
        headIndex++;

      }
    }

    if (direction == 0) {
      for (int i = headIndex - 1; i >= 0; i--) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
      
    
      for (int i = headIndex; i < temp.length; i++) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
    } else {

      for (int i = headIndex; i < temp.length; i++) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
     
      for (int i = headIndex - 1; i >= 0; i--) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
    }
    System.out.println("Total Head Movement: " + totalHeadMovement);

  }

  static void clook(int req[], int head, int direction) {
    int totalHeadMovement = 0;
    int currentHead = head;
    int[] temp = new int[req.length];
    int headIndex = 0;

    for (int i = 0; i < temp.length; i++) {
      temp[i] = req[i];
    }
    Arrays.sort(temp);

    System.out.println("Access Sequence:");
    System.out.println(currentHead + " ");

    for (int i = 0; i < temp.length; i++) {
      if (temp[i] < head) {
        headIndex++;

      }
    }

    if (direction == 0) {
      for (int i = headIndex - 1; i >= 0; i--) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }

      for (int i = temp.length - 1; i >= headIndex; i--) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
    } else {

      for (int i = headIndex; i < temp.length; i++) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }

      for (int i = 0; i < headIndex; i++) {
        System.out.println(temp[i] + " ");
        totalHeadMovement += Math.abs(currentHead - temp[i]);
        currentHead = temp[i];
      }
    }
    System.out.println("Total Head Movement: " + totalHeadMovement);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the number of requests: ");
    int n = sc.nextInt();
    System.out.println("Enter the initial head position: ");
    int head = sc.nextInt();
    System.out.println("Enter the maximum cylinder number: ");
    int maxCylinder = sc.nextInt();
    int[] requests = new int[n];
    System.out.println("Enter the requests: ");
    for (int i = 0; i < n; i++) {
      requests[i] = sc.nextInt();
    }
    System.out.println("Enter the direction (0 for left, 1 for right): ");
    int direction = sc.nextInt();

    System.out.println(
        "Enter the algorithm (0 for FCFS, 1 for SSTF, 2 for SCAN , 3 for C-SCAN , 4 for LOOK, 5 for C-LOOK and 6 to exit ): ");

    int choice = sc.nextInt();

    switch (choice) {
      case 0:
        fcfs(requests, head);
        break;
      case 1:
        sstf(requests, head);
        break;
      case 2:
        scan(requests, head, maxCylinder, direction);
        break;
      case 3:
        cscan(requests, head, maxCylinder, direction);
        break;
      case 4:
        look(requests, head, direction);
        break;
      case 5:
        clook(requests, head, direction);
        break;
      case 6:
        System.out.println("Exiting...");
        break;
      default:
        System.out.println("Invalid choice!");
    }

  }
}
