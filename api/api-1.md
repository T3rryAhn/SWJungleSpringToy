# 게시글 API

## 게시글글 목록 조회

<mark style="color:orange;">`GET`</mark> `/api/post`

#### Description

* 제목, 작성자명, 작성 내용, 작성 날짜를 조회
* 작성 날짜 기준 내림차순 정렬

#### Request Header

#### Request



**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "postList": [
        {
            "id": 1,
            "title": "게시글1",
            "content": "내용1",
            "username": "bin1234",
            "createdAt": "2022-12-01T12:52:06.729608",
            "modifiedAt": "2022-12-01T12:52:06.729608"
        },
        {
            "id": 2,
            "title": "게시글2",
            "content": "내용2",
            "username": "bin1234",
            "createdAt": "2022-12-01T12:52:10.566505",
            "modifiedAt": "2022-12-01T12:52:10.566505"
        },
        {
            "id": 3,
            "title": "게시글3",
            "content": "내용3",
            "username": "bin1234",
            "createdAt": "2022-12-01T12:52:16.773748",
            "modifiedAt": "2022-12-01T12:52:16.773748"
        }
    ]
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

## 게시글 작성

<mark style="color:green;">`POST`</mark> `/api/post`

#### Description

#### Request Header

#### Request

| Name       | Type   | Description |
| ---------- | ------ | ----------- |
| `title`    | String | 게시글 제목      |
| `content`  | String | 게시글 내용      |
| `username` | String | 게시글 작성자     |
| `password` | String | 게시글 비밀번호    |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "id": 1,
    "title": "게시글1",
    "content": "내용1",
    "username": "bin1234",
    "createdAt": "2022-12-01T12:52:06.729608",
    "modifiedAt": "2022-12-01T12:52:06.729608"
}
```
{% endtab %}
{% endtabs %}

***

## 게시글 상세 조회

<mark style="color:orange;">`GET`</mark> `/api/post/{id}`

#### Description

#### Request Header

Request

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "id": 1,
    "title": "게시글1",
    "content": "내용1",
    "username": "bin1234",
    "createdAt": "2022-12-01T12:52:06.729608",
    "modifiedAt": "2022-12-01T12:52:06.729608"
}
```
{% endtab %}
{% endtabs %}

***

## 게시글 수정

<mark style="color:orange;">`PUT`</mark> `/api/post/{id}`

#### Description

#### Request Header

#### Request

| Name      | Type   | Description   |
| --------- | ------ | ------------- |
| `title`   | String | 업데이트할  게시글 제목 |
| `content` | String | 업데이트할게시글 내용   |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "id": 5,
    "title": "게시글4 삭제 내용5 수정",
    "content": "내용4 삭제 내용5 수정",
    "username": "bin1234",
    "createdAt": "2022-12-01T12:56:36.821474",
    "modifiedAt": "2022-12-01T12:56:36.821474"
}
```
{% endtab %}
{% endtabs %}

***

## 게시글 삭제

<mark style="color:red;">`DELETE`</mark> `/api/post/{id}`

#### Description

#### Request Header

#### Request

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "msg": "게시글 삭제 성공",
    "statusCode": 200
}
```
{% endtab %}
{% endtabs %}
