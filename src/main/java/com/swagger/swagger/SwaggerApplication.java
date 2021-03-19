package com.swagger.swagger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class SwaggerApplication extends SpringBootServletInitializer {

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
            return builder.sources(SwaggerApplication.class);

        }
        
        public static InetAddress getClientIpAddr(HttpServletRequest request) {  
            String ip = request.getHeader("X-Forwarded-For");  
            System.out.println("Metodo getClientIpAddr");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("HTTP_CLIENT_IP");  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
               ip = request.getRemoteAddr();  
            }  
            try {
               return InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
               return null;
            }  
}
	public static void main(String[] args) {
            
               //System.out.println("args:::"+args[0]);
               SwaggerApplication swg = new SwaggerApplication();
               String banner = SpringApplication.BANNER_LOCATION_PROPERTY;
               System.out.println("banner:::"+banner);
	       SpringApplication.run(SwaggerApplication.class, args);
	}

}
