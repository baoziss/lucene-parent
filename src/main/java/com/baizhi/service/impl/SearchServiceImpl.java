package com.baizhi.service.impl;

import com.baizhi.entity.*;
import com.baizhi.entity.Searcher;
import com.baizhi.service.SearchService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
@Service("/searchService")
public class SearchServiceImpl implements SearchService {

    public List<com.baizhi.entity.Searcher> searchParse(String expression,String path) {

        File file = new File(path, "/index");
        if(!file.exists()){
            file.mkdirs();
        }


        ArrayList<Searcher> searchers = new ArrayList<Searcher>();


        IndexReader indexReader = null;
        IndexSearcher indexSearcher = null;
        //1.指定目录
        try {
            Directory directory = FSDirectory.open(file);
            //2.读取索引库
            indexReader = IndexReader.open(directory);
            //3.创建IndexSearcher
            indexSearcher = new IndexSearcher(indexReader);

            Analyzer analyzer = new IKAnalyzer();
            QueryParser queryParser = new QueryParser(Version.LUCENE_35, "default", analyzer);
            queryParser.setAllowLeadingWildcard(true);
            //MultiFieldQueryParser queryParser=new MultiFieldQueryParser(Version.LUCENE_35, new String[]{"id","name","content","price"}, analyzer);
            //String expression = "NOT id:2 book";
            Query query = queryParser.parse(expression);
            //5.查询结果TopDocs(索引区  不可能直接拿到Document)
            TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);
            int totalHits = topDocs.totalHits;//实际返回的文档数目
            System.out.println("总记录数：" + totalHits);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;//返回的scoreDoc【score、docID】
            //6.遍历结果
            for (ScoreDoc scoreDoc : scoreDocs) {

                Searcher searcher = new Searcher();
                float score = scoreDoc.score;//文档得分，相似度越高 score越大 文档排名越靠前
                int docID = scoreDoc.doc;//文档的docID唯一标示数据区的Document
                //更具docID获取数据区的Document
                Document document = indexSearcher.doc(docID);
                //获取document的Fields
                searcher.setId(document.get("id"));
                String price = document.get("price");

                System.out.println(price);

                searcher.setPrice(price);

                searcher.setName(document.get("name"));

                String descript = document.get("descript");
                QueryScorer queryScorer = new QueryScorer(query);

                //设置高亮样式
                Formatter formatter = new SimpleHTMLFormatter("<font style='color:red'>", "</font>");
                Highlighter highlighter = new Highlighter(formatter, queryScorer);

                //设置摘要长度
                Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer, 10);
                highlighter.setTextFragmenter(fragmenter);

                //获取文本 摘要
                String bestFragment = highlighter.getBestFragment(analyzer, "descript", descript);

                searcher.setDescript(bestFragment);

                searcher.setPath(document.get("path"));

                searcher.setScore(score);

                searchers.add(searcher);

            }
            return searchers;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        } finally {
            try {
                //7关闭资源
                indexSearcher.close();
                indexReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
