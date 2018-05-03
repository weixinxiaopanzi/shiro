# shiro

使用springboot+shiro搭建的小框架,仅供学习使用,勿作商业用途!
整合了mybatis,有统一异常处理.
ShiroConfig中有配置详细说明,如:filterChainDefinitionMap.put("/css/**", "anon");就不会被拦截,免登录访问!