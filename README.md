# operating-system

### FCFS (First Come First Serve) 
First come first serve is a non-preemptive CPU scheduling algorithm where the processes are scheduled to execute on the CPU based on their arrival time, the tie-breaker in FCFS algorithm is Process ID i.e., if two processes have same arrival time then the process with smaller process ID executes first.

### SJF (Shortest Job First)
Shortest Job First is a non-preemptive CPU schedulig algorithm where the processes are scheduled to execute on the CPU based on their burst time (execution time), the tie breaker in SJF algorithm is FCFS i.e., if two processes have same burst time then the process which arrived earlier is executed first and if the burst time and arrival time of the two processes is also same then the process with smaller process ID is executed first. The idea in SJF algorithm is to maintain a ready queue until new processes keep arriving.

### SRTF (Shortest Remaining Time First)
Shortest remaining time first is a preemptive CPU scheduling algorithm where the processes are scheduled based on the remaining burst time. The idea in SRTF scheduling algorithm is to schedule a process which has arrived first, execute it for one instance of time to check whether any other process has arrived after one instance of time and then compare the processes based on their burst time and schedule the process with the smallest remaining burst time to execute on the CPU. The processes are executed for one unit of time until new processes keep arriving and after when new processes stop arriving all the processes in the ready queue are executed for their remaining burst time without preemption, the tie-breaker in SRTF algorithm is FCFS.

### Priority Scheduling
In priority scheduling, each process is associated with some priority and the processes are scheduled on the CPU based on their priority and the priority depends whether higher number has highest priority or lower number has highest priority.

In non-preemptive priority scheduling algorithm each process is assigned first arrival time (less arrival time process first) if two processes have same arrival time, then compare to priorities (highest process first). Also, if two processes have same priority then compare to process ID (less process ID first). This process is repeated while all process get executed.

Preemptive Priority CPU Scheduling Algorithm is a preemptive method of CPU scheduling algorithm that works based on the priority of a process. In this algorithm, the scheduler schedules the tasks to work as per the priority, which means that a higher priority process should be executed first. In case of any conflict, i.e., when there are more than one processes with equal priorities, then pre-emptive priority CPU scheduling algorithm works on the basis of FCFS (First Come First Serve) algorithm.

Steps of implementation
1. First input the processes with their arrival time, burst time and priority.
2. First process will schedule, which have the lowest arrival time, if two or more processes will have lowest arrival time, then whoever has higher priority will schedule first.
3. Now further processes will be schedule according to the arrival time and priority of the process. (Here we are assuming that lower the priority number having higher priority). If two process priority are same then sort according to process number.
4. Once all the processes have been arrived, we can schedule them based on their priority.

### Round Robin Scheduling Algorithm
Round Robin Scheduling algorithm is a peemptive CPU scheduling algorithm which executes each process for a certain fixed time period known as time qunatum, the tie breaker in round robin scheduling algorithm is smaller process ID first.
