package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="project")
public class Project {

    public Project(){
        super();
    }

    public Project(String name, String description, Integer active, String agentPoolUuid
            , String envUuid, String data) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.agentPoolUuid = agentPoolUuid;
        this.envUuid = envUuid;
        this.data = data;
    }

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="name", length=32, unique = true, nullable=false)
    private String name;

    @Column(name="description",length=256)
    private String description;

    //1 stands for yes, 0 stands for no
    @Column(name="active")
    private Integer active = 1;


    @Column(name="agentpoolid", length=32)
    private String agentPoolUuid;

    //
    @Column(name="envid",length=32)
    private String envUuid;

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

    public String getAgentPoolUuid() {
        return agentPoolUuid;
    }
    public void setAgentPoolUuid(String agentPoolUuid) {
        this.agentPoolUuid = agentPoolUuid;
    }

    public String getEnvUuid() {
        return envUuid;
    }
    public void setEnvUuid(String envUuid) {
        this.envUuid = envUuid;
    }

    public String getData() {
        return data;
    }
    public void setdata(String data) {
        this.data = data;
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
