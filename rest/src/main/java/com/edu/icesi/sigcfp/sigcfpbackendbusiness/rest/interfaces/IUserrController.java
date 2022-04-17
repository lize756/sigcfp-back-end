package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;

public interface IUserrController {

	/**
	 * Allow add new userr in the system.
	 * 
	 * @param userr userr to add.
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<Userr> addUserr(Userr userr);
    
    /**
	 * Allow update a userr.
	 * 
	 * @param userId id of userr to update.
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<Userr> updateUserr(long userId, Userr userr);
    
    /**
	 * Allows to obtain a userr through you id.
	 * 
	 * @param userId id to search one userr
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<Userr> getUserr(long userId);

    /**
	 * Allows delete a userr through you id
	 * 
	 * @param userId id of the userr that you want to delete
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<?> deleteUserr(long userId);

    /**
	 * Allows to obtain the list of userrs saved in the database.
	 * 
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<List<Userr>> getUserr();
	
	
	
	
	
	
	
	
	
	
}
