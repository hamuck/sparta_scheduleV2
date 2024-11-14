# sparta_schedule


---

# API 명세서

## User
| 기능        | Method | URL               | request                     | response | 상태코드                        |
|-----------|--------|-------------------|-----------------------------|----------|-----------------------------|
| 유저 생성(가입) | POST   | /api/users/signup | 요청 body                     | 등록정보     | 201: 정상 생성, 400: 등록 실패      |
| 유저 로그인    | POST   | /api/users/login  | 요청 body, HttpServletRequest | -        | 200: 정상 로그인                 |
| 유저 로그아웃   | POST   | /api/users/logout | HttpServletRequest          | -        | 200: 정상 로그아웃                |
| 유저 조회     | GET    | /api/users/{id}   | -                           | 단건 응답 정보 | 200: 정상 조회                  |
| 유저 삭제     | DELETE | /api/users/{id}   | 요청 body                     | -        | 200: 정상 수정, 404: 존재하지 않는 유저 |

## Schedule
| 기능        | Method | URL                 | request | response          | 상태코드                        |
|-----------|--------|---------------------|---------|-------------------|-----------------------------|
| 스케쥴 생성    | POST   | /api/schedules      | 요청 body | 등록정보              | 201: 정상 생성                  |
| 스케쥴 전체 조회 | GET    | /api/schedules      | -       | 다건 응답 정보(List)    | 200: 정상 조회                  |
| 스케쥴 단건 조회 | GET    | /api/schedules/{id} | -       | 단건 응답 정보          | 200: 정상 조회                  |
| 스케쥴 수정    | PUT    | /api/schedule/{id}  | 요청 body | 단건 응답 정보(수정된 스케쥴) | 200: 정상 조회                  |
| 스케쥴 삭제    | DELETE | /api/schedules/{id} | 요청 body | -                 | 200: 정상 수정, 404: 존재하지 않는 유저 |
---

## 유저 생성
유저를 생성합니다.

|메서드| 요청 URL                              |
|---|-------------------------------------|
|POST| http://{SERVER_URL}/api/user/signup |

**Request**

```json
{
  "username" : "유저 이름", 
  "password" : "비밀번호",
  "usermail" : "유저 이메일"
}
```


| 파라미터     |타입   | 필수여부 | 설명     |
|----------|---|------|--------|
| username | String  | 필수   | 유저 이름  |
| password | String  | 필수   | 비밀 번호  | 
| usermail | String | 선택   | 유저 이메일 |

**Response**
```json
{
  "id": 1,
  "username": "유저 이름",
  "usermail": "유저 이메일"
}
```

| 파라미터     | 타입     | 필수여부 | 설명       |
|----------|--------|------|----------|
| id       | Long   | 필수   | 유저 고유 번호 |
| username | String | 필수   | 유저 이름    |
| usermail | String | 선택   | 유저 이메일   |



---

## 유저 로그인
사용자가 로그인 합니다.

| 메서드  | 요청 URL                              |
|------|-------------------------------------|
| POST | http://{SERVER_URL}/api/users/login |

**Request**
```json
{
  "usermail": "유저 이메일",
  "password": "비밀번호"
}
```

| 파라미터     | 타입     | 필수여부 | 설명      |
|----------|--------|------|---------|
| usermail | String | 필수   | 유저 이메일  |
| password | String | 필수   | 유저 비밀번호 |


**Response**

- Response 없음

---

## 유저 로그아웃
사용자가 로그아웃 합니다.

| 메서드  | 요청 URL                               |
|------|--------------------------------------|
| POST | http://{SERVER_URL}/api/users/logout |

**Request**
- Request 없음

**Response**

- Response 없음

---

## 선택 유저 조회
선택한 유저를 조회합니다.

|메서드| 요청 URL                             |
|---|------------------------------------|
|GET| http://{SERVER_URL}/api/users/{id} |

**Request**

- Request 없음


**Response**

```json
{
  "username" : "유저 이름",
  "usermail" : "유저 이메일"
}
```

| 파라미터     | 타입     | 필수여부 | 설명     |
|----------|--------|------|--------|
| username | String | 필수   | 유저 이름  |
| usermail | String | 필수   | 유저 이메일 |


---
##  유저 삭제
선택한 유저의 정보를 삭제합니다.

| 메서드    | 요청 URL                             |
|--------|------------------------------------|
| DELETE | http://{SERVER_URL}/api/users/{id} |

**Request**

```json
{
  "password": "비밀번호"
}

```


**Response**

- Response 없음

---
## 스케쥴 생성
스케쥴을 생성합니다.

**Request**

```json
{
  "userid" : 1,
  "title" : "제목",
  "contents" : "내용"
}
```

| 메서드  |요청 URL|
|------|---|
| POST |http://{SERVER_URL}/api/schedules|


| 파라미터    | 타입     | 필수여부  | 설명        |
|---------|--------|---|-----------|
| userid  | Long   | 필수  | 유저 고유 번호  | 
| title   | String | 필수 | 스케쥴 제목    |
| content | String | 선택 | 스케쥴 상세 내용 |

**Response**

```json
{
  "id" : 1,
  "userid" : 1,
  "title" : "제목",
  "contents" : "내용"
}
```

| 파라미터     | 타입     | 필수여부 | 설명        |
|----------|--------|------|-----------|
| id       | Long   | 필수   | 스케쥴 고유 번호 |
| userid   | Long   | 필수   | 유저 고유 번호  |
| title    | String | 필수   | 스케쥴 제목    |
| content  | String | 선택   | 스케쥴 상세 내용 |

---

## 스케쥴 목록 전체 조회
저장되어있는 모든 일정을 조회합니다.

**Request**

- Request 없음

| 메서드 |요청 URL|
|-----|---|
| GET |http://{SERVER_URL}/api/schedules|

**Response**

```json
[
      {
        "id" : 1,
        "userid" : 1,
        "title" : "제목",
        "contents" : "내용"
      },
      {
        "id" : 2,
        "userid" : 2,
        "title" : "제목2",
        "contents" : "내용2"
      },
      {
        "id" : 3,
        "userid" : 1,
        "title" : "제목3",
        "contents" : "내용3"
      }
]
```
| 파라미터     | 타입     | 필수여부 | 설명        |
|----------|--------|------|-----------|
| id       | Long   | 필수   | 스케쥴 고유 번호 |
| userid   | Long   | 필수   | 유저 고유 번호  |
| title    | String | 필수   | 스케쥴 제목    |
| content  | String | 선택   | 스케쥴 상세 내용 |


---

## 스케쥴 선택 조회
선택한 스케쥴의 정보를 조회합니다.

| 메서드 | 요청 URL                                 |
|-----|----------------------------------------|
| GET | http://{SERVER_URL}/api/schedules/{id} |

**Request**

- Request 없음

**Response**
```json
{
  "id" : 1,
  "userid" : 1,
  "title" : "제목",
  "contents" : "내용"
}
```

| 파라미터     | 타입     | 필수여부 | 설명        |
|----------|--------|------|-----------|
| id       | Long   | 필수   | 스케쥴 고유 번호 |
| userid   | Long   | 필수   | 유저 고유 번호  |
| title    | String | 필수   | 스케쥴 제목    |
| content  | String | 선택   | 스케쥴 상세 내용 |



------

## 스케쥴 선택 수정
선택한 스케쥴의 정보를 수정합니다.

| 메서드 | 요청 URL                                 |
|-----|----------------------------------------|
| PUT | http://{SERVER_URL}/api/schedules/{id} |

**Request**
```json
{
  "userid" : 1,
  "password" : "비밀번호",
  "title" : "제목",
  "contents" : "내용"
}
```

| 파라미터     | 타입     | 필수여부 | 설명        |
|----------|--------|------|-----------|
| userid   | Long   | 필수   | 유저 고유 번호  |
|password | String | 필수 | 유저 비밀번호 |
| title    | String | 필수   | 스케쥴 제목    |
| content  | String | 선택   | 스케쥴 상세 내용 |

**Response**
```json
{
  "id" : 1,
  "userid" : 1,
  "title" : "제목",
  "contents" : "내용"
}
```

| 파라미터     | 타입        | 필수여부 | 설명        |
|----------|-----------|------|-----------|
| id       | Integer   | 필수   | 스케쥴 고유 번호 |
| userid   | Integer   | 필수   | 유저 고유 번호  |
| title    | String    | 필수   | 스케쥴 제목    |
| content  | String    | 선택   | 스케쥴 상세 내용 |



---

## 스케쥴 선택 삭제
선택한 스케쥴의 정보를 삭제합니다.

| 메서드    | 요청 URL                                 |
|--------|----------------------------------------|
| DELETE | http://{SERVER_URL}/api/schedules/{id} |

**Request**
```json
{
  "password" : "비밀번호"
}
```

| 파라미터     | 타입     | 필수여부 | 설명     |
|----------|--------|------|--------|
|password | String | 필수 | 비밀번호 |

**Response**

- Response 없음

---
# ERD

![스크린샷 2024-11-14 오후 9.32.55.png](..%2F..%2F..%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-11-14%20%EC%98%A4%ED%9B%84%209.32.55.png)



