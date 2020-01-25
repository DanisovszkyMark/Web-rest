DELIMITER $$
CREATE PROCEDURE login(
        IN p_username VARCHAR(150),
        IN p_password VARCHAR(50),
        OUT token VARCHAR(32))
DETERMINISTIC
BEGIN
    DECLARE canLogin BOOL;
    DECLARE v_token VARCHAR(32);
    DECLARE userId BIGINT;
    SET token = "";
    
    SET canLogin = (SELECT COUNT(*) FROM users WHERE (username = p_username OR email = p_username) AND password = p_password AND enabled = 1) >= 1;
    
    IF(canLogin = TRUE) THEN
        SET v_token = SUBSTRING(MD5(RAND()) FROM 1 FOR 32);
        WHILE((SELECT COUNT(*) FROM tokens WHERE tokenKey = v_token) >= 1) DO
			SET v_token = SUBSTRING(MD5(RAND()) FROM 1 FOR 32);
        END WHILE;
        
        SET userId = (SELECT id FROM users WHERE username = p_username OR email = p_username);
        INSERT INTO tokens (tokenKey, userId) VALUES (v_token, userId);
        
        SET token = v_token;
    END IF;
END $$
 
DELIMITER ;