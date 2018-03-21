package com.yq.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yq.demo.entity.AgentProperty;
import com.yq.demo.service.AgentPropertyService;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/agentprop")
public class AgentPropertyController {

    @Autowired
    private AgentPropertyService agentPropSvc;

    @GetMapping(path="/findByName")
    public @ResponseBody AgentProperty findByName (@RequestParam String agentUuid, @RequestParam String name) {
        return agentPropSvc.getAgentPropertyByAgentUuidAndName(agentUuid, name);
    }

    @GetMapping(path="/findByAgentUuid")
    public @ResponseBody Iterable<AgentProperty> findByName (@RequestParam String agentUuid) {
        return agentPropSvc.getAgentPropertiesByAgentUuid(agentUuid);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<AgentProperty> getAllAgentProps() {
        List<AgentProperty> agentPropList = agentPropSvc.getAllAgentProperties();
        return agentPropList;
    }

    /*@RequestMapping(value = "/all",method = RequestMethod.GET)
    public String getAllAgents(Model model) {
        List<AgentProperty> agentPropList = agentPropSvc.getAllAgentProperties()
        model.addAttribute("agentPropList", agentPropList);
        return "admin/agent/agentProps";
    }*/
}