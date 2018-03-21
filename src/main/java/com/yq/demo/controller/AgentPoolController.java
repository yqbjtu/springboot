package com.yq.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yq.demo.dao.AgentPoolAgentMapJpaRepository;
import com.yq.demo.dao.AgentPoolJpaRepository;
import com.yq.demo.dao.UuidOnly;
import com.yq.demo.entity.AgentPool;


@Controller
@RequestMapping(path="/agentpool")
public class AgentPoolController {
    @Autowired
    AgentPoolAgentMapJpaRepository agentPoolAgentMapJpaRepo;

    @Autowired
    AgentPoolJpaRepository agentPoolJpaRepo;

    @GetMapping(path="/findByName")
    public @ResponseBody AgentPool findByName (@RequestParam String name) {
        return agentPoolJpaRepo.getByName(name);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<AgentPool> getAllAgentProps() {
        List<AgentPool> agentPoolList = agentPoolJpaRepo.findAll();
        return agentPoolList;
    }

    @GetMapping(path="/getAgentUuidByAgentPoolUuid")
    public @ResponseBody Iterable<UuidOnly> getAgentUuidByAgentPoolUuid (@RequestParam String poolUuid) {
        return agentPoolAgentMapJpaRepo.getAgentUuidByAgentPoolUuid(poolUuid);
    }
}