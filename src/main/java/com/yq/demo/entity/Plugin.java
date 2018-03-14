package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="plugin")
public class Plugin {

    public Plugin(){
        super();
    }

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="identifier",length=256)
    private String identifier;

    @Column(name="name",length=32, nullable=false, unique=true)
    private String name;

    //desc 在mysql是关键字，不能使用desc
    @Column(name="description",length=1000)
    private String description;

    //1 stands for automation, 0 stands for sourceconfig,default is 1
    @Column(name="type")
    private Integer type = 1;

    @Column(name="version", length=32)
    private String version = "1.0";

    @Column(name="hash",length=256)
    private String hash;

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

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /*
     * 1 stands for automation
     */
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }

}
