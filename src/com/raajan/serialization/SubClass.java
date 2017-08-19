package serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SubClass extends SuperClass implements Serializable {
	int c;
	String d;

	public SubClass(int c, String d, int a, String b) {
		super(a, b);
		this.c = c;
		this.d = d;

	}

	public SubClass() {
	}

	public static void main(String[] args) {
		try {
			
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\RaJaN\\Desktop\\a.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			SubClass sub = new SubClass(3, "D", 1, "B");
			oos.writeObject(sub);
			
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\RaJaN\\Desktop\\a.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			SubClass sub2 = (SubClass)ois.readObject();
			System.out.println();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		
		oos.writeInt(this.a);
		oos.writeObject(this.b);
		
	}
	
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException{
		ois.defaultReadObject();
		this.a = ois.readInt();
		this.b = (String)ois.readObject();
	}
	
	private Object writeReplace(){
		return new SubClass(21, "D2", 22, "B2");
	}
	
	private Object readResolve( ){
		return new  SubClass(31, "D3", 33, "B3");
	}
}
