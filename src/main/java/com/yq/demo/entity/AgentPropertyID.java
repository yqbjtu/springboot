package com.yq.demo.entity;

import java.io.Serializable;

public class AgentPropertyID implements Serializable{

    private static final long serialVersionUID = 155771990L;
    private String uuid;
    private String name;

    @Override
    public int hashCode() {
        int result = 1;
        result = uuid.hashCode() + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final AgentPropertyID other = (AgentPropertyID) obj;
        if (uuid == other.uuid && name == other.name) {
            return true;
        }
        else {
            return false;
        }
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

}
