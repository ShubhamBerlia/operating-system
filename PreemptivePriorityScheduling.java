package com.operatingsystem;

import java.util.PriorityQueue;

public class PreemptivePriorityScheduling {
    static class Process {
        int processID;
        int arrivalTime;
        int burstTime;

        int priority;

        Process(int processID, int arrivalTime, int burstTime, int priority) {
            this.processID = processID;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.priority=priority;
        }
    }

    public void scheduling(int[] processID, int[] arrivalTime, int[] burstTime,int[] priority) {
        int n = processID.length;
        int[] completionTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] waitingTime = new int[n];
        boolean[] processed = new boolean[n];
        int[] remainingBurstTime = new int[n];

        PriorityQueue<Process> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.arrivalTime > p2.arrivalTime)
                return 1;
            if (p1.arrivalTime < p2.arrivalTime)
                return -1;
            return Integer.compare(p2.priority, p1.priority);
        });

        PriorityQueue<Process> readyQueue = new PriorityQueue<>((p1, p2) -> {
            if (p1.priority > p2.priority)
                return -1;
            if (p1.priority < p2.priority)
                return 1;
            return Integer.compare(p1.arrivalTime, p2.arrivalTime);
        });
        int maxArrivalTime = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            pq.add(new Process(processID[i], arrivalTime[i], burstTime[i], priority[i]));
            remainingBurstTime[i] = burstTime[i];
            if(maxArrivalTime<arrivalTime[i]){
                maxArrivalTime = arrivalTime[i];
            }
        }

        readyQueue.add(pq.remove());
        int CT = 0;
        float sumTAT = 0;
        float sumWT = 0;
        int index;
        while (!readyQueue.isEmpty()) {
            Process p = readyQueue.remove();
            index = p.processID;
            if(CT<=maxArrivalTime){
                if (p.arrivalTime > CT) {
                    CT += p.arrivalTime - CT + 1;
                } else {
                    CT += 1;
                }
                remainingBurstTime[index-1]-=1;
            }
            else {
                if (p.arrivalTime > CT) {
                    CT += p.arrivalTime - CT + remainingBurstTime[index-1];
                } else {
                    CT += remainingBurstTime[index-1];
                }
                remainingBurstTime[index-1]-=remainingBurstTime[index-1];
            }
            if(remainingBurstTime[index-1]==0) {
                completionTime[index - 1] = CT;
                turnAroundTime[index - 1] = CT - p.arrivalTime;
                sumTAT += turnAroundTime[index - 1];
                waitingTime[index - 1] = turnAroundTime[index - 1] - p.burstTime;
                sumWT += waitingTime[index - 1];
                processed[index - 1] = true;
                readyQueue.removeAll(readyQueue);
            }
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= CT && !processed[i]) {
                    readyQueue.add(new Process(processID[i], arrivalTime[i], burstTime[i], priority[i]));
                }
            }
        }
        System.out.println("Priority Scheduling Preemptive");
        System.out.println("P\tAT\tBT\tPriority\tCT\tTAT\tWT");
        for (int i = 0; i < processID.length; i++) {
            System.out.println(processID[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t\t" + priority[i]+ "\t\t" + completionTime[i] + "\t" + turnAroundTime[i] + "\t" + waitingTime[i]);
        }
        System.out.println("Average turn around time: " + sumTAT / processID.length);
        System.out.println("Average waiting time: " + sumWT / processID.length);
    }
}
