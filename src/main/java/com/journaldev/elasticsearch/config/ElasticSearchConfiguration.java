package com.journaldev.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfiguration extends AbstractFactoryBean<RestHighLevelClient> {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfiguration.class);
    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void destroy() {
        try {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public Class<RestHighLevelClient> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public RestHighLevelClient createInstance() {
        return buildClient();
    }

    private RestHighLevelClient buildClient() {
        try {
        	
        	/*
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http")));
                            
                            */
                            
            //https://vpc-elasticsearchshared-vabhpx3iig2wszmmmbpb73k6xq.us-east-1.es.amazonaws.com 
        	
        	//vpc-elasticsearch3-2lks6gvfna46eek2j3gnnzmdui.us-east-1.es.amazonaws.com
        	
        	//https://vpc-elasticsearchshared3-kh7wytqbbk4uzdy73gr4depmhi.us-east-1.es.amazonaws.com
        	
        	/*
        	
        	restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("vpc-elasticsearchshared3-kh7wytqbbk4uzdy73gr4depmhi.us-east-1.es.amazonaws.com", 80, "http")));
        	
        	
               */
        	  int timeout = 60;
        	  
        	  
        	  final CredentialsProvider credentialsProvider =new BasicCredentialsProvider();
              credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("username", "Password1@"));
              RestClientBuilder builder =RestClient.builder(new HttpHost("vpc-elasticsearchcred-bvcce256iozdnytk44c56as3gu.us-east-1.es.amazonaws.com", 80, "http")).setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
              builder.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setConnectTimeout(timeout * 1000).setSocketTimeout(timeout * 1000)
                      .setConnectionRequestTimeout(0));

              RestHighLevelClient client = new RestHighLevelClient(builder);
              return client;
        	
                           
        	
        	
        	
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return restHighLevelClient;
    }


}

