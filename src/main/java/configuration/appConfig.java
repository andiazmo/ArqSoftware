/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 *
 * @author Anyelo
 */
@Configuration
public class appConfig {
//@Bean
//JedisConnectionFactory jedisConnectionFactory() {
//    JedisConnectionFactory jedisConFactory
//      = new JedisConnectionFactory();
//  //  jedisConFactory.setHostName("localhost");
//  //  jedisConFactory.setPort(6379);
//    return jedisConFactory;
//}

//@Bean
//public RedisTemplate<String, Object> redisTemplate() {
//    RedisTemplate<String, Object> template = new RedisTemplate<>();
//    template.setConnectionFactory(jedisConnectionFactory());
//    return template;
//}

//@Bean
//public RedisTemplate<Long, Book> redisTemplate(RedisConnectionFactory connectionFactory) {
//    RedisTemplate<Long, Book> template = new RedisTemplate<>();
//    template.setConnectionFactory(connectionFactory);
//    // Add some specific configuration here. Key serializers, etc.
//    return template;
//}

@Bean
public RedisTemplate<?, ?> redisTemplate() {

    RedisTemplate<String, Object> template = new RedisTemplate<>();
    RedisSerializer<String> stringSerializer = new StringRedisSerializer();
    JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();

    template.setConnectionFactory(redisConnectionFactory());

    template.setKeySerializer(stringSerializer);
    template.setHashKeySerializer(stringSerializer);

    template.setValueSerializer(jdkSerializationRedisSerializer);
    template.setHashValueSerializer(jdkSerializationRedisSerializer);

    template.setEnableTransactionSupport(true);
    template.afterPropertiesSet();


    return template;

}

@Bean
public LettuceConnectionFactory redisConnectionFactory() {

    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
    return new LettuceConnectionFactory(redisStandaloneConfiguration);

}

@Bean
public FilterRegistrationBean remoteAddressFilter() {

    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    RemoteAddrFilter filter = new RemoteAddrFilter();

   // filter.setAllow("0:0:0:0:0:0:0:1");
    filter.setDeny("0:0:0:0:0:0:0:1");
    filter.setDenyStatus(404);

    filterRegistrationBean.setFilter(filter);
    filterRegistrationBean.addUrlPatterns("/*");

    return filterRegistrationBean;

}
    
}
