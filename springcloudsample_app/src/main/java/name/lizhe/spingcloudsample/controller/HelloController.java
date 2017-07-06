package name.lizhe.spingcloudsample.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;
    
    @Value("${message1}")  
    private String message1; 
    
    @Value("${message2}")  
    private String message2; 

    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String add(@RequestParam String message) {
        ServiceInstance instance = client.getLocalServiceInstance();
        String result = "hello" + message + " from =>>    /hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId();
        result+="<br/>";
        result+=message1;
        result+="<br/>";
        result+=message2;
        return result;
    }

}