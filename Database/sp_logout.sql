DELIMITER $$
CREATE PROCEDURE logout(
        IN p_tokenKey VARCHAR(32),
        OUT success BOOLEAN)
DETERMINISTIC
BEGIN
    DECLARE canLogout BOOL;
    SET success = FALSE;
    
    SET canLogout = (SELECT COUNT(*) FROM tokens WHERE tokenKey = p_tokenKey) >= 1;
    
    IF(canLogout = TRUE) THEN
		DELETE FROM tokens WHERE tokenKey = p_tokenKey;
    END IF;
END $$
 
DELIMITER ;