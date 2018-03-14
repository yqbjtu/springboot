package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="enventry")
public class EnvEntry {

    public EnvEntry(){
        super();
    }

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="envid",length=32, nullable=false, unique=true)
    private String envUuid;

    @Column(name="seq", nullable=false)
    private Integer seq;

    @Column(name="name",length=32)
    private String name;

    @Column(name="value",length=256)
    private String value;

    //1 stands for readonly, 0 stands for step running can change it
    @Column(name="type")
    private Integer type = 0;

    //1 stands for yes, 0 stands for no
    @Column(name="secure")
    private Integer secure = 0;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 1 is secure. 0 is non-secure
     * @return
     */
    public Boolean getSecure() {
        if (secure.intValue() == 1) {
            return true;
        }
        else {
            return false;
        }
    }
    public void setSecure(Boolean isSecure) {
        if (isSecure) {
            this.secure = 1;
        }
    }

    public String getEnvUuid() {
        return envUuid;
    }
    public void setEnvUuid(String envUuid) {
        this.envUuid = envUuid;
    }

    public Integer getSeq() {
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}
