package com.smbt.pickod.service.festival;

import com.smbt.pickod.dto.festival.FestivalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FestivalService {
    WebClient webClient = WebClient.create();

    public List<FestivalDTO> getFestivals(String keyword) {
        String urlPath = "http://openapi.seoul.go.kr:8088/5254625053737964383558464c6d4b/xml/culturalEventInfo/1/30/";

        String response = webClient
                .get()
                .uri(urlPath)  // 요청 URL 지정
                .retrieve()
                .bodyToMono(String.class) // 응답을 Mono로 받음 (단일 값 비동기 스트림)
                .block();


        // XML 응답을 파싱하여 데이터를 리스트로 추출
        List<FestivalDTO> festivals = parseFestivalData(response);



        if (keyword != null) {
            List<FestivalDTO> getFestivalsList = new ArrayList<>();
            for (FestivalDTO festival : festivals) {
                if (festival.getTitle().contains(keyword) || festival.getPlace().contains(keyword)) {
                    getFestivalsList.add(festival);
                }
                System.out.println("Festival Title: " + festival.getTitle());
                System.out.println("Festival Place: " + festival.getPlace());
                System.out.println(getFestivalsList);
            }
            return getFestivalsList;
        }else {
            return festivals;
        }

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
