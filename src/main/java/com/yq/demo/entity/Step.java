package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.yq.demo.common.StepContOnFailEnum;

@Entity(name="step")
public class Step {

    public Step(){
        super();
    }

    @Id
    @GenericGenerator(name="hibernate-uuid", strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    //在project中的顺序
    @Column(name="seq", nullable=false)
    private Integer seq;

    @Column(name="name",length=32, nullable=false)
    private String name;

    @Column(name="description",length=256)
    private String description;

    //1 stands for yes, 0 stands for no
    @Column(name="active", nullable=false)
    private Integer active;

    //
    @Column(name="projectid", length=32, nullable=false)
    private String projectUuid;

    //
    @Column(name="agentpoolid", length=32)
    private String agentPoolUuid;

    //
    @Column(name="envid",length=32)
    private String envUuid;

    @Column(name="cmd", length=256, nullable=false)
    private String cmd;

    //30 stands for 30seconds
    @Column(name="timeout" ,nullable=false)
    private Integer timeout;

    //1 stands for no. when previous step failed, then not continue to execute
    //2 stands for yes, when previous step failed, then continue to execute
    @Column(name="continueOnFail")
    private Integer continueOnFail = 1;

    //如果这一个pluginstep那么的来自表plugin_step的具体某个步骤
    @Column(name="plugin_step_id", length=32)
    private String pluginStepUuid;

    ////1 stands for yes, 0 stands for no and is is default. 2 stands for join
    @Column(name="concurrent")
    private Integer concurrent;

    public String getPluginStepUuid() {
        return pluginStepUuid;
    }
    public void setPluginStepUuid(String pluginStepUuid) {
        this.pluginStepUuid = pluginStepUuid;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String id) {
        this.uuid = id;
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

    public String getAgentPoolUuid() {
        return agentPoolUuid;
    }
    public void setAgentPoolUuid(String agentPoolUuid) {
        this.agentPoolUuid = agentPoolUuid;
    }

    public String getEnvUuid() {
        return envUuid;
    }
    public void setEnvUuid(String envUuid) {
        this.envUuid = envUuid;
    }

    public Integer getSeq() {
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getCmd() {
        return cmd;
    }
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
    public String getProjectUuid() {
        return projectUuid;
    }
    public void setProjectUuid(String projectUuid) {
        this.projectUuid = projectUuid;
    }

    /**
     * unit is seconds
     */
    public Integer getTimeout() {
        return timeout;
    }
    /**
     * unit is seconds
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * 1 stands for no. when previous step failed, then not continue to execute
     * 0 stands for yes, when previous step failed, then continue to execute
     * @return
     */
    public StepContOnFailEnum getContinueOnFail() {
        return StepContOnFailEnum.getByIndex(continueOnFail);
    }
    /**
     * 1 stands for no. when previous step failed, then not continue to execute
     * 0 stands for yes, when previous step failed, then continue to execute
     * @return
     */
    public void setContinueOnFail(StepContOnFailEnum contOnFailEnum) {
        this.continueOnFail = contOnFailEnum.getIndex();
    }

    /**
     * //1 stands for yes, 0 stands for no
     * @return
     */
    public Integer getActive() {
        return active;
    }
    /**
     * //1 stands for yes, 0 stands for no
     * @param active
     */
    public void setActive(Integer active) {
        this.active = active;
    }

    /**
     * //1 stands for yes, 0 stands for no
     * @param active
     */
    public Integer getConcurrent() {
        return concurrent;
    }
    /**
     * //1 stands for yes, 0 stands for no
     * @param active
     */
    public void setConcurrent(Integer concurrent) {
        this.concurrent = concurrent;
    }
}
