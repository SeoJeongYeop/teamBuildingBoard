# CREATE

- `/api/v1/comments`
- POST
- Request

```json
{
    "content": String,
    "author": String,
    "authorId": Long,
    "postId": Long
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

- `/api/v1/comments/{id}`
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

- `/api/v1/comments/{id}`
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
        "content": String,
        "author": String
    }
}
```

# DELETE

- `/api/v1/comments/{id}`
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
