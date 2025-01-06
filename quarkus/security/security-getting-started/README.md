# Security Getting Started

Demo based on: https://quarkus.io/guides/security-getting-started-tutorial

## How to request

### Database
To grant the access for authorized endpoints (e.g. `/users/me`), one has to use Basic authentication. The only
registered users are:
```markdown
| Username | Password  | Roles      |
|----------|-----------|------------|
| Admin    | admin     | admin      |
| Alice    | qwerty123 | admin,user |
```

### Corresponding Base64-encoded data
- For `Admin`: `QWRtaW46YWRtaW4=`
- For `Alice`: `QWxpY2U6cXdlcnR5MTIz`
- Example call:
```shell
curl -X GET -w "\n" -H "Authorization: Basic QWxpY2U6cXdlcnR5MTIz" http://localhost:8080/users/me -v
```

## Example configuration of the database (in prod mode)
```shell
podman run -d --name postgres --replace -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=db -p 5432:5432 postgres:16.3
```
