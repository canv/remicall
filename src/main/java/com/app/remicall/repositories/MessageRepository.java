package com.app.remicall.repositories;

import com.app.remicall.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *      allows you to find a complete list of fields
 *      or find them by message.id
 */

public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag); //JPA will do it by keywords
}
