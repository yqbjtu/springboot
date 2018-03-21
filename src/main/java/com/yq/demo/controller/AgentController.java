package com.yq.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yq.demo.dao.AgentJpaRepository;
import com.yq.demo.entity.Agent;
import com.yq.demo.service.AgentService;


@Controller
@RequestMapping(path="/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentJpaRepository agentJpaRepo;

    @GetMapping(path="/find")
    public @ResponseBody Agent findByName (@RequestParam String name) {
        return agentService.getAgentByName(name);
    }

    @GetMapping(path="/findByArgOne")
    public @ResponseBody Agent findByArgOne(@RequestParam String name) {
        return agentJpaRepo.findByArgOne(name);
    }

    @GetMapping(path="/findByPortBetween")
    public @ResponseBody Iterable<Agent> findByPortBetween() {
        return agentJpaRepo.findByPortBetween(5000, 6000);
    }

    @GetMapping(path="/findByNameLike")
    public @ResponseBody Iterable<Agent> findByPortBetween(@RequestParam String pattern) {
        return agentJpaRepo.findByNameLike(pattern);
    }

    /*@GetMapping(path="/all")
    public @ResponseBody Iterable<Agent> getAllAgents() {
        // This returns a JSON or XML with the users
        return agentService.getAllAgents();
    }*/

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public String getAllAgents(Model model) {
        List<Agent> agentList = agentService.getAllAgents();
        model.addAttribute("agentList", agentList);
        return "admin/agent/agents";
    }
}