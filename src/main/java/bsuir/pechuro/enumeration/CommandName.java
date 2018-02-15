package bsuir.pechuro.enumeration;


public enum CommandName {
    SIGN_IN("all"), SIGN_UP("all"), SIGN_OUT("all"), INDEX("all"), WRONG_REQUEST("all"),
    BASKET("client"), CHANGE_LOCALE("all"), ADD_PRODUCT("admin"), ADD_PRODUCT_TO_BASKET("client"),
    REMOVE_PRODUCT_FROM_BASKET("client"), ADD_ACCOUNT("client"), EDIT_CLIENTS("admin"),
    CHANGE_CLIENT_STATUS("admin"), SET_CURRENT_PAGE("all"), SEARCH_PRODUCT("all"), FIND_BY_TYPE("all"),
    PAYMENT("client"), ADD_REVIEW("client"), ORDER_SHOW("staff"), ORDER_DENY("staff"), ORDER_ACCEPT("staff"), ADD_STAFF("admin"),
    CHANGE_PASSWORD_FORWARD("all"), CHANGE_PASSWORD("all"), STAFF_LIST("admin"), DELETE_STAFF("admin"), DELETE_CLIENT("admin"),
    ADMIN_LIST("admin"), DELETE_ADMIN("admin"), ADD_ADMIN("admin"), DELETE_REVIEW("admin"), EDIT_PRODUCT("admin"),
    RESET_PASSWORD("all"), RESET_PASSWORD_CONFIRM("all"), DELETE_PRODUCT("admin"), CHANGE_STAFF("admin"),
    CHANGE_ADMIN("admin"), RESET_FORM("all");

    private String role;

    CommandName(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
