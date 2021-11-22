package com.quantcast.interview;

import com.quantcast.interview.commons.CommonUtils;
import com.quantcast.interview.exceptions.CookieException;
import com.quantcast.interview.models.Cookie;
import com.quantcast.interview.services.MaxFreqService;
import com.quantcast.interview.services.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MaxFreqServiceTest {

    private List<Cookie> testCookieList;
    @BeforeTest
    private void setup() throws CookieException {
        testCookieList = new ArrayList<>();
        testCookieList.add(new Cookie("AtY0laUfhglK3lC7", CommonUtils.getDateFromString("2018-12-09T14:19:00+00:00")));
        testCookieList.add(new Cookie("SAZuXPGUrfbcn5UA", CommonUtils.getDateFromString("2018-12-09T10:13:00+00:00")));
        testCookieList.add(new Cookie("5UAVanZf6UtGyKVS", CommonUtils.getDateFromString("2018-12-09T07:25:00+00:00")));
        testCookieList.add(new Cookie("AtY0laUfhglK3lC7", CommonUtils.getDateFromString("2018-12-09T06:19:00+00:00")));
    }

    @Test
    public void testMaxFrequency() throws CookieException {
        MaxFreqService maxFreqService = new MaxFreqService(testCookieList);
        List<String> mostActiveCookies = maxFreqService.getMostActiveCookies();
        log.info("Most Active cookies:- "+mostActiveCookies);
        Assert.assertEquals(mostActiveCookies.size(),1);
    }

    @Test
    public void testMaxFrequencyWithEmptyList() throws CookieException {
        List<Cookie> emptyList = new ArrayList<>();
        MaxFreqService maxFreqService = new MaxFreqService(emptyList);
        List<String> mostActiveCookies = maxFreqService.getMostActiveCookies();
        log.info("Most Active cookies:- "+mostActiveCookies);
        Assert.assertEquals(mostActiveCookies.size(),0);
    }

    @Test
    public void testMaxFrequencyWithMutipleOp() throws CookieException {
        List<Cookie> testList = new ArrayList<>();
        testList.add(new Cookie("AtY0laUfhglK3lC7", CommonUtils.getDateFromString("2018-12-09T14:19:00+00:00")));
        testList.add(new Cookie("SAZuXPGUrfbcn5UA", CommonUtils.getDateFromString("2018-12-09T10:13:00+00:00")));
        MaxFreqService maxFreqService = new MaxFreqService(testList);
        List<String> mostActiveCookies = maxFreqService.getMostActiveCookies();
        log.info("Most Active cookies:- "+mostActiveCookies);
        Assert.assertEquals(mostActiveCookies.size(),2);
    }
}
