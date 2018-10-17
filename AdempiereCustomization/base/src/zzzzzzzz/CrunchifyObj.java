package zzzzzzzz;
 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
 
/**
 * @author Crunchify.com 
 * Total 6 different way to create an Object in Java
 */
 
public class CrunchifyObj implements Cloneable, Serializable {
 
	private static final long serialVersionUID = 1L;
 
	public CrunchifyObj() {
		log("Hello! CrunchifyObj() just got created..");
	}
 
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (CrunchifyObj) super.clone();
	}
 
	@SuppressWarnings({ "unused", "resource", "rawtypes" })
	public static void main(String[] args) throws Exception {
 
		// Create Object1
		CrunchifyObj object1 = new CrunchifyObj();
 
		// Create Object2
		CrunchifyObj object2 = (CrunchifyObj) Class.forName("crunchify.com.tutorial.CrunchifyObj").newInstance();
 
		// Create Object3
		CrunchifyObj secondObject = new CrunchifyObj();
		CrunchifyObj object3 = (CrunchifyObj) secondObject.clone();
 
		// Create Object4
		Object object4 = CrunchifyObj.class.getClassLoader().loadClass("crunchify.com.tutorial.CrunchifyObj")
				.newInstance();
 
		// Create Object5: Create a new file with an ObjectOutputStream
		FileOutputStream out = new FileOutputStream("crunchify.txt");
		ObjectOutputStream oout = new ObjectOutputStream(out);
		oout.writeObject(object4); // write object4 to file
		oout.flush();
 
		// create an ObjectInputStream for the file we created before
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("crunchify.txt"));
		CrunchifyObj object5 = (CrunchifyObj) ois.readObject();
		log(object5.toString());
 
		// Create Object6: use the Constructor class from the java.lang.reflect
		Class clazz = CrunchifyObj.class;
		Constructor crunchifyCon = clazz.getDeclaredConstructors()[0];
		CrunchifyObj obj = (CrunchifyObj) crunchifyCon.newInstance();
	}
 
	private static void log(String object) {
		System.out.println(object);
 
	}
}