package com.operatingsystem;

import java.util.PriorityQueue;

public class FirstComeFirstServe {
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
        int[] completionTime = new int[processID.length];
        int[] turnAroundTime = new int[processID.length];
        int[] waitingTime = new int[processID.length];
        PriorityQueue<Process> pq = new PriorityQueue<>((p1, p2)->{
            return Integer.compare(p1.arrivalTime, p2.arrivalTime);
        });
        for (int i = 0; i < processID.length; i++) {
            pq.add(new Process(processID[i], arrivalTime[i], burstTime[i]));
        }
        int CT=0;
        float sumTAT=0;
        float sumWT = 0;
        int index;
        while(!pq.isEmpty()){
            Process p = pq.remove();
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
        }
        System.out.println("First Come First Serve Scheduling");
        System.out.println("P\tAT\tBT\tCT\tTAT\tWT");
        for(int i=0;i< processID.length;i++){
            System.out.println(processID[i]+"\t"+arrivalTime[i]+"\t"+burstTime[i]+"\t"+completionTime[i]+"\t"+turnAroundTime[i]+"\t"+waitingTime[i]);
        }
        System.out.println("Average turn around time: "+sumTAT/ processID.length);
        System.out.println("Average waiting time: "+sumWT/ processID.length);
    }
}
