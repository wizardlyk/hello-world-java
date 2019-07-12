package zz;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Property;
import zz.annotation.MyConstraint;

public class Puppy {
    @MyConstraint(message = "这个一个测试信息")
    String name;
    private int age;
    @JsonProperty("end_time")
    private String endTime;

    @Property(clazz = "start_time")
    private String startTime;

    public Puppy() {
    }

    public Puppy(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Puppy{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
