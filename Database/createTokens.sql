CREATE TABLE tokens(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tokenKey VARCHAR(12) UNIQUE NOT NULL,
    userId BIGINT,
    FOREIGN KEY(userId) REFERENCES users(id)
)