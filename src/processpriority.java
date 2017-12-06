import java.util.Comparator;

class processpriority implements Comparator<pcb>{
	public int compare(pcb a, pcb b){
		if(a.priority < b.priority){
			return 1;
		}else if(a.priority == b.priority){
			return 0;
		}
		return -1;
	}
}
