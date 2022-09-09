package com.operatingsystem;

import java.util.PriorityQueue;

public class ShortestJobFirst {
    static class Process {
        int processID;
        int arrivalTime;
        int burstTime;

        Process(int processID, int arrivalTime, int burstTime){
            this.processID=processID;
            this.arrivalTime=arrivalTime;
            this.burstTime = burstTime;
        }
    }
    public void scheduling(int[] processID,int[] arrivalTime, int[] burstTime){
        int n = processID.length;
        int[] completionTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] waitingTime = new int[n];
        boolean[] processed = new boolean[n];

        PriorityQueue<Process> pq = new PriorityQueue<>((p1, p2)->{
            if(p1.arrivalTime>p2.arrivalTime)
                return 1;
            if(p1.arrivalTime<p2.arrivalTime)
                return -1;
            return Integer.compare(p1.burstTime, p2.burstTime);
        });

        PriorityQueue<Process> readyQueue = new PriorityQueue<>((p1, p2)->{
            if(p1.burstTime>p2.burstTime)
                return 1;
            if(p1.burstTime<p2.burstTime)
                return -1;
            return Integer.compare(p1.arrivalTime, p2.arrivalTime);
        });

        for (int i = 0; i <n; i++) {
            pq.add(new Process(processID[i], arrivalTime[i], burstTime[i]));
        }

        readyQueue.add(pq.remove());
        int CT=0;
        float sumTAT=0;
        float sumWT = 0;
        int index;
        while(!readyQueue.isEmpty()){
            Process p = readyQueue.remove();
            index=p.processID;
            if(p.arrivalTime>CT){
                CT+=p.arrivalTime-CT+p.burstTime;
            }
            else {
                CT += p.burstTime;
            }
            completionTime[index-1] = CT;
            turnAroundTime[index-1] = CT - p.arrivalTime;
            sumTAT+=turnAroundTime[index-1];
            waitingTime[index-1] = turnAroundTime[index-1] - p.burstTime;
            sumWT+=waitingTime[index-1];
            processed[index-1] = true;
            readyQueue.removeAll(readyQueue);
            for (int i = 0; i < n; i++) {
                if(arrivalTime[i]<=CT&& !processed[i]){
                    readyQueue.add(new Process(processID[i], arrivalTime[i], burstTime[i]));
                }
            }
        }
        System.out.println("Shortest Job First Scheduling");
        System.out.println("P\tAT\tBT\tCT\tTAT\tWT");
        for(int i=0;i< processID.length;i++){
            System.out.println(processID[i]+"\t"+arrivalTime[i]+"\t"+burstTime[i]+"\t"+completionTime[i]+"\t"+turnAroundTime[i]+"\t"+waitingTime[i]);
        }
        System.out.println("Average turn around time: "+sumTAT/ processID.length);
        System.out.println("Average waiting time: "+sumWT/ processID.length);
    }
}
