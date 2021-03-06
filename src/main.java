import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.IntPredicate;

import static java.lang.Math.abs;

class node {
    boolean accessed = false;
    String distance;
}

public class main {

    public static void FCFS(String[] q, int p) {
        System.out.println("FCFS algorithm sequence:........");
        System.out.print(p + "->");
        int sum = 0;
        int point = 0;
        int back = p;
        for (int i = 0; i < q.length; i++) {
            System.out.print(q[i]);
            point = abs(Integer.parseInt(q[i]) - back);
            back = Integer.parseInt(q[i]);
            sum += point;
            if (i < q.length - 1)
                System.out.print("->");
            else
                System.out.println("");
        }
        System.out.println("The total head movement: " + sum + " Cylinders.");
        System.out.println("-------------------------------------------------");
    }

    public static void SCAN(String[] q, int p) {
        System.out.println("SCAN algorithm sequence:........");
        System.out.print(p + "->");
        int sum = 0;
        int point = 0;
        int back = p;
        int size = q.length;
        int[] arr = new int[size + 1];
        arr[0] = 0;
        for (int i = 1; i <= size; i++) {
            arr[i] = Integer.parseInt(q[i - 1]);
        }
        Arrays.sort(arr);
        int next = 0;
        int idx = 0;
        int counter = 0;
        boolean f = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < p) {
                next = arr[i];
                idx = i;
                counter = i;
                f = true;
            }
        }
        if (!f) {
            counter = idx = 1;
            next = arr[0];
        }
        int c = 0;
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if (next != p) {
                System.out.print(next);
            }
            point = abs(next - back);
            back = next;
            if (i < arr.length - 1) {
                if (flag && f) {
                    if (next != arr[0]) {
                        counter--;
                        next = arr[counter];
                        c++;
                    } else {
                        flag = false;
                        next = arr[++c];
                        c++;
                    }
                } else {
                    next = arr[++c];
                }
            }
            if (counter < 1) {
                counter = idx + 1;
                flag = false;
            }
            sum += point;
            if (i < arr.length - 1) {
                if (next != p) {
                    System.out.print("->");
                }
            } else {
                System.out.println("");
            }
        }
        System.out.println("The total head movement: " + sum + " Cylinders.");
        System.out.println("-------------------------------------------------");
    }

    public static int findminSSTF(ArrayList<node> queue, int head) {
        ArrayList<Integer> m = new ArrayList<Integer>();
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).accessed != true) {
                m.add(abs(Integer.parseInt(queue.get(i).distance) - head));
            } else
                m.add(-1);
        }
        int min = 0;
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i) != -1) {
                min = m.get(i);
                break;
            }
        }
        int idx = -1;
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i) < min && m.get(i) != -1) {
                min = m.get(i);
                idx = m.indexOf(min);
            }
        }
        if (idx == -1)
            idx = m.indexOf(min);
        return idx;
    }

    public static void SSTF(ArrayList<node> q, int p) {
        System.out.println("SSTF algorithm sequence:........");
        System.out.print(p + "->");
        int sum = 0;
        int point = 0;
        int back = p;
        int req;
        for (int i = 0; i < q.size(); i++) {
            req = Integer.parseInt(q.get(findminSSTF(q, back)).distance);
            q.get(findminSSTF(q, back)).accessed = true;
            System.out.print(req);
            point = abs(req - back);
            back = req;
            sum += point;
            if (i < q.size() - 1)
                System.out.print("->");
            else
                System.out.println("");

        }
        System.out.println("The total head movement: " + sum + " Cylinders.");
        System.out.println("-------------------------------------------------");
    }

    public static void circularScan(String[] q, int p) {
        System.out.println("C-SCAN algorithm sequence:........");
        System.out.print(p + "->");
        int sum = 0;
        int size = q.length;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(q[i]);
        }
        Arrays.sort(arr);
        int max = roundUp(arr[arr.length - 1], 100) - 1;

        int index;
        for (index = 0; index < arr.length; index++) {
            if (p < arr[index]) {
                break;
            }
        }
        if (index == arr.length) {
            index = 0;
        }
        int len = arr.length;

        if (searchArray(arr, p)) {
            len--;
        }

        int i = 0;
        while (i < len) {


            if (p > arr[arr.length - 1]) {
                sum += abs(max - 0);
                System.out.print("0->");
                p = 0;
            } else {

                if (index != arr.length) {
                    System.out.print(arr[index]);
                    if (i + 1 != len) {
                        System.out.print("->");
                    }
                }


                if (index != arr.length) {
                    if (index != 0) {
                        if (i == 0) {
                            sum += abs(arr[index] - p);
                            index++;
                        } else {
                            sum += abs(arr[index] - arr[index - 1]);
                            index++;
                        }
                    } else {
                        sum += arr[index];
                        index++;
                    }
                    i++;
                } else {
                    sum += abs(max - arr[index - 1]);
                    System.out.print(max + "->");
                    sum += abs(max - 0);
                    index = 0;
                    System.out.print("0->");
                }

            }

        }
        System.out.println();
        System.out.println("The total head movement: " + sum + " Cylinders.");
        System.out.println("-------------------------------------------------");

    }

    public static void look(String[] q, int p) {
        System.out.println("LOOK algorithm sequence:........");
        System.out.print(p + "->");
        int sum = 0;
        int size = q.length;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(q[i]);
        }
        Arrays.sort(arr);
        int max = roundUp(arr[arr.length - 1], 100) - 1;

        int index;
        for (index = 0; index < arr.length; index++) {
            if (p < arr[index]) {
                break;
            }
        }


        int initialIndex = index - 1;

        boolean reverseFlag = false;
        int i = 0;
        while (i < arr.length) {

            if (index != arr.length) {
                if (arr[index] != p) {
                    System.out.print(arr[index]);
                    if (i + 1 != arr.length) {
                        System.out.print("->");
                    }
                }
            } else {
                if (arr[initialIndex] != p) {
                    System.out.print(arr[initialIndex]);
                    if (i + 1 != arr.length) {
                        System.out.print("->");
                    }
                }
            }


            if (i == 0) {
                if (index != arr.length) {
                    sum += abs(arr[index] - p);
                    index++;
                } else {
                    sum += p - arr[initialIndex];
                    initialIndex--;
                }
            } else if (index != arr.length && reverseFlag == false) {
                sum += abs(arr[index] - arr[index - 1]);
                index++;
            } else {
                if (reverseFlag == false) {
                    sum += abs(arr[index - 1] - arr[initialIndex]);
                }

                if (initialIndex - 1 != -1) {
                    sum += abs(arr[initialIndex] - arr[initialIndex - 1]);
                }

                initialIndex--;
                reverseFlag = true;
            }
            i++;
        }
        System.out.println();
        System.out.println("The total head movement: " + sum + " Cylinders.");
        System.out.println("-------------------------------------------------");
    }

    static int roundUp(int n, int approximate) {
        int a = (n / approximate) * approximate;

        int b = a + approximate;

        return b;
    }

    public static void OptimizedAlgorithm(String[] q) {
        System.out.println("Optimized Algorithm algorithm sequence:........");
        System.out.print(0 + "->");
        int sum = 0;
        int point = 0;
        int back = 0;
        int size = q.length;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(q[i]);
        }
        Arrays.sort(arr);
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            next = arr[i];
            System.out.print(next);
            point = abs(next - back);
            back = next;
            sum += point;
            if (i < q.length - 1)
                System.out.print("->");
            else
                System.out.println("");
        }
        System.out.println("The total head movement: " + sum + " Cylinders.");
        System.out.println("-------------------------------------------------");
    }

    public static boolean searchArray(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return true;
            }
        }
        return false;
    }

    public static void circularLook(String[] q, int p) {
        System.out.println("C-LOOK algorithm sequence:........");
        System.out.print(p + "->");
        int sum = 0;
        int size = q.length;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(q[i]);
        }
        Arrays.sort(arr);
        int max = arr[arr.length - 1];

        int index;
        for (index = 0; index < arr.length; index++) {
            if (p < arr[index]) {
                break;
            }
        }
        if (index == arr.length) {
            index = 0;
        }

        int i = 0;
        int len = arr.length;

        if (searchArray(arr, p)) {
            len--;
        }
        while (i < len) {

            if (index != arr.length) {
                System.out.print(arr[index]);

                if (i + 1 != len) {

                    System.out.print("->");
                }
            }

            if (i == 0) {
                sum += abs(arr[index] - p);
            } else {
                if (index == arr.length) {
                    index = 0;
                    sum += abs(arr[index] - arr[arr.length - 1]);
                    System.out.print(arr[index]);

                    if (i + 1 != arr.length) {
                        System.out.print("->");
                    }
                } else {
                    sum += abs(arr[index] - arr[index - 1]);
                }
            }
            index++;
            i++;
        }


        System.out.println();
        System.out.println("The total head movement: " + sum + " Cylinders.");
        System.out.println("-------------------------------------------------");


    }

    public static void main(String args[]) {

        System.out.println("Enter input queue:");
        Scanner m = new Scanner(System.in);
        String input = m.nextLine();
        String[] queue = input.split(",");
        ArrayList<node> q = new ArrayList<>();
        for (int i = 0; i < queue.length; i++) {
            node n = new node();
            n.distance = queue[i];
            q.add(n);
        }
        System.out.println("Enter Head pointer:");
        int pointer = m.nextInt();
        FCFS(queue, pointer);
        SSTF(q, pointer);
        SCAN(queue, pointer);
        circularScan(queue, pointer);
        look(queue, pointer);
        circularLook(queue, pointer);
        OptimizedAlgorithm(queue);

    }
}
