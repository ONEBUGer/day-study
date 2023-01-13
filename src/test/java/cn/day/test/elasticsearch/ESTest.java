package cn.day.test.elasticsearch;

import cn.day.test.elasticsearch.dto.Book;
import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.net.URL;

/**
 * @author ZhengChangBing
 * @Date 2022/12/19 14:11
 * @Description
 */
public class ESTest {

    public static void main(String[] args) throws IOException {

        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);
//        ElasticsearchAsyncClient asyncClient = new ElasticsearchAsyncClient(transport);


//        System.out.println(restClient.isRunning());
//        CreateIndexResponse createIndexResponse = client.indices().create(c -> c.index("javaboy_books").settings(s -> s.numberOfShards("3").numberOfReplicas("1"))
//                .mappings(m -> m.properties("name", p -> p.text(f -> f.analyzer("standard")))
//                        .properties("birthday", p -> p.date(d -> d.format("yyyy-MM-dd"))))
//                .aliases("books_alias", f -> f.isWriteIndex(true)));
//
//        System.out.println("createResponse.acknowledged()=" + createIndexResponse.acknowledged());
//        System.out.println("createResponse.index()=" + createIndexResponse.index());
//        System.out.println("createResponse.shardsAcknowledged()=" + createIndexResponse.shardsAcknowledged());
//
//
//        client.indices().delete(f -> f.index("javaboy_books"));

//        Book book = new Book();
//        book.setId(890);
//        book.setAuthor("ONEBUGer");
//        book.setName("深入学习理解ES");
//        IndexResponse response = client.index(i -> i.index("books").document(book).id("890"));
//        System.out.println(response.result());
//        System.out.println(response.id());
//        System.out.println(response.index());

        ElasticsearchAsyncClient asyncClient = new ElasticsearchAsyncClient(transport);
        asyncClient.delete(d -> d.index("books").id("891")).whenComplete((resp, e) -> {
            System.out.println("resp.result() = " + resp.result());
        });
    }
}
