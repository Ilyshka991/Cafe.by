package bsuir.pechuro.enumeration;


public enum AttributeParameterName {
    SIGNIN_LOGIN("signin_login"),
    SIGNIN_PASSWORD("signin_password"),
    SIGNUP_NAME("signup_name"),
    SIGNUP_SURNAME("signup_surname"),
    SIGNUP_EMAIL("signup_email"),
    SIGNUP_LOGIN("signup_login"),
    SIGNUP_PASSWORD("signup_password"),
    USER("user"),
    J_SESSION_ID("jsessionid"),
    INFORMATION("information"),
    LOCALE("locale"),
    PRODUCT_TYPE("product_type"),
    NAME_RU("name_ru"),
    NAME_EN("name_en"),
    VALUE("value"),
    COST("cost"),
    DESCRIPTION_RU("description_ru"),
    DESCRIPTION_EN("description_en"),
    PRODUCT_ID("productId"),
    IMAGE("file"),
    NUMBER_FOR_DELETE("number_for_delete"),
    NUMBER_FOR_ADD("number_for_add"),
    CLIENT_ID("clientId"),
    CURRENT_PAGE("current_page"),
    SEARCH_NAME("search_name"),
    REVIEW_TEXT("review_text"),
    MARK_VALUE("mark_value"),
    ORDER_ID("order_id"),
    STAFF_LOGIN("staff_login"),
    STAFF_PASSWORD("staff_password"),
    OLD_PASSWORD("changePassword_old"),
    NEW_PASSWORD("changePassword_new"),
    NEW_PASSWORD_REPEAT("changePassword_re_new"),
    POINT("point"),
    ADD_STAFF_ERROR("add_staff_error"),
    BASKET_ERROR("basket_error"),
    CHANGE_CLIENT_STATUS_ERROR("change_client_status_error"),
    CHANGE_PASSWORD_ERROR("change_password_error"),
    PRODUCT_NOT_FIND("not_find"),
    STAFF_ID("staffId"),
    ADMIN_ID("adminId"),
    REVIEW_ID("reviewId"),
    HEADER_ERROR("header_error"),
    ACCOUNT_PAYMENT_ERROR("account_payment_error"),
    RESET_EMAIL("reset_email"),
    RESET_NEW_PASSWORD("resetPassword_new"),
    ADMIN_LOGIN("admin_login"),
    ADMIN_PASSWORD("admin_password"),
    ADD_ADMIN_ERROR("add_admin_error"),
    CODE("code"),
    CLIENT("client");


    private String value;

    AttributeParameterName(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

}
