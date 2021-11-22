package com.quantcast.interview;

import com.quantcast.interview.commons.CommonUtils;
import com.quantcast.interview.exceptions.CookieException;
import com.quantcast.interview.models.Cookie;
import com.quantcast.interview.services.SearchService;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SearchServiceTest {

    private List<Cookie> testCookieList;
    @BeforeTest
    private void setup() throws CookieException {
        testCookieList = new ArrayList<>();
        testCookieList.add(new Cookie("AtY0laUfhglK3lDS", CommonUtils.getDateFromString("2018-12-10T14:19:00+00:00")));
        testCookieList.add(new Cookie("AtY0laUfhglK3lC7", CommonUtils.getDateFromString("2018-12-09T14:19:00+00:00")));
        testCookieList.add(new Cookie("SAZuXPGUrfbcn5UA", CommonUtils.getDateFromString("2018-12-09T10:13:00+00:00")));
        testCookieList.add(new Cookie("5UAVanZf6UtGyKVS", CommonUtils.getDateFromString("2018-12-09T07:25:00+00:00")));
        testCookieList.add(new Cookie("AtY0laUfhglK3lC7", CommonUtils.getDateFromString("2018-12-09T06:19:00+00:00")));
        testCookieList.add( new Cookie("SAZuXPGUrfbcn5UA", CommonUtils.getDateFromString("2018-12-08T22:03:00+00:00")));
        testCookieList.add(new Cookie("4sMM2LxV07bPJzwf", CommonUtils.getDateFromString("2018-12-08T21:30:00+00:00")));
        testCookieList.add(new Cookie("fbcn5UAVanZf6UtG", CommonUtils.getDateFromString("2018-12-08T09:30:00+00:00")));
        testCookieList.add(new Cookie("4sMM2LxV07bPJzwf", CommonUtils.getDateFromString("2018-12-07T23:30:00+00:00")));
    }

    @Test
    public void testFirstAndLastIndex() throws CookieException {
        Cookie[] cookieArray = testCookieList.toArray(new Cookie[testCookieList.size()]);
        SearchService searchService = new SearchService(cookieArray, CommonUtils.getDateFromString("2018-12-09T14:19:00+00:00"));
        Assert.assertEquals(searchService.getTargetList().size(),4);
    }

    @Test
    public void testFirstAndLastIndexWhenTargetDoesntExist() throws CookieException {
        Cookie[] cookieArray = testCookieList.toArray(new Cookie[testCookieList.size()]);
        SearchService searchService = new SearchService(cookieArray, CommonUtils.getDateFromString("2018-12-01T14:19:00+00:00"));
        Assert.assertEquals(searchService.getTargetList().size(),0);
    }

    @Test
    public void testFirstAndLastIndexWhenOnlyOnetarget() throws CookieException {
        Cookie[] cookieArray = testCookieList.toArray(new Cookie[testCookieList.size()]);
        SearchService searchService = new SearchService(cookieArray, CommonUtils.getDateFromString("2018-12-07T14:19:00+00:00"));
        Assert.assertEquals(searchService.getTargetList().size(),1);
    }

    @Test
    public void testFirstAndLastIndexWhenOnlyOnetargetAtFirst() throws CookieException {
        Cookie[] cookieArray = testCookieList.toArray(new Cookie[testCookieList.size()]);
        SearchService searchService = new SearchService(cookieArray, CommonUtils.getDateFromString("2018-12-10T14:19:00+00:00"));
        Assert.assertEquals(searchService.getTargetList().size(),1);
    }

    @Test
    public void testFirstAndLastIndexWhenEmptyList() throws CookieException {
        List<Cookie> emptyList = new ArrayList<>();
        Cookie[] cookieArray = emptyList.toArray(new Cookie[emptyList.size()]);
        SearchService searchService = new SearchService(cookieArray, CommonUtils.getDateFromString("2018-12-10T14:19:00+00:00"));
        Assert.assertEquals(searchService.getTargetList().size(),0);
    }
}
