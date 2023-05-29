package org.acme;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Collections;

@Path("/mail")
public class MailResource {

    @Inject Mailer mailer;

    @GET
    @Blocking
    public void sendEmail() {
        mailer.send(
                Mail.withText("yann.jeanmaire@gmail.com",
                        "Ahoy from Quarkus",
                        "A simple email sent from a Quarkus application."
                )
        );
    }
}