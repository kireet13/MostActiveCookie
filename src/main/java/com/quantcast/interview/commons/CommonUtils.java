package com.quantcast.interview.commons;

import com.quantcast.interview.exceptions.CookieException;
import com.quantcast.interview.exceptions.ExceptionMessage;
import com.quantcast.interview.models.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class CommonUtils {

    public static CommandLine getArguments(String[] args) throws ParseException {
        Options options = new Options();
        Option timeOption = new Option("d", true, "time for which most active cookies is needed");
        timeOption.setRequired(true);
        Option fileOption = new Option("f", true, "file name");
        fileOption.setRequired(true);

        options.addOption(timeOption);
        options.addOption(fileOption);
        CommandLineParser parser = new DefaultParser();
        //parse the options passed as command line arguments
        return parser.parse(options, args);
    }

    /**
     * Check if file exits otherwise throw and Exception
     * @param fileName
     * @return
     * @throws CookieException
     */
    public static boolean checkIfFileExists(String fileName){
        File file = new File(fileName);
        boolean exists = file.exists();
        if (!exists) {
            return false;
        }
        return true;
    }

    /**
     * Check if file format is valid.
     * @param fileName
     * @return
     * @throws CookieException
     */
    public static boolean checkIfFileValid(String fileName) {
        if (!fileName.endsWith(".csv")) {
            return false;
        }
        return true;
    }

    public static Date getDateFromString(String dateString) throws CookieException {
        String startDateString = dateString;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = df.parse(startDateString);
        } catch (java.text.ParseException e) {
            log.error("error while parsing date string ",e);
            throw new CookieException(new ExceptionMessage("date format is invalid", 10002));
        }
        return startDate;
    }

    /**
     * Read the file and return List of Cookie object.
     * @param filePath
     * @return
     */
    public static List<Cookie> getCookieListFromFile(String filePath) throws IOException, CookieException {
        List<Cookie> cookieList = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                String[] strings = line.split(",");
                Cookie cookie = new Cookie(strings[0], CommonUtils.getDateFromString(strings[1]));
                cookieList.add(cookie);
                line = br.readLine();
            }
        } catch (IOException e) {
            log.error("error while eeading file ", e);
            e.printStackTrace();
            throw e;
        } catch (CookieException e) {
            throw e;
        }
        return cookieList;
    }
}
