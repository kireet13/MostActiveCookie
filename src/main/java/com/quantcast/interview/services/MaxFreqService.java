package com.quantcast.interview.services;

import com.quantcast.interview.models.Cookie;

import java.util.*;

/**
 * Service to find out the most frequent cookie from a list.
 */
public class MaxFreqService {
    private List<Cookie> targetList;

    public MaxFreqService(List<Cookie> targetList) {
        this.targetList = targetList;
    }

    public List<String> getMostActiveCookies() {
        Map<String, Integer> cookieFreqMap = getCookieFreqMap();
        int highestFrequency = getHighestFrequency(cookieFreqMap);
        List<String> mostFrequentCookies = getMostFrequentCookies(cookieFreqMap, highestFrequency);
        return mostFrequentCookies;
    }

    private Map<String, Integer> getCookieFreqMap() {
        Map<String, Integer> cookieFrequency = new HashMap<>();
        for (Cookie cookie : targetList) {
            if (cookieFrequency.containsKey(cookie.getCookie())) {
                cookieFrequency.put(cookie.getCookie(), cookieFrequency.get(cookie.getCookie()) + 1);
            } else {
                cookieFrequency.put(cookie.getCookie(), 1);
            }
        }
        return cookieFrequency;
    }

    private int getHighestFrequency(Map<String, Integer> cookieFreqMap) {
        int maxFreq = Integer.MIN_VALUE;
        Iterator<Map.Entry<String, Integer>> iterator = cookieFreqMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            if (value > maxFreq) {
                maxFreq = value;
            }
        }
        return maxFreq;
    }

    private List<String> getMostFrequentCookies(Map<String, Integer> cookieFreqMap, int highestFrequency) {
        List<String> mostFrequentCookies = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> iterator = cookieFreqMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            if (value == highestFrequency) {
                mostFrequentCookies.add(key);
            }
        }
        return mostFrequentCookies;
    }
}
