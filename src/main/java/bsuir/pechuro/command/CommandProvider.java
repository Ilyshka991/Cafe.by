package bsuir.pechuro.command;


import bsuir.pechuro.command.impl.forwarding.*;
import bsuir.pechuro.command.impl.redirecting.*;
import bsuir.pechuro.enumeration.CommandName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public final class CommandProvider {
    private final static CommandProvider instance = new CommandProvider();
    private static final Logger LOGGER = Logger.getLogger(CommandProvider.class);
    private Map<CommandName, ICommand> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.INDEX, new Index());
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_UP, new SignUp());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
        repository.put(CommandName.ADD_PRODUCT, new AddProduct());
        repository.put(CommandName.BASKET, new Basket());
        repository.put(CommandName.ADD_PRODUCT_TO_BASKET, new AddBasketProduct());
        repository.put(CommandName.REMOVE_PRODUCT_FROM_BASKET, new RemoveBasketProduct());
        repository.put(CommandName.ADD_ACCOUNT, new AddAccount());
        repository.put(CommandName.EDIT_CLIENTS, new ClientList());
        repository.put(CommandName.CHANGE_CLIENT_STATUS, new ChangeClientStatus());
        repository.put(CommandName.SET_CURRENT_PAGE, new SetCurrentPage());
        repository.put(CommandName.SEARCH_PRODUCT, new SearchProduct());
        repository.put(CommandName.FIND_BY_TYPE, new FindByType());
        repository.put(CommandName.PAYMENT, new Payment());
        repository.put(CommandName.ADD_REVIEW, new AddReview());
        repository.put(CommandName.ORDER_SHOW, new OrderShow());
        repository.put(CommandName.ORDER_ACCEPT, new OrderAccept());
        repository.put(CommandName.ORDER_DENY, new OrderDeny());
        repository.put(CommandName.ADD_STAFF, new AddStaff());
        repository.put(CommandName.CHANGE_PASSWORD_FORWARD, new ChangePassword());
        repository.put(CommandName.CHANGE_PASSWORD, new ChangePasswordFunction());
        repository.put(CommandName.STAFF_LIST, new StaffList());
        repository.put(CommandName.DELETE_STAFF, new DeleteStaff());
        repository.put(CommandName.DELETE_CLIENT, new DeleteClient());
        repository.put(CommandName.ADMIN_LIST, new AdminList());
        repository.put(CommandName.DELETE_ADMIN, new DeleteAdmin());
        repository.put(CommandName.ADD_ADMIN, new AddAdmin());
        repository.put(CommandName.DELETE_REVIEW, new DeleteReview());
        repository.put(CommandName.EDIT_PRODUCT, new EditProduct());
        repository.put(CommandName.RESET_PASSWORD, new ResetPassword());
        repository.put(CommandName.RESET_PASSWORD_CONFIRM, new ResetPasswordConfirm());
        repository.put(CommandName.DELETE_PRODUCT, new DeleteProduct());
        repository.put(CommandName.CHANGE_STAFF, new ChangeStaff());
        repository.put(CommandName.CHANGE_ADMIN, new ChangeAdmin());
        repository.put(CommandName.RESET_FORM, new ResetForm());
    }


    public static CommandProvider getInstance() {
        return instance;
    }


    public ICommand getCommand(HttpServletRequest request) {
        ICommand iCommand = repository.get(CommandName.WRONG_REQUEST);
        String command = request.getRequestURI();
        command = command.replace("/cafe.by/", "");
        try {
            CommandName commandName = CommandName.valueOf(command.toUpperCase().replace('-', '_'));
            LOGGER.log(Level.DEBUG, "Command name:" + commandName.toString());
            iCommand = repository.get(commandName);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.DEBUG, "No such support command name");
            request.setAttribute("wrongAction", e.getMessage());
        }
        return iCommand;
    }
}
