package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="plugin_step_prop_value")
public class PluginStepPropValue {

    public PluginStepPropValue(){
        super();
    }

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="name",length=32, nullable=false, unique=true)
    private String name;

    @Column(name="value",length=20480)
    private String value;

    @Column(name="plugin_step_prop_id", length=32)
    private String pluginStepPropUuid;

    @Column(name="step_id", length=32)
    private String stepUuid;

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
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getPluginStepPropUuid() {
        return pluginStepPropUuid;
    }
    public void setPluginStepPropUuid(String pluginStepPropUuid) {
        this.pluginStepPropUuid = pluginStepPropUuid;
    }
    public String getStepUuid() {
        return stepUuid;
    }
    public void setStepUuid(String stepUuid) {
        this.stepUuid = stepUuid;
    }
}
