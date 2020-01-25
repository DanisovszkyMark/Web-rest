DELIMITER $$
CREATE PROCEDURE insertToken(
		IN p_userId BIGINT,
        IN p_tokenKey VARCHAR(32),
        OUT success BOOLEAN)
DETERMINISTIC
BEGIN
    DECLARE canInsert BOOL;
    SET success = FALSE;
    
    SET canInsert = (SELECT COUNT(*) FROM tokens WHERE tokenKey = p_tokenKey) = 0;
    
    IF(canInsert = TRUE) THEN
        INSERT INTO tokens (tokenKey, userId) VALUES(p_tokenKey, p_userId);
        
        SET success = TRUE;
    END IF;
END $$
 
DELIMITER ;