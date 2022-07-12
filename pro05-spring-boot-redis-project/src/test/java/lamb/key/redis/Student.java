package lamb.key.redis;

/**
 * @author JoinYang
 * @date 2022/5/17 19:57
 */

public class Student {
    private String stuName;
    private String stuSex;
    private Integer age;

    public Student() {
    }

    public Student(String stuName, String stuSex, Integer age) {
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.age = age;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", age=" + age +
                '}';
    }
}
