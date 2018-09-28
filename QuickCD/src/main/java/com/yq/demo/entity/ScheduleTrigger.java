package com.yq.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@XmlRootElement
@Entity(name="scheduletrigger")
public class ScheduleTrigger {

    /** id of the schedule */
    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="uuid",length=32)
    private String uuid;

    /** schedule uuid */
    @Column(name="scheduuid",length=32)
    private String scheduuid;

    /**  project uuid */
    @Column(name="projuuid",length=32)
    private String projuuid;

    //1 stands for yes, 0 stands for no
    @Column(name="active")
    private Integer active = 1;

    public static final Integer ACTIVE = 1;
    public static final Integer INACTIVE = 1;

    public ScheduleTrigger() {
    }

    public ScheduleTrigger(String scheduuid, String projuuid, Integer active) {
        this.scheduuid = scheduuid;
        this.projuuid = projuuid;
        this.active = active;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getScheduuid() {
        return scheduuid;
    }

    public void setScheduuid(String scheduuid) {
        this.scheduuid = scheduuid;
    }

    public String getProjuuid() {
        return projuuid;
    }

    public void setProjuuid(String projuuid) {
        this.projuuid = projuuid;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

}
