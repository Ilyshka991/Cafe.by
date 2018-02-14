package bsuir.pechuro.utils;

import bsuir.pechuro.enumeration.RedirectingCommandName;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * class SessionElements created get most popular session elements
 */
public class SessionElements {
    /**
     * @param request
     * @return String
     */
    public static String getLocale(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute("locale");
        if (locale == null || locale.isEmpty()) {
            locale = Locale.getDefault().getLanguage();
        }
        return locale;
    }

    /**
     * @param request
     * @return String
     */
    public static String getPageCommand(HttpServletRequest request) {
        String pageCommand = (String) request.getSession().getAttribute("pageCommand");
        System.out.println(pageCommand);
        if (pageCommand == null || pageCommand.isEmpty()) {
            pageCommand = RedirectingCommandName.INDEX.getCommand();
        }
        return pageCommand;
    }
}
