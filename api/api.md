---
description: 로그인, 회원가입
---

# 인증 API

## 회원가입

<mark style="color:green;">`POST`</mark> `/api/auth/signup`



**Request**

<table><thead><tr><th width="176">Name</th><th>Type</th><th>Description</th></tr></thead><tbody><tr><td><code>username</code></td><td>String</td><td>Name of the user</td></tr><tr><td><code>password</code></td><td>String</td><td>password of the user</td></tr></tbody></table>

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "msg": "회원가입 성공",
    "statusCode": 200
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



**Request**

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
    "msg": "로그인 성공",
    "statusCode": 200
}
```
{% endtab %}
{% endtabs %}
