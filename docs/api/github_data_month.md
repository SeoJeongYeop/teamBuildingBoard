# CREATE

- `/api/v1/github-data-month`
- POST
- Request

```json
{
    "username": String,
    "year": int,
    "month": int,
    "commitCount": int,
    "prCount": int,
    "issueCount": int,
    "repoCreationCount": int,
    "repoContributionCount": int
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

- `/api/v1/github_data_month/{username}/{%Y%m}`
- PUT
- Request

```json
{
    "commitCount": int,
    "prCount": int,
    "issueCount": int,
    "repoCreationCount": int,
    "repoContributionCount": int
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

- `/api/v1/github_data_month/{username}/{%Y%m}`
- GET
- findById
- Request

```json
{
    "username": String,
    "year": int,
    "month":int
}
```

- Response

```json
{
    "success": true,
    "code": 200,
    "message": "Ok",
    "data": {
        "commitCount": int,
        "prCount": int,
        "issueCount": int,
        "repoCreationCount": int,
        "repoContributionCount": int
    }
}
```

- `/api/v1/github_data_month/{username}`
- GET
- findById
- Request

```json
{
    "username": String
}
```

- Response

```json
{
    "success": true,
    "code": 200,
    "message": "Ok",
    "data": [{
        "year": int,
        "month":int,
        "commitCount": int,
        "prCount": int,
        "issueCount": int,
        "repoCreationCount": int,
        "repoContributionCount": int
    }, ...]
}
```
