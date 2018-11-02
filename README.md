# shiro学习记录

###### 1.授权需要继承AuthorizingRealm类， 并实现其dogetAuthorizationInfo方法
###### 2. AuthorizingRealm继承自AuthenticationRealm，但没有实现AuthenticationRealm中的dogetAuthenticationInfo方法，
所以认证和授权只需要继承AuthorizingRealm就可以了。同时实现他的两个方法。


###### 1. 为什么使用md5盐值加密：我们希望密码相同时也能有所区别。
###### 2. 如何做到：
	(1). 在doGetAuthenticationInfo方法返回值创建SimpleAuthenticationInfo对象时，
	使用SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName) 构造器。
	(2). 使用ByteSource.Util.byte()来计算盐值
	(3). 盐值需要唯一：一般使用随机字符串或者user_id
	(4). 使用 new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations); 来计算盐值加密后的密码值。

######  1. 如何把一个字符串加密成md5
######  2. 替换当前的Realm的credentialsMather属性，直接使用

密码的比对：
通过AuthenticationRealm的credentialsMatcher属性来进行密码的比对

###### 1. 获取当前的 Subject. 调用 SecurityUtils.getSubject();
###### 2. 测试当前的用户是否已经被认证. 即是否已经登录. 调用 Subject 的 isAuthenticated() 
###### 3. 若没有被认证, 则把用户名和密码封装为 UsernamePasswordToken 对象
> *  创建一个表单页面
> *  把请求提交到 SpringMVC 的 Handler
> *  获取用户名和密码. 
### 4. 执行登录: 调用 Subject 的 login(AuthenticationToken) 方法. 
### 5. 自定义 Realm 的方法, 从数据库中获取对应的记录, 返回给 Shiro.
> * 实际上需要继承 org.apache.shiro.realm.AuthenticatingRealm 类
> * 实现 doGetAuthenticationInfo(AuthenticationToken) 方法. 
### 6. 由 shiro 完成对密码的比对. 