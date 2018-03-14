package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="setting")
public class Setting {

    public Setting(){
        super();
    }

    @Id
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="name",length=255, nullable=false, unique=true)
    private String name;

    //desc 在mysql是关键字，不能使用desc
    @Column(name="description",length=256)
    private String description;

    @Column(name="value",length=256)
    private String value;

    @Column(name="data",length=256)
    private String data;

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return description;
    }
    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
