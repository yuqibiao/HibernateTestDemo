package com.yyyu.hibernate.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能：对于student表
 *
 * @author yu
 * @date 2017/7/12.
 */
public class Student {
    private int sId;
    private String sName;
    private Integer sAge;
    Set<Course> courses = new HashSet<>();

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getsAge() {
        return sAge;
    }

    public void setsAge(Integer sAge) {
        this.sAge = sAge;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
