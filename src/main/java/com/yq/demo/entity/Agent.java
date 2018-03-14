package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.yq.demo.common.AgentStatusEnum;

@Entity(name="agent")
public class Agent {

    public Agent(){
        super();
    }

    @Id
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="name",length=32, nullable=false, unique=true)
    private String name;

    //desc 在mysql是关键字，不能使用desc
    @Column(name="description",length=256)
    private String description;

    //1 stands for online, 0 stands for offline
    @Column(name="status")
    private Integer status = 0;

    @Column(name="maxbuild")
    private Integer maxbuild = 3;

    @Column(name="port")
    private Integer port = 5566;

    //
    @Column(name="host",length=32)
    private String host;

    //
    @Column(name="envid",length=32)
    private String envUuid;

    @Column(name="data",length=256)
    private String data;

    @Column(name="version",length=32)
    private String version;

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

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnvUuid() {
        return envUuid;
    }
    public void setEnvUuid(String envId) {
        this.envUuid = envId;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    /**
     *  1 stands for online, 0 stands for offline
     *  AgentStatusEnum
     */
    public AgentStatusEnum getStatus() {
        return AgentStatusEnum.getByIndex(status);
    }
    /**
     *  1 stands for online, 0 stands for offline
     * @param AgentStatusEnum TODO
     */
    public void setStatus(AgentStatusEnum status) {
        this.status = status.getIndex();
    }

    public Integer getPort() {
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public Integer getMaxbuild() {
        return maxbuild;
    }
    public void setMaxbuild(Integer maxbuild) {
        this.maxbuild = maxbuild;
    }

    @Override
    public boolean equals(Object obj) {
        boolean retVal = false;
        if (obj instanceof Agent) {
            Agent objAgent = (Agent)obj;
            if ( this.uuid.equals(objAgent) ) {
                retVal = true;
            }
        }
        return retVal;
    }
}

