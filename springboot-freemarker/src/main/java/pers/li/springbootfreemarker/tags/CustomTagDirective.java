package pers.li.springbootfreemarker.tags;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Component
public class CustomTagDirective implements TemplateDirectiveModel {
    /**
     *
     * @param environment   环境变量
     * @param map   指令参数：储存所需要的值
     * @param templateModels    循环变量
     * @param templateDirectiveBody 指令内容
     * @throws TemplateException    map不能为null，其余均可为null
     * @throws IOException
     */
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        //        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_23);
        //         environment.setVariable("listParentBanks", builder.build().wrap(bankBranchService.listParents()));
        //参数读取
        TemplateScalarModel user = (TemplateScalarModel) map.get("user");
        TemplateScalarModel role = (TemplateScalarModel) map.get("role");


        //结果封装
        if("123456".equals(user.getAsString()) && "admin".equals(role.getAsString())){
            templateModels[0] = TemplateBooleanModel.TRUE;
        }
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("add");
        strings.add("update");
        strings.add("delete");
        strings.add("select");
        templateModels[1] = new SimpleSequence(strings);
        templateDirectiveBody.render(environment.getOut());
    }
}