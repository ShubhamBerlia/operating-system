package com.operatingsystem;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class RoundRobin {
    static class Process {
        int processID;
        int arrivalTime;
        int burstTime;

        Process(int processID, int arrivalTime, int burstTime) {
            this.processID = processID;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
        }
    }

    public void scheduling(int[] processID, int[] arrivalTime, int[] burstTime,int timeQuantum) {
        int n = processID.length;
        int[] completionTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] waitingTime = new int[n];
        boolean[] processed = new boolean[n];
        int[] remainingBurstTime = new int[n];
        boolean[] contains = new boolean[n];

        PriorityQueue<Process> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.arrivalTime > p2.arrivalTime)
                return 1;
            if (p1.arrivalTime < p2.arrivalTime)
                return -1;
            return 0;
        });

        Queue<Process> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            pq.add(new Process(processID[i], arrivalTime[i], burstTime[i]));
            remainingBurstTime[i] = burstTime[i];
        }

        queue.add(pq.remove());
        int CT = 0;
        float sumTAT = 0;
        float sumWT = 0;
        int index;
        while (!queue.isEmpty()) {
            Process p = queue.remove();
            index = p.processID;
            if (p.arrivalTime > CT) {
                if(remainingBurstTime[index-1]>=timeQuantum) {
                    CT += p.arrivalTime - CT + timeQuantum;
                    remainingBurstTime[index - 1] -= timeQuantum;
                }
                else{
                    CT += remainingBurstTime[index-1];
                    remainingBurstTime[index-1]-=remainingBurstTime[index-1];
                }
            } else {
                if(remainingBurstTime[index-1]>=timeQuantum) {
                    CT+=timeQuantum;
                    remainingBurstTime[index - 1] -= timeQuantum;
                }
                else{
                    CT += remainingBurstTime[index-1];
                    remainingBurstTime[index-1]-=remainingBurstTime[index-1];
                }
            }
            if(remainingBurstTime[index-1]==0) {
                completionTime[index - 1] = CT;
                turnAroundTime[index - 1] = CT - p.arrivalTime;
                sumTAT += turnAroundTime[index - 1];
                waitingTime[index - 1] = turnAroundTime[index - 1] - p.burstTime;
                sumWT += waitingTime[index - 1];
                processed[index - 1] = true;
            }
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= CT && !processed[i] && i!=index-1&&!contains[i]) {
                    queue.add(new Process(processID[i], arrivalTime[i], burstTime[i]));
                    contains[i] = true;
                }
            }
            if(remainingBurstTime[index-1]!=0) {
                queue.add(p);
                contains[index-1] = true;
            }
        }
        System.out.println();
        System.out.println("Round Robin Algorithm");
        System.out.println("P\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < processID.length; i++) {
            System.out.println(processID[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + completionTime[i] + "\t" + turnAroundTime[i] + "\t" + waitingTime[i]);
        }
        System.out.println("Average turn around time: " + sumTAT / processID.length);
        System.out.println("Average waiting time: " + sumWT / processID.length);
    }
}
