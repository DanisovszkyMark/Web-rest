DELIMITER $$
CREATE PROCEDURE deleteToken(
        IN p_tokenKey VARCHAR(32),
        OUT success BOOLEAN)
DETERMINISTIC
BEGIN
    DECLARE canDelete BOOL;
    SET success = FALSE;
    
    SET canDelete = (SELECT COUNT(*) FROM tokens WHERE tokenKey = p_tokenKey) >= 1;
    
    IF(canDelete = TRUE) THEN
        DELETE FROM tokens WHERE tokenKey = p_tokenKey;
        
        SET success = TRUE;
    END IF;
END $$
 
DELIMITER ;