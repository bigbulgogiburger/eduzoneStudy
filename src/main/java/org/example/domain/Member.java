package org.example.domain;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "id") private String id;

    @Column(name = "name")
    private String username;

    @Column(name = "age")
    private Integer age;


    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Member(String id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }


    public Member() {
    }
}
