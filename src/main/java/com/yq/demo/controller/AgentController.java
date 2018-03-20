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


@Controller    // This means that this class is a Controller
@RequestMapping(path="/agent") // This means URL's start with /demo (after Application path)
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentJpaRepository agentJpaRepo;

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser(@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        return "Saved";
    }

    @GetMapping(path="/find") // Map ONLY GET Requests
    public @ResponseBody Agent findByName (@RequestParam String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return agentService.getAgentByName(name);
    }

    @GetMapping(path="/findByArgOne") // Map ONLY GET Requests
    public @ResponseBody Agent findByArgOne(@RequestParam String name) {
        return agentJpaRepo.findByArgOne(name);
    }

    @GetMapping(path="/findByPortBetween") // Map ONLY GET Requests
    public @ResponseBody Iterable<Agent> findByPortBetween() {
        return agentJpaRepo.findByPortBetween(5000, 6000);
    }

    @GetMapping(path="/findByNameLike") // Map ONLY GET Requests
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