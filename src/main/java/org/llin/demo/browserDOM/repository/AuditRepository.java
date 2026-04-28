package org.llin.demo.browserDOM.repository;

import org.llin.demo.browserDOM.model.AuditCentral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditCentral, Integer> {

}
