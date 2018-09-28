package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="agentpool")
public class AgentPool {

    public AgentPool(){
        super();
    }

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="name",length=32, nullable=false, unique=true)
    private String name;

    @Column(name="description",length=256)
    private String description;

    //1 stands for by name selector, 0 stands for dynamically
    @Column(name="type")
    private Integer type;

    //if the agentpool selects agent dynamically
    @Column(name="criterionuuid",length=32)
    private String criterionUuid;

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

    /**
     * 1 stands for by name selector, 0 stands for dynamically
     * @return type
     */
	public Integer getType() {
		return type;
	}
    /**
     * 1 stands for by name selector, 0 stands for dynamically
     *
     */
	public void setType(Integer type) {
		this.type = type;
	}

	public String getCriterionUuid() {
		return criterionUuid;
	}
	public void setCriterionUuid(String criterionUuid) {
		this.criterionUuid = criterionUuid;
	}
}
