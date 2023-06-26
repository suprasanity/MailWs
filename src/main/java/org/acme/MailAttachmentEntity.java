package org.acme;

import jakarta.persistence.*;

@Entity
@Table(name = "mail_attachment")
public class MailAttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mail_id")
    private MailEntity mail;

    private String filename;
    private String contentType;

    @Lob
    private byte[] data;

    // Constructors, getters, and setters

    public MailAttachmentEntity() {
    }

    public MailAttachmentEntity(String filename, String contentType, byte[] data) {
        this.filename = filename;
        this.contentType = contentType;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MailEntity getMail() {
        return mail;
    }

    public void setMail(MailEntity mail) {
        this.mail = mail;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
