package com.smbt.pickod.service.festival;

import com.smbt.pickod.dto.festival.Criteria;
import com.smbt.pickod.dto.festival.FestivalDTO;
import com.smbt.pickod.dto.festival.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FestivalService {
    WebClient webClient = WebClient.create();

    public Page getFestivals(Criteria criteria, String keyword) {
        String urlPath = "http://openapi.seoul.go.kr:8088/5254625053737964383558464c6d4b/xml/culturalEventInfo/1/55/";

        String response = webClient
                .get()
                .uri(urlPath)  // 요청 URL 지정
                .retrieve()
                .bodyToMono(String.class) // 응답을 Mono로 받음 (단일 값 비동기 스트림)
                .block();


        // XML 응답을 파싱하여 데이터를 리스트로 추출
        List<FestivalDTO> festivals = parseFestivalData(response);

        //검색어 필터
        List<FestivalDTO> filteredFestivals;
        if (keyword != null) {
            filteredFestivals = new ArrayList<>();
            for (FestivalDTO festival : festivals) {
                if (festival.getTitle().contains(keyword) || festival.getPlace().contains(keyword)) {
                    filteredFestivals.add(festival);
                }
            }
        } else {
            // keyword가 없으면 전체 리스트 사용
            filteredFestivals = festivals;
        }

        //페이징객체
        int total = filteredFestivals.size();
        Page page = new Page(criteria,total);

        // 페이징된 리스트 반환(그 페이지만 반환)
        int startIdx = (criteria.getPage() - 1) * criteria.getAmount();
        int endIdx = Math.min(startIdx + criteria.getAmount(), filteredFestivals.size()); //Math.min 둘 중에 더 작은거

        //subList는 List 인터페이스의 메소드.  리스트의 일부분을 추출하여 새로운 리스트를 반환
        List<FestivalDTO> pagedFestivals = filteredFestivals.subList(startIdx, endIdx);

        page.setFestivals(pagedFestivals);
        return page;
    }

    private List<FestivalDTO> parseFestivalData(String xmlResponse) {
        List<FestivalDTO> festivalList = new ArrayList<>();

        try {
            InputStream inputStream = new ByteArrayInputStream(xmlResponse.getBytes(StandardCharsets.UTF_8));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);

            // XML에서 items 태그 찾기
            NodeList itemList = doc.getElementsByTagName("row");

            // 각 item에 대해 데이터를 추출하여 FestivalDTO 객체 생성
            for (int i = 0; i < itemList.getLength(); i++) {
                Element item = (Element) itemList.item(i);

                String title = item.getElementsByTagName("TITLE").item(0).getTextContent();
                String date = item.getElementsByTagName("DATE").item(0).getTextContent();
                String place = item.getElementsByTagName("PLACE").item(0).getTextContent();
                String orgLink = item.getElementsByTagName("ORG_LINK").item(0).getTextContent();
                String mainImg = item.getElementsByTagName("MAIN_IMG").item(0).getTextContent();

                // FestivalDTO 객체 생성
                FestivalDTO festival = new FestivalDTO();
                festival.setTitle(title);
                festival.setDate(date);
                festival.setPlace(place);
                festival.setOrgLink(orgLink);
                festival.setMainImg(mainImg);

                // 리스트에 추가
                festivalList.add(festival);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return festivalList;
    }





}
