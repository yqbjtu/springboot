package com.yq.service;

import com.yq.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.Collection;
import java.util.List;

@Slf4j
public class DroolsProcessor {

    private KnowledgeBase knowledgeBase;

    public synchronized void initProcessor(String userId, String ruleContent){
        this.knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        try {
            kbuilder.add(ResourceFactory.newByteArrayResource(ruleContent.getBytes()), ResourceType.DRL);
            if (kbuilder.hasErrors()){
                KnowledgeBuilderErrors errors = kbuilder.getErrors();
                log.warn("The DRL file has errors={}", errors);
            }
        } catch (Exception e) {
            log.warn("Found exception when loadRule. userId={}",userId, e);
        }

        //生成有状态的会话
        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
        this.knowledgeBase.addKnowledgePackages( pkgs );
    }

    public void process(Object fact, List<User> list){
        if(fact == null) {
            log.debug("RuleRunner`facts is empty!");
            return;
        }
        log.debug( "Insert fact: {}" , fact );

        StatefulKnowledgeSession ksession = this.knowledgeBase.newStatefulKnowledgeSession();
        ksession.setGlobal("list", list);
        ksession.insert(fact);
        ksession.insert(list);
        ksession.fireAllRules();
        ksession.getEntryPoints().clear();
    }
}
