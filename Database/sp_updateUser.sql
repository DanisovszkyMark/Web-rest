DELIMITER $$
CREATE PROCEDURE updateUser(
		IN p_id BIGINT,
        IN p_username VARCHAR(50),
        IN p_email VARCHAR(150),
        IN p_password VARCHAR(50),
        OUT success BOOLEAN)
DETERMINISTIC
BEGIN
    DECLARE canUpdate BOOL;
    DECLARE alreadyUsed BOOL;
    
    SET success = FALSE;
    
    SET canUpdate = (SELECT COUNT(*) FROM users WHERE id = p_id) >= 1;
    
    IF(canUpdate = TRUE) THEN
        SET alreadyUsed = (SELECT COUNT(*) FROM users WHERE id != p_id AND (username = p_username OR email = p_email)) >= 1;
        IF(alreadyUsed = FALSE) THEN
			UPDATE users SET username = p_username, email = p_email, password = SHA(p_password) WHERE id = p_id;
			SET success = TRUE;
        END IF;
    END IF;
END $$
 
DELIMITER ;