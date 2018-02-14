package bsuir.pechuro.enumeration;

/**
 * enum JspPageName created to choose command for redirecting
 */
public enum RedirectingCommandName
{
    INDEX("/cafe.by/index"),
    BASKET("/cafe.by/basket"),
    CLIENT("/cafe.by/edit_clients"),
    CHANGE_PASSWORD("/cafe.by/change_password_forward"),
    EDIT_CLIENTS("/cafe.by/edit_clients"),
    ORDER_SHOW("/cafe.by/order_show"),
    ADMIN_LIST("/cafe.by/admin_list"),
    FIND_BY_TYPE("/cafe.by/find_by_type"),
    SEARCH_PRODUCT("/cafe.by/search_product"),
    STAFF_LIST("/cafe.by/staff_list"),
    RESET_FORM("/cafe.by/reset_form");

    private String command;

    RedirectingCommandName(String command) {
        this.command = command;
    }

    /**
     * @return String
     */
    public String getCommand() {
        return command;
    }

}
