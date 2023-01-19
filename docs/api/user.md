# CREATE

- `/api/v1/users`
- POST
- Request

```json
{
    "name": String,
    "email": String,
    "githubUsername": String,
    "picture": String,
    "role": String
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

- `/api/v1/users/{id}`
- PUT
- Request

```json
{
    "id": Long,
    "name": String,
    "email": String,
    "picture": String,
    "role": String
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

- `/api/v1/users/{id}`
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
        "email": String,
        "githubUsername": String,
        "picture": String,
        "role": String
    }
}
```

- `/api/v1/users/{id}/teams`
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
        "description": String,
        "picture": String
    }, ...]
}
```

# DELETE

- `/api/v1/users/{id}`
- DELETE
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
      "id": Long
    }
}
```
