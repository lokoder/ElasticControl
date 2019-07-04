package org.hackstyle.Main;

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

public class Main {

    public static void main(String[] args) throws IOException {

        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
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
                
            list.add(index);            
        }
        
        
        System.out.println(list);
        
        
        /*response = client.performRequest(new Request("GET", "/desenv_dejt_adm?pretty=true"));
        responseBody = EntityUtils.toString(response.getEntity());
        
        System.out.println(responseBody);*/

        client.close();
    }

}
