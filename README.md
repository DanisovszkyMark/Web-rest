# Web-rest

Ebben a repositrotyban nyomonkövethető az InnoviTech próbafeladatának megoldása lépésekre bontva. Jól láthatóak a tervezési és feljesztési lépések.

# Használt technológiák

Backend - Thorntail / maven

Fronted - Angular

Database - MySql

# Adatbázis

<p align="center">
<img src="Documentation/databaseDesign.png">
</p>

**Users**

Tárolt eljárások:
- registration(p_username, p_email, p_password): boolean
- activateUser(p_activationKey): boolean
- blockUser(p_id): boolean
- updateUser(p_id, p_username, p_email, p_password): boolean
- deleteUser(p_id): boolean

**Tokens**

Tárolt eljárások:
- insertToken(p_userId, p_tokenKey): boolean
- existsToken(p_tokenKey): boolean
- deleteToken(p_tokenKey): boolean
- login(p_username, p_password): varchar(32)
- logout(p_tokenKey): boolean
