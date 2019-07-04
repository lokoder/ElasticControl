package org.hackstyle.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.hackstyle.dto.Index;

/*
http://dejt.ua01.trtsp.jus.br:9200/_cat/master?v

_cat/indices?format=json&pretty=true

http://dejt.ua01.trtsp.jus.br:9200/_cat/count?v

http://dejt.ua01.trtsp.jus.br:9200/_cat/health?v

http://dejt.ua01.trtsp.jus.br:9200/_cat/nodes?v

http://dejt.ua01.trtsp.jus.br:9200/_cat/pending_tasks?v
http://dejt.ua01.trtsp.jus.br:9200/_cat/shards?v
https://www.elastic.co/guide/en/elasticsearch/reference/current/cat-shards.html

http://dejt.ua01.trtsp.jus.br:9200/_cat/templates?v


*/
public class Main {

    public static void main(String[] args) throws IOException {

        RestClientBuilder builder = RestClient.builder(new HttpHost("dejt.ua01.trtsp.jus.br", 9200, "http"));
        Header[] defaultHeaders = new Header[]{new BasicHeader("Content-Type", "application/json")};
        builder.setDefaultHeaders(defaultHeaders);

        RestClient client = builder.build();

        Request request = new Request("GET", "/_cat/indices?v");
        Response response = client.performRequest(request);

        RequestLine requestLine = response.getRequestLine();
        HttpHost host = response.getHost();
        int statusCode = response.getStatusLine().getStatusCode();
        Header[] headers = response.getHeaders();
        String responseBody = EntityUtils.toString(response.getEntity());
        
        
        String[] lines = responseBody.split(System.getProperty("line.separator"));
        

        List<Index> list = new ArrayList<>();
        
        for (int i=1; i<lines.length; i++) {
            
            String[] line = lines[i].split("\\s+");
            
            Index index = new Index();
            
            index.setHealth(line[0]);
            index.setStatus(line[1]);
            index.setIndex(line[2]);
            index.setUuid(line[3]);
            index.setPrimary(line[4]);
            index.setReplica(line[5]);
            index.setTotalDocs(line[6]);
            index.setDeletedDocs(line[7]);
            index.setStoreSize(line[8]);
            index.setPriStoreSize(line[9]);
                
            /* index_name/_settings, index_name/_mapping, index_name/ retorna mapping + settings */
            response = client.performRequest(new Request("GET", "/" + index.getIndex() + "/_mapping?pretty=true"));
            responseBody = EntityUtils.toString(response.getEntity());
    
            index.setMapping(responseBody);
            
            response = client.performRequest(new Request("GET", "/" + index.getIndex() + "/_settings?pretty=true"));
            responseBody = EntityUtils.toString(response.getEntity());
            

            index.setSettings(responseBody);
            
            list.add(index);            
        }
        
        
        System.out.println(list);
        
        
        /*for (Index index : list) {
            
            response = client.performRequest(new Request("GET", "/" + index.getIndex() + "?pretty=true"));
            responseBody = EntityUtils.toString(response.getEntity());
            System.out.println(responseBody);
        }*/
        
        /*response = client.performRequest(new Request("GET", "/desenv_dejt_adm?pretty=true"));
        responseBody = EntityUtils.toString(response.getEntity());
        
        System.out.println(responseBody);*/

        client.close();
    }

}
