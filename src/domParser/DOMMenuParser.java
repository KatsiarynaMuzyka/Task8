package domParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import food.Food;

public class DOMMenuParser {
	public static void main(String[] args) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.parse("../Task8/res/NewXMLSchema.xml");
		Document document = parser.getDocument();

		Element root = document.getDocumentElement();

		List<Food> menu = new ArrayList<Food>();

		NodeList foodNodes = root.getElementsByTagName("food");
		Food food = null;
		for (int i = 0; i < foodNodes.getLength(); i++) {
			food = new Food();
			Element foodElement = (Element) foodNodes.item(i);
			food.setSectionName((foodElement.getAttribute("sectionName")));
			food.setTitle(getSingleChild(foodElement, "title").getTextContent().trim());
			food.setDescription(getSingleChild(foodElement, "description").getTextContent().trim());
			food.setPortion(getSingleChild(foodElement, "portion").getTextContent().trim());
			food.setPrice(Integer.parseInt((getSingleChild(foodElement, "price").getTextContent().trim())));
			menu.add(food);
		}
		for (Food f : menu) {
			if(f.getDescription().equals("")) {
				f.setDescription(f.getTitle());
			}
			System.out.println(f.getTitle() + ", " + f.getDescription() + ", " 
							+ f.getPortion() + ", " + f.getPrice());
		}
	}

	private static Element getSingleChild(Element element, String childName) {
		NodeList nlist = element.getElementsByTagName(childName);
		Element child = (Element) nlist.item(0);
		return child;
	}

}
