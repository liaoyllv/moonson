package com.jf.moonson.business.storage.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("moonson-business-points")
public interface PointsFeignClient {

    // @GetMapping("/findByArticleId")
    // public Video getVideo(@RequestParam("articleId") Long articleId);


}
