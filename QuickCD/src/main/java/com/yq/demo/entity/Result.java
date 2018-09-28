package com.yq.demo.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.yq.demo.common.StepContOnFailEnum;
import com.yq.demo.common.StepStatusEnum;

@Entity(name="result")
public class Result {

    public Result(){
        super();
    }

    @Id
    @GenericGenerator(name="hibernate-uuid", strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="buildid", length=32, nullable=false)
    private String buildUuid;

    //在build中的顺序, -1表示没有设置
    @Column(name="seq", nullable=false)
    private Integer seq = -1;

    @Column(name="stepname",length=256, nullable=false)
    private String stepName;

    //  状态，0， 表示none， 1 running, 2，pass， 3， failed， 4 warning
    @Column(name="status")
    private Integer status;

    @Column(name="agentid", length=32)
    private String agentUuid;

    //
    @Column(name="envid",length=32)
    private String envUuid;

    // step的log默认在/var/logs/{projectUUid}/{buildNo}/{stepUuid}/log.txt error.txt
    // 存放agent，env cmd timeout是为了restart将来考虑的，暂时没什么用
    @Column(name="cmd", length=256, nullable=false)
    private String cmd;

    //30 stands for 30seconds
    @Column(name="timeout" ,nullable=false)
    private Integer timeout;

    //  状态，1
    @Column(name="startdate")
    private Date startDate;

    //  状态，
    @Column(name="enddate")
    private Date endDate;

    //一些comments信息
    @Column(name="data", length=16777216)
    private String data;

    //1 stands for no. when previous step failed, then not continue to execute
    //0 stands for yes, when previous step failed, then continue to execute
    @Column(name="continueOnFail")
    private Integer continueOnFail = 1;

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String id) {
        this.uuid = id;
    }

    public String getStepName() {
        return stepName;
    }
    public void setStepName(String name) {
        this.stepName = name;
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

    /**
     * unit is second
     */
    public Integer getTimeout() {
        return timeout;
    }
    /**
     * unit is second
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
    public String getBuildUuid() {
        return buildUuid;
    }
    public void setBuildUuid(String buildUuid) {
        this.buildUuid = buildUuid;
    }

    public StepStatusEnum getStatus() {
        return StepStatusEnum.getByIndex(status);
    }
    /**
     *  状态，0， 表示none， 1 running, 2，pass， 3， failed， 4 warning
     * @param result
     */
    public void setStatus(StepStatusEnum statusEnum) {
        this.status = statusEnum.getIndex();
    }

    public String getAgentUuid() {
        return agentUuid;
    }
    public void setAgentUuid(String agentUuid) {
        this.agentUuid = agentUuid;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
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
}
