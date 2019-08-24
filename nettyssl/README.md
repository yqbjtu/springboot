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
   
   以上所有步骤都完成后，还可以通过命令来查看 keystore 文件基本信息，如图 3 所示
   
   keytool -list -keystore yqClient.jks (输入cstorepass654)
   ---数据结果为----
   D:\E\workspaceGitub\springboot\nettyssl\src\main\resources\certs>keytool -list -keystore yqClient.jks
   输入密钥库口令:
   密钥库类型: jks
   密钥库提供方: SUN
   
   您的密钥库包含 2 个条目
   
   myclientalias1, 2019-8-10, PrivateKeyEntry,
   证书指纹 (SHA1): D6:8A:D7:BB:4E:12:60:95:1D:17:51:78:D3:74:D1:00:95:7E:92:08
   mysrvalias1, 2019-8-10, trustedCertEntry,
   证书指纹 (SHA1): A7:F6:DF:C8:83:82:4C:63:8D:24:57:5E:7D:25:58:E0:DE:76:DE:DF
   
   Warning:
   JKS 密钥库使用专用格式。建议使用 "keytool -importkeystore -srckeystore yqClient.jks -destkeystore yqClient.jks -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。
   
   D:\E\workspaceGitub\springboot\nettyssl\src\main\resources\certs>