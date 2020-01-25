DELIMITER $$
CREATE PROCEDURE registration(
		IN p_username VARCHAR(50),
        IN p_email VARCHAR(150),
        IN p_password VARCHAR(50),
        OUT success BOOLEAN)
DETERMINISTIC
BEGIN
    DECLARE canRegister BOOL;
    DECLARE userId BIGINT;
    DECLARE v_activationKey VARCHAR(12);
    SET success = FALSE;
    
    -- Vizsgálat: egyedi-e
    SET canRegister = (SELECT COUNT(*) FROM Users WHERE username = p_username OR email = p_email) = 0;
    
    IF(canRegister = TRUE) THEN
		-- Rekord felvétele
		INSERT INTO Users (username, password, email)
			VALUES(p_username, SHA(p_password), p_email);
		
        SET userId = (SELECT id FROM Users WHERE username = p_username);
        
		-- Egyedi aktivációs kulcs generálása
        SET v_activationKey = SUBSTRING(MD5(RAND()) FROM 1 FOR 12);
        WHILE((SELECT COUNT(*) FROM activationKeys WHERE activationKey = v_activationKey) >= 1 ) DO
				SET v_activationKey = SUBSTRING(MD5(RAND()) FROM 1 FOR 12);
		END WHILE;
        
		INSERT INTO activationKeys (activationKey, userId)
			VALUES(v_activationKey, userId);
        
        SET success = TRUE;
	END IF;
END $$
 
DELIMITER ;