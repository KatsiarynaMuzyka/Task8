package food;

public enum MenuTagName {
	MENU, FOOD, PHOTO, TITLE, DESCRIPTION, PORTION, PRICE;
	
	public static MenuTagName getElementTagName(String element) {
		switch (element) {
		case "photo":
			return PHOTO;
		case "menu":
			return MENU;
		case "food":
			return FOOD;
		case "title":
			return TITLE;
		case "description":
			return DESCRIPTION;
		case "portion":
			return PORTION;
		case "price":
			return PRICE;
		default:
			throw new EnumConstantNotPresentException(MenuTagName.class, element);
		}
	}
}
