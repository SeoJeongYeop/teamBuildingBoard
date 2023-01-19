# CREATE

- `/api/v1/teams`
- POST
- Request

```json
{
    "name": String,
    "description": String,
    "picture": String
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

# UPDATE

- `/api/v1/teams/{id}`
- PUT
- Request

```json
{
    "id": Long,
    "name": String,
    "description": String,
    "picture": String
}
```

- Response

```json
{
    "success": true,
    "code": 200,
    "message": "Ok",
    "data": {
       "id": Long
    }
}
```

# READ

- `/api/v1/teams/{id}`
- GET
- findById
- Request

```json
{
    "id": Long
}
```

- Response

```json
{
    "success": true,
    "code": 200,
    "message": "Ok",
    "data": {
        "id": Long,
        "name": String,
        "description": String,
        "picture": String
    }
}
```

- `/api/v1/teams/{id}/members`
- GET
- Request

```json
{
    "id": Long
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
        "name": String,
        "email": String,
        "githubUsername": String,
        "picture": String
    }, ...]
}
```
