package bsuir.pechuro.enumeration;

/**
 * enum JspPageName created to choose jsp pages
 */
public enum JspPageName {
    INDEX("/front/jsp/index.jsp"),
    ERROR("/front/jsp/error.jsp"),
    BASKET("/front/jsp/client/basket.jsp"),
    CLIENTS("/front/jsp/admin/client.jsp"),
    CHANGE_PASSWORD("/front/jsp/common/changePassword.jsp"),
    STAFF("/front/jsp/admin/staff.jsp"),
    ADMIN("/front/jsp/admin/adminList.jsp"),
    RESET_FORM("/front/jsp/client/resetForm.jsp");


    private String path;

    JspPageName(String path) {
        this.path = path;
    }

    /**
     * @return String
     */
    public String getPath() {
        return path;
    }

}