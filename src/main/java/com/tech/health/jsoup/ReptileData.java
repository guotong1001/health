package com.tech.health.jsoup;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.tech.health.entity.Food;
import com.tech.health.service.IFoodService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 饮食 数据爬虫类
 */
@Component
public class ReptileData {


    /**
     * 爬取地址
     */
    final static String[] reqUrl = {
            "http://www.jsons.cn/wugucalorie/",
            "http://www.jsons.cn/shucaicalorie/",
            "http://www.jsons.cn/shuiguocalorie/",
            "http://www.jsons.cn/roucalorie/",
            "http://www.jsons.cn/dancalorie/",
            "http://www.jsons.cn/shuichancalorie/",
            "http://www.jsons.cn/naicalorie/",
            "http://www.jsons.cn/youzhicalorie/",
            "http://www.jsons.cn/gaodiancalorie/",
            "http://www.jsons.cn/tangcalorie/",
            "http://www.jsons.cn/yinliaocalorie/",
            "http://www.jsons.cn/yinzaocalorie/",
            "http://www.jsons.cn/lajiangcalorie/"
    };


    public List<Food> findData()  {
        List<Food> arrData = new ArrayList<>();
        try {
            for (String url : reqUrl) {
                Document document = Jsoup.connect(url).get();
                Elements select = document.getElementsByClass("table table-bordered table-striped table-hover");
                for (Element next : select) {
                    Elements trs = next.select("tr");
                    for (Element tr : trs) {
                        Food food1 = new Food();
                        Food food2 = new Food();
                        Elements tds = tr.select("td");
                        if (tds.size() == 0) {
                            continue;
                        }
                        for (int j = 0; j < tds.size(); j++) {
                            String text = tds.get(j).text();
                            if(StringUtils.isBlank(text)){
                                continue;
                            }
                            if (j == 0) {
                                food1.setFoodName(text);
                            }
                            if (j == 1) {
                                String[] split = text.split("/");
                                food1.setQuantityHeat(Integer.parseInt(split[0]));
                                food1.setDosage(Integer.parseInt(split[1]));
                                arrData.add(food1);
                            }
                            if (j == 2) {
                                food2.setFoodName(text);
                            }
                            if (j == 3) {
                                String[] split = text.split("/");
                                food2.setQuantityHeat(Integer.parseInt(split[0]));
                                food2.setDosage(Integer.parseInt(split[1]));
                                arrData.add(food2);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("网页数据爬取失败");
        }
        return arrData;
    }

    @Autowired
    IFoodService iFoodService;

//    @PostConstruct
    public void synData(){
        List<Food> dataBatch = findData();
        iFoodService.saveBatch(dataBatch);

    }




}
