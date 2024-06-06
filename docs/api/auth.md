---
description: 로그인, 회원가입
---

# 인증 API

## 회원가입

<mark style="color:green;">`POST`</mark> `/api/auth/signup`

**Description**
- 요청 바디에 `username`, `password` 를 보낸다.
- `username` 제한 : 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)
- `password` 제한 : 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)
- 조건에 불일치하면 회원가입이 실패하며, 실패 메시지 응답을 리턴.

**Request Header**

없음

**Request Body**

<table><thead><tr><th width="176">Name</th><th>Type</th><th>Description</th></tr></thead><tbody><tr><td><code>username</code></td><td>String</td><td>Name of the user</td></tr><tr><td><code>password</code></td><td>String</td><td>password of the user</td></tr></tbody></table>

```json
{
  "username": "bin1234",
  "password": "bin1234"
}
```

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "msg": "회원가입 성공"
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "error": "Invalid request"
}
```
{% endtab %}
{% endtabs %}

***

## 로그인

<mark style="color:green;">`POST`</mark> `/api/auth/login`

**Description**
- 요청 바디에 `username`, `password` 를 보낸다.
- `username` 제한 : 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)
- `password` 제한 : 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)
- 조건에 불일치하면 잘못된 요청
- `db` 에 저장된 정보와 일치하면 jwt 토큰 발급

**Request Header**

없음

**Request Body**

| Name       | Type   | Description  |
| ---------- | ------ | ------------ |
| `username` | String | 로그인 할 이름     |
| `password` | String | 로그인 시도할 패스워드 |

#### **Response header**

| Name          | Value            |
| ------------- | ---------------- |
| Authorization | `Bearer <token>` |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "msg": "로그인 성공"
}
```
{% endtab %}
{% endtabs %}
