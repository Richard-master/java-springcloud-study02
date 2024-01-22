package cn.itcast.order.controller;


import cn.itcast.order.command.OrderCommand;
import cn.itcast.order.entity.Product;
import cn.itcast.order.feign.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private ProductFeignClient productFeignClient;

    //注入restTemplate对象
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 注入DiscoveryClient:
     *      springcloud提供的获取原数据的工具类
     *          调用方法获取服务的原数据信息
     *
     */
//    @Autowired
//    private DiscoveryClient discoveryClient;

    /**
     * 基于ribbon的形式调用远程微服务
     * 1.使用@LoadBalanced声明RestTemplate
     * 2.使用服务名称替换ip地址
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
//        Product product = null;
//        product = restTemplate.getForObject("http://service-product/product/1", Product.class);
//        product = productFeignClient.findById(id);
//        return product;
        return new OrderCommand(restTemplate, id).execute();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOrder(@PathVariable("id") Long id) {
        return "根据id查询订单";
    }
    /**
     * 参数：商品id
     *    通过订单系统，调用商品服务，根据id查询商品
     *          1.需要配置商品对象
     *          2.需要调用商品服务
     *    使用java中的urlconnection，httpclient，okhttp
     *
     */
    /*@RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id){
        //调用discoveryclient方法
        List<ServiceInstance> instances = discoveryClient.getInstances("SERVICE-PRODUCT");

        ServiceInstance instance = instances.get(0);

        Product product = null;
        product = restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/product/1", Product.class);
        return product;
    }*/

    /**
     * 降级方法
     *  和需要保护的方法的返回值保持一直
     *  方法参数一致
     * @return
     */
//    public Product orderFallBack(){
//
//    }
}
