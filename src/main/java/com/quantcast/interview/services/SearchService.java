package com.quantcast.interview.services;

import com.quantcast.interview.models.Cookie;

import java.util.*;

/**
 * Service to return list of cookies whith in target date.
 */
public class SearchService {
    private Cookie[] cookies;
    private Date targetDate;

    public SearchService(Cookie[] cookies, Date targetDate) {
        this.cookies = cookies;
        this.targetDate = targetDate;
    }

    /**
     * Use Binary search to get the first and last index of an element and return a list corresponds to those indexes.
     * @return
     */
    public List<Cookie> getTargetList() {
        int start = 0;
        int end = cookies.length - 1;
        int[] firstAndLastIndexes = {getFirstIndex(cookies, start, end, targetDate),
                getLastIndex(cookies, start, end, targetDate)};
        List<Cookie> targetList = new ArrayList<>();
        for (int i = firstAndLastIndexes[0]; i <= firstAndLastIndexes[1] && i >= 0; i++) {
            targetList.add(cookies[i]);
        }
        return targetList;
    }

    private int getFirstIndex(Cookie[] cookies, int start, int end, Date target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (cookies[mid].getTimestamp().equals(target)) {
                if (mid == 0) {
                    return mid;
                } else if (!cookies[mid - 1].getTimestamp().equals(cookies[mid].getTimestamp())) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            } else if (cookies[mid].getTimestamp().before(target)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;

    }

    private int getLastIndex(Cookie[] cookies, int start, int end, Date target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (cookies[mid].getTimestamp().equals(target)) {
                if (mid == cookies.length-1) {
                    return mid;
                } else if (!cookies[mid + 1].getTimestamp().equals(cookies[mid].getTimestamp())) {
                    return mid;
                } else {
                    start = mid + 1;
                }
            } else if (cookies[mid].getTimestamp().before(target)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
