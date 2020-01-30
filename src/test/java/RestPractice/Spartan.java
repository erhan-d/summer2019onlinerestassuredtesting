package RestPractice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Name;


public class Spartan {
    private long id;
    private String name;
    private String gender;
    private String phone;

    public Spartan(Name name, String male, long l){

    }

    public Spartan(String name,String gender,String phone) {
        this.name = name;
        this.gender=gender;
        this.phone=phone;

    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @JsonIgnore
    public long getId(){
        return id;
    }
    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
