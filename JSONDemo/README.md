#JSONDemo

http://127.0.0.1:9092/swagger-ui.html
https://www.baeldung.com/jackson-ignore-properties-on-serialization


@JsonIgnore注解用来忽略某些字段，可以用在Field或者Getter方法上，用在Setter方法时，和Filed效果一样。这个注解只能用在POJO存在的字段要忽略的情况，不能满足现在需要的情况。

@JsonIgnoreProperties(ignoreUnknown = true)，将这个注解写在类上之后，就会忽略类中不存在的字段，可以满足当前的需要。这个注解还可以指定要忽略的字段。使用方法如下：

@JsonIgnoreProperties({ "internalId", "secretKey" })
指定的字段不会被序列化和反序列化。


创建user的json  
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