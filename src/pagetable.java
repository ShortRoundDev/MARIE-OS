public class pagetable{
	page table[];
	public pagetable(){
		this.table = new page[16];
	}
	public short getAtMemory(short addr){
		short page = (short)(addr/4096);
		if(table[page] == null)
			table[page] = new page();
		short offset = (short)(addr % 4096);
		return table[page].getMemory(offset);
	}

	public void setAtMemory(short addr, short value){
		short page = (short)(addr/4096);
		if(table[page] == null)
			table[page] = new page();
		short offset = (short)(addr % 4096);
		table[page].setMemory(offset, value);
	}
}
