**实际工作之SpringSecurity 简单案例实现** ：扩展根据实际业务即可用在企业项目
- 双因素认证登录 √
  - 怎么实现的双因素认证？
    双因素身份验证是指用户提供除用户名和密码之外的第二种形式的身份验证来增加额外的安全层。
  1. **思路一**：用户名和密码认证通过后，再进行验证码认证，认证通过后返回token给客户
     - 有漏洞，如果直接调用接口验证码登录，可能导致第一因素失效无作用
  2. **思路二**：用户名和密码认证通过后，生成非认证token；非认证token+短信验证码再次认证通过后，生成认证token返回给客户，即登录成功
  3. **思路三**（推荐）：用户名和密码认证通过后，在成功方法successfulAuthentication中判断是否开启了双因素认证，如果开启了，将用户信息先缓存，然后在响应头中添加标识给前端，让前端再去调用对应的接口，进行下一步的认证（totp验证码、短信验证码等等），认证成功后返回token

  - **关键类、接口**：AuthenticationManager。起辅助作用的有自上而下有Filter类、Provider类、Token类
     - WebSecurityConfigurerAdapter 、OncePerRequestFilter、AbstractAuthenticationToken
     - JwtAuthenticationFilter、SmsAuthenticationProvider、SmsVerifyCodeAuthenticationToken
     - AbstractAuthenticationProcessingFilter、AbstractUserDetailsAuthenticationProvider
  - 源码中实际是使用AuthenticationManager执行provider的authenticate进行认证的，实现定制化认证主要是两个接口：UserDetails和UserDetailsService
- Oauth2登录
- cas登录
- 微信登录
- SSO单点登录
