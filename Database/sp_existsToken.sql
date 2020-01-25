DELIMITER $$
CREATE PROCEDURE existsToken(
        IN p_tokenKey VARCHAR(32),
        OUT ex BOOLEAN)
DETERMINISTIC
BEGIN
    SET ex = (SELECT COUNT(*) FROM tokens WHERE tokenKey = p_tokenKey) >= 1;
END $$
 
DELIMITER ;