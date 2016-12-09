package test;

public class TestClass {
    private Integer helloworldmynameishello;
    private Integer age;
    public void testHello(){

        Integer testage=this.getAge();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer testInteger(){
        return 0;
    }

    public void sayHello(String name, Integer age){
        System.out.println("hello:"+name+"you'r:"+age);
    }

}
