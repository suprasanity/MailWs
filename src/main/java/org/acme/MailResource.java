package org.acme;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/mail")
public class MailResource {

    @Inject
    Mailer mailer;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response.ResponseBuilder sendEmail(EmailDTO emailDTO) {
        String missingProperties = checkMissingProperties(emailDTO);

        if (!missingProperties.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Missing properties: " + missingProperties);
        }

        mailer.send(
                Mail.withText(emailDTO.getRecipient(), emailDTO.getSubject(), emailDTO.getContent())
        );
        return Response.accepted();
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
