package com.yq.demo.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.yq.demo.common.BuildResultEnum;
import com.yq.demo.common.BuildStatusEnum;
import com.yq.demo.common.FireWayEnum;

@Entity(name="build")
public class Build {

    public Build(){
        super();
    }

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    //
    @Column(name="projectid", length=32, nullable=false)
    private String projectUuid;

    //生成的文件夹默认是在project名称下的Build_buildNo, 目前不允许改变BuildNo自动增长不允许手动改变
    // mysql数据库不允许有两列是auto的，必须放弃
    @Column(name="buildno")
    private Integer buildNo;

    //
    @Column(name="agentpoolid", length=32)
    private String agentPoolUuid;

    //
    @Column(name="envid",length=32)
    private String envUuid;

    //  状态，1， 表示create new， 2，starting， 3， executing， 4 complete
    @Column(name="status")
    private Integer status = 1;

    //  状态，1， 表示none， 2，pass， 3， failed， 4 warning
    @Column(name="result")
    private Integer result;

    //  表示谁启动的build. -1表示没有设置任何userid
    @Column(name="userid")
    private Integer userId = -1;

    //  状态，1， manual， 2，schedule， 3，code changes trigger， 4， triggered by other project
    @Column(name="fireway")
    private Integer fireWay=1;

    // 生成build request时候自动去当前时间
    @Column(name="createdate")
    private Date createDate;

    //  状态，1
    @Column(name="startdate")
    private Date startDate;

    //  状态，
    @Column(name="enddate")
    private Date endDate;

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

    public String getEnvUuid() {
        return envUuid;
    }
    public void setEnvUuid(String envUuid) {
        this.envUuid = envUuid;
    }

    public String getProjectUuid() {
        return projectUuid;
    }
    public void setProjectUuid(String projectUuid) {
        this.projectUuid = projectUuid;
    }

    public Integer getBuildNo() {
        return buildNo;
    }
    public void setBuildNo(Integer buildNo) {
        this.buildNo = buildNo;
    }

    public BuildStatusEnum getStatus() {
        return BuildStatusEnum.getByIndex(status) ;
    }
    public void setStatus(BuildStatusEnum status) {
        this.status = status.getIndex();
    }

    public BuildResultEnum getResult() {
        return BuildResultEnum.getByIndex(result);
    }
    public void setResult(BuildResultEnum resultEnum) {
        this.result = resultEnum.getIndex();
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public FireWayEnum getFireWay() {
        return FireWayEnum.getByIndex(fireWay);
    }
    public void setFireWay(FireWayEnum fireWay) {
        this.fireWay = fireWay.getIndex();
    }

    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

}
