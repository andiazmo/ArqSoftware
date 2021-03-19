/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Anyelo
 */

    
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "video not found")
    public class IncorrectLogin extends RuntimeException {
        
        
}
    

