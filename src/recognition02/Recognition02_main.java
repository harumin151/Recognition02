package recognition02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class Recognition02_main {

	public static void main(String[] args) {

		VisualRecognition service = new VisualRecognition("2018-03-19");
		service.setApiKey("j16015");

		InputStream imagesStream = null;
		try {
			imagesStream = new FileInputStream("./img/fruitbowl.jpg");
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder().imagesFile(imagesStream)
				.imagesFilename("fruitbowl.jpg").threshold((float) 0.6).owners(Arrays.asList("IBM")).build();
		ClassifiedImages result = service.classify(classifyOptions).execute();
		System.out.println(result);

		// ココから第２回授業だお↓
		String s = String.valueOf(result);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(s);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// nameを取得する
		String name = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("class").toString();
		System.out.println("name:" + name);

		// scoreを取得する
		double score = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("score")
				.asDouble();
		System.out.println("score:" + score);



		// colorを取得する
		String color = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class")
				.toString();
		System.out.println("color:" + color);

		// scoreを取得する
		double score2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class")
				.asDouble();
		System.out.println("score:" + score2);



		// colorを取得する
		String color2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("class")
				.toString();
		System.out.println("color:" + color2);

		// scoreを取得する
		double score3 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("score")
				.asDouble();
		System.out.println("score:" + score3);

	}

}
