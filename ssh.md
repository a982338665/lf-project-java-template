
**1.ssh回顾:**

    1.web       struts2
    2.service   spring      
    3.dao       hibernate
    
    hbm生成数据表
    
    基于SSH实现员工管理系统之案例实现篇 https://www.imooc.com/learn/679 
    基于SSH实现员工管理系统之框架整合篇 https://www.imooc.com/learn/586
                                     
**2.ssh和ssm的区别：**

    1.三层：
        1.web       struts2     springmvc
        2.service   spring      spring
        3.dao       hibernate   mybatis
    2.相同点：
        1.spring依赖注入管理各层组件
        2.spring-aop管理事务日志权限等
    3.不同点：
        1.struts2和springmvc的区别：
            1.struts2：action 类级别
                http请求-->经过一系列过滤器到达FilterDispatcher-->询问ActionMapper请求是否需要调用某个action
                需要调用Action-->FilterDispatcher-->转交请求给ActionProxy-->询问配置文件Configuration Manage-->找到action类
                ActionProxy创建一个ActionInvocation实例-->调用action类
                Action执行完毕，ActionInvocation负责根据struts.xml中的配置找到对应的返回结果：Action链,JSP或者FreeMarker的模版。
                处理结果返回客户端
            2.springmvc：方法级别
                 http请求-->web服务器-->解析http匹配DispatcherServlet的请求映射路径（在web.xml中指定）
                 DipatcherServlet接收请求-->通过HandlerMapping的配置-->找到处理请求的处理器（Handler）Handler将具体的处理进行封装
                 再由具体的HandlerAdapter对Handler进行具体的调用
                 Handler对数据处理完成-->返回一个ModelAndView()对象给DispatcherServlet(逻辑视图)
                 DispatcherSevlet通过ViewResolver将逻辑视图转化为真正的视图View
                 DispatcherSevlet通过model将真正视图返回客户端
                 