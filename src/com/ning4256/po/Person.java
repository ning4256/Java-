package com.ning4256.po;

import com.ning4256.annotation.Annonation.Column;
import com.ning4256.annotation.Annonation.Table;

@Table("person")
public class Person
{
    @Column("name")
    private String name;
 
    @Column("sex")
    private String sex;
 
    @Column("id")
    private int id;
 
    @Column("age")
    private int age;
 
    public String getName()
    {
        return name;
    }
 
    public void setName(String name)
    {
        this.name = name;
    }
 
    public String getSex()
    {
        return sex;
    }
 
    public void setSex(String sex)
    {
        this.sex = sex;
    }
 
    public int getId()
    {
        return id;
    }
 
    public void setId(int id)
    {
        this.id = id;
    }
 
    public int getAge()
    {
        return age;
    }
 
    public void setAge(int age)
    {
        this.age = age;
    }
 
}