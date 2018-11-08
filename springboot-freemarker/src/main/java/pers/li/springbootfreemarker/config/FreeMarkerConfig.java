package pers.li.springbootfreemarker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pers.li.springbootfreemarker.tags.CustomTagDirective;

import javax.annotation.PostConstruct;

/**
 * springboot中自定义指令
 *
 * 配置文件中：
     <property name="freemarkerVariables">
         <map>
            <entry key="role" value-ref="customTagDirective" />
         </map>
     </property>
 *
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected CustomTagDirective customTagDirective;
    @PostConstruct
    public void setSharedVariable() {
        // icbcTag即为页面上调用的标签名
        configuration.setSharedVariable("role", customTagDirective);
    }
}