package bsuir.pechuro.tag;

import bsuir.pechuro.entity.User;

import javax.servlet.jsp.JspException;

/**
 * class ChooseBanner is a function-teg which can choose certain banner for including
 */
public class ChooseBanner {
    /**
     * @param obj
     * @return String
     * @throws JspException
     */
    public static String chooseBanner(Object obj) throws JspException {
        String banner;
        User user = (User) obj;
        if (user != null && user.getRole().equals("client")) {
            banner = "/front/jsp/client/banner.jsp";
        } else {
            banner = "/front/jsp/common/banner.jsp";
        }
        return banner;
    }
}
