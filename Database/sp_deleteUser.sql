DELIMITER $$
CREATE PROCEDURE deleteUser(
		IN p_id BIGINT,
        OUT success BOOLEAN)
DETERMINISTIC
BEGIN
    DECLARE canDelete BOOL;
    SET success = FALSE;
    
    SET canDelete = (SELECT COUNT(*) FROM users WHERE id = p_id) >= 1;
    
    IF(canDelete = TRUE) THEN
        DELETE FROM activationKeys WHERE userId = p_id;
        DELETE FROM tokens WHERE userId = p_id;
        DELETE FROM users WHERE id = p_id;
        
        SET success = TRUE;
    END IF;
END $$
 
DELIMITER ;