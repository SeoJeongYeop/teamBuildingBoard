# CREATE

- `/api/v1/user-team-relations`
- POST
- Request

```json
{
    "teamId": int,
    "userId": int
}
```

- Response

```json
{
    "success": true,
    "code": 201,
    "message": "Created",
    "data": {
       "id": Long
    }
}
```

# READ

- `/api/v1/user-team-relation/teams/{teamId}`
- GET
- Request

```json
{
    "teamId": Long
}
```

- Response

```json
{
    "success": true,
    "code": 200,
    "message": "Ok",
    "data": [{
        "id": Long,
        "userId": Long
    }, ...]
}
```

- `/api/v1/user-team-relation/users/{userId}`
- GET
- Request

```json
{
    "userId": Long
}
```

- Response

```json
{
    "success": true,
    "code": 200,
    "message": "Ok",
    "data": [{
        "id": Long,
        "teamId": Long
    }, ...]
}
```
