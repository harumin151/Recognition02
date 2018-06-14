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

	public static void main(String[] args){

		VisualRecognition service = new VisualRecognition("2018-03-19");
		service.setApiKey("");

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
		String class1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("class").toString();
		System.out.println("name:" + class1);

		// scoreを取得する
		double score1 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("score")
				.asDouble();
		System.out.println("score:" + score1);



		// colorを取得する
		String class2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class")
				.toString();
		System.out.println("color:" + class2);

		// scoreを取得する
		double score2 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class")
				.asDouble();
		System.out.println("score:" + score2);



		// colorを取得する
		String class3 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("class")
				.toString();
		System.out.println("color:" + class3);

		// scoreを取得する
		double score3 = node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("score")
				.asDouble();
		System.out.println("score:" + score3);


		MySQL mysql = new MySQL();

		mysql.updateImage(class1,score1,class2,score2,class3,score3);

	}

}
