package minMaxQuarto;

import me.kutrumbos.DdpClient;

import java.net.URISyntaxException;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: thaffe
 * Date: 10.09.13
 * Time: 18:17
 * To change this template use File | Settings | File Templates.
 */
public class NetworkQuarto {
    public static void main(String[] args) {
        //Initer observeren
    	boolean test = false;
       if(test)
    	   new TestObserver();
       else{
    	   new NetworkGame();
       }
    }
}
