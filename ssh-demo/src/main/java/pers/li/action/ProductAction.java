package pers.li.action;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import pers.li.entity.Product;
import pers.li.service.ProductService;

@Action(value = "productAction")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
        //product对象的属性通过ModelDriven从http请求中获取到
        private Product product = new Product();
        ProductService productService;

        //此需要导入struts2-spring-plugin-2.3.20.jar包
        //则struts和spring整合实现了按名称自动注入service。
        public void setProductService(ProductService productService) {
            this.productService = productService;
        }
        @Override
        public Product getModel() {
            // TODO Auto-generated method stub
            return product;
        }

        //执行保存商品的方法
        public String save() {
            System.out.println(product.getPname());
            productService.save(product);
            return null;
        }
    }
