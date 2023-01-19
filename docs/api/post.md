# CREATE

- `/api/v1/posts`
- POST
- Request

```json
{
    "title": String,
    "content": String,
    "author": String,
    "boardId": Long
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

- `/api/v1/posts/{id}`
- PUT
- Request

```json
{
    "id": Long,
    "title": String,
    "content": String
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

- `/api/v1/posts/{id}`
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
        "title": String,
        "content": String,
        "author": String
    }
}
```

- `/api/v1/posts/{id}/comments`
- GET
- findById
- Request

```json
{
    "id": Long
}
```

- Response: comment의 리스트를 응답

```json
{
    "success": true,
    "code": 200,
    "message": "Ok",
    "data": [
        {
            "id": Long,
            "content": String,
            "author": String,
            "created": String
        }, ... ]
}
```

# DELETE

- `/api/v1/posts/{id}`
- DELETE
- Request

```json
{
    "id": Long
}
```

- Response
-

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
