package org.llin.demo.browserDOM.entity;

import org.llin.demo.browserDOM.entity.listener.EntityAuditListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
@EntityListeners(EntityAuditListener.class) 
public class Role {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Matches INT(11) in MySQL
    private String type; // Matches VARCHAR(64)
    private String description; // Matches VARCHAR(64)
	
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    
	@Override
	public String toString() {
		return "Role [id=" + id + ", type=" + type + ", description=" + description + "]";
	} 
    
}