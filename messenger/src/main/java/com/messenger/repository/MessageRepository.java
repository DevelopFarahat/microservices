package com.messenger.repository;

import com.messenger.entity.Message;
import com.messenger.projection.MessageProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query(value = "select ID, CONTENT from messages where USER_RECEIVER_ID=:reciverId",nativeQuery = true)
    List<MessageProjection> getAllMessages(Long reciverId);
}
