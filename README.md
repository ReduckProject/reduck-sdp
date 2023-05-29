# 项目介绍
SDP(Sensitive Data Protect) 主要提供web开发中敏感数据的自动加解密处理
* 接口接收中敏感参数自动处理
* 配置文件中敏感数据的自动处理
* JPA数据持久化数据的透明加解密

# 使用示例
* 接口接收中敏感参数自动处理
  * 在DTO需要处理的自动增加`@Encrypted`
* 配置文件中敏感数据的自动处理
  * 将敏感数据解密后用 `@ENC{}`包裹,使用时会自动解密
* JPA数据持久化数据的透明加解密
  * 在实体中敏感数据自动添加`@Sensitive`注解，在通过JPA处理数据时会自动加解密（会存在性能损失，查询条数很多时要考虑）