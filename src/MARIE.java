public class MARIE{
	static void Add(short addr, pcb process){
		process.ac += process.getAtMemory(addr);
		process.pc += 2;
	}
	static void Subt(short addr, pcb process){
		process.ac -= addr;
		process.pc += 2;
	}
	static void Addi(short addr, pcb process){
		process.ac += process.getAtMemory(process.getAtMemory(addr));
		process.pc += 2;
	}
	static void Clear(short addr, pcb process){
		process.ac = 0;
		process.pc++;
	}
	static void Load(short addr, pcb process){
		process.ac = process.getAtMemory(addr);
		process.pc += 2;
	}
	static void Store(short addr, pcb process){
		process.setAtMemory(addr, process.ac);
		process.pc += 2;
	}
	static void Input(pcb process){
		//handle IO
	} 
	static void Output(pcb process){
		System.out.println("#" + process.pid + ": " + (char)process.ac);
		process.pc++;
	}
	static void Jump(short condition, pcb process){
		process.pc = condition;
		process.pc += 2;
	}
	static void Branch(short condition, pcb process){
		if(condition == 000){
			if(process.ac < 0){
				process.pc += 3;
				return;
			}
		}else if(condition == 400){
			if(process.ac == 0){
				process.pc += 3;
				return;
			}
		}else if(condition == 800){
			if(process.ac > 0){
				process.pc += 3;
				return;
			}
		}
		process.pc += 2;
	}
	static void JNS(short addr, pcb process){
		process.setAtMemory(addr, process.pc);
		process.pc = (short)(addr + 1);
	}
	static void Jumpi(short addr, pcb process){
		process.pc = process.getAtMemory(addr);
	}
	static void Storei(short addr, pcb process){
		process.setAtMemory(process.getAtMemory(addr), process.ac);
		process.pc += 2;
	}
	static void Loadi(short addr, pcb process){
		process.ac = process.getAtMemory(process.getAtMemory(addr));
		process.pc += 2;
	}
	static void Halt(short addr, pcb process){
		process.state = -1;
	}
}
