package com.ssafy.vue.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.NewsDto;

@Service
public class NewsServiceImpl implements NewsService {

	// 네이버 부동산 뉴스
	private static String NEWS_DATAS_URL = "https://land.naver.com/news/headline.naver?bss_ymd=now()";

	@Override
	@PostConstruct // 즉시 실행
	public List<NewsDto> getNewsDatas() throws IOException {

		// 리턴해줄 list 생성
		List<NewsDto> NewsDtoList = new ArrayList<NewsDto>();
		
		// 네이버 뉴스 가져오기
		Document doc = Jsoup.connect(NEWS_DATAS_URL).get();

		// HTMl 받아오기
		Elements contents = doc.select("ul.headline_list > li > dl > dt > a");

		// 기사 제목, 링크 반환
		for (Element element : contents) {
			
			NewsDto newsDto = new NewsDto();
			

			// 링크 만들어서 저장 ( 클릭해서 링크로 이동 )
			String link = "https://land.naver.com/" + element.attr("href");
			// 기사 제목 받아오기
			String title = element.ownText();

			// 기사 제목 없으면 통과
			if(title.length() > 5) {
				newsDto.setNewsTitle(title);
				newsDto.setNewsLink(link);
				NewsDtoList.add(newsDto);			
			}

		}

		// HTMl 받아오기
		Elements contents2 = doc.select("ul.headline_list > li > dl > dd");

		// 기사 본문, 작성자, 날짜 반환
		int idx = 0;
		for (Element element : contents2) {
			
			NewsDto newsDto = NewsDtoList.get(idx);
			
			// 작성자 구문 전체 받아오기
			String str = element.getElementsByClass("writing").toString();
			// 작성자 데이터 만들기 ( 필요없는 구문 자르기 )
			String writing = str.substring(22, str.length()-7);
			
			// 날짜 구문 전체 받아오기
			String str_date = element.getElementsByClass("date").toString();
			// 날짜 데이터 만들기 
			String date = str_date.substring(19, str_date.length()-7);

			// 데이터 입력
			// 기사 본문 입력
			newsDto.setNewsContent(element.ownText());
			newsDto.setNewsWriting(writing);
			newsDto.setNewsDate(date);
			
			idx++;
		}
		
		return NewsDtoList;
		
	}

}
