# 证书生成过程


 ### 1 生成Netty服务端私钥和证书仓库命令
 keytool -genkey -alias mySrvAlias1 -keysize 2048 -validity 365 -keyalg RSA -dname "CN=localhost" -keypass skeypass123 -storepass sstorepass456 -keystore yqServer.jks

 -storepass 指定密钥库的密码(获取keystore信息所需的密码) 
 -keypass 指定别名条目的密码(私钥的密码) 

 ### 2  生成客户端的密钥对和证书仓库，用于将服务端的证书保存到客户端的授信证书仓库中
 keytool -genkey -alias myClientAlias1 -keysize 2048 -validity 365 -keyalg RSA -dname "CN=localhost" -keypass ckeypass987 -storepass cstorepass654 -keystore yqClient.jks
 
 ### 3  生成Netty服务端自签名证书
 keytool -export -alias mySrvAlias1 -keystore yqServer.jks -storepass sstorepass456 -file yqServer.cer
 
 ### 4  将Netty服务端的证书导入到客户端的证书仓库中：
 keytool -import -trustcacerts -alias mySrvAlias1 -file yqServer.cer -storepass cstorepass654 -keystore yqClient.jks
 
 如果你只做单向认证，则到此就可以结束了，如果是双响认证，则还需继续往下走

 ### 5  生成客户端自签名证书
   keytool -export -alias myClientAlias1 -keystore yqClient.jks -storepass cstorepass654 -file yqClient.cer

 ### 6 将客户端的自签名证书导入到服务端的信任证书仓库中：

   keytool -import -trustcacerts -alias myClientSelfAlias -file yqClient.cer -storepass sstorepass456 -keystore yqServer.jks