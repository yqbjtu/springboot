package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="agent_property")
@Table(name="agent_property")
public class AgentProperty {

    public AgentProperty(){
        super();
    }

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="agentid",length=32, nullable=false)
    private String agentUuid;

    public String getAgentUuid() {
        return agentUuid;
    }
    public void setAgentUuid(String agentUuid) {
        this.agentUuid = agentUuid;
    }

    @Column(name="name",length=255)
    private String name;

    @Column(name="value",length=1024)
    private String value;

    //1 stands for yes, 0 stands for no
    @Column(name="secure")
    private Integer secure = 0;

    @Column(name="data",length=256)
    private String data;

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

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

}
