public class page{

	public short memory[];

	public page(){
		this.memory = new short[4096];
	}

	public short getMemory(short location){
		return memory[location];
	}

	public void setMemory(short location, short value){
		memory[location] = value;
	}
}
