DELIMITER $$
CREATE PROCEDURE activateUser(
		IN p_activationKey VARCHAR(12),
        OUT success BOOLEAN)
DETERMINISTIC
BEGIN
    DECLARE canActivate BOOL;
    DECLARE userId BIGINT;
    SET success = FALSE;
    
    SET canActivate = (SELECT COUNT(*) FROM activationKeys WHERE activationKey = p_activationKey) >= 1;
    
    IF(canActivate = TRUE) THEN
		SET userId = (SELECT userId FROM activationKeys WHERE activationKey = p_activationKey);
        UPDATE users SET enabled = 1 WHERE id = userId;
        
        DELETE FROM activationKeys WHERE activationKey = p_activationKey;
        
        SET success = TRUE;
    END IF;
END $$
 
DELIMITER ;