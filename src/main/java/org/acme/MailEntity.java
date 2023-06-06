package org.acme;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mail")
public class MailEntity {
    @Id
    @GeneratedValue
    private Long id;

    public MailEntity(EmailDTO emailDTO) {
        this.recipient = emailDTO.getRecipient();
        this.subject = emailDTO.getSubject();
        this.content = emailDTO.getContent();
    }

    public MailEntity() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private String recipient;
    private String subject;
    private String content;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
