package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="plugin_step")
public class PluginStep {

    public PluginStep(){
        super();
    }

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="id",length=32)
    private String uuid;

    @Column(name="version", length=32)
    private String version = "1.0";

    @Column(name="name",length=32, nullable=false, unique=true)
    private String name;

    @Column(name="plugin_id", length=32)
    private String pluginUuid = "1.0";

    //desc 在mysql是关键字，不能使用desc
    @Column(name="description",length=1000)
    private String description;

    @Column(name="pl_step_type")
    private Integer plStepType = 1;

    @Column(name="data",length=256)
    private String data;

    @Column(name="script_lang",length=32)
    private String scriptLang;

    @Column(name="validate_script",length=16777216)
    private String validateScript;

    @Column(name="post_process_script",length=16777216)
    private String postProcessScript;

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

    public String getDesc() {
        return description;
    }
    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public String getPluginUuid() {
        return pluginUuid;
    }
    public void setPluginUuid(String pluginUuid) {
        this.pluginUuid = pluginUuid;
    }
    public Integer getPlStepType() {
        return plStepType;
    }
    public void setPlStepType(Integer plStepType) {
        this.plStepType = plStepType;
    }
    public String getScriptLang() {
        return scriptLang;
    }
    public void setScriptLang(String scriptLang) {
        this.scriptLang = scriptLang;
    }
    public String getValidateScript() {
        return validateScript;
    }
    public void setValidateScript(String validateScript) {
        this.validateScript = validateScript;
    }
    public String getPostProcessScript() {
        return postProcessScript;
    }
    public void setPostProcessScript(String postProcessScript) {
        this.postProcessScript = postProcessScript;
    }
}
