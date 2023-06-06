package org.acme;

import org.springframework.data.repository.CrudRepository;

public interface MailRepository extends CrudRepository<MailEntity, Long> {

}
