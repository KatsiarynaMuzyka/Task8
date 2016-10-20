package staxParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import food.Food;
import food.MenuTagName;

public class StAXMenuParse {
	public static void main(String[] args) throws FileNotFoundException {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream("../Task8/res/NewXMLSchema.xml");
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			List<Food> menu = process(reader);
			for (Food food : menu) {
				System.out.print(food.getTitle());
				if(food.getDescription() == null) {
					System.out.println(", " + food.getPortion());
				}
				else {
					System.out.println(", " + food.getDescription() + ", " + food.getPortion());
				}
			
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private static List<Food> process(XMLStreamReader reader) throws XMLStreamException {
		List<Food> menu = new ArrayList<Food>();
		Food food = null;
		MenuTagName elementName = null;

		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = MenuTagName.getElementTagName(reader.getLocalName());
				switch (elementName) {
				case FOOD:
					food = new Food();
					String sectionName = (reader.getAttributeValue(null, "sectionName"));
					food.setSectionName(sectionName);
					break;
				}
				break;
			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();
				if (text.isEmpty()) {
					break;
				}
				switch (elementName) {
				case TITLE:
					food.setTitle(text);
					break;
				case DESCRIPTION:	
					food.setDescription(text);
					break;
				case PORTION:
					food.setPortion(text);
					break;
				case PRICE:
					Integer price = Integer.parseInt(text);
					food.setPrice(price);
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				elementName = MenuTagName.getElementTagName(reader.getLocalName());
				switch (elementName) {
				case FOOD:
					menu.add(food);
				}
			}
		}
		return menu;
	}
}
