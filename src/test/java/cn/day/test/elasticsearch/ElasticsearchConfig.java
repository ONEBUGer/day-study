package cn.day.test.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhengChangBing
 * @Date 2022/12/7 10:20
 * @Description
 */
@Configuration
public class ElasticsearchConfig {

    private String hosts = "localhost:9200";

    /**
     * 配置Es连接信息
     * @return
     */
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        String[] hostArray= new String[]{hosts};
        HttpHost[] httpHosts=new HttpHost[hostArray.length];
        for (int i=0;i<hostArray.length;i++){
            String host = hostArray[i];
            String[] split = host.split(":");
            httpHosts[i]=new HttpHost(split[0],Integer.valueOf(split[1]),"http");
        }
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        RestHighLevelClient client = restHighLevelClient();
        CreateIndexRequest createIndexRequest=new CreateIndexRequest("es_java");
        //设置参数
        createIndexRequest.settings(Settings.builder().put("number_of_shards","1").put("number_of_replicas","0"));
        XContentBuilder xContentBuilder= XContentFactory.jsonBuilder()
                .startObject()
                .field("properties")
                .startObject()
                .field("description")
                .startObject().field("type","text")
                .field("analyzer","ik_max_word")
                .endObject()
                .field("name").startObject()
                .field("type","keyword")
                .endObject()
                .field("pic").startObject()
                .field("type","text")
                .field("index",false)
                .endObject()
                .field("studymodel")
                .startObject()
                .field("type","keyword")
                .endObject()
                .endObject().endObject();
        createIndexRequest.mapping(xContentBuilder);

        //操作索引的客户端
        IndicesClient indices = client.indices();
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("创建结果："+acknowledged);

    }

    /**
     * 通过json的方式创建索引
     * @throws IOException
     */
    @Test
    public void createIndexByJson() throws IOException {
        RestHighLevelClient client = restHighLevelClient();
        CreateIndexRequest createIndexRequest=new CreateIndexRequest("es_java");
        //设置参数
        createIndexRequest.settings(Settings.builder().put("number_of_shards","1").put("number_of_replicas","0"));
        createIndexRequest.mapping("{\n" +
                "    \"properties\": {\n" +
                "      \"description\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_max_word\"\n" +
                "      },\n" +
                "      \"name\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"pic\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"index\": false\n" +
                "      },\n" +
                "      \"studymodel\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      }\n" +
                "    }\n" +
                "  }.", XContentType.JSON);
        //操作索引的客户端
        IndicesClient indices = client.indices();
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("创建结果："+acknowledged);
    }

    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void deleteIndex() throws IOException {
        RestHighLevelClient client = restHighLevelClient();
        DeleteIndexRequest deleteIndexRequest=new DeleteIndexRequest("es_java");
        IndicesClient indices = client.indices();
        AcknowledgedResponse acknowledgedResponse = indices.delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(acknowledgedResponse.isAcknowledged());

    }


    /**
     * 添加文档
     * @throws IOException
     */
    @Test
    public void testAddDoc() throws IOException {
        RestHighLevelClient client = restHighLevelClient();
        IndexRequest indexRequest=new IndexRequest("es_java","_doc");
        indexRequest.id("1");
        Map<String,Object> jsonMap=new HashMap<>();
        jsonMap.put("name","spring cloud实战");
        jsonMap.put("description","本课程主要从四个章节进行讲解： 1.微服务架构入门\n" +
                "2.spring cloud 基础入门 3.实战Spring Boot 4.注册中心eureka");
        jsonMap.put("studymodel","20001");
        jsonMap.put("name","spring cloud实战");
        jsonMap.put("pic","ttt.jpg");
        indexRequest.source(jsonMap);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse.getResult());
    }

    /**
     * 查询文档
     * @throws IOException
     */
    @Test
    public void testGetDoc() throws IOException {
        RestHighLevelClient client = restHighLevelClient();
        GetRequest getRequest=new GetRequest("es_java","1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        System.out.println(sourceAsMap);
    }

    /**
     * match搜索
     * @throws IOException
     */
    @Test
    public void testSearchAll() throws IOException {
        RestHighLevelClient client = restHighLevelClient();
        SearchRequest searchRequest=new SearchRequest("es_java");
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        seachDoc(searchRequest, searchSourceBuilder);

    }

    private void seachDoc(SearchRequest searchRequest, SearchSourceBuilder searchSourceBuilder) throws IOException {
        RestHighLevelClient client = restHighLevelClient();
        //第一个参数代表结果集包含什么字段，第二个参数代表结果集不包括哪些字段
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","description","pic"},new String[]{});
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        //搜索结果
        SearchHits hits = searchResponse.getHits();
        //匹配的记录数
        TotalHits totalHits = hits.getTotalHits();
        for (SearchHit hit:hits){
            String id = hit.getId();
            //源文档内容
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println("文档的id："+id);
            System.out.println(sourceAsMap);
        }
    }

    /**
     * term搜索
     * @throws IOException
     */
    @Test
    public void testTermQuery() throws IOException {
        RestHighLevelClient client = restHighLevelClient();
        SearchRequest searchRequest=new SearchRequest("es_java");
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("name","spring"));
        seachDoc(searchRequest,searchSourceBuilder);
    }

    /**
     * 分页搜索及排序
     * @throws IOException
     */
    @Test
    public void testSearchPage() throws IOException {
        RestHighLevelClient client = restHighLevelClient();
        SearchRequest searchRequest=new SearchRequest("es_java");
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(2);
        searchSourceBuilder.sort("name", SortOrder.DESC);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        seachDoc(searchRequest,searchSourceBuilder);
    }
}
