
**1.spring-freemarker**

**2.springboot-freemarker**

    https://start.spring.io/
    输入信息生成项目Demo:web devTools,freemarker

**3.变量申明与取值**

    <#assign var="hello,spring"/>
        ${var}
    
    1.基本类型取值
        <li>int:${intVar}</li>
        <li>long:${longVar}</li>
        <li>String:${StringVar}</li>
        <li>Double:${DoubleVar}</li>
        <li>boolean:${boolVar?string('true','false')}</li>
        <li>boolean:${boolVarfalse?string('true','false')}</li>

    2.日期取值：
        <li>年月日时分秒：${currentTime?string('yyyy-MM-dd HH:mm:ss zzzz')}</li>
        <li>年月日时分秒：${currentTime?string('yyyy-MM-dd HH:mm:ss')}</li>
        <li>年月日：${currentTime?string('yyyy-MM-dd')}</li>
        <li>时分秒：${currentTime?string('HH:mm:ss')}</li>
    3.null取值：
        <!--加！号，会先判断是否存在，若值为null则不显示-->
        <li>null：${nullVar!}</li>
        <li>null：${nullVar!'我是null'}</li>
    4.在后端没有定义的取值：
        <!--加！号，若值为null则不显示-->
        <li>missing：${sdfsafasdf!'默认值'}</li>
        
**4.变量赋值与运算：**
    
    1.取基本值：
        <#assign a=100/>
        <li>a=${a}</li>
        <li>输出模板无法解析的值:a=${r'${a}'}</li>
        <li>a+100=${a+100}</li>
        <li>a*100=${a*100}</li>
    2.封装对象的取值：
        <li>${userInfo.id}</li>
        <li>${userInfo.name}</li>
        <!--加()!会判断其中的每个变量是否存在-->
        <li>${(userInfo.name)!'不存在输出'}</li>
        <!--富文本测试输出-->
        <li>${(htmlUserInfo.name)! }</li>
        <!--富文本当作文本输出-->
        <li>${(htmlUserInfo.name)!?html}</li>
    3.集合list的取值：
        <!--加两个??或?exist判断list是否为null-->
        <!-- ?exist -->
        <#if ListStringInfo??>
            <#list ListStringInfo as list>
                <li>${list!}</li>
            </#list>
        </#if>
        <#list ListUserInfo as list>
            <li>${(list)!}</li>
            <li>${(list.name)!}</li>
        </#list>
    4.Map的取值：
        <#list MapInfo?keys as key>
            <li>${key!}:${(MapInfo[key])!}</li>
        </#list>
**5.if/else表达式：**
    
    <#assign x=100/>
    <li>x=${x}</li>
    <#if x == 99 >
        <li>如果x==99，则输出</li>
    </#if>

    <#if x == 99 >
        <li>如果x==99，则输出</li>
        <#else />
        <li>如果x!=99，则输出</li>
    </#if>
    <#if x == 99 >
        <li>如果x==99，则输出</li>
        <#elseif x &gt; 99 />
        <li>如果x>99，则输出</li>
        <#else />
        <li>如果x<99，则输出</li>
    </#if>

    <#if x == 100 || x == 99>
        <li>if支持 || && !</li>
    </#if>
    <#assign t='long'/>
    <#if x == 100 && t?length == 4>
        <li>if支持 || && !(非)  ' x == 100 && t?length == 4' </li>
    </#if>
    
**6.switch-case语法：**

    <#assign t=11/>
    <#switch t>
        <#case 10>10<br/><#break>
        <#case 100>100<br/><#break>
        <#default>其他
    </#switch>
    
**7.字符串操作：**    
    
    <#assign string1='hello,'/>
    <#assign string2='world'/>
    <li>连接：${string1+string2}</li>
    <li>截取：${(string1+string2)?substring(5,8)} &nbsp; --> 同java</li>
    <li>分隔：${(string1+string2)?split(',')[0]} &nbsp; --> 同java</li>
    <li>长度：${(string1+string2)?length }--> 同java</li>
    <li>大写：${(string1+string2)?upper_case}</li>
    <li>小写：${(string1+string2)?lower_case}</li>
    <!--首次出现位置-->
    <li>indexOf：${(string1+string2)?index_of('o')}</li>
    <!--最后出现位置-->
    <li>lastIndexOf：${(string1+string2)?last_index_of('o')}</li>
    <li>替换：${(string1+string2)?replace('w','xxx')}</li>

**8.自定义函数：**  
    
    <#assign strings=[2,3,4,5,6,9,8,3,2,7]/>
    <li>未排序：</li>
    <#list strings as str>
        ${str}
    </#list>
    <li>已排序：--> 自定义排序在后台写 </li>
    <!--list sort_int(strings) as str>-->
    <li>已排序：--> 调用已存在的排序方法 -->升序</li>
    <#list strings?sort as str>
        <li>${str}</li>
        <li>输出list下标：${str_index}</li>
    </#list>
    <li>已排序：--> 调用已存在的排序方法 -->降序</li>
    <#list strings?sort?reverse as str>
        <li>${str}</li>
        <li>输出list下标：${str_index}</li>
    </#list>
    <li>list长度:${strings?size}</li>
    <li>下标取值:${strings[0]}</li>

**9.自定义指令：使用@开始**
    
    1.用户123456是否拥有admin角色，并返回admin权限
        <!--入参：user='123456' role='admin'-->
        <!--出参：result1,result2-->
        <@role user='123456' role='admin';result1,result2>
            <#if result1>
                我的角色是 admin
            </#if>
            <#list result2 as item>
                我的权限是 ${item}
            </#list>
        </@role>
**10.内建函数：**

     1.处理字符串的内建函数：
        <li>substring:</li>
        <li>cap_first:首字母大写</li>
        <li>ends_with：以。。。结尾</li>
        <li>contains：包含</li>
        <#assign var1="01/03/2017"?date("MM/dd/yyyy")/>
        <#assign var2="15:05:30"?time('HH:mm:ss')/>
        <#assign var3="2015-12-31 03:05 PM"?datetime('yyyy-MM-dd hh:mm')/>
        <li>date：${var1}   </li>
        <li>datetime：${var3}</li>
        <li>time：${var2}</li>
        <li>starts_with：</li>
        <li>index_of：</li>
        <li>last_index_of：</li>
        <#list 'a|b|c'?split("|") as item>
            <li>split：${item}</li>
        </#list>
        <li>trim：</li>
    2.处理数字的内建函数：
        <li>string:x?string("0.##") --> 转换为保留两位小数点-四舍五入</li>
        <li>round:四舍五入</li>
        <li>floor：去掉小数点</li>
        <li>ceiling：进一</li>
        <#assign num=3.1415926/>
        <li>${num?string("0.##")}</li>
        <li>${num?round}</li>
        <li>${num?floor}</li>
        <li>${num?ceiling}</li>
    3.处理List的内建函数：
        <li>first：第一个值</li>
        <li>last：最后一值</li>
        <li>seq_contains：序列是否包含</li>
        <li>seq_index_of：序列位置</li>
        <li>size：大小</li>
        <li>reverse：倒转list</li>
        <li>sort：升序</li>
        <li>sort_by：根据某属性排序</li>
        <li>chunk：分块处理</li>
        <#assign nums=[1,2,3,4,7,8,9,0,11,12,13,14]/>
        <li>集合分组个数：${nums?chunk(5)?size}</li>
        <li>遍历最后一组数据：</li>
        <#list nums?chunk(5)?last as item>
            <li>${item}</li>
        </#list>
    4.其他内建函数：
        <#assign isstr='hello'/>
        <li>is_string:是否字符串 ${isstr?is_string?string('yes','no')}</li>
        <li>is_number:是否数字   ${isstr?is_number?string('yes','no')}</li>
        <li>is_method:是否方法</li>
        <li>has_content:判断是否有内容 ${isstr?has_content?string('yes','no')}</li>
        <li>eval求值-->字符串先连接再转换为整数：${'1'+'2'?eval}</li>
        <li></li>
        
**11.macro宏指令，function指令**   

    <!--定义指令-带默认值--无返回值-->
    <#macro macro_name param1 param2 paramn='默认值'>
        <li>macro指令：-->参数：${param1} | ${param2}|${paramn}</li>
    </#macro>
    <!--定义指令-带多个参数--参数类型map--无返回值-->
    <#macro macro_name_2 param1 param2 paramn...>
        <li>macro指令：-->参数：${param1} | ${param2}|${paramn['param3']}</li>
        <li>${paramn?size}</li>
        <#list paramn?keys as key>
            <li>${key!}:${(paramn[key])!}</li>
        </#list>
    </#macro>
    <!--调用指令--无返回值-->
    <@macro_name param1='参数1' param2='参数2' paramn='参数N'/>
    <@macro_name param1='参数1' param2='参数2' />
    <@macro_name_2 param1='参数1' param2='参数2' param3='多个参数' param4='n+1'/>

    <!--定义有返回值的宏指令-->
    <#macro returnVal param1 >
        <li>指令获取的参数:${param1!}</li>
        <#nested param1,"指令返回的参数！"></br>
    </#macro>
    <!--调用：return1,2分别对应nested后的参数-->
    <@returnVal param1="java";return1,return2>
        <font color="red">hello,${return1}|${return2}</font>
    </@returnVal>
    <@returnVal param1="python";return1,return2>
        <font color="green">hello,${return1}|${return2}</font>
    </@returnVal>
    <li>_______________________________________________________</li>
    <!--函数定义:加和函数-->
    <#function sum nums...>
        <#local sum = 0>
        <#list nums as num>
            <#local sum = sum + num>
        </#list>
        <#if nums?size != 0>
            <#return sum >
        </#if>
    </#function>
    <!--函数定义:均值函数-->
    <#function avg nums...>
        <#local sum = 0>
        <#list nums as num>
            <#local sum = sum + num>
        </#list>
        <#if nums?size != 0>
            <#return sum / nums?size>
        </#if>
    </#function>
    <!--调用-->
    <li>调用加和：${sum(1,2,3,4)}</li>
    <li>调用平均：${avg(1,2,3,4)}</li>