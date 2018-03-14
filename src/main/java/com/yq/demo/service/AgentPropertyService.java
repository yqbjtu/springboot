package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.AgentPropertyDAO;
import com.yq.demo.entity.AgentProperty;

public class AgentPropertyService {
    private AgentPropertyDAO agentPropertyDao;

    public List<AgentProperty> getAllAgentProperties() {
        return agentPropertyDao.getAllAgentProperties();
    }

    public AgentPropertyDAO getAgentPropertyDao() {
        return agentPropertyDao;
    }

    public void setAgentPropertyDao(AgentPropertyDAO agentPropertyDao) {
        this.agentPropertyDao = agentPropertyDao;
    }

    public void addAgentProperty(AgentProperty agentProperty) {
        agentPropertyDao.addAgentProperty(agentProperty);
    }

    public int delAgentPropertiesByAgentUuid(String agentUuid) {
        return agentPropertyDao.delAgentPropertiesByAgentUuid(agentUuid);
    }

    public AgentProperty getAgentPropertiesByAgentUuidAndName(String agentUuid, String name) {
        return agentPropertyDao.getAgentPropertiesByAgentUuidAndName(agentUuid, name);
    }

    public List<AgentProperty> getAgentPropertiesByAgentUuid(String agentUuid) {
        return agentPropertyDao.getAgentPropertiesByAgentUuid(agentUuid);
    }
}
