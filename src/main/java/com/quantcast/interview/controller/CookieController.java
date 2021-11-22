package com.quantcast.interview.controller;

import com.quantcast.interview.commons.CommonUtils;
import com.quantcast.interview.exceptions.CookieException;
import com.quantcast.interview.exceptions.ExceptionMessage;
import com.quantcast.interview.models.Cookie;
import com.quantcast.interview.services.MaxFreqService;
import com.quantcast.interview.services.SearchService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
/**
 * Controller class which controls the flow and returns the most active cookie
 */
public class CookieController {

    private String targetDate;
    private String filePath;

    public CookieController(final String filePath, final String targetDate) {
        this.targetDate = targetDate;
        this.filePath = filePath;
    }

    public void getMostFrequentCookie() throws IOException, CookieException {
        try {
            // Perform some validations on file before processing the file.
            if (!CommonUtils.checkIfFileValid(filePath)) {
                throw new CookieException(new ExceptionMessage("File format invalid", 10001));
            }
            if (!CommonUtils.checkIfFileExists(filePath)) {
                throw new CookieException(new ExceptionMessage("File doesn't exist", 10000));
            }
            List<Cookie> cookieList = CommonUtils.getCookieListFromFile(filePath);
            Cookie[] cookieArray = cookieList.toArray(new Cookie[cookieList.size()]);
            SearchService searchService = new SearchService(cookieArray, CommonUtils.getDateFromString(targetDate));
            List<Cookie> targetCookieList = searchService.getTargetList();
            MaxFreqService maxFreqService = new MaxFreqService(targetCookieList);
            List<String> mostActiveCookies = maxFreqService.getMostActiveCookies();
            for (String cookie : mostActiveCookies) {
                System.out.println(cookie);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw e;
        } catch (CookieException e) {
            throw e;
        }
    }
}
