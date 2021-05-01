import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class node{
    boolean accessed=false;
    String distance;
}
public class main {

    public static void FCFS (String[] q,int p){
        System.out.println("FCFS algorithm sequence:........");
        System.out.print(p+"->");
        int sum=0;
        int point=0;
        int back=p;
        for(int i=0;i<q.length;i++){
            System.out.print(q[i]);
            point=Math.abs(Integer.parseInt(q[i])-back);
            back=Integer.parseInt(q[i]);
            sum+=point;
            if(i<q.length-1)
                System.out.print("->");
            else
                System.out.println("");
        }
        System.out.println("The total head movement: "+sum+" Cylinders.");
        System.out.println("-------------------------------------------------");
    }
    public static void SCAN (String[] q,int p){
        System.out.println("SCAN algorithm sequence:........");
        System.out.print(p+"->");
        int sum=0;
        int point=0;
        int back=p;
        int size = q.length;
        int [] arr = new int [size];
        for(int i=0; i<size; i++) {
            arr[i] = Integer.parseInt(q[i]);
        }
        Arrays.sort(arr);
        int next=0;
        int idx=0;
        int counter=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<p) {
                next = arr[i];
                idx=i;
                counter=i;
            }
        }
        boolean flag=true;
        for(int i=0;i<q.length;i++){
            System.out.print(next);
            point=Math.abs(next-back);
            back=next;
            if(i<q.length-1) {
                if (flag) {
                    counter--;
                    next = arr[counter];
                } else {
                    next = arr[counter++];
                }
            }
            if(counter<1){
                counter=idx+1;
                flag=false;
            }
            sum+=point;
            if(i<q.length-1)
                System.out.print("->");
            else
                System.out.println("");
        }
        System.out.println("The total head movement: "+sum+" Cylinders.");
        System.out.println("-------------------------------------------------");
    }
    public static int findminSSTF(ArrayList<node>queue, int head)
    {
        ArrayList<Integer>m = new ArrayList<Integer>();
        for (int i = 0; i < queue.size(); i++){
                if(queue.get(i).accessed!=true) {
                    m.add(Math.abs(Integer.parseInt(queue.get(i).distance) - head));
                }
                else
                    m.add(-1);
        }
        int min=0;
        for(int i=0;i<m.size();i++) {
            if(m.get(i)!=-1){
                min=m.get(i);
                break;
            }
        }
        int idx=-1;
        for(int i=0;i<m.size();i++){
            if(m.get(i)<min&&m.get(i)!=-1) {
                min=m.get(i);
                idx=m.indexOf(min);
            }
        }
        if(idx==-1)
            idx=m.indexOf(min);
        return idx;
    }

    public static void SSTF (ArrayList<node>q,int p){
        System.out.println("SSTF algorithm sequence:........");
        System.out.print(p+"->");
        int sum=0;
        int point=0;
        int back=p;
        int req;
        for(int i=0;i<q.size();i++){
            req=Integer.parseInt(q.get(findminSSTF(q,back)).distance);
            q.get(findminSSTF(q,back)).accessed=true;
            System.out.print(req);
            point=Math.abs(req-back);
            back=req;
            sum+=point;
            if(i<q.size()-1)
                System.out.print("->");
            else
                System.out.println("");

        }
        System.out.println("The total head movement: "+sum+" Cylinders.");
        System.out.println("-------------------------------------------------");
    }

    public static void main(String args[]){

        System.out.println("Enter input queue:");
        Scanner m= new Scanner(System.in);
        String input=m.nextLine();
        String[] queue = input.split(",");
        ArrayList<node>q=new ArrayList<>();
        for(int i=0;i<queue.length;i++){
            node n=new node();
            n.distance=queue[i];
            q.add(n);
        }
        System.out.println("Enter Head pointer:");
        int pointer=m.nextInt();
        FCFS(queue,pointer);
        SSTF(q,pointer);
        SCAN(queue,pointer);






    }
}