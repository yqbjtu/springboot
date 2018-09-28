package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="agentpoolagentmap")
public class AgentPoolAgentMap {

    public AgentPoolAgentMap(){
        super();
    }

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="agentpoolid",length=32)
    private String agentPoolUuid;

    @Column(name="agentid",length=32)
    private String agentUuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAgentPoolUuid() {
		return agentPoolUuid;
	}

	public void setAgentPoolUuid(String agentPoolUuid) {
		this.agentPoolUuid = agentPoolUuid;
	}

	public String getAgentUuid() {
		return agentUuid;
	}

	public void setAgentUuid(String agentUuid) {
		this.agentUuid = agentUuid;
	}

}
