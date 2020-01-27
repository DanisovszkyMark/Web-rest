# Web-rest

Ebben a repositrotyban nyomonkövethető az InnoviTech próbafeladatának megoldása lépésekre bontva. Jól láthatóak a tervezési és feljesztési lépések.

# Használt technológiák

Backend - Thorntail / maven

Fronted - Angular

Database - MySql

A backend és frontend JSON-t használva kommunikál egymással.

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

# URI-k (backend):

| URI | Erőforrás | Leírás | Művelet | Válasz |
| --- | --------- | ------ | ------- | ------ |
| /auth/reg | RegistrationRequest | Új user felvétele az adatbázisba | POST | 201, 409 |
| /auth/activation/{key} | - | User aktiválása a kulcs alapján | PUT | 200, 404 |
| /auth/login | LoginRequest | User-hez token rendelés | POST | 201, 404 |
| * /auth/logout | - | Userhez tartozó token törlése (csak az aktuális) | DELETE | 200, 404, 503 |
| * /auth/block/{id} | - | User frissítése | PUT | 200, 404, 503 |
| * /users/ | UserDTO lista | Vissza adja az összes felhasználót | GET | 200, 404, 503 |
| * /users/{id} | UserDTO | User visszaadása id alapján | GET | 200, 404, 503 |
| * /users/{id} | UpdateRequest | User frissítése | PUT | 200, 404, 503 |
| * /users/{id} | - | User törlése | DELETE | 200, 404, 503 |

(*) Token alapú azonosítás után érhető el a szolgáltatás.
