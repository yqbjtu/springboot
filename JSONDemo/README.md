#JSONDemo

http://127.0.0.1:9092/swagger-ui.html
https://www.baeldung.com/jackson-ignore-properties-on-serialization


@JsonIgnore注解用来忽略某些字段，可以用在Field或者Getter方法上，用在Setter方法时，和Filed效果一样。这个注解只能用在POJO存在的字段要忽略的情况，不能满足现在需要的情况。

@JsonIgnoreProperties(ignoreUnknown = true)，将这个注解写在类上之后，就会忽略类中不存在的字段，可以满足当前的需要。这个注解还可以指定要忽略的字段。使用方法如下：

@JsonIgnoreProperties({ "internalId", "secretKey" })
指定的字段不会被序列化和反序列化。

https://github.com/yqbjtu/springboot/tree/master/JSONDemo

创建user的json  , 没有配置spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
                   spring.jackson.time-zone=GMT+8，  所以可以创建成功
```json
{
  "comment": "comment1",
  "fullName": "TomFull",
  "id": "456",
  "mail": "tom@qq.com",
  "name": "tom",
  "reg2Date": "2018-11-16T14:55:38.244Z",
  "regDate": "2018-11-16T14:55:38.244Z"
}
```


在application.properties中配置了
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=GMT+8

之后，默认的User对象的private Date reg2Date;会按照yyyy-MM-dd HH:mm:ss输出，
如果create输入
```json
{
  "userId": "06",
  "comment": "张三",
  "fullName": "tomFull",
  "id": "099",
  "mail": "06",
  "name": "tom",
  "reg2Date": "2018-11-17T02:46:25.793Z",
  "regDate": "2018-11-16 16:07:08"
}
```


就会报
{
  "timestamp": "2018-11-17 10:46:49",
  "status": 400,
  "error": "Bad Request",
  "exception": "org.springframework.http.converter.HttpMessageNotReadableException",
  "message": "JSON parse error: Can not deserialize value of type java.util.Date from String \"2018-11-17T02:46:25.793Z\": not a valid representation (error: Failed to parse Date value '2018-11-17T02:46:25.793Z': Unparseable date: \"2018-11-17T02:46:25.793Z\"); nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Can not deserialize value of type java.util.Date from String \"2018-11-17T02:46:25.793Z\": not a valid representation (error: Failed to parse Date value '2018-11-17T02:46:25.793Z': Unparseable date: \"2018-11-17T02:46:25.793Z\")\n at [Source: java.io.PushbackInputStream@29925daf; line: 8, column: 15] (through reference chain: com.yq.domain.vo.UserVO[\"reg2Date\"])",
  "path": "/user/users"
}

修改成
```json
{
  "userId": "06",
  "comment": "张三",
  "fullName": "tomFull",
  "id": "099",
  "mail": "06",
  "name": "tom",
  "reg2Date": "2018-11-06 16:07:08",
  "regDate": "2018-11-16 16:07:08"
}
```

这样就可以创建成功