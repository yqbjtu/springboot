package com.yq.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="plugin_step_prop")
public class PluginStepProp {

    public PluginStepProp(){
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

    @Column(name="plugin_step_id", length=32)
    private String pluginStepUuid;

    //desc 在mysql是关键字，不能使用desc
    @Column(name="description",length=1000)
    private String description;

    //propType, 1 is text, 2 is checkbox, 3 is select, 4 is textarea
    @Column(name="prop_type")
    private Integer propType = 1;

    @Column(name="label",length=32)
    private String label;

    @Column(name="default_value",length=256)
    private String defaultValue;

    //1 is required, 0 is not required
    @Column(name="required")
    private Integer required = 1;

    // 1 is hidden, o is not hidden
    @Column(name="hidden")
    private Integer hidden = 1;

    //UI 显示这些属性是有顺序的
    @Column(name="prop_index")
    private Integer propIndex = 1;

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

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public String getPluginStepUuid() {
        return pluginStepUuid;
    }
    public void setPluginStepUuid(String pluginStepUuid) {
        this.pluginStepUuid = pluginStepUuid;
    }
    public Integer getPropType() {
        return propType;
    }
    public void setPropType(Integer propType) {
        this.propType = propType;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    public Integer getRequired() {
        return required;
    }
    public void setRequired(Integer required) {
        this.required = required;
    }
    public Integer getHidden() {
        return hidden;
    }
    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }
    public Integer getPropIndex() {
        return propIndex;
    }
    public void setPropIndex(Integer propIndex) {
        this.propIndex = propIndex;
    }
}
