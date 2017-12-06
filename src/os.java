import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Date;
import java.util.Iterator;

public class os{
	public static Comparator<pcb> comparator;// = new processpriority();
	public static PriorityQueue<pcb> mainQueue;// = new PriorityQueue<pcb>(comparator);
	public static void main(String args[]){	
		comparator = new processpriority();
		mainQueue = new PriorityQueue<pcb>(comparator);
		long start, finish;
		Date d = new Date();

		int pid = 0;
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		mainQueue.add(new pcb(pid++));
		
		while(true){
			pcb currentProcess = mainQueue.poll();
			if(currentProcess != null){
				//System.out.println("running pid#" + currentProcess.pid);
				d = new Date();
				finish = d.getTime();
				start = d.getTime();
				while(finish - start < 1000){
					currentProcess.exec(
						currentProcess.getAtMemory(
							currentProcess.pc
						),
						currentProcess.getAtMemory(
							(short)(currentProcess.pc + (short)1)
						)
					);
					d = new Date();
					finish = d.getTime();
					if(currentProcess.state == -1)
						break;
				}
				if(currentProcess.state != -1){
					currentProcess.priority = currentProcess.initialpriority;
					mainQueue.add(currentProcess);
				}
					//age non-head
				Iterator<pcb> it = mainQueue.iterator();
				if(it.hasNext()){
					it.next();
					while(it.hasNext()){
						pcb next = it.next();
						next.priority++;
						//System.out.println(next.priority + ": #" + next.pid);
					}
				}
			}
			
		}
	}
}


