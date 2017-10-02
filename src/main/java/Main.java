import Servlets.AllRequestsServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Arrays;

/**
 * Created by kl on 02.10.2017.
 * :3
 */
public class Main {
    public static void main(String[] args) throws Exception{

        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(allRequestsServlet), "/*");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        int port = 8080;
        if(args.length>=1){
            port = Integer.parseInt(args[0].split("=")[1]);
        }
        Server server = new Server(port);
        System.out.println(Arrays.toString(args));
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started at "+port);
        server.join();
    }
}
