package com.messenger.projection;

import org.springframework.beans.factory.annotation.Value;

public interface MessageProjection {
    @Value("#{target.ID}")
    Long getId();
    @Value("#{target.CONTENT}")
    String getContent();
}
