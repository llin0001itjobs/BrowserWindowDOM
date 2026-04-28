package org.llin.demo.browserDOM.model.listener;

import java.sql.Timestamp;

import org.llin.demo.browserDOM.model.AuditCentral;
import org.llin.demo.browserDOM.repository.AuditRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class EntityAuditListener implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        EntityAuditListener.context = applicationContext;
    }

    @PrePersist
    public void prePersist(Object entity) {
        createAudit(entity, "INSERT");
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        createAudit(entity, "UPDATE");
    }

    @PreRemove
    public void preRemove(Object entity) {
        createAudit(entity, "DELETE");
    }

    private void createAudit(Object entity, String action) {
    	
        AuditRepository auditRepository = context.getBean(AuditRepository.class);

        AuditCentral audit = new AuditCentral();
                
        audit.setAction(action);
        audit.setTableName(entity.getClass().getSimpleName().toLowerCase());
        
        audit.setDateCreated(new Timestamp(System.currentTimeMillis()));
        audit.setDateUpdated(new Timestamp(System.currentTimeMillis()));

        auditRepository.save(audit);
    }
}