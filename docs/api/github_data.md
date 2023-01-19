# CREATE

- `/api/v1/github-data`
- POST
- Request

```json
{
    "username": String,
    "repoCount": int,
    "starCount": int,
    "commitCount": int,
    "prCount": int,
    "issueCount": int,
    "followerCount": int,
    "followingCount": int
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

- `/api/v1/github-data/{id}`
- PUT
- Request

```json
{
    "id": Long,
    "repoCount": int,
    "starCount": int,
    "commitCount": int,
    "prCount": int,
    "issueCount": int,
    "followerCount": int,
    "followingCount": int
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

- `/api/v1/github-data/{id}`
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
        "username": String,
        "repo_count": int,
        "star_count": int,
        "commit_count": int,
        "pr_count": int,
        "issue_count": int,
        "follower_count": int,
        "following_count": int
    }
}
```
