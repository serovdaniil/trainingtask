package com.qulix.serovdo.core.command.page;

import com.qulix.serovdo.api.command.Command;
import com.qulix.serovdo.api.command.CommandRequest;
import com.qulix.serovdo.api.command.CommandResponse;
import com.qulix.serovdo.api.controller.PropertyContext;
import com.qulix.serovdo.api.controller.RequestFactory;

/**
 * The main page
 *
 * @author Daniil Serov
 */
public class ShowMainPageCommand implements Command {
    private static final String MAIN_PAGE = "page.main";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowMainPageCommand() {
          this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(MAIN_PAGE));
    }
    public static ShowMainPageCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowMainPageCommand INSTANCE =
                new ShowMainPageCommand();
    }
}
