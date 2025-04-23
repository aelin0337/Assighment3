public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int hashCode(){
        int result = 17;
        result = 31 * result + id;
        for(int i = 0; i<name.length(); i++){
            result = 31 * result + name.charAt(i);
        }
        return result;
    }
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if(obj == null|| getClass() != obj.getClass()){
            return false;
        }
        MyTestingClass other = (MyTestingClass) obj;
        return this.id == other.id && this.name.equals(other.name);
    }
    public String toString(){
        return "ID: " + id + " Name: " + name;
    }
}
