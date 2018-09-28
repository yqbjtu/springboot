package com.yq.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@XmlRootElement
@Entity(name="schedule")
public class Schedule {

    /** id of the schedule */
    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="uuid",length=32)
    private String uuid;

    /** name of the Schedule, every 2 minutes */
    @Column(name="name", length=32, unique = true)
    private String name;

    @Column(name="description",length=256)
    private String description;

    //1 stands for yes, 0 stands for no
    @Column(name="active")
    private Integer active = 1;

    /**
     * 0 0/15 * * * ?  就是corn的表达式
     */
    @Column(name="data",length=256)
    private String data;

    public Schedule() {
    }

    public Schedule(String name, String description, Integer active, String data) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
