package com.zuul.gateway.controller;

import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import redis.clients.jedis.Jedis;
@Api(value = "ProductInterface", description = "product相关api")
@RestController
public class TestQueueController {
	private final static Logger logger = LoggerFactory.getLogger(TestQueueController.class);

	private LinkedBlockingQueue<String> buyqueue;

	@ApiOperation(value = "商品详情", notes = "URL：http://localhost:8080/buy",httpMethod="POST")
	@PostMapping(value = "buy")
	public String testController(@RequestParam String goodId) throws InterruptedException {
		
		Jedis jedis = JedisUtil.getInstance().getJedis("localhost",6379);
		String residue = jedis.get("good_number");
		if (buyqueue == null) {// 第一次初始化请求队列,队列的容量为当前的商品剩余数量
			buyqueue = new LinkedBlockingQueue<String>(Integer.valueOf(residue));
		} 
		if(buyqueue.remainingCapacity()>0){//当队列的可用容量大于0时,将请求放到请求队列中
            buyqueue.put(goodId);
            logger.info("success");
            return "success";
        }else{//当请求队列已满,本次请求不能处理,直接响应客户端提示请求队列已满
        	logger.info("抢购队列已满，请稍候重试！");
            return  "抢购队列已满，请稍候重试！";
        }
	}

}
