# CREATE

- `/api/v1/boards`
- POST
- Request

```json
{
    "title": String
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

- `/api/v1/boards/{id}`
- PUT
- Request

```json
{
    "id": Long,
    "title": String,
    "remark": String
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

- `/api/v1/boards/{id}`
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
        "remark": String,
        "posts": [
            "Post Obj", 
            ...
        ]
    }
}
```

# DELETE

- `/api/v1/boards/{id}`
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
