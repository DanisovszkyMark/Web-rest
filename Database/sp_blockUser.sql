DELIMITER $$
CREATE PROCEDURE blockUser(
		IN p_id BIGINT,
        OUT success BOOLEAN)
DETERMINISTIC
BEGIN
    DECLARE canBlock BOOL;
    SET success = FALSE;
    
    SET canBlock = (SELECT COUNT(*) FROM users WHERE id = p_id) >= 1;
    
    IF(canBlock = TRUE) THEN
        DELETE FROM activationKeys WHERE userId = p_id;
        DELETE FROM tokens WHERE userId = p_id;
        UPDATE users SET enabled = 0 WHERE id = p_id;
        
        SET success = TRUE;
    END IF;
END $$
 
DELIMITER ;