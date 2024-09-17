package kitten.diy.api.application.port.in.command.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public record LogoutCommand(

        HttpServletRequest request,

        HttpServletResponse response
) {
}
