import java.util.Random;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
public class pcb{

	public short pc;
	public short ac;
	public short state;
	int pid;
	int priority;
	int initialpriority;
	int reg[];
	pagetable maintable;

	public pcb(int pid){
		this.pid = pid;
		this.reg = new int[9];
		this.pc = 0;
		this.ac = 0;
		this.maintable = new pagetable();
		Random rand = new Random();
		this.initialpriority = rand.nextInt(16) + 1;
		this.priority = this.initialpriority;

			//open program
		File file = new File("test.hex");
		BufferedReader buff;
		try{
			buff = new BufferedReader(new FileReader(file));
		}catch(Exception e){
			//whatever
			buff = null;
		}
		if(buff == null)
			return;
		boolean done = false;
		short idx = 0;
		while(!done){
			String b = "";
			for(int i = 0; i < 4; i++){
				char c = (char)-1;
				try{ c = (char) buff.read();}catch(Exception e){return;}
				if(c == -1 || c == '\n'){
					return;
				}
				b += c;
			}
			if(done == true)
				break;
			short a = Short.parseShort(b, 16);
			this.setAtMemory(idx++, a);
		}
	}
	public pcb(String path){
		
	}

	public short getAtMemory(short addr){
		return maintable.getAtMemory(addr);
	}
	public void setAtMemory(short addr, short value){
		maintable.setAtMemory(addr, value);
	}

	public void exec(short code, short next){
		switch(code){
			case 0x03:
				MARIE.Add(next, this);
				break;
			case 0x04:
				MARIE.Subt(next, this);
				break;
			case 0x0B:
				MARIE.Addi(next, this);
				break;
			case 0x0A:
				MARIE.Clear(next, this);
				break;
			case 0x01:
				MARIE.Load(next, this);
				break;
			case 0x02:
				MARIE.Store(next, this);
				break;
			case 0x05:
				MARIE.Input(this);
				break;
			case 0x06:
				MARIE.Output(this);
				break;
			case 0x09:
				MARIE.Jump(next, this);
				break;
			case 0x08:
				MARIE.Branch(next, this);
				break;
			case 0x00:
				MARIE.JNS(next, this);
				break;
			case 0x0C:
				MARIE.Jumpi(next, this);
				break;
			case 0x0E:
				MARIE.Storei(next, this);
				break;
			case 0x0D:
				MARIE.Loadi(next, this);
				break;
		}
	}
}
