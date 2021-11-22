import com.quantcast.interview.commons.CommonUtils;
import com.quantcast.interview.controller.CookieController;
import org.apache.commons.cli.CommandLine;

public class CookieDriver {
    public static void main(String[] args) throws Exception {
        CommandLine arguments = CommonUtils.getArguments(args);
        String filePath = arguments.getOptionValue("f");
        String target = arguments.getOptionValue("d");
        CookieController cookieController = new CookieController(filePath, target);
        cookieController.getMostFrequentCookie();
    }
}
