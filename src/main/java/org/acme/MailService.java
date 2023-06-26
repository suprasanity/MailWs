package org.acme;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.io.File;
import java.net.http.HttpResponse;

@ApplicationScoped
public class MailService {
    @Inject
    Mailer mailer;

    private final MailRepository mailRepository;

    public MailService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    public Response.ResponseBuilder sendEmail(EmailDTO emailDTO) {
        MailEntity mail = new MailEntity(emailDTO);
        mailRepository.save(mail);
        String missingProperties = checkMissingProperties(emailDTO);

        if (!missingProperties.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Missing properties: " + missingProperties);
        }
        Mail m= Mail.withText(emailDTO.getRecipient(), emailDTO.getSubject(), emailDTO.getContent());
      m.addAttachment("my-file-2.txt",
                new File("C:\\Users\\yann\\Desktop\\test.txt"), "text/plain");
        mailer.send(
                m
        );
        return Response.ok(emailDTO);
    }
    private String checkMissingProperties(EmailDTO emailDTO) {
        StringBuilder missingProperties = new StringBuilder();

        if (emailDTO.getRecipient() == null) {
            missingProperties.append("recipient, ");
        }
        if (emailDTO.getSubject() == null) {
            missingProperties.append("subject, ");
        }
        if (emailDTO.getContent() == null) {
            missingProperties.append("content, ");
        }

        // Remove trailing comma and space if there are missing properties
        if (missingProperties.length() > 0) {
            missingProperties.setLength(missingProperties.length() - 2);
        }

        return missingProperties.toString();
    }
}
