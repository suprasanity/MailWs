package org.acme;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mail")
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_seq")
    @SequenceGenerator(name = "mail_seq", sequenceName = "mail_sequence", allocationSize = 1)
    private Long id;

    public MailEntity(EmailDTO emailDTO) {
        this.recipient = emailDTO.getRecipient();
        this.subject = emailDTO.getSubject();
        this.content = emailDTO.getContent();
    }
    @OneToMany(mappedBy = "mail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MailAttachmentEntity> attachments = new ArrayList<>();
    public MailEntity() {

    }
    public void addAttachment(MailAttachmentEntity attachment) {
        attachments.add(attachment);
        attachment.setMail(this);
    }

    public void removeAttachment(MailAttachmentEntity attachment) {
        attachments.remove(attachment);
        attachment.setMail(null);
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
