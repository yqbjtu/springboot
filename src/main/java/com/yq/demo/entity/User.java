package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="user")
public class User {

    public User(){
        super();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="username",length=32, nullable=false, unique=true)
    private String userName;

    //1 stands for built-in, 2 stands for created , 3 stands for ldap
    @Column(name="usertype")
    private Integer userType;

    @Column(name="fullname",length=32)
    private String fullName;

    @Column(name="password",length=32)
    private String password;

    @Column(name="email",length=32)
    private String email;

    // 1 stands for yes, 0 stands for no
    @Column(name="can_delete")
    private Integer can_delete;

    //1 stands for yes, 0 stands for no
    @Column(name="active")
    private Integer active;

    //such as yyyy/MM/dd  or dd-MM-yy
    @Column(name="dateformat",length=32)
    private String dateFormat;

    // such as HH:mm or hh mm z
    @Column(name="timeformat",length=32)
    private String timeFormat;

    @Column(name="timezone",length=32)
    private String timeZone;

    @Column(name="language",length=32)
    private String language;

    @Column(name="data",length=256)
    private String data;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }
    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getFullname() {
        return fullName;
    }
    public void setFullname(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }
    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getData() {
        return data;
    }
    public void setdata(String data) {
        this.data = data;
    }

    public String getDateFormat() {
        return dateFormat;
    }
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getTimeFormat() {
        return timeFormat;
    }
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getTimeZone() {
        return timeZone;
    }
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    /*
     *  1 stands for yes, 0 stands for no
     */
    public Integer getCan_delete() {
        return can_delete;
    }

    /*
     *  1 stands for yes, 0 stands for no
     */
    public void setCan_delete(Integer can_delete) {
        this.can_delete = can_delete;
    }

    /*
     * 1 stands for yes, 0 stands for no
     */
    public Integer getActive() {
        return active;
    }
    /*
     * 1 stands for yes, 0 stands for no
     */
    public void setActive(Integer active) {
        this.active = active;
    }
}
