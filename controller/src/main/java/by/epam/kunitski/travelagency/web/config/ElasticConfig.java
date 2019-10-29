package by.epam.kunitski.travelagency.web.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfig {

    @Bean
    public RestHighLevelClient client() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("username", "password"));

        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"))
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

        RestHighLevelClient client = new RestHighLevelClient(builder);

        return client;
    }
}
