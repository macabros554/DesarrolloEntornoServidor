package dwes_jsp;

public class StudentBean {

	private String firstName = null;
	private String lastName = null;
	private int age = 0;

	public StudentBean() {
	}
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public int getAge(){
		return age;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public void setAge(int age){
		this.age = age;
	}
}