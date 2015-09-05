/**
 * Copyright (c) 2015 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jmnarloch.spring.cloud.feign.app.configuration;

import com.netflix.loadbalancer.ILoadBalancer;
import feign.Client;
import feign.Feign;
import feign.httpclient.ApacheHttpClient;
import feign.ribbon.LBClientFactory;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Enables Ribbon for Feign HttpClient.
 *
 * This needed only by Spring Cloud 1.0.3/Spring Cloud Netflix 1.0.1 - newest version 1.1.x already has this.
 *
 * @author Jakub Narloch
 */
@Configuration
@ConditionalOnClass({ ILoadBalancer.class, Feign.class })
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignRibbonClientAutoConfiguration {

    @Configuration
    @ConditionalOnClass(ApacheHttpClient.class)
    public static class HttpClientConfiguration {

        @Autowired(required = false)
        private HttpClient httpClient;

        @Resource(name = "cachingLBClientFactory")
        private LBClientFactory lbClientFactory;

        @Bean
        public Client feignClient() {
            feign.ribbon.RibbonClient.Builder builder = feign.ribbon.RibbonClient.builder();

            if (httpClient != null) {
                builder.delegate(new ApacheHttpClient(httpClient));
            } else {
                builder.delegate(new ApacheHttpClient());
            }

            if (lbClientFactory != null) {
                builder.lbClientFactory(lbClientFactory);
            }

            return builder.build();
        }
    }
}
