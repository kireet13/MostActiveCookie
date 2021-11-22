package com.quantcast.interview.models;

import java.util.Date;

public class Cookie {
    private String cookie;
    private Date timestamp;

    public Cookie(String cookie, Date timestamp) {
        this.cookie = cookie;
        this.timestamp = timestamp;
    }

    public String getCookie() {
        return cookie;
    }
    public Date getTimestamp() {
        return timestamp;
    }
}
