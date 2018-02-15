package bsuir.pechuro.utils;

import bsuir.pechuro.enumeration.RedirectingCommandName;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


public class SessionElements {

    public static String getLocale(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute("locale");
        if (locale == null || locale.isEmpty()) {
            locale = Locale.getDefault().getLanguage();
        }
        return locale;
    }


    public static String getPageCommand(HttpServletRequest request) {
        String pageCommand = (String) request.getSession().getAttribute("pageCommand");
        System.out.println(pageCommand);
        if (pageCommand == null || pageCommand.isEmpty()) {
            pageCommand = RedirectingCommandName.INDEX.getCommand();
        }
        return pageCommand;
    }
}
