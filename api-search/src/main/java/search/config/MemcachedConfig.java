package search.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemcachedConfig {

    private final Logger logger = LoggerFactory.getLogger(MemcachedConfig.class);

//    @Bean
//    public net.spy.memcached.MemcachedClient memcachedClient() {
//        net.spy.memcached.MemcachedClient client = null;
//        try {
//            client = new net.spy.memcached.MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
//        } catch (IOException e) {
//            logger.error("Memcached connction could not be stablished", e);
//        } finally {
//            return client;
//        }
//    }
}
